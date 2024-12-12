package com.lkh.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lkh.board_back.dto.request.auth.SignInRequestDTO;
import com.lkh.board_back.dto.request.auth.SignInResponseDTO;
import com.lkh.board_back.dto.request.auth.SignUpRequestDTO;
import com.lkh.board_back.dto.response.ResponseDTO;
import com.lkh.board_back.dto.response.auth.SignUpResponseDTO;
import com.lkh.board_back.entity.UserEntity;
import com.lkh.board_back.provider.JwtProvider;
import com.lkh.board_back.repository.UserRepository;
import com.lkh.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    // 필드를 통한 의존성 주입을 보다 안전하게 하는 방법
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto) {
        
        try{
        
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return SignUpResponseDTO.duplicateEmail();
            
            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return SignUpResponseDTO.duplicateNickName();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber) return SignUpResponseDTO.duplicateTelNumber();           
            // 비밀번호는 암호화를 해서 넣어야 한다. 아래 코드 세줄 처럼 쓰면 코드를 암호화하여 들어가게 된다.
            String password = dto.getPassword();
            String encodedPassword =passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity); // userEntity를 통해 데이터베이스에다 저장까지 완료가 됨. 
            
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return SignUpResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDTO> singIn(SignInRequestDTO dto) {

        String token = null;

        try {

            String email =dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return SignInResponseDTO.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDTO.signInFailed();

            token = jwtProvider.create(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SignInResponseDTO.success(token);
    }
}