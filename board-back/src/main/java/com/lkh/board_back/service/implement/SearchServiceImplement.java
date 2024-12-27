package com.lkh.board_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.dto.response.search.GetPopularListResponseDTO;
import com.lkh.board_back.repository.SearchLogRepository;
import com.lkh.board_back.repository.resultSet.GetPopularListResultSet;
import com.lkh.board_back.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService{

    private final SearchLogRepository searchLogRepository;
    
    @Override
    public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getPopularList();

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetPopularListResponseDTO.success(resultSets);
    }
    
}
