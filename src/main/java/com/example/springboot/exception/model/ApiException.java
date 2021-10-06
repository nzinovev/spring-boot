package com.example.springboot.exception.model;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class ApiException extends RuntimeException {

	private static final String EXCEPTION_DESCRIPTION = "Unexpected error occurred in spring-boot-service";

	private final Integer status;
	private final String description;

	public ApiException(Integer status, String description) {
		super(description);
		this.status = status;
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		String message = "Error with message: %s";
		return format(message, getDescription());
	}

	public String getDescription() {
		return isEmpty(description) ? EXCEPTION_DESCRIPTION : description;
	}
}
