package com.gabriel.slot.domain.model;

import com.gabriel.slot.domain.mathmodel.Line;
import com.gabriel.slot.domain.mathmodel.ReelSet;
import com.gabriel.slot.domain.mathmodel.WinInfo;
import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Class that represents a math model
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MathModel {

    //Fields
    @XmlAttribute(required=true)
    private Integer id;

    @XmlAttribute(required=true)
    private String name;

    @XmlAttribute
    private Integer modelRtp;

    @XmlAttribute
    private List<String> symbols;

    @XmlElement (name="reelset")
    private List<ReelSet> reelSets;

    @XmlElementWrapper (name="lines")
    @XmlElement (name="line")
    private List<Line> lines;

    @XmlElement (name="win")
    private WinInfo winInfo;



    //Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModelRtp() {
        return modelRtp;
    }

    public void setModelRtp(Integer modelRtp) {
        this.modelRtp = modelRtp;
    }

    public List<ReelSet> getReelSets() {
        return reelSets;
    }

    public void setReelSets(List<ReelSet> reelSets) {
        this.reelSets = reelSets;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public WinInfo getWinInfo() {
        return winInfo;
    }

    public void setWinInfo(WinInfo winInfo) {
        this.winInfo = winInfo;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }
}
