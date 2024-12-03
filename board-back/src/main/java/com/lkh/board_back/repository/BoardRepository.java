package com.lkh.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lkh.board_back.entity.BoardEntity;
import com.lkh.board_back.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    
    BoardEntity findByBoardNumber(Integer boardNumber);

    @Query( // 게시물 상세 불러오기 쿼리문. 단순히 boardEntity만 가져올 수 없으므로(UserEntity도 있기 때문) nativeQuery문을 작성해준다. 
        value = 
        "SELECT " +
        "B.board_number as boardNumber, " +
        "B.title as title, " +
        "B.content as content, " +
        "B.write_datetime as writeDatetime, " +
        "B.writer_email as writerEmail, " +
        "U.nickname as writerNickname, " +
        "u.profile_image as writerProfileImage " +
        "from board as B " +
        "INNER JOIN user as U " +
        "ON B.writer_email = U.email " +
        "WHERE board_number = ?1",
        nativeQuery = true
    )
    GetBoardResultSet getBoard(Integer boardNumber);
}
