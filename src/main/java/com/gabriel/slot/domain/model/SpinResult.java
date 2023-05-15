package com.gabriel.slot.domain.model;

//Imports
import java.util.Map;

/**
 * Class that represents a spin result
 */
public class SpinResult {

    //Fields
    private Long transactionId;
    private Map<Integer, Boolean> lines;
    private Integer totalWin;


    //Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Map<Integer, Boolean> getLines() {
        return lines;
    }

    public void setLines(Map<Integer, Boolean> lines) {
        this.lines = lines;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
    }

}
