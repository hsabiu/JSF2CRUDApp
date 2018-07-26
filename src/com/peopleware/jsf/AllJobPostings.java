package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean(name="jobpostings", eager=true)
public class AllJobPostings {

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
				job.setJobDescription(rs.getString("job_description"));
				job.setSalaryRange(rs.getString("salary_range"));
				job.setWorkingTime(rs.getString("working_time"));
				job.setRequirements(rs.getString("requirements"));
				
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
	
	/*
	 * Setters and Getters
	 */

	public List<JobDescription> getJobsList() {
		return jobsList;
	}
}
