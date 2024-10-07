package com.src.mycomplex.main.exceptions;

import com.src.mycomplex.main.utils.RequestStatus;

public class UserNotFoundException extends AbstractRuntimeException {
    
	public UserNotFoundException(RequestStatus error,Object... msgParams) {
        super(error.getCode(),error.getDescription(msgParams));
    }
}