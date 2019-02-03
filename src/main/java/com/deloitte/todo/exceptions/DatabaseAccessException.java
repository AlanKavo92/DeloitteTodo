package com.deloitte.todo.exceptions;

public class DatabaseAccessException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String errorMessage) {
		super(errorMessage);
	}
}