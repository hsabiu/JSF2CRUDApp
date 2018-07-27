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

	public String rankApplicants(JobDescription job) {

		applicantList = new ArrayList<Applicant>();

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

			applicantList.add(applicant);
		}

		return "applicantsrank.xhtml?faces-redirect=true";

	}

	/*
	 * Getters
	 */

	public List<Applicant> getapplicantList() {
		return applicantList;
	}
}
