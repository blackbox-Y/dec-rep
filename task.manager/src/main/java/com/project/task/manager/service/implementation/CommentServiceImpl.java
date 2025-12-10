package com.project.task.manager.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.task.manager.domain.entities.Comment;
import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.entities.User;
import com.project.task.manager.domain.DTO.DTOcomment;
import com.project.task.manager.repository.CommentRepository;
import com.project.task.manager.service.interfaces.CommentService;
import com.project.task.manager.service.map.CommentMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository repo;
	private final UserServiceImpl userService;
	private final TaskServiceImpl taskService;
	private final CommentMapper map;
	
	
	private  final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	@Override
	public Comment show(Long id) {
		log.info("Fetching a comment by id: {}", id);
		
		Comment comment = repo.findById(id).orElseThrow(
				()-> new EntityNotFoundException("comment not found"));
		return comment;
	}
	
	@Override
	public Page<DTOcomment> showByUser (Long id, int pageNumber) {
		log.info("Fetching a comment page: {}", id);
		
		User user = userService.show(id);
		Pageable pageable = PageRequest.of(pageNumber, 20);
		Page <Comment> comments = repo
				.findByCommenter(user, pageable);
		
		return map.toDTOpage(comments);
	}
	
	@Override
	public Page<DTOcomment> showByTask(Long id, int pageNumber) {
		log.info("Fetching a comment page: {}", id);
		
		Task task = taskService.show(id);
		Pageable pageable = PageRequest.of(pageNumber, 20);
		Page <Comment> comments = repo
				.findByTask(task, pageable);
		
		return map.toDTOpage(comments);
	}
	
	@Override	
	@Transactional
	public Comment add(Comment comment) {
		log.info("saving new comment: {}", comment.getId());
		return repo.save(comment);
	}

	@Transactional
	@Override
	public Comment update(Long id, DTOcomment dto) {
		log.info("updatig a comment");
		
		Comment comment = show(id);
		repo.save(map.updateEntityFromDTO(dto, comment));
		
		return show(id);
	}

	@Override
	@Transactional
	public String delete(Long id) {
		log.info("Deletig a comment");
		repo.deleteById(id);
		return "comment "+id+" was deleted";
	}
	
}
