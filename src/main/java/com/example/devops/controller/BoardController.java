package com.example.devops.controller;

import com.example.devops.Service.BoardService;
import com.example.devops.domain.Board;
import com.example.devops.dto.BoardRequest;
import com.example.devops.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void writePost(@RequestBody BoardRequest boardRequest){
        boardService.writePost(boardRequest);
    }

    @GetMapping
    public List<BoardResponse> getAll(){
      return boardService.getAllBoard();
    }

    @GetMapping("{id}")
    public Board getBoard(@PathVariable(name = "id")Long id ){
        return boardService.getBoard(id);
    }

    @DeleteMapping("{id}")
    public void deleteBoard(@PathVariable(name = "id") Long id){
        boardService.deleteBoard(id);
    }
}
