package com.gabriel.slot.domain.model.mathmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabriel.slot.domain.model.mathmodel.oxm.ReelsMappingAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Map;

/**
 * Class that represents a reel set
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class ReelSet {

    //Fields
    @XmlAttribute(required=true)
    private Integer id;

    @XmlAttribute (required=true)
    private String name;

    @XmlAttribute (required=true)
    private String type;

    @XmlJavaTypeAdapter(ReelsMappingAdapter.class)
    @XmlElement(name="reels")
    private Map<Integer, Reel> reels;

    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Map<Integer, Reel> getReels() {
        return reels;
    }

    public void setReels(Map<Integer, Reel> reels) {
        this.reels = reels;
    }

}
