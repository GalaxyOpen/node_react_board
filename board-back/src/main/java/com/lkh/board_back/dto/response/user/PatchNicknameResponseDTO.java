package com.lkh.board_back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class PatchNicknameResponseDTO extends ResponseDTO {

    private PatchNicknameResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchNicknameResponseDTO> success() {
        PatchNicknameResponseDTO result = new PatchNicknameResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExistUser () {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDTO> duplicateNickname () {
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}