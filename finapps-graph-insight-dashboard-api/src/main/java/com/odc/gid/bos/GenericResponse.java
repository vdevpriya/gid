package com.odc.gid.bos;


public class GenericResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GenericResponse(String message) {
		super();
		this.message = message;
	}
	
	
}
