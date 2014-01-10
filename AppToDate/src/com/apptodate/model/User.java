package com.apptodate.model;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private int semester;
	private String course;
	
	
	public User() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.semester = 0;
		this.course = "";
	}


	public User(long id, String firstName, String lastName, int semester, String course) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.semester = semester;
		this.course = course;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	
}
