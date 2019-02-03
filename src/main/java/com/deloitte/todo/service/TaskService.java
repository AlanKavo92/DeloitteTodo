package com.deloitte.todo.service;

import java.util.List;

import com.deloitte.todo.model.Task;

public interface TaskService {
	List<Task> getAllTasksForUser(Long id);
	List<Task> getActiveTasksForUser(Long id);
	List<Task> getCompletedTasksForUser(Long id);

	void addTask(String desc);
	void updateTask(Long id, String desc);
	void removeTask(Long id);
	
	void toggleChecked(Long id, Boolean isChecked);
}
