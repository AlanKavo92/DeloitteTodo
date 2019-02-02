package com.deloitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{}
