package com.gabriel.slot.domain.model;

//Imports
import java.util.Map;

/**
 * Class that represents a spin result
 */
public class SpinResult {

    //Fields
    private String transactionId;
    private Map<Integer, Integer> lines;
    private Integer totalWin;


    //Getters and Setters


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Map<Integer, Integer> getLines() {
        return lines;
    }

    public void setLines(Map<Integer, Integer> lines) {
        this.lines = lines;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
    }
}
