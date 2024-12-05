package com.lkh.board_back.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDTO {

    @NotBlank // int나 boolean 같은 건 못씀.
    private String comment;
    
}
