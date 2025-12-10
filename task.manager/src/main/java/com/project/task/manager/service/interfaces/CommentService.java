package com.project.task.manager.service.interfaces;

import org.springframework.data.domain.Page;

import com.project.task.manager.domain.entities.Comment;
import com.project.task.manager.domain.DTO.DTOcomment;

public interface CommentService {


	Comment add (
			Comment comment
			);
	
	Page <DTOcomment> showByUser (
			Long id,
			int pageNumber
			);
	
	Page <DTOcomment> showByTask (
			Long id,
			int pageNumber
			);
	
	Comment show (
			Long id
			);
	
	String delete (
			Long id
			);
	
	Comment update (
			Long id,
			DTOcomment dto
			);
}
