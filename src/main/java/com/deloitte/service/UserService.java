package com.deloitte.service;

import com.deloitte.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
