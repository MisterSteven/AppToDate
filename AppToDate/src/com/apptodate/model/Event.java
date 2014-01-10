package com.apptodate.model;

public class Event implements IEvent{
	
	public long createdAt;
	public long id;
	public long eventAt;
	
	public Event (){
		id = -1;
		createdAt = -1;
	}
}
