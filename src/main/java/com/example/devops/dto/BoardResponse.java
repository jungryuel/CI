package com.example.devops.dto;

import com.example.devops.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {
    String name;
    String text;
    Long id;
    public static BoardResponse from(Board boards){
        return BoardResponse.builder()
                .id(boards.getId())
                .name(boards.getName())
                .text(boards.getText())
                .build();
    }
}
