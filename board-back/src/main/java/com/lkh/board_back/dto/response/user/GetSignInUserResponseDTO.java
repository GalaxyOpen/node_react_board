package com.lkh.board_back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.entity.UserEntity;

import lombok.Getter;

@Getter // 정보를 가지고 와야하기 때문에 Getter만 있어도 됨. 
public class GetSignInUserResponseDTO extends ResponseDTO {

    private String email;
    private String nickname;
    private String profileImage;

    private GetSignInUserResponseDTO(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();

    }
    // 관련 자료 : userRepository.java에 기입되어 있음. 
    public static ResponseEntity<GetSignInUserResponseDTO> success(UserEntity userEntity){
        GetSignInUserResponseDTO result = new GetSignInUserResponseDTO(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result); // 유저의 로그인 성공
    } 

    public static ResponseEntity<ResponseDTO> notExistUser(){
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result); // 유저 없음
    } // 이후 Service 생성해야함. 

}