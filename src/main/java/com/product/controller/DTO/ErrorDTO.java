package com.product.controller.DTO;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorDTO {
	
	private HttpStatus status;
	private LocalDateTime currentTime;
	private String ErrorDescription;
	
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
	
	

}
