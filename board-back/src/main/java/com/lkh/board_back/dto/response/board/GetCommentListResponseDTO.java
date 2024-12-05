package com.lkh.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.object.CommentListItem;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDTO extends ResponseDTO{
    
    private List<CommentListItem> commentList;

    private GetCommentListResponseDTO (List<GetCommentListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetCommentListResponseDTO> success(List<GetCommentListResultSet> resultSets) {
        GetCommentListResponseDTO result = new GetCommentListResponseDTO(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExiestBoard () {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
