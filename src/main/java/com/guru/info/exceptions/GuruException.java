package com.guru.info.exceptions;

import org.springframework.http.HttpStatus;

public abstract class GuruException extends RuntimeException{
    public GuruException(String errMsg){
        super(errMsg);
    }

    public abstract HttpStatus getHttpStatus();
}
