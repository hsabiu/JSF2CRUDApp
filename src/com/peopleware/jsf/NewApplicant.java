package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * A class representing applicant information. This class is also responsible 
 * for inserting a new applicant record into the applicants table of the database 
 * when the newApplicant form is submitted within subscribeapplicants.xhtml page
 * 
 * @author habib
 *
 */
@SessionScoped
@ManagedBean(name="applicantinfo", eager=true)
@SuppressWarnings("deprecation")
public class NewApplicant {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contactNumber;
	private String minimumSalary;
	private String workingTime;
	private String degreeLevel;
	private String specialization;
	private String technicalSkills;
	
	/**
	 * Add a new applicant to the database table of applicants. This function does
	 * not take any argument. It is called on submitting the newApplicant form
	 * inside subscribeapplicants.xhtml
	 * 
	 * @return After successfully inserting the new applicant information into the database, 
	 * this method redirect back to the subscribeapplicants.xhtml page
	 */
	public String addNewApplicant() {
		
		// Create a database manager and get the connection
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try {
			// Insertion SQL query
			String query = "INSERT INTO applicants " 
			             + "(firstname, lastname, email, phone_number, min_salary, working_time, degree_level, specialization, skills) "
					     + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			// Prepare the statement and execute the query
		    PreparedStatement stm = conn.prepareStatement(query);
		    stm.setString(1, firstName);
		    stm.setString(2, lastName);
		    stm.setString(3, emailAddress);
		    stm.setString(4, contactNumber);
		    stm.setString(5, minimumSalary);
		    stm.setString(6, workingTime);
		    stm.setString(7, degreeLevel);
		    stm.setString(8, specialization);
		    stm.setString(9, technicalSkills);
		    
		    stm.executeUpdate();
		    
			// Close the connection after successful data insertion
		    stm.close();
		    conn.close();
		    			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		// Set variables to null before redirecting back to the subscribeapplicants.xhtml
		// page to clear the data entered in the form fields
		firstName = null;
		lastName = null;
		emailAddress = null;
		contactNumber = null;
		minimumSalary = null;
		workingTime = null;
		degreeLevel = null;
		specialization = null;
		technicalSkills = null;
		
		// Redirect back to the subscribeapplicants.xhtml page
		return "subscribeapplicants.xhtml?faces-redirect=true";
	}

	
	/*
	 * Setters and Getters
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMinimumSalary() {
		return minimumSalary;
	}

	public void setMinimumSalary(String minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getDegreeLevel() {
		return degreeLevel;
	}

	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	
}
