
//Imports
package com.gabriel.slot.domain.dto.api;

//Imports
import com.gabriel.slot.domain.model.Player;
import com.gabriel.slot.domain.model.Spin;
import jakarta.validation.constraints.NotNull;

/**
 * Class that represents a request for a spin action
 */
public class SpinRequest {
    //Fields

    @NotNull
    private Player player;

    @NotNull
    private Spin spin;

    //Getters and Setters
    public Spin getSpin() {
        return spin;
    }

    public void setSpin(Spin spin) {
        this.spin = spin;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
