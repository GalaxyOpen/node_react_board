package com.lkh.board_back.common;

public interface ResponseMassage {
        // HTTP STATUS 200 
        String SUCCESS = "Success";
        //  public static final String SUCCESS = "SU";
        
            // HTTP STATUS 400 Error
            String VALIDATION_FAILED = "Validation Failed";
            String DUPLICATE_EMAIL ="Duplicate Email";
            String DUPLICATE_NICKNAME = "Duplicate Nickname";
            String DUPLICATE_TEL_NUMBER = "Duplicate Tel number.";
            String NOT_EXIST_USER = "This user does not exist.";
            String NOT_EXIST_BOARD = "This Board does not exist.";
            
            // HTTP STATUS 401 
            String SIGN_IN_FAIL = "Login information mismatch.";
            String AUTHORIZATION_FAILED ="Authorization failed.";
            
            // HTTP STATUS 403 
            String NO_PERMISSION = "Do not have permission.";
        
            // HTTP STATUS 500 
            String DATABASE_ERROR = "DateBase Error.";
        
}
