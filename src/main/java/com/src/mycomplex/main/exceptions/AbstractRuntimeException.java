package com.src.mycomplex.main.exceptions;

import lombok.Data;

@Data
public abstract class AbstractRuntimeException extends RuntimeException {
	
	private String message;
	private int errorCode;
	
	AbstractRuntimeException(int errorCode,String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
}
