package com.lkh.board_back.dto.request.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class SignInResponseDTO extends ResponseDTO {
    private String token;
    private int expirationTime; 

    private SignInResponseDTO(String token){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600; // provider > JwtProvider.java에 보면 만료기간을 1시간으로 해두었기 때문에 값을 3600으로 지정 
    }
    // 200 : 로그인 성공
    public static ResponseEntity<SignInResponseDTO> success(String token){
        SignInResponseDTO result = new SignInResponseDTO(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // 401 error : 로그인 실패(Login information mismatch)
    public static ResponseEntity<ResponseDTO> signInFailed() {
        ResponseDTO result = new ResponseDTO(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
