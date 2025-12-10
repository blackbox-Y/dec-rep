package com.project.task.manager.service.interfaces;

import org.springframework.data.domain.Page;

import com.project.task.manager.domain.DTO.DTOuser;
import com.project.task.manager.domain.entities.User;

public interface UserService {
	
	User create (User user);
	
	User show (Long id);
	
	User showByEmail (String email);
	
	Page <DTOuser> readMany (int pageNumeber);
	
	User update (DTOuser dto, Long id);
	
	String delete (Long id);
}
