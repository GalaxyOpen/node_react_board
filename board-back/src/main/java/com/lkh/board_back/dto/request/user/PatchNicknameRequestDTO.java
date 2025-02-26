package com.lkh.board_back.dto.request.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchNicknameRequestDTO {

    @NotBlank
    private String nickname;
    
}
