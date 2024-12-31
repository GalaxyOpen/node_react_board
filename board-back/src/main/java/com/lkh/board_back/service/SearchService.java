package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;
import com.lkh.board_back.dto.response.search.GetPopularListResponseDTO;
import com.lkh.board_back.dto.response.search.GetRelationListResponseDTO;

public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDTO> getPopularList();
    ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord);
    
}
