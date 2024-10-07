package com.src.mycomplex.main.exceptions;

import com.src.mycomplex.main.utils.RequestStatus;

public class UserRequestException extends AbstractRuntimeException {
    
	public UserRequestException(RequestStatus error,Object... msgParams) {
        super(error.getCode(),error.getDescription(msgParams));
    }
}