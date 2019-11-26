package com.tcc.resources.exception;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date timeStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError(long timeStamp, Integer status, String error, String message, String path) {
		super();
		this.timeStamp = new Date(timeStamp);
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}