package com.project.task.manager.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HttpExceptions {
	private int statusCode;
	private String message;
	
	public HttpExceptions () {}
}
