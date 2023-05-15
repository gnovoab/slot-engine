package com.gabriel.slot.domain.model;


import java.util.List;

/**
 * Class that represents a reel
 */
public class Reel {

    //Fields
    private Integer id;

    private List<Symbol> symbols;




    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
}
