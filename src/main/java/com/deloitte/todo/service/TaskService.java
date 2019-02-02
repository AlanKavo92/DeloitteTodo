package com.deloitte.todo.service;

import java.util.List;

import com.deloitte.todo.model.Task;

public interface TaskService {
	List<Task> getTasksForUser(Long id);
	void addTask(Task task);
	void removeTask(Task task);
}
