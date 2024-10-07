package com.src.mycomplex.main.exceptions;

import com.src.mycomplex.main.utils.RequestStatus;

public class SignupException extends AbstractRuntimeException {
    
	public SignupException(RequestStatus error,Object... msgParams) {
        super(error.getCode(),error.getDescription(msgParams));
    }
}