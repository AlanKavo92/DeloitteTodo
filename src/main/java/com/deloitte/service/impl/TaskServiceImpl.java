package com.deloitte.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.model.Task;
import com.deloitte.repository.TaskRepository;
import com.deloitte.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Task> getTasksForUser(Long id) {
		logger.debug("getting all tasks for user id: " + id);
		return taskRepository.findAllByUserId(id);
	}

	@Override
	public void addTask(Task task) {
		logger.debug("saving task: " + task.toString());
		taskRepository.save(task);
	}

	@Override
	public void removeTask(Task task) {
		logger.debug("removing task: " + task.toString());
		taskRepository.delete(task);
	}
}
