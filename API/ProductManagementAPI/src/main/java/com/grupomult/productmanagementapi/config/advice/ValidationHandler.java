package com.grupomult.productmanagementapi.config.advice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grupomult.productmanagementapi.controllers.dto.DefaultErrorDTO;

@RestControllerAdvice
public class ValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<DefaultErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		List<DefaultErrorDTO> errorDto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			DefaultErrorDTO actualErrorDto = new DefaultErrorDTO(e.getField(), message);
			errorDto.add(actualErrorDto);
		});
		
		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public DefaultErrorDTO handleEntityNotFound(EntityNotFoundException exception) {
		return new DefaultErrorDTO("product not found", exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public DefaultErrorDTO handleEmptyResultDataAccess(EmptyResultDataAccessException exception) {
		return new DefaultErrorDTO("product not found", exception.getMessage());
	}
}