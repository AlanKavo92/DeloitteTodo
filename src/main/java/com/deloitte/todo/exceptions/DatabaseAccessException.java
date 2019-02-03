package com.deloitte.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deloitte.todo.config.WebSecurityConfig;

/**
 * Database Access Exception
 * 
 * @author Alan Kavanagh
 */
public class DatabaseAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseAccessException.class);


	public DatabaseAccessException(String errorMessage) {
		super(errorMessage);
		logger.error(errorMessage);
	}
}