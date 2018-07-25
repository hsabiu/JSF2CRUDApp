package com.peopleware.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean(name="jobprofile", eager=true)
@SuppressWarnings("deprecation")
public class NewJobPosting {
	
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
	public NewJobPosting() {
		
		
	}

	public String addNewJobPosting() {
		
		System.out.println(employer);
		System.out.println(contactNo);
		System.out.println(jobTitle);
		System.out.println(jobDescription);
		System.out.println(salaryRange);
		System.out.println(workingTime);
		System.out.println(requirements);

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
