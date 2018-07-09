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
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private int orderAmount;
    
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    @XmlElement
    private User user;
    
    @ManyToOne
    @JoinColumn(name="SABER_ID", nullable=false)
    @XmlElement
    private Saber saber;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	@XmlElement
	public User getUser() {
		User responseUser = user;
		responseUser.setPassword(null);
		return responseUser;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@XmlElement
	public Saber getSaber() {
		return saber;
	}

	public void setSaber(Saber saber) {
		this.saber = saber;
	}
	

}
