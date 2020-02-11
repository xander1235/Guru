package com.guru.info.utils;

import com.guru.info.constants.exceptions.GuruExceptions;
import com.guru.info.constants.RequestHeaderConstants;
import com.guru.info.exceptions.BadRequestException;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.http.HttpServletRequest;


public class GuruUtils {

    static EmailValidator emailValidator = EmailValidator.getInstance();
    public static String validateEmailInHeader(HttpServletRequest request){
        String email = request.getHeader(RequestHeaderConstants.USER_EMAIL);
        if(email==null){
            throw new BadRequestException(GuruExceptions.NO_EMAIL_FOUND_IN_HEADER);
        }
        if(!isValidEmail(email)){
            throw new BadRequestException((GuruExceptions.INAVLID_EMAIL_IN_REQ));
        }
        return email;
    }

    public static boolean isValidEmail(String email){
        return emailValidator.isValid(email);
    }
}
