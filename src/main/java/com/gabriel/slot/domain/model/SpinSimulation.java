package com.gabriel.slot.domain.model;

//Imports
import com.gabriel.slot.domain.object.ReelSetPositions;

/**
 * Class that represents a Spin simulation
 */
public class SpinSimulation {

    private Symbol[][] board;
    private ReelSetPositions reelSetPositions;


    //Getters and Setters

    public Symbol[][] getBoard() {
        return board.clone();
    }

    public void setBoard(Symbol[]... board) {
        this.board = board.clone();
    }

    public ReelSetPositions getReelSetPositions() {
        return reelSetPositions;
    }

    public void setReelSetPositions(ReelSetPositions reelSetPositions) {
        this.reelSetPositions = reelSetPositions;
    }
}
