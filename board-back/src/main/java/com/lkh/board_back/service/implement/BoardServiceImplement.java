package com.lkh.board_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lkh.board_back.dto.request.board.PostBoardRequestDTO;
import com.lkh.board_back.dto.request.board.PostCommentRequestDTO;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.dto.response.board.DeleteBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetBoardResponseDTO;
import com.lkh.board_back.dto.response.board.GetCommentListResponseDTO;
import com.lkh.board_back.dto.response.board.GetFavoriteListResponseDTO;
import com.lkh.board_back.dto.response.board.IncreaseViewCountResponseDTO;
import com.lkh.board_back.dto.response.board.PostBoardResponseDTO;
import com.lkh.board_back.dto.response.board.PostCommentResponseDTO;
import com.lkh.board_back.dto.response.board.PutFavoriteResponseDTO;
import com.lkh.board_back.entity.BoardEntity;
import com.lkh.board_back.entity.CommentEntity;
import com.lkh.board_back.entity.FavoriteEntity;
import com.lkh.board_back.entity.ImageEntity;
import com.lkh.board_back.repository.BoardRepository;
import com.lkh.board_back.repository.CommentRepository;
import com.lkh.board_back.repository.FavoriteRepository;
import com.lkh.board_back.repository.ImageRepository;
import com.lkh.board_back.repository.UserRepository;
import com.lkh.board_back.repository.resultSet.GetBoardResultSet;
import com.lkh.board_back.repository.resultSet.GetCommentListResultSet;
import com.lkh.board_back.repository.resultSet.GetFavoriteListResultSet;
import com.lkh.board_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 아래 구문에선 받아온 이메일이 존재하는지 체크할 예정임.
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;


    @Override
    public ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {

            resultSet = boardRepository.getBoard(boardNumber);
            if(resultSet == null) return GetBoardResponseDTO.noExistBoard();

            imageEntities = imageRepository.findByBoardNumber(boardNumber);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return GetBoardResponseDTO.success(resultSet, imageEntities);
    }    
    @Override
    public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNumber) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try{

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetFavoriteListResponseDTO.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardNumber);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetFavoriteListResponseDTO.success(resultSets);
    }
    
    @Override
    public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNumber) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if (!existedBoard) return GetCommentListResponseDTO.noExiestBoard();

            resultSets = commentRepository.getCommentList(boardNumber);

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetCommentListResponseDTO.success(resultSets);
    }

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

    @Override
    public ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO DTO, Integer boardNumber, String email) {

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PostCommentResponseDTO.noExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PostCommentResponseDTO.noExistUser();

            CommentEntity commentEntity = new CommentEntity(DTO, boardNumber, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return PostCommentResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNumber, String email) {
        
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDTO.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PutFavoriteResponseDTO.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
            if(favoriteEntity == null){
                favoriteEntity = new FavoriteEntity(email, boardNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }else{
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);


        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return PutFavoriteResponseDTO.success();
    }
    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer boardNumber) {
        
        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return IncreaseViewCountResponseDTO.noExistBoard();

            boardEntity.increaseViewCount(); // 조회수 증가 메소드 
            boardRepository.save(boardEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return IncreaseViewCountResponseDTO.success();
    }
    @Override
    public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNumber, String email) {
        
        try{

            Boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return DeleteBoardResponseDTO.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return DeleteBoardResponseDTO.noExistBoard();

            String writerEmail = boardEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeleteBoardResponseDTO.noPermission();

            imageRepository.deleteByBoardNumber(boardNumber);
            commentRepository.deleteByBoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber(boardNumber);
                
            boardRepository.delete(boardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return DeleteBoardResponseDTO.success();
    }

}
