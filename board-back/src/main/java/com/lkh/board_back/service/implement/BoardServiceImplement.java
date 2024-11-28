package com.lkh.board_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
import com.lkh.board_back.entity.BoardEntity;
import com.lkh.board_back.entity.ImageEntity;
import com.lkh.board_back.repository.BoardRepository;
import com.lkh.board_back.repository.ImageRepository;
import com.lkh.board_back.repository.UserRepository;
import com.lkh.board_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 아래 구문에선 받아온 이메일이 존재하는지 체크할 예정임.
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    
    
    @Override
    public ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO DTO, String email) {

        try{
            
            boolean existEmail = userRepository.existsByEmail(email);
            if (!existEmail) return PostBoardResponseDTO.notExistUser();

            BoardEntity boardEntity = new BoardEntity(DTO, email);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<String> boardImageList = DTO.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities); // 여기서 save(imageEntity)를 써도 되긴 하지만 데이터베이스에 많은 양이 들어가기 때문에 saveAll이 좋다.

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return PostBoardResponseDTO.success();
    }
    
}
