package com.gabriel.slot.domain.model;


import java.util.List;

/**
 * Class that represents a Line
 */
public class Line {

    //Fields
    private Integer id;
    private List<Short> positions;


    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Short> getPositions() {
        return positions;
    }

    public void setPositions(List<Short> positions) {
        this.positions = positions;
    }
}
