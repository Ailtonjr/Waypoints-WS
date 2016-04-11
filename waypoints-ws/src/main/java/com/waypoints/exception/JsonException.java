package com.waypoints.exception;

public class JsonException {

	private final int errorCode;
	private final String message;

	public JsonException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return message;
	}

}
