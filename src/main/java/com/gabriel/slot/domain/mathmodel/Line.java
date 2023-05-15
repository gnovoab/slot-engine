package com.gabriel.slot.domain.mathmodel;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlList;

import java.util.List;

/**
 * Class that represents a line
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Line {

    //Fields
    @XmlAttribute(required=true)
    private Integer id;

    @XmlList
    @XmlAttribute (required=true)
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
