package com.peopleware.jsf;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		setUpJobsList();
	}

	public void setUpJobsList() {

		try {

			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			String query = "SELECT * FROM jobposts ORDER BY id";

			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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

				String[] splitted;

				// Split the degree requirements string and get each required degree into a hashmap 
				Map<String, String> degreeMap = new HashMap<String, String>();
				String[] jobDegreeRequirementArray = jobDegreeRequirements.split(",");

				for (String s : jobDegreeRequirementArray) {

					splitted = s.split(":");
					degreeMap.put(splitted[0], splitted[1]);
				}

				String jobDegreeRequirement = "";

				for (String s : degreeMap.keySet()) {
					// System.out.println(s + " is " + map.get(s));
					jobDegreeRequirement += s + " in " + degreeMap.get(s) + ", ";
				}

				String jobSkillsRequirement = rs.getString("skills");

				// Split the skills requirements string and get each required skill into a hashmap
				Map<String, String> skillsMap = new HashMap<String, String>();
				String[] jobSkillsRequirementArray = jobSkillsRequirement.split(",");

				for (String s : jobSkillsRequirementArray) {
					splitted = s.split(":");
					skillsMap.put(splitted[0].replace("\"", ""), splitted[1]);
				}

				jobSkillsRequirement = "";

				for (String s : skillsMap.keySet()) {
					jobSkillsRequirement += s + ", ";
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
				
		String[] requirements = job.getRequirements().split(",");

		Map<String, String> degrees = new HashMap<String, String>();
		List<String> skills = new ArrayList<String>();

		for (String s : requirements) {
			
			if (s.contains(" in ")) {
				String[] t = s.split(" in ");
				degrees.put(t[0], t[1]);
			}else {
				skills.add(s);
			}
		}
		
		String[] salaryRange = job.getSalaryRange().split(" - ");
		String startValue = salaryRange[0];
		String endValue = salaryRange[1];
						
		try {

			DBManager db = new DBManager();
			Connection conn = db.getConnection();			
			
			String query = "SELECT * FROM applicants WHERE min_salary BETWEEN " + Integer.parseInt(startValue) + " AND " + Integer.parseInt(endValue) + " ORDER BY id";
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Applicant applicant = new Applicant();
				
				applicant.setFirstname(rs.getString("firstname"));
				applicant.setLastname(rs.getString("lastname"));
				applicant.setEmail(rs.getString("email"));
				applicant.setPhone_number(rs.getString("phone_number"));
				applicant.setMin_salary(rs.getString("min_salary"));
				applicant.setWorking_time(rs.getString("working_time"));
				applicant.setDegree_level(rs.getString("degree_level"));
				applicant.setSpecialization(rs.getString("specialization"));
				applicant.setSkill(rs.getString("skills"));
				
				rankedApplicantsList.add(applicant);
								
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return "applicantsrank.xhtml?faces-redirect=true";
	
	}

	public List<JobDescription> getJobsList() {
		return jobsList;
	}
	
	public List<Applicant> getRankedApplicantsList() {
		return rankedApplicantsList;
	}
}
