package com.lkh.board_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lkh.board_back.dto.request.board.PatchBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostCommentRequestDTO;
import com.lkh.board_back.dto.response.board.DeleteBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetCommentListResponseDTO;
import com.lkh.board_back.dto.response.board.GetLatestBoardListResponseDTO;
import com.lkh.board_back.dto.response.board.GetTop3BoardListResponseDTO;
import com.lkh.board_back.dto.response.board.GetFavoriteListResponseDTO;
import com.lkh.board_back.dto.response.board.IncreaseViewCountResponseDTO;
import com.lkh.board_back.dto.response.board.PatchBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostCommentResponseDTO;
import com.lkh.board_back.dto.response.board.PutFavoriteResponseDTO;
import com.lkh.board_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDTO> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
        ) {
        ResponseEntity<? super GetBoardResponseDTO> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoritelist(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetFavoriteListResponseDTO> response = boardService.getFavoriteList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetCommentListResponseDTO> response = boardService.getCommentList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super IncreaseViewCountResponseDTO> response = boardService.increaseViewCount(boardNumber);
        return response;
    }

    @GetMapping("latest-list")
    public ResponseEntity<? super GetLatestBoardListResponseDTO> getLatestBoardList() {
        ResponseEntity<? super GetLatestBoardListResponseDTO> response = boardService.getLatestBoardList();
        return response;
    }

    @GetMapping("/top-3")
    public ResponseEntity<? super GetTop3BoardListResponseDTO> getTop3BoardList() {
        ResponseEntity<? super GetTop3BoardListResponseDTO> response = boardService.getTop3BoardList();
        return response;
    }
    
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDTO> postBoard(
        @RequestBody @Valid PostBoardRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(requestBody, email);
        return response;
    }

    @PostMapping("/{boardNumber}/comment")
    public ResponseEntity<? super PostCommentResponseDTO> postComment(
        @RequestBody @Valid PostCommentRequestDTO requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostCommentResponseDTO> response = boardService.postComment(requestBody, boardNumber, email);
        return response;
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PutFavoriteResponseDTO> response = boardService.putFavorite(boardNumber, email);
        return response;
    }

    @PatchMapping("/{boardNumber}")
    public ResponseEntity<? super PatchBoardResponseDTO> patchBoard(
        @RequestBody @Valid PatchBoardRequestDTO requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchBoardResponseDTO> response = boardService.patchBoard(requestBody, boardNumber, email);
        return response;
    }

    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String email
    ) { 
        ResponseEntity<? super DeleteBoardResponseDTO> response = boardService.deleteBoard(boardNumber, email);
        return response;
    }
}
