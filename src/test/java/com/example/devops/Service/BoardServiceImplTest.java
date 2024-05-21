package com.example.devops.Service;

import com.example.devops.domain.Board;
import com.example.devops.dto.BoardRequest;
import com.example.devops.dto.BoardResponse;
import com.example.devops.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


    @ExtendWith(MockitoExtension.class)
    class BoardServiceImplTest {
        @Mock
        private BoardRepository boardRepository;
        @InjectMocks
        private BoardServiceImpl boardService;

        @Test
        void getById() {
            Board board = new Board(1L, "test", "test");
            BDDMockito.given(boardRepository.findById(1L))
                    .willReturn(Optional.of(board));


            Board byId = boardService.getBoard(1L);

//        행위 검증
            Mockito.verify(boardRepository, Mockito.times(1)).findById(1L);
//        상태 검증
            assertEquals("test", byId.getName());
            assertEquals("test", byId.getText());
            assertNotNull(byId.getId());
        }
        @Test
        void getByIdNotExist() {
            BDDMockito.given(boardRepository.findById(1L)).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, ()-> boardService.getBoard(1L));
            Mockito.verify(boardRepository,Mockito.times(1)).findById(1L);

        }

        @Test
        void getAll() {

            BDDMockito.given(boardRepository.findAll()).willReturn(
                    List.of(new Board(1L,"test", "test"),new Board(2L,"test", "test")));

            List<BoardResponse> all = boardService.getAllBoard();

            assertEquals(2, all.size());
            assertEquals("test", all.get(1).getName());
            Mockito.verify(boardRepository).findAll();
        }

        @Test
        void saveBoard() {
            BoardRequest request = new BoardRequest("test", "test");
            Board entity = request.toEntity();
            BDDMockito.given(boardRepository.save(entity))
                    .willReturn(entity);

            boardService.writePost(request);

            Mockito.verify(boardRepository, Mockito.times(1)).save(entity);
        }
    }
