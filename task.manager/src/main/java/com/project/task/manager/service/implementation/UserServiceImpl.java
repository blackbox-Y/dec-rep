package com.project.task.manager.service.implementation;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.task.manager.domain.DTO.DTOuser;
import com.project.task.manager.domain.entities.User;
import com.project.task.manager.repository.UserRepository;
import com.project.task.manager.service.interfaces.UserService;
import com.project.task.manager.service.map.UserMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository repo;
	private final UserMapper map;
	
	@Override
	@Transactional
	public User create(User user) {
		log.info("creating a user entity:{}" + user.getEmail());
		return repo.save(user);
	}
	
	@Override
	public User show(Long id) {
		log.info("reading a user entity of id: {}" + id);
		
		return repo.findById(id).orElseThrow(
				()-> new EntityNotFoundException());
	}

	
	@Override
	public User showByEmail(String email) {
		log.info("reading a user entity of email: {}" + email);	
		
		return repo.findByEmail(email).orElseThrow(
				()-> new EntityNotFoundException());
	}

	
	@Override
	public Page<DTOuser> readMany(int pageNumber) {
		log.info("reading a page of number: {}" + pageNumber);

		Pageable pageable = PageRequest
				.of(pageNumber, 20, Sort.by("id").ascending());
		
		Page <User> page = repo.findAll(pageable);
		
		return map.toDTOpage(page);
	}
	
	@Transactional
	@Override
	public User update(DTOuser dto, Long id) {
		User user = show(id);
		
		repo.save(map.updateEntityFromDTO(dto, user));
		
		return show(id);
	}

	@Transactional
	@Override
	public String delete(Long id) {
		log.info("deleting a User entity: {}" + id);
		
		repo.deleteById(id);
		
		return "User "+ id + " was deleted";
	}


}
