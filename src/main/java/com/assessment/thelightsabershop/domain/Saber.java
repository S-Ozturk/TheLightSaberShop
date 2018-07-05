package com.assessment.thelightsabershop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Saber {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private int available;
    
    @ManyToOne
    @JoinColumn(name="CRYSTAL_ID", nullable=false)
    private Crystal crystal;

    @XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	@XmlElement
	public Crystal getCrystal() {
		return crystal;
	}

	public void setCrystal(Crystal crystal) {
		this.crystal = crystal;
	}
    
}
