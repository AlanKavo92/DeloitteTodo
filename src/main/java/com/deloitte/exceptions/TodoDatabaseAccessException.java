package com.deloitte.exceptions;

public class TodoDatabaseAccessException extends Exception {

	public TodoDatabaseAccessException(String errorMessage) {
		super(errorMessage);
	}
}