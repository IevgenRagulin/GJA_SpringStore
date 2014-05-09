package com.yummynoodlebar.persistence.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CUSTOMERS")
public class Customer {

	@Column(nullable = false)
	private String name;

	private String streetAdress;

	private String city;

	private String postalCode;

	@Column(nullable = false)
	private String email;

	@Id
	@Column(name = "CUSTOMER_ID")
	private String id;

	public Customer() {
		this.id = UUID.randomUUID().toString();
	}

	public Customer(String key) {
		this.id = key;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
