package com.dbc.localitys_back.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		ApiErro apiErro = new ApiErro(HttpStatus.BAD_REQUEST, ex.getMessage());
        return handleExceptionInternal(ex, apiErro, new HttpHeaders(), apiErro.getHttpStatus(), request);
    }
	
    @ExceptionHandler(value = { BusinessException.class})
    protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getApiErro(), new HttpHeaders(), ex.getApiErro().getHttpStatus(), request);
    }
}