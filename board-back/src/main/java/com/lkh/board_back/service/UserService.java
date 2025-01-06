package com.lkh.board_back.service;

import org.springframework.http.ResponseEntity;

import com.lkh.board_back.dto.response.user.GetUserResponseDTO;
import com.lkh.board_back.dto.request.user.PatchNicknameRequestDTO;
import com.lkh.board_back.dto.request.user.PatchProfileImageRequestDTO;
import com.lkh.board_back.dto.response.user.GetSignInUserResponseDTO;
import com.lkh.board_back.dto.response.user.PatchNicknameResponseDTO;
import com.lkh.board_back.dto.response.user.PatchProfileImageResponseDTO;

public interface UserService {
    ResponseEntity<? super GetUserResponseDTO> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email); // ? super가 부모 요소까지 가지고 오는 것. 
    ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(PatchNicknameRequestDTO DTO, String email);
    ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(PatchProfileImageRequestDTO DTO, String email);
}
