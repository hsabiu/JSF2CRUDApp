package com.peopleware.jsf;

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
	private String academicDegree;
	private String technicalSkills;

	public NewApplicant() {
		
	}
	
	public String addNewApplicant() {
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(emailAddress);
		System.out.println(contactNumber);
		System.out.println(minimumSalary);
		System.out.println(workingTime);
		System.out.println(academicDegree);
		System.out.println(technicalSkills);
		
		
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

	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	
}
