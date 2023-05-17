package com.gabriel.slot.service;

import com.gabriel.slot.domain.dto.object.ReelSetPositions;
import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.domain.model.mathmodel.Line;
import com.gabriel.slot.domain.model.mathmodel.Reel;
import com.gabriel.slot.domain.model.mathmodel.WinLine;
import com.gabriel.slot.utils.JsonUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpinServiceTest {

    //Fields
    @Autowired
    private transient SpinService spinService;

    @Test
    void simulateSpin() {

        //Create Variables
        Reel reel1 = new Reel();
        reel1.setId(1);
        reel1.setSymbols(Lists.newArrayList("A", "B", "C", "D", "E"));

        Reel reel2 = new Reel();
        reel2.setId(2);
        reel2.setSymbols(Lists.newArrayList("A", "B", "C", "D", "E"));

        Reel reel3 = new Reel();
        reel3.setId(3);
        reel3.setSymbols(Lists.newArrayList("A", "B", "C", "D", "E"));

        Reel reel4 = new Reel();
        reel4.setId(4);
        reel4.setSymbols(Lists.newArrayList("A", "B", "C", "D", "E"));

        Reel reel5 = new Reel();
        reel5.setId(5);
        reel5.setSymbols(Lists.newArrayList("A", "B", "C", "D", "E"));


        Map<Integer, Reel> reels = new HashMap<>();
        reels.put(1,reel1);
        reels.put(2,reel2);
        reels.put(3,reel3);
        reels.put(4,reel4);
        reels.put(5,reel5);


        //Execute
        SpinSimulation spinSimulation = spinService.simulateSpin(reels);

        String[][] board = spinSimulation.getBoard();
        ReelSetPositions reelSetPositions = spinSimulation.getReelSetPositions();

        //Validate Board
        Assertions.assertTrue(board.length == 3);
        Assertions.assertTrue(board[0].length >= 3);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Assertions.assertNotNull(board[i][j]);
            }
        }

        //Validate positions
        Assertions.assertFalse(reelSetPositions.getReelsPositions().isEmpty());
        for (int i = 0; i < reelSetPositions.getReelsPositions().size() ; i++) {
            Assertions.assertEquals(i+1, reelSetPositions.getReelsPositions().get(i).getReelNumber());
            Assertions.assertNotNull(reelSetPositions.getReelsPositions().get(i).getSymbol());
            Assertions.assertNotEquals("", reelSetPositions.getReelsPositions().get(i).getSymbol());
            Assertions.assertTrue(reelSetPositions.getReelsPositions().get(i).getPosition() >= 0);
        }
    }


    @Test
    void processSpinTest() {
        //Create Board
        String[][] board = new String[3][5];


        //Fill last row with specific
        board[0][0] = "g";
        board[0][1] = "a";
        board[0][2] = "a";
        board[0][3] = "u";
        board[0][4] = "r";

        board[1][0] = "b";
        board[1][1] = "a";
        board[1][2] = "a";
        board[1][3] = "a";
        board[1][4] = "c";

        board[2][0] = "a";
        board[2][1] = "a";
        board[2][2] = "q";
        board[2][3] = "a";
        board[2][4] = "a";


        //Create Lines
        Line line1 = new Line();
        line1.setId(1);
        line1.setPositions(Lists.newArrayList((short)0, (short)0, (short)0, (short)0, (short)0));

        Line line2 = new Line();
        line2.setId(2);
        line2.setPositions(Lists.newArrayList((short)-1, (short)-1, (short)-1, (short)-1, (short)-1));

        Line line3 = new Line();
        line3.setId(3);
        line3.setPositions(Lists.newArrayList((short)1, (short)1, (short)1, (short)1, (short)1));

        List<Line> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        //Create WinLines

        WinLine winLine1 = new WinLine();
        winLine1.setSymbol("5a");
        winLine1.setValue(5000);

        WinLine winLine2 = new WinLine();
        winLine2.setSymbol("4a");
        winLine2.setValue(4000);

        WinLine winLine3 = new WinLine();
        winLine3.setSymbol("3a");
        winLine3.setValue(3000);

        WinLine winLine4 = new WinLine();
        winLine4.setSymbol("2a");
        winLine4.setValue(2000);

        List<WinLine> winLineSet = new ArrayList<>();
        winLineSet.add(winLine1);
        winLineSet.add(winLine2);
        winLineSet.add(winLine3);
        winLineSet.add(winLine4);

        Spin spin = new Spin();
        spin.setStake((short) 1);
        spin.setNumLines(3);

        SpinResult result = spinService.processSpin(board, lines, winLineSet, spin);

        Assertions.assertFalse(result.getTransactionId().isEmpty());
        Assertions.assertEquals(3, result.getLines().size());
        Assertions.assertEquals(3000, result.getLines().get(1));
        Assertions.assertEquals(2000, result.getLines().get(2));
        Assertions.assertEquals(4000, result.getLines().get(3));
        Assertions.assertEquals(9000, result.getTotalWin());
    }
}