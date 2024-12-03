package com.lkh.board_back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int boardNumber;
    private String title;
    private String content;
    private String writeDatetime;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writerEmail;

    public BoardEntity(PostBoardRequestDTO DTO, String email){

        Date now = Date.from(Instant.now());
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDateTime = SimpleDateFormat.format(now);

        this.title = DTO.getTitle();
        this.content = DTO.getContent();
        this.writeDatetime = writeDateTime;
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
        this.writerEmail = email;
    }
    // 조회수 증가로직 
    public void increaseViewCount() {
        this.viewCount++;
    }
    // 좋아요 눌렀을 때 좋아요 증가로직 
    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }
    // 좋아요가 이미 있는 좋아요 눌렀을 때 좋아요 감소로직 
    public void decreaseFavoriteCount(){
        this.favoriteCount--;
    }
    
}
