package com.guru.info.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GuruException {
    public BadRequestException(String errMsg){
        super(errMsg);
    }

    @Override
    public HttpStatus getHttpStatus(){return HttpStatus.BAD_REQUEST;}
}
