package com.src.mycomplex.main.exceptions;

import com.src.mycomplex.main.utils.RequestStatus;

public class OTPException extends AbstractRuntimeException {
    
	public OTPException(RequestStatus error,Object... msgParams) {
        super(error.getCode(),error.getDescription(msgParams));
    }
}