package com.project.dto;


public class DTONotification {

	private String notification;
	
	public DTONotification(){
		super();
	}

	public DTONotification(String notification){
		this.notification = notification;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}	
}
