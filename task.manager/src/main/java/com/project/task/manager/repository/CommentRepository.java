package com.project.task.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.task.manager.domain.entities.Comment;
import com.project.task.manager.domain.entities.Task;
import com.project.task.manager.domain.entities.User;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{
	
	Page <Comment> findByTask (Task task, Pageable pageable);
	Page <Comment> findByCommenter (User user, Pageable pageable);
	
	void deleteById (Long Id);
}
