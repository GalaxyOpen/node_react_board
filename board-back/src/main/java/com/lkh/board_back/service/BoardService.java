package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;

public interface BoardService {
    ResponseEntity<PostBoardResponseDTO> postBoard(PostBoardRequestDTO DTO, String email);

    
}
