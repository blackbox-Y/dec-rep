package com.project.task.manager.service.map;

import com.project.task.manager.domain.DTO.DTOuser;
import com.project.task.manager.domain.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-08T19:40:45+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public DTOuser toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        DTOuser.DTOuserBuilder dTOuser = DTOuser.builder();

        dTOuser.email( user.getEmail() );
        dTOuser.name( user.getName() );
        dTOuser.surname( user.getSurname() );

        return dTOuser.build();
    }

    @Override
    public User toEntity(DTOuser dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( dto.getEmail() );
        user.name( dto.getName() );
        user.surname( dto.getSurname() );

        return user.build();
    }

    @Override
    public User updateEntityFromDTO(DTOuser dto, User user) {
        if ( dto == null ) {
            return user;
        }

        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            user.setSurname( dto.getSurname() );
        }

        return user;
    }
}
