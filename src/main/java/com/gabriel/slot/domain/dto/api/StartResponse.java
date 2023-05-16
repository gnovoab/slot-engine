package com.gabriel.slot.domain.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabriel.slot.domain.model.game.SlotGame;
import com.gabriel.slot.domain.model.mathmodel.Line;
import com.gabriel.slot.domain.model.mathmodel.ReelSet;
import com.gabriel.slot.domain.model.mathmodel.WinLineSet;

import java.util.List;

/**
 * Payload Response for Start endpoint
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartResponse {

    private SlotGame gameSettings;

    private ReelSet reelSet;

    private List<Line> lines;

    private WinLineSet winLineSet;



    //Getters and Setters


    public SlotGame getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(SlotGame gameSettings) {
        this.gameSettings = gameSettings;
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
