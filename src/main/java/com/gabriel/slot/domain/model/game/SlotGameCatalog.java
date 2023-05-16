package com.gabriel.slot.domain.model.game;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Class that represents a math model
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SlotGameCatalog {

    @XmlAttribute(required=true)
    private String name;

    @XmlElement (name="game")
    private List<SlotGame> games;

    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SlotGame> getGames() {
        return games;
    }

    public void setGames(List<SlotGame> games) {
        this.games = games;
    }
}
