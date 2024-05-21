package com.example.devops.Service;

import com.example.devops.domain.Board;
import com.example.devops.dto.BoardRequest;
import com.example.devops.dto.BoardResponse;

import java.util.List;

public interface BoardService {
    void writePost(BoardRequest boardRequest);

    List<BoardResponse> getAllBoard();

    void deleteBoard(Long id);

    Board getBoard(Long id);
}
