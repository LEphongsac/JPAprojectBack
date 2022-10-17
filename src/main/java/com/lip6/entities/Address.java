package com.lip6.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	private String address;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_Address;
	
	@OneToOne(mappedBy="address")
	private Contact contact;

	public Address(String address) {
		this.address = address;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
}
