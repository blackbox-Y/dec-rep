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

import com.project.task.manager.domain.DTO.DTOuser;
import com.project.task.manager.domain.entities.User;
import com.project.task.manager.domain.exceptions.HttpExceptions;
import com.project.task.manager.service.implementation.UserServiceImpl;
import com.project.task.manager.service.map.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
	private final UserServiceImpl service;
	private final UserMapper map;
	
	@PostMapping("/add")
	public ResponseEntity <?> add (@RequestBody User user) {
		try {
			return new ResponseEntity <User> (service.create(user), HttpStatus.CREATED); 
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
					"object: " + user.getEmail() + " cannot be created"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("-{userId}")
	public ResponseEntity<?> findTask (@PathVariable Long userId) {
		try {
			DTOuser task = map.toDTO(service.show(userId));
			return new ResponseEntity<DTOuser>(task, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.NOT_FOUND.value(), 
					"User with ID%: " + userId + " not found"), 
					HttpStatus.NOT_FOUND);			
		}	
	}
	
	@GetMapping("/all/{pageNumber}")
	public ResponseEntity <?> findMultipleTask (@PathVariable int pageNumber) {
		try {
			Page <DTOuser> page = service.readMany(pageNumber);
			return new ResponseEntity<Page <DTOuser> >(page, HttpStatus.OK);
		} catch (Exception e){		
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.NOT_FOUND.value(),
					"Page no. " + pageNumber + " is empty"),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("-{id}/set")
	public ResponseEntity <?> update (@RequestBody DTOuser DTO, @PathVariable Long id) {
		try {
			return new ResponseEntity <DTOuser> (
					map.toDTO(service.update(DTO, id)),
					HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"user: " + id + " cannot be updated"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("-{id}/delete")
	public ResponseEntity <?> delete(@PathVariable Long id) {
		try {
			return new ResponseEntity <String> (service.delete(id), HttpStatus.NOT_FOUND); 
		} catch (Exception e) {
			return new ResponseEntity<> (
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"user: " + id + " cannot be deleted"),
					HttpStatus.BAD_REQUEST);
		}
	}
}
