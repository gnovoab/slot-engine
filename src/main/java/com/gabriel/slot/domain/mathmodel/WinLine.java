
//Namspace
package com.gabriel.slot.domain.mathmodel;

//Imports
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Class that represents a win line
 */
@XmlRootElement (name="winLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class WinLine {

	//Fields
	@XmlAttribute (required=true)
	private Integer id;

	@XmlAttribute (required=true)
	private String symbol;

	@XmlAttribute
	private Integer value;

	@XmlAttribute
	private Short multiplier;

	@XmlAttribute
	private Short freeSpins;



	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Short getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Short multiplier) {
		this.multiplier = multiplier;
	}


	public Short getFreeSpins() {
		return freeSpins;
	}

	public void setFreeSpins(Short freeSpins) {
		this.freeSpins = freeSpins;
	}
}

