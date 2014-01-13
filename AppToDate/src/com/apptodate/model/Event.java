package com.apptodate.model;

public class Event {

	private long createdAt;
	private long id;
	private long dtstart;
	private String description;
	private String place;

	public Event() {
		this.id = -1;
		this.createdAt = -1;
		this.dtstart = -1;
		this.description = "";
		this.place = "";
	}

	public Event(long id, long createdAt, long eventAt, String description, String place) {
		this.id = id;
		this.createdAt = createdAt;
		this.dtstart = eventAt;
		this.description = description;
		this.place = place;
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

	public long getDtstart() {
		return dtstart;
	}

	public void setDtstart(long dtstart) {
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
	
	
}
