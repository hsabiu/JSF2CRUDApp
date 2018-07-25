package com.peopleware.jsf;

public class Employee {

	private int ID;
	private String firstName;
	private String lastName;
	private String degree;
	private String title;
	private String photo;
	private String description;
	
	private String fullName;
	
	/*
	 * Constructor
	 */
	public Employee() {
		
	}

	
	/*
	 * Setters and Getters
	 */
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getFullName() {
		String fname = firstName + " " + lastName;
		if(!degree.isEmpty()) {
			fname += ", " + degree;
		}
		return fname;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	

}
