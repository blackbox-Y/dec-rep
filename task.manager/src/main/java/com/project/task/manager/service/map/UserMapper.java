package com.project.task.manager.service.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.project.task.manager.domain.DTO.DTOuser;
import com.project.task.manager.domain.entities.User;

@Mapper (
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE 
		)
public interface UserMapper {
	
	DTOuser toDTO (User user);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	User toEntity (DTOuser dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	User updateEntityFromDTO (DTOuser dto, @MappingTarget User user);
	
	default Page<DTOuser> toDTOpage (Page <User> page) {
		return page.map(this::toDTO);
	}
	
}
