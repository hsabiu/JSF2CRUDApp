package com.peopleware.jsf;


/**
 * A class to represents all the information of a
 * applicant including contact information, education
 * working preferences and skills
 * 
 * @author habib
 */
public class Applicant {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String minSalary;
	private String workingTime;
	private String degreeLevel;
	private String specialization;
	private String skill;
	private int rank;
	private int score;


	/*
	 * Constructor
	 */
	public Applicant() {
		
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getMinSalary() {
		return minSalary;
	}


	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
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


	public String getSkill() {
		return skill;
	}


	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
