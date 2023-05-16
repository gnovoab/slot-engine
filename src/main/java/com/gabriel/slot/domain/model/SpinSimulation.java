package com.gabriel.slot.domain.model;

//Imports
import com.gabriel.slot.domain.dto.object.ReelSetPositions;

/**
 * Class that represents a Spin simulation
 */
public class SpinSimulation {

    private String[][] board;
    private ReelSetPositions reelSetPositions;


    //Getters and Setters

    public String[][] getBoard() {
        return board.clone();
    }

    public void setBoard(String[]... board) {
        this.board = board.clone();
    }

    public ReelSetPositions getReelSetPositions() {
        return reelSetPositions;
    }

    public void setReelSetPositions(ReelSetPositions reelSetPositions) {
        this.reelSetPositions = reelSetPositions;
    }
}
