
//Namespace
package com.gabriel.slot.domain.model.game;

//Imports

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabriel.slot.domain.model.game.SlotConfigData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
/**
 * Class that represents a Slot
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotGame {

	//Fields
	@XmlAttribute (required=true)
	private String name;

	@XmlAttribute (required=true)
	private Integer id;

	@XmlAttribute
	private Integer mathModel;

	@XmlAttribute
	private Integer progressiveRtp;

	@XmlElement
	private SlotConfigData configData;


	//Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMathModel() {
		return mathModel;
	}

	public void setMathModel(Integer mathModel) {
		this.mathModel = mathModel;
	}

	public Integer getProgressiveRtp() {
		return progressiveRtp;
	}

	public void setProgressiveRtp(Integer progressiveRtp) {
		this.progressiveRtp = progressiveRtp;
	}

	public SlotConfigData getConfigData() {
		return configData;
	}

	public void setConfigData(SlotConfigData configData) {
		this.configData = configData;
	}


}
