package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Reel;
import com.gabriel.slot.domain.model.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardServiceTest {

    //Fields
    @Autowired
    private transient BoardService boardService;

    @Test
    void transposeReelTest() {

        //Create reels
        Reel reel1 = new Reel();
        Reel reel2 = new Reel();
        Reel reel3 = new Reel();
        Symbol[] symbolArray = { Symbol.BAR, Symbol.BELL, Symbol.CHERRY, Symbol.ORANGE, Symbol.LEMON, Symbol.PLUM };

        //Setup reels
        reel1.setId(1);
        reel1.setSymbols(Arrays.asList(symbolArray));

        reel2.setId(2);
        reel2.setSymbols(Arrays.asList(symbolArray));

        reel3.setId(3);
        reel3.setSymbols(Arrays.asList(symbolArray));

        //Create the board
        Symbol[][] board = new Symbol[3][3];

        //Simulate first reel
        board =  boardService.transposeReel(board, 1, reel1.getSymbols(), 3);
        Assertions.assertEquals(Symbol.CHERRY, board[0][0]);
        Assertions.assertEquals(Symbol.ORANGE, board[1][0]);
        Assertions.assertEquals(Symbol.LEMON, board[2][0]);

        //Simulate second reel on first item
        board =  boardService.transposeReel(board, 2, reel2.getSymbols(), 0);
        Assertions.assertEquals(Symbol.PLUM, board[0][1]);
        Assertions.assertEquals(Symbol.BAR, board[1][1]);
        Assertions.assertEquals(Symbol.BELL, board[2][1]);

        //Simulate third reel on last item
        board =  boardService.transposeReel(board, 3, reel3.getSymbols(), 5);
        Assertions.assertEquals(Symbol.LEMON, board[0][2]);
        Assertions.assertEquals(Symbol.PLUM, board[1][2]);
        Assertions.assertEquals(Symbol.BAR, board[2][2]);
    }
}