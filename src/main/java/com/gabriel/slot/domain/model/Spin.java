
//Namespace
package com.gabriel.slot.domain.model;

//Imports
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Class that represents a spin
 */
public class Spin {

    //Fields
    @Min(1)
    @NotNull
    private Short stake;
    @Min(1)
    @NotNull
    private Integer numLines;
    private Boolean autoSpin;


    //Getters and Setters


    public Short getStake() {
        return stake;
    }

    public void setStake(Short stake) {
        this.stake = stake;
    }

    public Integer getNumLines() {
        return numLines;
    }

    public void setNumLines(Integer numLines) {
        this.numLines = numLines;
    }

    public Boolean getAutoSpin() {
        return autoSpin;
    }

    public void setAutoSpin(Boolean autoSpin) {
        this.autoSpin = autoSpin;
    }
}
