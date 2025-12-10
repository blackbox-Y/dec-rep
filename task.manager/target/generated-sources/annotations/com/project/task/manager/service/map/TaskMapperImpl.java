package com.project.task.manager.service.map;

import com.project.task.manager.domain.DTO.DTOtask;
import com.project.task.manager.domain.entities.Task;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-08T19:40:44+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public DTOtask toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        DTOtask.DTOtaskBuilder dTOtask = DTOtask.builder();

        dTOtask.agent( task.getAgent() );
        dTOtask.author( task.getAuthor() );
        dTOtask.description( task.getDescription() );
        dTOtask.priority( task.getPriority() );
        dTOtask.status( task.getStatus() );
        dTOtask.title( task.getTitle() );

        return dTOtask.build();
    }

    @Override
    public Task toEntity(DTOtask dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setAgent( dto.getAgent() );
        task.setAuthor( dto.getAuthor() );
        task.setDescription( dto.getDescription() );
        task.setPriority( dto.getPriority() );
        task.setStatus( dto.getStatus() );
        task.setTitle( dto.getTitle() );

        return task;
    }

    @Override
    public Task updateEntityFromDTO(DTOtask dto, Task task) {
        if ( dto == null ) {
            return task;
        }

        if ( dto.getAgent() != null ) {
            task.setAgent( dto.getAgent() );
        }
        if ( dto.getAuthor() != null ) {
            task.setAuthor( dto.getAuthor() );
        }
        if ( dto.getDescription() != null ) {
            task.setDescription( dto.getDescription() );
        }
        if ( dto.getPriority() != null ) {
            task.setPriority( dto.getPriority() );
        }
        if ( dto.getStatus() != null ) {
            task.setStatus( dto.getStatus() );
        }
        if ( dto.getTitle() != null ) {
            task.setTitle( dto.getTitle() );
        }

        return task;
    }
}
