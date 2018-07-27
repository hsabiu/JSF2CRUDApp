package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * A class implementing the logic for ranking all applicants based on the 
 * score from their academic degree and skills list for displaying on the 
 * applicantsrank.xhtml page 
 * 
 * @author habib
 *
 */
@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean(name = "rankapplicants", eager = true)
public class RankApplicants {

	private List<Applicant> applicantList = new ArrayList<Applicant>();

	/*
	 * Constructor
	 */
	public RankApplicants() {

	}

	/**
	 * Method implementing the logic for ranking applicants based on their academic degree 
	 * and technical skills as specified by the job offer passed through the 'job' parameter
	 * 
	 * After successful execution, the method redirect back to the applicantsrank.xhtml page for displaying data
	 * 
	 * @param job An object of JobDescription class containing the description and requirements of a job offer
	 * 
	 * @return After successfully ranking applicants, this method redirect back to the applicantsrank.xhtml 
	 *         page for displaying the data 
	 */
	public String rankApplicants(JobDescription job) {

		applicantList = new ArrayList<Applicant>();

		// Get all the requirements separately into an array
		String[] requirements = job.getRequirements().split(",");

		List<String> jobDegreeRequirements = new ArrayList<String>();
		Map<String, Integer> jobSkillsMap = new HashMap<String, Integer>();
		Map<String, Applicant> applicantsAndScoreMap = new HashMap<String, Applicant>();

		// Separate the requirement into academic degree or skills requirement
		for (String s : requirements) {
			if (s.contains(" in ")) {jobDegreeRequirements.add(s.replaceAll("\\s+", ""));
			} else {
				String[] t = s.split(" skill level ");
				jobSkillsMap.put((t[0].replace("\"", "")).replaceAll("\\s+", ""),Integer.parseInt(t[1].replaceAll("\\s+", "")));
			}
		}

		// Split the salary range and retrieve the minimum and maximum salary for this job offer
		String[] salaryRange = job.getSalaryRange().split(" - ");
		String startValue = salaryRange[0];
		String endValue = salaryRange[1];

		try {

			// Create a database manager and get the connection
			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			// Selection SQL query to retrieve the data from the database (this only select the applicants whose
			// minimum salary requirement fall between the salary range of the job offer)
			String query = "SELECT * FROM applicants WHERE min_salary BETWEEN " + Integer.parseInt(startValue) + " AND "
					+ Integer.parseInt(endValue) + " ORDER BY id";
			
			// Prepare the statement and execute the query
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			// Process each of the rows returned by the SQL query
			while (rs.next()) {

				// For each row returned, get the columns and store the data in an Application object
				Applicant applicant = new Applicant();

				applicant.setFirstName(rs.getString("firstname"));
				applicant.setLastName(rs.getString("lastname"));
				applicant.setEmail(rs.getString("email"));
				applicant.setPhoneNumber(rs.getString("phone_number"));
				applicant.setMinSalary(rs.getString("min_salary"));
				applicant.setWorkingTime(rs.getString("working_time"));
				applicant.setDegreeLevel(rs.getString("degree_level"));
				applicant.setSpecialization(rs.getString("specialization"));
				applicant.setSkill(rs.getString("skills"));
				applicant.setScore(0);

				// Format the applicant academic degree by merging the degree level and specialization,
				// and removing any empty space in the string
				String applicantDegree = (applicant.getDegreeLevel() + " in " + applicant.getSpecialization()).replaceAll("\\s+", "");

				// Check if the applicant has the mandatory degree requirement of the job offer
				if (jobDegreeRequirements.contains(applicantDegree)) {

					// If the applicant meet the degree requirements, check if they have the skills 
					// requirements of the job and add the level value for all the skill they have
					for (String skill : jobSkillsMap.keySet()) {

						Map<String, Integer> applicantSkillsMap = new HashMap<String, Integer>();
						String[] applicantSkills = (applicant.getSkill()).split(",");

						for (String s : applicantSkills) {
							String[] t = s.split(":");
							applicantSkillsMap.put((t[0].replace("\"", "")).replaceAll("\\s+", ""), Integer.parseInt(t[1].replaceAll("\\s+", "")));
						}

						// Check if the applicant has the required skill and make sure their skill level is 
						// greater than or equal to the minimum level specified by this job offer
						if (applicantSkillsMap.containsKey(skill) && applicantSkillsMap.get(skill) >= jobSkillsMap.get(skill)) {

							// Calculate the total score of the applicant by adding together the level of kills they have for this job offer
							if (applicantsAndScoreMap.containsKey(applicant.getFirstName())) {
								Applicant temp = applicantsAndScoreMap.get(applicant.getFirstName());
								int score = temp.getScore() + applicantSkillsMap.get(skill);
								temp.setScore(score);
								applicantsAndScoreMap.put(applicant.getFirstName(), temp);
							} else {
								applicant.setScore(applicantSkillsMap.get(skill));
								applicantsAndScoreMap.put(applicant.getFirstName(), applicant);
							}
						}
					}
				}
			}

			// Close the connection after successful data insertion
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Create a new HashMap with key=applicant total score and value=Applicant 
		// object containing all the information regarding this applicant
		Map<Integer, Applicant> scoreMap = new HashMap<Integer, Applicant>();

		for (Applicant applicant : applicantsAndScoreMap.values()) {
			scoreMap.put(applicant.getScore(), applicant);
		}

		// Converting HashMap to TreeMap in order to sort the elements
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<Integer, Applicant> sortedScoreTreeMap = new TreeMap(Collections.reverseOrder());
		sortedScoreTreeMap.putAll(scoreMap);

		// Counter used to rank applicants
		int rankCounter = 0;

		// For each applicant, add their rank value to the applicant object
		for (Map.Entry<Integer, Applicant> entry : sortedScoreTreeMap.entrySet()) {
			@SuppressWarnings("unused")
			Integer key = entry.getKey();
			Applicant applicant = entry.getValue();
			applicant.setRank(++rankCounter);

			applicantList.add(applicant);
		}

		// Redirect back to the applicantsrank.xhtml page for displaying the data 
		return "applicantsrank.xhtml?faces-redirect=true";

	}

	/*
	 * Getters
	 */

	public List<Applicant> getapplicantList() {
		return applicantList;
	}
}
