package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostCommentRequestDTO;
import com.lkh.board_back.dto.response.board.GetBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetFavoriteListResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostCommentResponseDTO;
import com.lkh.board_back.dto.response.board.PutFavoriteResponseDTO;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber); // 게시물 상세의 URL을 확인해보면 pathvariable로 boardNumber를 받아오고 있기 때문. 
    ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO DTO, String email);
    ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO DTO, Integer boardNumber, String email);
    ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNumber, String email);

    
}
