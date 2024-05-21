package com.example.devops.dto;

import com.example.devops.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String name;
    private String text;


    public Board toEntity (){
        return Board.builder()
                .name(name)
                .text(text)
                .build();
    }
}
