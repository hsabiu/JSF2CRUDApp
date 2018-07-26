package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="jobprofile", eager=true)
@SuppressWarnings("deprecation")
public class NewJob {
	
	private String employer;
	private String contactNo;
	private String jobTitle;
	private String jobDescription;
	private String salaryRange;
	private String workingTime;
	private String requirements;
	

	/*
	 * Constructor
	 */
	public NewJob() {
		
	}

	public String addNewJobPosting() {
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try {
			String query = "INSERT INTO jobposts " 
			             + "(employer_name, contact_number, job_title, job_description, salary_range, working_time, skills) "
					     + "values (?, ?, ?, ?, ?, ?, ?)";
			
		    PreparedStatement stm = conn.prepareStatement(query);
		    stm.setString(1, employer);
		    stm.setString(2, contactNo);
		    stm.setString(3, jobTitle);
		    stm.setString(4, jobDescription);
		    stm.setString(5, salaryRange);
		    stm.setString(6, workingTime);
		    stm.setString(7, requirements);
		    
		    stm.executeUpdate();
		    stm.close();
		    conn.close();
		    
			System.out.println("Update successful");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		employer = null;
		contactNo = null;
		jobTitle = null;
		jobDescription = null;
		salaryRange = null;
		workingTime = null;
		requirements = null;

		return "employers.xhtml?faces-redirect=true";
	}
	
	/*
	 * Setters and Getters
	 */
	public String getEmployer() {
		return employer;
	}


	public void setEmployer(String employer) {
		this.employer = employer;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getJobDescription() {
		return jobDescription;
	}


	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}


	public String getSalaryRange() {
		return salaryRange;
	}


	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}


	public String getWorkingTime() {
		return workingTime;
	}


	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}


	public String getRequirements() {
		return requirements;
	}


	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

}
