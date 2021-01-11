package com.dbc.localitys_back.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ApiErro apiErro;
	
	public BusinessException(ApiErro apierro) {
		this.apiErro = apierro;
	}
	
	public ApiErro getApiErro() {
		return apiErro;
	}
	
	public void setApiErro(ApiErro apiErro) {
		this.apiErro = apiErro;
	}
}
