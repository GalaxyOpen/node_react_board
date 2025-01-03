package com.lkh.board_back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lkh.board_back.dto.request.auth.SignUpRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")

// DataBase의 Table을 보고 진행
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String addressDetail;
    private String profileImage;
    private boolean agreedPersonal; 

    public UserEntity(SignUpRequestDTO dto) {
        this.email = dto.getEmail();
        this.password= dto.getPassword();
        this.nickname= dto.getNickname();
        this.telNumber= dto.getTelNumber();
        this.address= dto.getAddress();
        this.addressDetail= dto.getAddressDetail();
        this.agreedPersonal=dto.getAgreedPersonal();
    }
}
