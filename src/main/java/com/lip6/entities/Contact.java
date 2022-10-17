package com.lip6.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="contact")
	private List<PhoneNumber> phoneNumber = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idaddress")
	private Address address;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="CTC_GRP", 
	joinColumns=@JoinColumn(name="CTC_ID"), 
	inverseJoinColumns=@JoinColumn(name="GRP_ID"))
	private List<ContactGroup> groupContact = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_Contact;
	
	public Contact(){
	}
	

	public Contact(String firstName, String lastName, String email, long idContact) {
		this();
		this.id_Contact = idContact;
	}


	public Contact(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}

	public long getIdContact() {
		return id_Contact;
	}

	public void setIdContact(long idContact) {
		this.id_Contact = idContact;
	}


	public List<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber.add(phoneNumber);
		phoneNumber.setContact(this);
	}	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		address.setContact(this);
	}


	public List<ContactGroup> getGroupContact() {
		return groupContact;
	}


	public void setGroupContact(ContactGroup groupContact) {
		this.groupContact.add(groupContact);
	}
	
	

	
}
