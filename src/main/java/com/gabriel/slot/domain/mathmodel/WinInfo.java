
//Namespace
package com.gabriel.slot.domain.mathmodel;

//Imports

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Class that represents all the win information
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({WinLine.class})
public class WinInfo {

	//Fields
	@XmlElementWrapper
	@XmlElement (name="winLineSet")
	private List<WinLineSet> winLineSets;


	//Getters and Setters

	public List<WinLineSet> getWinLineSets() {
		return winLineSets;
	}

	public void setWinLineSets(List<WinLineSet> winLineSets) {
		this.winLineSets = winLineSets;
	}

}
