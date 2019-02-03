package com.deloitte.todo.service;

import org.springframework.cache.annotation.Cacheable;

/**
 * Security Service Interface
 * 
 * @author Alan Kavanagh
 */
public interface SecurityService {
    String findLoggedInUsername();
	@Cacheable("autologin")
    void autologin(String username, String password);
}
