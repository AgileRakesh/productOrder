package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.controller.DTO.ErrorDTO;



@Component
@ControllerAdvice("com.product.controller")
public class GlobalExceptionHandler {
	
	// we can add more exception types to map
	@ExceptionHandler(value= CustomOrderNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleException(CustomOrderNotFoundException e){
		ErrorDTO dto=new ErrorDTO();
		dto.setCurrentTime(LocalDateTime.now());
		dto.setErrorDescription(e.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		
		
	}

}
