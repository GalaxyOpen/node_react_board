package com.lkh.board_back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lkh.board_back.dto.request.user.PatchNicknameRequestDTO;
import com.lkh.board_back.dto.request.user.PatchProfileImageRequestDTO;
import com.lkh.board_back.dto.response.user.GetSignInUserResponseDTO;
import com.lkh.board_back.dto.response.user.GetUserResponseDTO;
import com.lkh.board_back.dto.response.user.PatchNicknameResponseDTO;
import com.lkh.board_back.dto.response.user.PatchProfileImageResponseDTO;

import org.springframework.web.bind.annotation.RestController;

import com.lkh.board_back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponseDTO> getUser(
        @PathVariable("email") String email
    ){
        ResponseEntity<? super GetUserResponseDTO> response = userService.getUser(email);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetSignInUserResponseDTO> response = userService.getSignInUser(email);
        return response;
    }
    
    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDTO> patchNickname (
        @RequestBody @Valid PatchNicknameRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchNicknameResponseDTO> response = userService.patchNickname(requestBody, email);
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(
        @RequestBody @Valid PatchProfileImageRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchProfileImageResponseDTO> response = userService.patchProfileImage(requestBody, email);
        return response;
    }
}
