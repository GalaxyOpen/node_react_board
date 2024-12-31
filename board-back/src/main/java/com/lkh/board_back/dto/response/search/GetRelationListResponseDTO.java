package com.lkh.board_back.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class GetRelationListResponseDTO extends ResponseDTO {
    
    private List<String> relativeWordList;

    private GetRelationListResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        List<String> relativeWordList = new ArrayList<>();
        

        this.relativeWordList = relativeWordList;
    }

    public static ResponseEntity<GetRelationListResponseDTO> success() {
        GetRelationListResponseDTO result = new GetRelationListResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
