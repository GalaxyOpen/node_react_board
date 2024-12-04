package com.lkh.board_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.object.FavoriteListItem;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDTO extends ResponseDTO{
    
    private List<FavoriteListItem> favoriteList; 

    private GetFavoriteListResponseDTO(List<GetFavoriteListResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteList = FavoriteListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetFavoriteListResponseDTO> success(List<GetFavoriteListResultSet> resultSets) {
        GetFavoriteListResponseDTO result = new GetFavoriteListResponseDTO(resultSets); 
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
