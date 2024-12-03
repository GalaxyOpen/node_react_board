package com.lkh.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class GetBoardResponseDTO extends ResponseDTO {

    private int boardNumber; 
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDateTime;
    private String writerEmail;
    private String writerNickname;
    private String writerProifileImage;
    // success
    private GetBoardResponseDTO() {
        super(ResponseCode.SUCCESS,  ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<GetBoardResponseDTO> success() {
        GetBoardResponseDTO result = new GetBoardResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // error 
    public static ResponseEntity<ResponseDTO> noExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
