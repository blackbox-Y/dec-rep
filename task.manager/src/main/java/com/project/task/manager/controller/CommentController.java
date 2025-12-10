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

import com.project.task.manager.domain.DTO.DTOcomment;
import com.project.task.manager.domain.entities.Comment;
import com.project.task.manager.domain.exceptions.HttpExceptions;
import com.project.task.manager.service.implementation.CommentServiceImpl;
import com.project.task.manager.service.map.CommentMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user-{userId}/task-{taskId}/com")
public class CommentController {
	
	CommentServiceImpl service;
	CommentMapper map;
	
	@GetMapping("/{comId}")
	public ResponseEntity <?> show (@PathVariable Long comId, @RequestBody int pageNumber) {
		try {
			Page <DTOcomment> com = service.showByTask(comId, pageNumber);
			return new ResponseEntity <Page <DTOcomment>> (com, HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.NOT_FOUND.value(),
							"Comment with id " + comId + " cannot be found"),
					HttpStatus.NOT_FOUND);
		}
	}
		
	@PostMapping("/{comId}-set")
	public ResponseEntity <?> add (@RequestBody Comment comment, @RequestBody Long comId) {
		try {
			return new ResponseEntity <Comment> (service.add(comment), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"Comment " + comment.getText() 
							+ "cannot be added to Task with ID: " + comId),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping ("/{comId}/update")
	public ResponseEntity <?> update (@RequestBody DTOcomment dto, @PathVariable Long comId) {
		try {
			DTOcomment com = map.toDTO(service.update(comId, dto));
			return new ResponseEntity <DTOcomment> (com, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
							"Comment : \"" + dto.getText() 
							+ "\" cannot be added to Task with ID: " + comId),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{comId}/delete")
	public ResponseEntity <?> update (@PathVariable Long comId) {
		try {
			return new ResponseEntity <String> (service.delete(comId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new HttpExceptions(HttpStatus.BAD_REQUEST.value(),
						" cannot delte this comment " + comId),
					HttpStatus.BAD_REQUEST);
		}
	}
}
