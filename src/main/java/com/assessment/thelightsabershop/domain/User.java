package com.assessment.thelightsabershop.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private int age, registrationYear;
    
    private String name, email, password;
    
    @OneToMany(mappedBy="user")
    private List<CustomerOrder> customerOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		//age can be different after registration because of that i added a age calculation phase to my entity
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int calculatedAge = this.age;
		if(year != registrationYear || year > registrationYear) {
			calculatedAge += (year - registrationYear);
		}
		return calculatedAge;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setRegistrationYear(int registrationYear) {
		this.registrationYear = registrationYear;
	}

	public double getForce() {
		double force;
		double age = this.getAge();
		if(age<18) {
			force = age * 10;
		}else {
			force = -1;
		}
		return force;
	}
	
	public boolean isDissolvedInForce() {
		if(this.getAge() >= 140) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getRole() {
		double force = this.getForce();
		if(force >= 93.2 || force == -1) {
			return "Jedi";
		} else {
			return "Padavan";
		}
	}
	
}
