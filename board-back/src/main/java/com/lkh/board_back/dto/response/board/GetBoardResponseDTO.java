package com.lkh.board_back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.entity.ImageEntity;
import com.lkh.board_back.repository.resultSet.GetBoardResultSet;

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
    private GetBoardResponseDTO(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS,  ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity  imageEntity:imageEntities){
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardNumber = resultSet.getBoardNumber();
        this.title =resultSet.getTitle();
        this.content=resultSet.getContent();
        this.boardImageList = boardImageList;
        this.writeDateTime = resultSet.getWriteDateTime();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProifileImage = resultSet.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDTO> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        GetBoardResponseDTO result = new GetBoardResponseDTO(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // error 
    public static ResponseEntity<ResponseDTO> noExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
