package com.lkh.board_back.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lkh.board_back.dto.request.auth.SignInRequestDTO;
import com.lkh.board_back.dto.request.auth.SignInResponseDTO;
import com.lkh.board_back.dto.request.auth.SignUpRequestDTO;
import com.lkh.board_back.dto.response.auth.SignUpResponseDTO;
import com.lkh.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;
// 경고 : 컨트롤러에는 비지니스 로직이 적히면 안됨.
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDTO> signUp(
        @RequestBody @Valid SignUpRequestDTO requestBody
    ){
        ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDTO> signIn (
        @RequestBody @Valid SignInRequestDTO requestBody
    ) {
        ResponseEntity<? super SignInResponseDTO> response = authService.singIn(requestBody);
        return response;
    }
}
