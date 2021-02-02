package com.app.dto;

import java.time.LocalDateTime;

public class ResponseDTO {
	private String message;
	private LocalDateTime ts;
	public ResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	public ResponseDTO(String message) {
		super();
		this.message = message;
		this.ts=LocalDateTime.now();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTs() {
		return ts;
	}
	public void setTs(LocalDateTime ts) {
		this.ts = ts;
	}
	
	

}
