package com.deloitte.todo.service;

import com.deloitte.todo.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
