package com.lip6.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PhoneNumber {

	private String phoneNumber;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPhoneNumber;
	@ManyToOne
	@JoinColumn(name="id_contact")
	private Contact contact=null;

	public PhoneNumber(String phoneNumber2) {
		this.phoneNumber = phoneNumber2;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getIdPhoneNumber() {
		return idPhoneNumber;
	}

	public void setIdPhoneNumber(long idPhoneNumber) {
		this.idPhoneNumber = idPhoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		
	}

	
	
}
