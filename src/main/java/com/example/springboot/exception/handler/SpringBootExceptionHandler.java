package com.example.springboot.exception.handler;

import com.example.springboot.domain.exception.ServiceApiException;
import com.example.springboot.exception.model.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class SpringBootExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ServiceApiException> handleApiException(ApiException ex) {
		if (ex.getStatus() == BAD_REQUEST.value()) {
			log.warn("Wrong data in the request. Message: {}", ex.getDescription());
			return ResponseEntity.status(BAD_REQUEST.value())
			        .body(new ServiceApiException(ex.getDescription(), ex.getStatus()));
		}
		if (ex.getStatus() == NOT_FOUND.value()) {
			log.warn("Data wasn't found. Message: {}.", ex.getDescription());
			return ResponseEntity.notFound()
			        .build();
		}
		log.error("Unspecified exception has occurred.");

		return ResponseEntity.status(ex.getStatus())
		        .body(new ServiceApiException(ex.getDescription(), ex.getStatus()));
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ServiceApiException handle(IllegalArgumentException ex) {
		return new ServiceApiException("some message", BAD_REQUEST.value());
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ServiceApiException handleUnspecifiedException(Exception ex) {
		log.error("Unspecified exception has occurred.");

		return new ServiceApiException("some message", INTERNAL_SERVER_ERROR.value());
	}
}
