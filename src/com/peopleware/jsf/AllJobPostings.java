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
@ManagedBean(name="jobpostings", eager=true)
public class AllJobPostings implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<JobDescription> jobsList = new ArrayList<JobDescription>();

	public AllJobPostings() {
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
				
				// Split the degree requirements string and get each required degree into array of academic degree
				String[] jobDegreeRequirementArray = jobDegreeRequirements.split(",");
				
				String jobDegreeRequirement = "";
				
				for (String s: jobDegreeRequirementArray) {           
					jobDegreeRequirement += s + ", "; 
			    }

				String jobSkillsRequirement = rs.getString("skills");

				// Split the skills requirements string and get each required skill into a hashmap of key=skill, value=experience level
				Map<String, String> map = new HashMap<String, String>();
				String[] jobSkillsRequirementArray = jobSkillsRequirement.split(",");
				
				for (String s : jobSkillsRequirementArray) {
				    String[] t = s.split(":");
				    map.put(t[0].replace("\"", ""), t[1]);
				}

				jobSkillsRequirement = "";

				for (String s : map.keySet()) {
					jobSkillsRequirement += s + ", ";
				    //System.out.println(s + " is " + map.get(s));
				}				
				
				String jobRequirement = jobDegreeRequirement + jobSkillsRequirement;
				
				// Remove the comma sign at the end to the requirement string
				jobRequirement = jobRequirement.substring(0, jobRequirement.lastIndexOf(",")) ;
				
				
				job.setRequirements(jobRequirement);
				job.setJobDescription(jobDescription);

				
				String formattedDate = new SimpleDateFormat("MMM dd, YYYY").format(rs.getTimestamp("timestamp"));
			
				job.setTimestamp(formattedDate);

			    //Add new job posting to jobsList
				jobsList.add(job);
				
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String rankApplicants(JobDescription job) {
		System.out.println("===>>> " + job.getJobTitle());
		return "rank.xhtml?faces-redirect=true";
	}
	
	
	/*
	 * Setters and Getters
	 */

	public List<JobDescription> getJobsList() {
		return jobsList;
	}
}
