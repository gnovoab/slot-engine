package com.gabriel.slot.domain.mathmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlList;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a reel
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reel {

    //Fields
    @XmlAttribute(required=true)
    private Integer id;

    @XmlList
    @XmlAttribute
    private List<String> symbols;

    /**
     * Constructor
     */
    public Reel(){
        this.symbols=new ArrayList<>();
    }


    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

}
