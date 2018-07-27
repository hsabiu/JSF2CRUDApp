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


/**
 * A class implementing the logic for getting all job postings from the 
 * database and formatting the results for displaying on the index.xhtml page 
 * 
 * @author habib
 *
 */
@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean(name = "joboffers", eager = true)
public class JobOffers implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<JobDescription> jobsList = new ArrayList<JobDescription>();

	/*
	 * Constructor
	 */
	public JobOffers() {
	
		initializeAllJobOffers();
		
	}

	public void initializeAllJobOffers() {

		try {

			// Create a database manager and get the connection
			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			// Selection SQL query to retrieve the data from the database
			String query = "SELECT * FROM jobposts ORDER BY id";

			// Prepare the statement and execute the query
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			// Process each of the rows returned by the SQL query
			while (rs.next()) {

				// For each row returned, get the columns and store the data in a Job object
				JobDescription job = new JobDescription();

				job.setEmployer(rs.getString("employer_name"));
				job.setContactNo(rs.getString("contact_number"));
				job.setJobTitle(rs.getString("job_title"));
				job.setSalaryRange(rs.getString("salary_range"));
				job.setWorkingTime(rs.getString("working_time"));

				String jobDescription = rs.getString("job_description");
				String jobDegreeRequirements = rs.getString("degree");

				// Split (based on ',') the string returned for the degree column in case their are more
				// than one academic degree specified for this posting
				String[] jobDegreeRequirementArray = jobDegreeRequirements.split(",");

				String jobDegreeRequirement = "";
				
				// Merge all the academic degree requirements into a string representing all 
				// degree requirements for this job posting 
				for (String s : jobDegreeRequirementArray) {
					jobDegreeRequirement += s + ", ";
				}

				String jobSkillsRequirement = rs.getString("skills");

				// Split the skills requirements string and get each required skill into a hashmap
				Map<String, String> skillsMap = new HashMap<String, String>();
				
				// Split (based on ',') the string returned for the skills column in case their are more
				// than one skill requirement specified for this posting
				String[] jobSkillsRequirementArray = jobSkillsRequirement.split(",");

				// Process each skill to get the skill name and the required level (from 1 (low) to 5 (high))
				for (String s : jobSkillsRequirementArray) {
					String[] splitted = s.split(":");
					skillsMap.put(splitted[0].replace("\"", ""), splitted[1]);
				}

				jobSkillsRequirement = "";

				// Merge all the skills requirements into a string representing all skill requirements 
				// for this job posting 
				for (String s : skillsMap.keySet()) {
					jobSkillsRequirement += s + " skill level " + skillsMap.get(s) + ", ";
				}

				// Merge academic degree and skills requirements into a single string for displaying
				String jobRequirement = jobDegreeRequirement + jobSkillsRequirement;

				// Remove the comma sign at the end to the requirement string
				jobRequirement = jobRequirement.substring(0, jobRequirement.lastIndexOf(","));

				job.setRequirements(jobRequirement);
				job.setJobDescription(jobDescription);

				// Format the job post date for nice display on the index.xhtml page
				String formattedDate = new SimpleDateFormat("MMM dd, YYYY").format(rs.getTimestamp("timestamp"));

				job.setTimestamp(formattedDate);

				// Add a new job posting to jobsList
				jobsList.add(job);

			}

			// Close the connection after successful data insertion
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Getters
	 */

	public List<JobDescription> getJobsList() {
		return jobsList;
	}
}
