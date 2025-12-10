package com.project.task.manager.service.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.project.task.manager.domain.DTO.DTOtask;
import com.project.task.manager.domain.entities.Task;



@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
		)
public interface TaskMapper {
	
	DTOtask toDTO (Task task);
	
	@Mapping(target = "id", ignore = true)
	Task toEntity (DTOtask dto);
	
	@Mapping(target = "id", ignore = true)
    Task updateEntityFromDTO(DTOtask dto, @MappingTarget Task task);
	
	default Page <DTOtask> toDTOpage (Page <Task> page) {
		return page.map(this::toDTO);
	}

}
