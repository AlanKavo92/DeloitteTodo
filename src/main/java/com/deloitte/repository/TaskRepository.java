package com.deloitte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.model.Task;
import com.deloitte.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findAllByUser(User user);
	List<Task> findAllByUserId(Long userId);
}
