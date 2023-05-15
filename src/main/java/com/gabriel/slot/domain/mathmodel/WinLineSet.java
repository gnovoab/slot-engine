
//Namespace
package com.gabriel.slot.domain.mathmodel;

//Imports

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

/**
 * Class that represents a set of win lines
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WinLineSet {

	//Fields

	@XmlAttribute(required = true)
	private Integer id;

	@XmlElement(name = "winLine", required = true)
	private List<WinLine> winLines;


	//Getters and Setters

	public List<WinLine> getWinLines() {
		return winLines;
	}

	public void setWinLines(List<WinLine> winLines) {
		this.winLines = winLines;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
