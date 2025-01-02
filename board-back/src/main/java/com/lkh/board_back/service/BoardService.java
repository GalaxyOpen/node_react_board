package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.request.board.PatchBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostCommentRequestDTO;
import com.lkh.board_back.dto.response.board.GetCommentListResponseDTO;
import com.lkh.board_back.dto.response.board.DeleteBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetFavoriteListResponseDTO;
import com.lkh.board_back.dto.response.board.GetLatestBoardListResponseDTO;
import com.lkh.board_back.dto.response.board.GetTop3BoardListResponseDTO;
import com.lkh.board_back.dto.response.board.GetSearchBoardListResponseDTO;
import com.lkh.board_back.dto.response.board.GetUserBoardListResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostCommentResponseDTO;
import com.lkh.board_back.dto.response.board.PutFavoriteResponseDTO;
import com.lkh.board_back.dto.response.board.IncreaseViewCountResponseDTO;
import com.lkh.board_back.dto.response.board.PatchBoardResponseDTO;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber); // 게시물 상세의 URL을 확인해보면 pathvariable로 boardNumber를 받아오고 있기 때문. 
    ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDTO> getLatestBoardList();
    ResponseEntity<? super GetTop3BoardListResponseDTO> getTop3BoardList();
    ResponseEntity<? super GetSearchBoardListResponseDTO> getSearchBoardList(String searchword, String preSearchWord);
    ResponseEntity<? super GetUserBoardListResponseDTO> getUserBoardList(String email);
    ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO DTO, String email);
    ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO DTO, Integer boardNumber, String email);
    ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNumber, String email);
    ResponseEntity<? super PatchBoardResponseDTO> patchBoard(PatchBoardRequestDTO DTO, Integer boardNumber, String email);
    ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer boardNumber);
    ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNumber, String email);
}
