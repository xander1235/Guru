package com.guru.info.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{

    private static final long serailVesrsionUID = 1L;

    public ForbiddenException(String msg){
        super(msg);
    }
}
