package com.deloitte.todo.exceptions;

/**
 * Database Access Exception
 * 
 * @author Alan Kavanagh
 */
public class DatabaseAccessException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String errorMessage) {
		super(errorMessage);
	}
}