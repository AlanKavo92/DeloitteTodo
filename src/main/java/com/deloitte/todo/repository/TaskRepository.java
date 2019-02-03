package com.deloitte.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todo.model.Task;
import com.deloitte.todo.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findAllByUser(User user);
	List<Task> findAllByUserId(Long userId);
}
