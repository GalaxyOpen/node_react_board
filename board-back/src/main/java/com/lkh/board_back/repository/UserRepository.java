package com.lkh.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lkh.board_back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    //Query Method(JPA 특성) : 아래 구문에서는 이메일이 UserEntity에서 존재하는지를 찾는 것이다. 
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickName);
    boolean existsByTelNumber(String telNumber);
}
