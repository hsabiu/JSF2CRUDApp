package com.peopleware.jsf;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean(name = "joboffers", eager = true)
public class JobOffers implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<JobDescription> jobsList = new ArrayList<JobDescription>();
	private List<Applicant> rankedApplicantsList = new ArrayList<Applicant>();

	public JobOffers() {
		initializeAllJobOffers();
	}

	public void initializeAllJobOffers() {

		try {

			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			String query = "SELECT * FROM jobposts ORDER BY id";

			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				JobDescription job = new JobDescription();

				job.setEmployer(rs.getString("employer_name"));
				job.setContactNo(rs.getString("contact_number"));
				job.setJobTitle(rs.getString("job_title"));
				job.setSalaryRange(rs.getString("salary_range"));
				job.setWorkingTime(rs.getString("working_time"));

				String jobDescription = rs.getString("job_description");
				String jobDegreeRequirements = rs.getString("degree");

				String[] jobDegreeRequirementArray = jobDegreeRequirements.split(",");

				String jobDegreeRequirement = "";

				for (String s : jobDegreeRequirementArray) {
					jobDegreeRequirement += s + ", ";
				}

				String jobSkillsRequirement = rs.getString("skills");

				// Split the skills requirements string and get each required skill into a
				// hashmap
				Map<String, String> skillsMap = new HashMap<String, String>();
				String[] jobSkillsRequirementArray = jobSkillsRequirement.split(",");

				for (String s : jobSkillsRequirementArray) {
					String[] splitted = s.split(":");
					skillsMap.put(splitted[0].replace("\"", ""), splitted[1]);
				}

				jobSkillsRequirement = "";

				for (String s : skillsMap.keySet()) {
					jobSkillsRequirement += s + " skill level " + skillsMap.get(s) + ", ";
				}

				String jobRequirement = jobDegreeRequirement + jobSkillsRequirement;

				// Remove the comma sign at the end to the requirement string
				jobRequirement = jobRequirement.substring(0, jobRequirement.lastIndexOf(","));

				job.setRequirements(jobRequirement);
				job.setJobDescription(jobDescription);

				String formattedDate = new SimpleDateFormat("MMM dd, YYYY").format(rs.getTimestamp("timestamp"));

				job.setTimestamp(formattedDate);

				// Add a new job posting to jobsList
				jobsList.add(job);

			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String rankApplicants(JobDescription job) {

		rankedApplicantsList = new ArrayList<Applicant>();

		String[] requirements = job.getRequirements().split(",");

		List<String> jobDegreeRequirements = new ArrayList<String>();
		Map<String, Integer> jobSkillsMap = new HashMap<String, Integer>();
		Map<String, Applicant> applicantsAndScoreMap = new HashMap<String, Applicant>();

		for (String s : requirements) {
			if (s.contains(" in ")) {
				jobDegreeRequirements.add(s.replaceAll("\\s+", ""));
			} else {
				String[] t = s.split(" skill level ");
				jobSkillsMap.put((t[0].replace("\"", "")).replaceAll("\\s+", ""),
						Integer.parseInt(t[1].replaceAll("\\s+", "")));
			}
		}

		String[] salaryRange = job.getSalaryRange().split(" - ");
		String startValue = salaryRange[0];
		String endValue = salaryRange[1];

		try {

			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			String query = "SELECT * FROM applicants WHERE min_salary BETWEEN " + Integer.parseInt(startValue) + " AND "
					+ Integer.parseInt(endValue) + " ORDER BY id";
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Applicant applicant = new Applicant();

				applicant.setFirstname(rs.getString("firstname"));
				applicant.setLastname(rs.getString("lastname"));
				applicant.setEmail(rs.getString("email"));
				applicant.setPhone_number(rs.getString("phone_number"));
				applicant.setMinSalary(rs.getString("min_salary"));
				applicant.setWorking_time(rs.getString("working_time"));
				applicant.setDegreeLevel(rs.getString("degree_level"));
				applicant.setSpecialization(rs.getString("specialization"));
				applicant.setSkill(rs.getString("skills"));
				applicant.setScore(0);

				String applicantDegree = (applicant.getDegreeLevel() + " in " + applicant.getSpecialization())
						.replaceAll("\\s+", "");

				if (jobDegreeRequirements.contains(applicantDegree)) {

					for (String skill : jobSkillsMap.keySet()) {

						Map<String, Integer> applicantSkillsMap = new HashMap<String, Integer>();
						String[] applicantSkills = (applicant.getSkill()).split(",");

						for (String s : applicantSkills) {
							String[] t = s.split(":");
							applicantSkillsMap.put((t[0].replace("\"", "")).replaceAll("\\s+", ""),
									Integer.parseInt(t[1].replaceAll("\\s+", "")));
						}

						if (applicantSkillsMap.containsKey(skill)
								&& applicantSkillsMap.get(skill) >= jobSkillsMap.get(skill)) {

							if (applicantsAndScoreMap.containsKey(applicant.getFirstname())) {
								Applicant temp = applicantsAndScoreMap.get(applicant.getFirstname());
								int score = temp.getScore() + applicantSkillsMap.get(skill);
								temp.setScore(score);
								applicantsAndScoreMap.put(applicant.getFirstname(), temp);
							} else {
								applicant.setScore(applicantSkillsMap.get(skill));
								applicantsAndScoreMap.put(applicant.getFirstname(), applicant);
							}
						}
					}
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Map<Integer, Applicant> scoreMap = new HashMap<Integer, Applicant>();

		for (Applicant applicant : applicantsAndScoreMap.values()) {
			scoreMap.put(applicant.getScore(), applicant);
		}

		// Converting HashMap to TreeMap in order to sort the elements
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<Integer, Applicant> sortedScoreTreeMap = new TreeMap(Collections.reverseOrder());
		sortedScoreTreeMap.putAll(scoreMap);

		int rankCounter = 0;
		
		for (Map.Entry<Integer, Applicant> entry : sortedScoreTreeMap.entrySet()) {
			@SuppressWarnings("unused")
			Integer key = entry.getKey();
			Applicant applicant = entry.getValue();
			applicant.setRank(++rankCounter);

			rankedApplicantsList.add(applicant);
		}

		return "applicantsrank.xhtml?faces-redirect=true";

	}

	/*
	 * Getters
	 */

	public List<JobDescription> getJobsList() {
		return jobsList;
	}

	public List<Applicant> getRankedApplicantsList() {
		return rankedApplicantsList;
	}
}
