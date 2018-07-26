package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


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
	
	public String addNewApplicant() {
		
		DBManager db = new DBManager();
		Connection conn = db.getConnection();
		
		try {
			String query = "INSERT INTO candidates " 
			             + "(firstname, lastname, email, phone_number, min_salary, working_time, degree_level, specialization, skills) "
					     + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
		    stm.close();
		    conn.close();
		    
			System.out.println("Update successful");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		firstName = null;
		lastName = null;
		emailAddress = null;
		contactNumber = null;
		minimumSalary = null;
		workingTime = null;
		degreeLevel = null;
		specialization = null;
		technicalSkills = null;
		
		return "applicants.xhtml?faces-redirect=true";
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
