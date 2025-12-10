package com.project.task.manager.service.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.project.task.manager.domain.DTO.DTOcomment;
import com.project.task.manager.domain.entities.Comment;

@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE 
		)
public interface CommentMapper {

	DTOcomment toDTO (Comment comment);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "task", ignore = true)
	Comment toEntity (DTOcomment dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "task", ignore = true)
	Comment updateEntityFromDTO(DTOcomment dto, @MappingTarget Comment comment);
	
	default Page <DTOcomment> toDTOpage (Page <Comment> page) {
		return page.map(this::toDTO);
	}
	
}
