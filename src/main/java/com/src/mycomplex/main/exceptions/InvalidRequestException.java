package com.src.mycomplex.main.exceptions;

import com.src.mycomplex.main.utils.RequestStatus;

public class InvalidRequestException extends AbstractRuntimeException {
	
	public InvalidRequestException(RequestStatus error,Object... msgParams) {
        super(error.getCode(),error.getDescription(msgParams));
    }
}
