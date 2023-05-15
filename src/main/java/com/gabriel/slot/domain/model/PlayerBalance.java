package com.gabriel.slot.domain.model;

//Imports
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * Class that represents a player
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerBalance {

    //Fields
    private BigDecimal real;
    private BigDecimal bonus;
    private BigDecimal bonusWins;

    //Getters and Setters
    public BigDecimal getReal() {
        return real;
    }

    public void setReal(BigDecimal real) {
        this.real = real;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getBonusWins() {
        return bonusWins;
    }

    public void setBonusWins(BigDecimal bonusWins) {
        this.bonusWins = bonusWins;
    }
}