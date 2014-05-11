package com.yummynoodlebar.persistence.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "CUSTOMERS")
public class Customer implements Serializable {
	private static final long serialVersionUID = -1296927540765837398L;

	@NotNull
	@NotEmpty
	@Column(nullable = false)
	private String name;

	@NotNull
	@NotEmpty
	private String streetAddress;

	@NotNull
	@NotEmpty
	private String city;

	@NotNull
	@NotEmpty
	private String postalCode;

	@NotNull
	@NotEmpty
	@Email
	@Column(nullable = false)
	private String email;

	@Id
	@Column(name = "CUSTOMER_ID")
	private String id;

	@NotNull
	@NotEmpty
	private String paymentMethod;

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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAdress) {
		this.streetAddress = streetAdress;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
