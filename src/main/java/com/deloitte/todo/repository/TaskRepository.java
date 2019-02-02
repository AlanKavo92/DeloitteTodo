package com.deloitte.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.todo.model.Task;
import com.deloitte.todo.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findAllByUser(User user);
	List<Task> findAllByUserId(Long userId);
}
