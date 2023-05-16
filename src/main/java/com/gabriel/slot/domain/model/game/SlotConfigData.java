
//Namespace
package com.gabriel.slot.domain.model.game;

//Imports

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.*;

import java.util.List;


/**
 * Class that represents a game config data
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class SlotConfigData {


	//Fields
	@XmlAttribute (required=true)
	private Integer maxBet;

	@XmlAttribute (required=true)
	private Integer maxWin;

	@XmlAttribute
	private Short maxLines;

	@XmlAttribute
	private Short maxGamblePicks;

	@XmlList
	@XmlElement (required=true)
	private List<Short> stakes;

	@XmlList
	@XmlElement (required=true)
	private List<Short> autoplay;


	//Getters and Setters

	public Integer getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(Integer maxBet) {
		this.maxBet = maxBet;
	}

	public Integer getMaxWin() {
		return maxWin;
	}

	public void setMaxWin(Integer maxWin) {
		this.maxWin = maxWin;
	}

	public Short getMaxLines() {
		return maxLines;
	}

	public void setMaxLines(Short maxLines) {
		this.maxLines = maxLines;
	}

	public Short getMaxGamblePicks() {
		return maxGamblePicks;
	}

	public void setMaxGamblePicks(Short maxGamblePicks) {
		this.maxGamblePicks = maxGamblePicks;
	}

	public List<Short> getStakes() {
		return stakes;
	}

	public void setStakes(List<Short> stakes) {
		this.stakes = stakes;
	}

	public List<Short> getAutoplay() {
		return autoplay;
	}

	public void setAutoplay(List<Short> autoplay) {
		this.autoplay = autoplay;
	}
}
