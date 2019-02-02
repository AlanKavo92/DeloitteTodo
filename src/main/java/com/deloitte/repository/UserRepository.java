package com.deloitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
