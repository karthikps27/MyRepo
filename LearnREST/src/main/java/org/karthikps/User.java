package org.karthikps;

import java.util.Date;

public class User {

	private String FirstName;
	private String LastName;
	private Date DOB;
	private long phonenumber;
	public User(String firstName, String lastName, Date dOB, long phonenumber) {
		super();
		FirstName = firstName;
		LastName = lastName;
		DOB = dOB;
		this.phonenumber = phonenumber;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
