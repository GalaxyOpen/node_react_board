package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.request.auth.SignUpRequestDTO;
import com.lkh.board_back.dto.response.auth.SignUpResponseDTO;

public interface AuthService {
    // 원래 개발할 때는 interface를 모두 깔아놓고 진행하는 것이 목적이나 공부를 하고 있으니 천천히 하나하나 진행함.
    ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto);
}