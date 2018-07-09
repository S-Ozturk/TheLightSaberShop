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
public class Crystal {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name, planet, color;
    
    private double powerUsageMultiplier;
    
    private int stockPrice, calculatedPrice; 
    
    @OneToMany(mappedBy="crystal")
    private List<Saber> sabers;

    
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

	//@XmlElement - not needed in assessment
	public String getPlanet() {
		return planet;
	}

	public void setPlanet(String planet) {
		this.planet = planet;
	}

	@XmlElement
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPowerUsageMultiplier() {
		return (int)(powerUsageMultiplier * 100); //For converting data to make calculations easy 0.20 -> 20%
	}

	public void setPowerUsageMultiplier(int powerUsageMultiplier) {
		double powerUsageMultiplierCalculated = powerUsageMultiplier / 100; //For converting data to make calculations easy 20% -> 0.20
		this.powerUsageMultiplier = powerUsageMultiplierCalculated;
	}

	//@XmlElement - not needed in assessment
	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getCalculatedPrice() {
		return calculatedPrice;
	}
	
	public void setCalculatedPrice() {
		this.calculatedPrice = (int)(this.stockPrice * this.powerUsageMultiplier); //For calculating the price of crystal and round it 
	}
}
