package com.project.task.manager.service.interfaces;

import org.springframework.data.domain.Page;

import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.DTO.DTOtask;

public interface TaskService {
	
	Task add (Task task);
	
	Task show (Long taskId);
	
	Page <DTOtask> showByAuthor (Long authorId, int pageNumber);

	Page <DTOtask> showAll (int pageNumber);

//	TaskWithCommentsDTO taskWithComments (Long taskId, Long authorId, int pageNumber, int pageSize);

	Task update (Long id, DTOtask DTO);
	
	String delete (Long id);
	
}
