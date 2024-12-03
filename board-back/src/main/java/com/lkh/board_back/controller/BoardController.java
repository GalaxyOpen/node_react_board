package com.lkh.board_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.response.board.GetBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
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

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDTO> postBoard(
        @RequestBody @Valid PostBoardRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(requestBody, email);
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

}
