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
public class GetTop3BoardListResponseDTO extends ResponseDTO {
    
    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDTO(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDTO> success(List<BoardListViewEntity> boardListViewEntities) {
        GetTop3BoardListResponseDTO result = new GetTop3BoardListResponseDTO(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
