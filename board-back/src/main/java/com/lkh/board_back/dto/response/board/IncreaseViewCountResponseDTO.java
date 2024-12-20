package com.lkh.board_back.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

public class IncreaseViewCountResponseDTO extends ResponseDTO {
    
    private IncreaseViewCountResponseDTO(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    }

    public static ResponseEntity<IncreaseViewCountResponseDTO> success() {
        IncreaseViewCountResponseDTO result = new IncreaseViewCountResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDTO> noExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
