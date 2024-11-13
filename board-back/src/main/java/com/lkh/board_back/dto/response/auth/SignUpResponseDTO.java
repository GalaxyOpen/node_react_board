package com.lkh.board_back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class SignUpResponseDTO extends ResponseDTO{
    private SignUpResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<SignUpResponseDTO> success() {
        SignUpResponseDTO result = new SignUpResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> duplicateEmail(){
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    public static ResponseEntity<ResponseDTO> duplicateNickName(){
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    public static ResponseEntity<ResponseDTO> duplicateTelNumber(){
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_TEL_NUMBER, ResponseMessage.DUPLICATE_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
