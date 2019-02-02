package com.deloitte.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.todo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
