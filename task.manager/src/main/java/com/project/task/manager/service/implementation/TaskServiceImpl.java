package com.project.task.manager.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.entities.User;
import com.project.task.manager.domain.DTO.DTOtask;
import com.project.task.manager.repository.TaskRepository;
import com.project.task.manager.service.interfaces.TaskService;
import com.project.task.manager.service.interfaces.UserService;
import com.project.task.manager.service.map.TaskMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
	
	private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	private final UserService userService;
	private final TaskRepository repo;
	protected final TaskMapper map;

	@Override
	@Transactional
	public Task add (Task task) {	
		log.info("adding a task: {}", task);
			return repo.save(task);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Task show (Long taskId) {
		log.info("fetching task: {}", taskId);
		Task task = repo.findById(taskId).orElseThrow(
				()-> new EntityNotFoundException());
		System.out.println(task);
		return task;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DTOtask> showByAuthor (Long authorId, int pageNumber) {
		log.info("attempt to get task via author: {}", authorId);
		
		Pageable pageable = PageRequest
				.of(pageNumber, 20, Sort.by("id").ascending()); 
		
		User user = userService.show(authorId);
		

		Page <Task> page = repo
				.findByAuthor(pageable, user);
		return map.toDTOpage(page);
	}

	
	public Page <DTOtask> showAll (int pageNumber) {
		log.info("attempt to show all tasks: {}");

		Pageable pageable = PageRequest
				.of(pageNumber, 20, Sort.by("id").ascending());
		Page <Task> page = repo
				.findAll(pageable);
		
				
		return map.toDTOpage(page);
	}


	@Override
	@Transactional
	public Task update (Long id, DTOtask DTO) {
		log.info("updating a task: {}", id);
		Task task = show(id);
		repo.save(map.updateEntityFromDTO(DTO, task));
		return show(id);
		
	}
	
	@Transactional
	@Override
	public String delete(Long id) {
		log.info("deleting a task by id: {}", id);
		repo.deleteById(id);
		return id + " was deleted";
	}
	
		
}
