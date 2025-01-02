package com.lkh.board_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lkh.board_back.dto.response.user.GetSignInUserResponseDTO;
import com.lkh.board_back.dto.response.user.GetUserResponseDTO;
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
    
}
