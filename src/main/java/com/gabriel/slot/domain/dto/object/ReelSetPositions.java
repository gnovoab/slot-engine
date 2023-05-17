package com.gabriel.slot.domain.dto.object;


import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a reel
 */
public class ReelSetPositions {

    //Fields
    private List<ReelPosition> reelsPositions = new ArrayList<>();



    //Getters and Setters
    public List<ReelPosition> getReelsPositions() {
        return reelsPositions;
    }

    public void setReelsPositions(List<ReelPosition> reelsPositions) {
        this.reelsPositions = reelsPositions;
    }
}
