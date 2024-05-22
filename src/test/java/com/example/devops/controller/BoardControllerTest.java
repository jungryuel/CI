package com.example.devops.controller;

import com.example.devops.Service.BoardService;
import com.example.devops.Service.BoardServiceImpl;
import com.example.devops.domain.Board;
import com.example.devops.dto.BoardResponse;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @MockBean
    private BoardService boardService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void writePost() {
    }

    @Test
    void getAll() throws Exception {
        //given
        //boardService.getAll() list[3]
        //TDD = 테스트 주도 개발 / DDD 도메인 주도 설계
        //BDD = Business driven design
        //Monolithic 하나로 묶어서 개발
        //MSA
        //EDA 이벤트 주도

        BDDMockito.given(boardService.getAllBoard())
                .willReturn(List.of(
                        new BoardResponse(1L ,"test1" ,"test1"),
                        new BoardResponse(2L ,"test2" ,"test2"),
                        new BoardResponse(3L ,"test3" ,"test3")
                ));
        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boards"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id").value(1)
                ) //검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.status().isOk()) //상태코드 무조건 써야 함
                .andDo(MockMvcResultHandlers.print()); //하는거

    }

    @Test
    void getBoard() throws Exception {
        BDDMockito.given(boardService.getBoard(1L))
                .willReturn(new Board(1L, "test1", "test2"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/boards/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 상태코드 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) // id 필드 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test1")) // name 필드 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("test2")) // description 필드 검증
                .andDo(MockMvcResultHandlers.print()); // 결과 출력
    }


    @Test
    void deleteBoard() {
    }
}