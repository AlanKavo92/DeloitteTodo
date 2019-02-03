package com.deloitte.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findAllByUserId(Long userId);
	List<Task> findAllByUserIdAndIsChecked(Long userId, Boolean isChecked);
	Task findOneById(Long taskId);
}
