package com.lkh.board_back.common;

public interface ResponseCode {
    // interface 내에서는 모든 구성이
    // public static final 이어야 하기 때문에, 
    // 아무 것도 없어도 public static final이 있다고 알아두면 된다. 

    // HTTP STATUS 200 
    String SUCCESS = "SU";
//  public static final String SUCCESS = "SU";

    // HTTP STATUS 400 Error
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL ="DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_BOARD = "NB";
    
    // HTTP STATUS 401 
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAILED ="AF";
    
    // HTTP STATUS 403 
    String NO_PERMISSION = "NP";

    // HTTP STATUS 500 
    String DATABASE_ERROR = "DBE";

}
