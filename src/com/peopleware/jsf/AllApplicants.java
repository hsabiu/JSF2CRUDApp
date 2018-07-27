package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@SuppressWarnings("deprecation")
@ManagedBean(name = "allapplicants", eager = true)
public class AllApplicants {

	private List<Applicant> applicantList = new ArrayList<Applicant>();

	/*
	 * Constructor
	 */
	public AllApplicants() {
		
	}

	/**
	 * 
	 * @return
	 */
	public String initializaAllApplicants() {

		applicantList = new ArrayList<Applicant>();

		try {

			// Create a database manager and get the connection
			DBManager db = new DBManager();
			Connection conn = db.getConnection();

			// Selection SQL query to retrieve the data from the database
			String query = "SELECT * FROM applicants ORDER BY id";

			// Prepare the statement and execute the query
			PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();

			// Process each of the rows returned by the SQL query
			while (rs.next()) {

				Applicant applicant = new Applicant();

				applicant.setFirstName(rs.getString("firstname"));
				applicant.setLastName(rs.getString("lastname"));
				applicant.setEmail(rs.getString("email"));
				applicant.setPhoneNumber(rs.getString("phone_number"));
				applicant.setMinSalary(rs.getString("min_salary"));
				applicant.setWorkingTime(rs.getString("working_time"));
				applicant.setDegreeLevel(rs.getString("degree_level"));
				applicant.setSpecialization(rs.getString("specialization"));
				applicant.setSkill(rs.getString("skills"));
				applicant.setScore(0);

				applicantList.add(applicant);

			}
			// Close the connection after successful data insertion
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "allapplicants.xhtml?faces-redirect=true";
	}

	/*
	 * Getters
	 */

	public List<Applicant> getapplicantList() {
		return applicantList;
	}

}
