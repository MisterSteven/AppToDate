package com.apptodate.model;

public class Event implements IEvent {

	private long createdAt;
	private long id;
	private long eventAt;

	public Event() {
		this.id = -1;
		this.createdAt = -1;
		this.eventAt = -1;
	}

	public Event(long id, long createdAt, long eventAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.eventAt = eventAt;
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

	public long getEventAt() {
		return eventAt;
	}

	public void setEventAt(long eventAt) {
		this.eventAt = eventAt;
	}
	
	
}
