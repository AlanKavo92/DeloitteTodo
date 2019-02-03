package com.deloitte.todo.service;

/**
 * Security Service Interface
 * 
 * @author Alan Kavanagh
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
