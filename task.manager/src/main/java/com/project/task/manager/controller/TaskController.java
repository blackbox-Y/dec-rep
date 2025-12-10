package com.project.task.manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.task.manager.domain.DTO.DTOtask;
import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.exceptions.HttpExceptions;
import com.project.task.manager.service.implementation.TaskServiceImpl;
import com.project.task.manager.service.map.TaskMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user-{userId}/task")
public class TaskController {
	private final TaskServiceImpl service;
	private final TaskMapper map;
	
	@PostMapping("/add")
	public ResponseEntity <?> add (@RequestBody Task task) {
		try {
			return new ResponseEntity <Task> (service.add(task), HttpStatus.CREATED); 
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
					"object: " + task.getTitle() + " cannot be created"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("-{taskId}")
	public ResponseEntity<?> findTask (@PathVariable Long taskId) {
		try {
			DTOtask task = map
					.toDTO(service.show(taskId));
			return new ResponseEntity<DTOtask>(task, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.NOT_FOUND.value(), 
					"Task with ID%: " + taskId + " not found"), 
					HttpStatus.NOT_FOUND);			
		}		
	}
	
	@GetMapping("/all/{pageNumber}")
	public ResponseEntity <?> findMultipleTask (@PathVariable int pageNumber) {
		try {
			Page <DTOtask> page = service.showAll(pageNumber);
			return new ResponseEntity<Page <DTOtask> >(page, HttpStatus.OK);
		} catch (Exception e){		
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.NOT_FOUND.value(),
					"Page no. " + pageNumber + " is empty"),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("-{taskId}/set")
	public ResponseEntity <?> update (@RequestBody DTOtask DTO, @PathVariable Long taskId) {
		try {
			return new ResponseEntity <DTOtask> (
					map.toDTO(service.update(taskId, DTO)),
					HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"object: " + taskId + " cannot be updated"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("-{taskId}/delete")
	public ResponseEntity <?> delete(@PathVariable Long taskId) {
		try {
			return new ResponseEntity <String> (service.delete(taskId), HttpStatus.NOT_FOUND); 
		} catch (Exception e) {
			return new ResponseEntity<> (
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"object: " + taskId + " cannot be deleted"),
					HttpStatus.BAD_REQUEST);
		}
	}	
}
