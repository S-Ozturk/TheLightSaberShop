package com.assessment.thelightsabershop.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Color {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    private String planet; 
    
    private String color;
    
    private double powerUsageMultiplier;
    
    private int stockPrice; 
    
    @OneToMany(mappedBy="color")
    private List<Crystal> crystals;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getPlanet() {
		return planet;
	}

	public void setName(String name) {
		this.planet = name;
	}

	@XmlElement
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	//@XmlElement - not needed in assessment
	public int getPowerUsageMultiplier() {
		return (int)(powerUsageMultiplier * 100); //For converting data to make calculations easy 0.20 -> 20%
	}

	public void setPowerUsageMultiplier(int powerUsageMultiplier) {
		double powerUsageMultiplierCalculated = powerUsageMultiplier / 100; //For converting data to make calculations easy 20% -> 0.20
		this.powerUsageMultiplier = powerUsageMultiplierCalculated;
	}

	@XmlElement
	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}

	//@XmlElement - not needed in assessment
	public int getCalculatedPrice() {
		int calculatedPrice = (int)(this.stockPrice * this.powerUsageMultiplier); //For calculating the price of crystal and round it 
		return calculatedPrice;
	}
}
