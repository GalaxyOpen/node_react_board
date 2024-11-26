package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.response.user.GetSignInUserResponseDTO;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email); // ? super가 부모 요소까지 가지고 오는 것. 
    // 이후 service > implement 에 파일을 하나 또 만듬. 
}
