package com.tcc.services.exceptions;

public class DateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DateFormatException(String msg) {
		super(msg);
	}
	
	public DateFormatException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}