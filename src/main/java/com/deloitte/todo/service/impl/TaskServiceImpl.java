package com.deloitte.todo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todo.model.Task;
import com.deloitte.todo.repository.TaskRepository;
import com.deloitte.todo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired
	TaskRepository taskRepository;

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

	@Override
	public List<Task> getAllTasksForUser(Long id) {
		logger.debug("getting tasks for user id: " + id.toString());

		return taskRepository.findAllByUserId(id);
	}

	@Override
	public List<Task> getActiveTasksForUser(Long id) {
		logger.debug("getting tasks for user id: " + id.toString());

		return taskRepository.findAllByUserIdIsCheckedFalse(id);
	}

	@Override
	public List<Task> getCompletedTasksForUser(Long id) {
		logger.debug("getting tasks for user id: " + id.toString());

		return taskRepository.findAllByUserIdIsCheckedTrue(id);
	}
}
