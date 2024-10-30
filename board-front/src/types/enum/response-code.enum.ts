enum ResponseCode {
    SUCCESS = "SU",
    //  public static final SUCCESS = "SU",
    
        // HTTP STATUS 400 Error
        VALIDATION_FAILED = "VF",
        DUPLICATE_EMAIL ="DE",
        DUPLICATE_NICKNAME = "DN",
        DUPLICATE_TEL_NUMBER = "DT",
        NOT_EXIST_USER = "NU",
        NOT_EXIST_BOARD = "NB",
        
        // HTTP STATUS 401 
        SIGN_IN_FAIL = "SF",
        AUTHORIZATION_FAILED ="AF",
        
        // HTTP STATUS 403 
        NO_PERMISSION = "NP",
    
        // HTTP STATUS 500 
        DATABASE_ERROR = "DBE",
}

export default ResponseCode;