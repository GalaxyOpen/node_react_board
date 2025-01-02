package com.lkh.board_back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lkh.board_back.common.ResponseCode;
import com.lkh.board_back.common.ResponseMessage;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDTO extends ResponseDTO {
    
    private String email;
    private String nickname;
    private String profileImage;

    private GetUserResponseDTO(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetUserResponseDTO> success(UserEntity userEntity){
        GetUserResponseDTO result = new GetUserResponseDTO(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExistUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
