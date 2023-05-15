package com.gabriel.slot.domain.model;

//Imports
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Class that represents a player
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    //Fields
    @JsonIgnore
    private Integer id;
    private boolean selfExcluded;
    private PlayerBalance balance;


    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSelfExcluded() {
        return selfExcluded;
    }

    public void setSelfExcluded(boolean selfExcluded) {
        this.selfExcluded = selfExcluded;
    }

    public PlayerBalance getBalance() {
        return balance;
    }

    public void setBalance(PlayerBalance balance) {
        this.balance = balance;
    }

}
