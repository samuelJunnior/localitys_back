package com.dbc.localitys_back.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErro {

	private HttpStatus httpStatus;
	private String message;
	private List<String> erros;
	
	public ApiErro(HttpStatus httpStatus, String message, List<String> erros) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.erros = erros;
	}
	
	public ApiErro(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.erros = new ArrayList<String>();
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErros() {
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}
}
