package com.project.task.manager.domain.DTO;

import com.project.task.manager.domain.entities.User;
import com.project.task.manager.domain.status.PRIORITY;
import com.project.task.manager.domain.status.STATUS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DTOtask {
	private String title;
	private String description;
	private PRIORITY priority;
	private STATUS status;
	private User author;
	private User agent;
}
