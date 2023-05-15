package com.gabriel.slot.domain.object;

/**
 * Class that represents a reel details for a position
 */
public class ReelPosition {

    //Fields
    private Integer reelNumber;
    private Integer position;
    private String symbol;


    //Getters and Setters

    public Integer getReelNumber() {
        return reelNumber;
    }

    public void setReelNumber(Integer reelNumber) {
        this.reelNumber = reelNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
