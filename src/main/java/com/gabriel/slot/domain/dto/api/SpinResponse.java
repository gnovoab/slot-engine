package com.gabriel.slot.domain.dto.api;

//Imports
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;

/**
 * Representation of a Payload after a spin execution
 */
public class SpinResponse {

    //Fields
    private SpinSimulation spinSimulation;
    private SpinResult spinResult;



    //Getters and Setters

    public SpinResult getSpinResult() {
        return spinResult;
    }

    public void setSpinResult(SpinResult spinResult) {
        this.spinResult = spinResult;
    }


    public SpinSimulation getSpinSimulation() {
        return spinSimulation;
    }

    public void setSpinSimulation(SpinSimulation spinSimulation) {
        this.spinSimulation = spinSimulation;
    }
}
