package com.lkh.board_back.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMassage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    
    private String code;
    private String message;

    public static ResponseEntity<ResponseDTO> databaseError() {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.DATABASE_ERROR, ResponseMassage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
