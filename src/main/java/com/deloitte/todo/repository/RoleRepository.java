package com.deloitte.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.todo.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{}
