	package com.project.task.manager.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.entities.User;



public interface TaskRepository extends JpaRepository<Task, Long> {
	
	Page <Task> findAll (Pageable pageable);	
	
	Page <Task> findByAuthor (Pageable pageable, User Author);
	
	Page <Task> findByAgent (Pageable pageable, User Agent);
	
	ArrayList<Task> findByAuthor (User Author);
	
	Task findByTitle (String title);
	
	void deleteById (Long id);

}
