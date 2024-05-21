package com.example.devops.Service;

import com.example.devops.domain.Board;
import com.example.devops.dto.BoardRequest;
import com.example.devops.dto.BoardResponse;
import com.example.devops.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public void writePost(BoardRequest boardRequest) {
        boardRepository.save(boardRequest.toEntity());
    }

    @Override
    public List<BoardResponse> getAllBoard() {
       List<Board> boards = boardRepository.findAll();
       return boards.stream()
               .map(BoardResponse:: from)
               .toList();
    }
//
    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("글 없음"));
    }

}
