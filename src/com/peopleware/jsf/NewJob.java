package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * A class representing job post information. This class is also responsible for
 * inserting a new job post into the jobposts table of the database when the
 * newJobProfile form is submitted within employers.xhtml page
 * 
 * @author habib
 *
 */

@SessionScoped
@ManagedBean(name = "jobprofile", eager = true)
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

	/**
	 * Add a new job posting to the database table of jobposts. This function does
	 * not take any argument. It is called on submitting the newJobProfile form
	 * inside employers.xhtml
	 * 
	 * This method redirect back to the employers.xhtml page after it has inserted
	 * the new job profile data into the database
	 */

	public String addNewJobPosting() {

		// Create a database manager and get the connection
		DBManager db = new DBManager();
		Connection conn = db.getConnection();

		try {
			// Insertion SQL query
			String query = "INSERT INTO jobposts "
					+ "(employer_name, contact_number, job_title, job_description, salary_range, working_time, skills) "
					+ "values (?, ?, ?, ?, ?, ?, ?)";

			// Preparing the statement and executing the query
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, employer);
			stm.setString(2, contactNo);
			stm.setString(3, jobTitle);
			stm.setString(4, jobDescription);
			stm.setString(5, salaryRange);
			stm.setString(6, workingTime);
			stm.setString(7, requirements);

			stm.executeUpdate();

			// Close the connection after successful data insertion
			stm.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Set variables to null before redirecting back to the employers.xhtml
		// page to clear the data entered in the form fields
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
