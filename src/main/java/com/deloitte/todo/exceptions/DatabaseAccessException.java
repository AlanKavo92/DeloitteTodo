package com.deloitte.todo.exceptions;

public class DatabaseAccessException extends Exception {

	public DatabaseAccessException(String errorMessage) {
		super(errorMessage);
	}
}