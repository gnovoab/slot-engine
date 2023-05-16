package com.gabriel.slot.domain.api;

import com.gabriel.slot.domain.model.SlotGame;
import com.gabriel.slot.domain.model.mathmodel.Line;
import com.gabriel.slot.domain.model.mathmodel.ReelSet;
import com.gabriel.slot.domain.model.mathmodel.WinLineSet;

import java.util.List;

/**
 * Payload Response for Start endpoint
 */
public class StartResponse {

    private SlotGame slotParams;

    private ReelSet reelSet;

    private List<Line> lines;

    private WinLineSet winLineSet;



    //Getters and Setters
    public SlotGame getSlotParams() {
        return slotParams;
    }

    public void setSlotParams(SlotGame slotParams) {
        this.slotParams = slotParams;
    }

    public ReelSet getReelSet() {
        return reelSet;
    }

    public void setReelSet(ReelSet reelSet) {
        this.reelSet = reelSet;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public WinLineSet getWinLineSet() {
        return winLineSet;
    }

    public void setWinLineSet(WinLineSet winLineSet) {
        this.winLineSet = winLineSet;
    }
}
