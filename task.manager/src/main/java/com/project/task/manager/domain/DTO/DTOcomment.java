package com.project.task.manager.domain.DTO;

import com.project.task.manager.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DTOcomment {
	private User commenter;
	private String text;
}
