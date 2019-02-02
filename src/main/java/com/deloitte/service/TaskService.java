package com.deloitte.service;

import java.util.List;

import com.deloitte.model.Task;

public interface TaskService {
	List<Task> getTasksForUser(Long id);
	void addTask(Task task);
	void removeTask(Task task);
}
