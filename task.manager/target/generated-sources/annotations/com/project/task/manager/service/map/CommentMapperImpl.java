package com.project.task.manager.service.map;

import com.project.task.manager.domain.DTO.DTOcomment;
import com.project.task.manager.domain.entities.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-08T19:40:44+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public DTOcomment toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        DTOcomment.DTOcommentBuilder dTOcomment = DTOcomment.builder();

        dTOcomment.commenter( comment.getCommenter() );
        dTOcomment.text( comment.getText() );

        return dTOcomment.build();
    }

    @Override
    public Comment toEntity(DTOcomment dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commenter( dto.getCommenter() );
        comment.text( dto.getText() );

        return comment.build();
    }

    @Override
    public Comment updateEntityFromDTO(DTOcomment dto, Comment comment) {
        if ( dto == null ) {
            return comment;
        }

        if ( dto.getCommenter() != null ) {
            comment.setCommenter( dto.getCommenter() );
        }
        if ( dto.getText() != null ) {
            comment.setText( dto.getText() );
        }

        return comment;
    }
}
