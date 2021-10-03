package com.sangkhochil.rest.webervices.restfulwebservice.controller.customexception;

import java.util.Date;

public class ExceptionReponse {
	private Date timeStapm;
	private String message;
	private String details;
	
	public ExceptionReponse(Date timeStapm, String message, String details) {
		super();
		this.timeStapm = timeStapm;
		this.message = message;
		this.details = details;
	}
	public Date getTimeStapm() {
		return timeStapm;
	}
	public void setTimeStapm(Date timeStapm) {
		this.timeStapm = timeStapm;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
