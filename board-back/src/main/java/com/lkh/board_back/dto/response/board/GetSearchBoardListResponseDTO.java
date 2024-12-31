package com.lkh.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.object.BoardListItem;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetSearchBoardListResponseDTO extends ResponseDTO {
    
    private List<BoardListItem> searchList; 

    private GetSearchBoardListResponseDTO(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetSearchBoardListResponseDTO> success(List<BoardListViewEntity> boardListViewEntities) {
        GetSearchBoardListResponseDTO result = new GetSearchBoardListResponseDTO(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
