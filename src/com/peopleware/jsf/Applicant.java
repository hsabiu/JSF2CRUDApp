package com.peopleware.jsf;

public class Applicant {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phone_number;
	private String minSalary;
	private String working_time;
	private String degreeLevel;
	private String specialization;
	private String skill;
	private int score;


	/*
	 * Constructor
	 */
	public Applicant() {
		
	}
	
	/*
	 * Setters and Getters
	 */

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getMinSalary() {
		return minSalary;
	}


	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}


	public String getWorking_time() {
		return working_time;
	}


	public void setWorking_time(String working_time) {
		this.working_time = working_time;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
