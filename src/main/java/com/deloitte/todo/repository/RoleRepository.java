package com.deloitte.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{}
