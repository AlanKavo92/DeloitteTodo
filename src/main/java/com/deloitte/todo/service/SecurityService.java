package com.deloitte.todo.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
