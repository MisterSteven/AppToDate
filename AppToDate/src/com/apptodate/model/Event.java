package com.apptodate.model;

import java.util.Date;

public class Event {

	private long createdAt;
	private long id;
	private Date dtstart;
	private String description;
	private String place;
	private String title;

	public Event() {
		this.id = -1;
		this.createdAt = -1;
		this.dtstart = null;
		this.description = "";
		this.place = "";
		this.title = "";
	}

	public Event(long id, long createdAt, Date eventAt, String description, String place, String title) {
		this.id = id;
		this.createdAt = createdAt;
		this.dtstart = eventAt;
		this.description = description;
		this.place = place;
		this.title = title;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDtstart() {
		return dtstart;
	}

	public void setDtstart(Date dtstart) {
		this.dtstart = dtstart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
