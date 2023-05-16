
//Namespace
package com.gabriel.slot.domain.model.mathmodel.oxm;

//Imports


import com.gabriel.slot.domain.model.mathmodel.Reel;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the reel map
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReelsMapping {

	@XmlElement (name="reel")
	private List<Reel> reels;

	/**
	 * Constructor
	 */
	public ReelsMapping() {
		reels = new ArrayList<>();
	}


	//Getters and Setters

	public List<Reel> getReels() {
		return reels;
	}
	public void setReels(List<Reel> reels) {
		this.reels = reels;
	}
}
