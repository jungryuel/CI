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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


//    @ExtendWith(MockitoExtension.class)
@SpringBootTest
class BoardServiceImplTest {
//    @Autowired
//    private BoardService boardService;
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    void deleteById() {
////        given
//        Board board = boardRepository.save(new Board(null, "test", "test"));
//        Long id = board.getId();
////        when
//        boardService.deleteBoard(id);
////        then
//        assertEquals(0, boardRepository.findAll().size());
//    }
//
//    @Test
//    void deleteByIdFail() {
////        given
//        Long id = 125L;
////        when & then
//        assertThrows(IllegalArgumentException.class,() ->{ //실행과 동시에 에러를 던진다
//            boardService.deleteBoard(id);
//        });
//    }
//}


    @Mock //가짜 데이터
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
        assertThrows(IllegalArgumentException.class, () -> boardService.getBoard(1L));
        Mockito.verify(boardRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    void deleteById() {
        Long id = 1L;
        BDDMockito.given(boardRepository.existsById(id)).willReturn(true);
        BDDMockito.doNothing().when(boardRepository).deleteById(id);
        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.of(new Board(1L, null, null)));
        //when
        boardService.deleteBoard(id);
        //then

    }

    @Test
    void deleteByIdFail() {
        Long id = 5000L;
        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.of(new Board(1L, null, null)));

        //when&then
        assertThrows(IllegalArgumentException.class,()->{
            boardService.deleteBoard(id);
        });
    }
    @Test
    void getAll() {

        BDDMockito.given(boardRepository.findAll()).willReturn(
                List.of(new Board(1L, "test", "test"), new Board(2L, "test", "test")));

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
