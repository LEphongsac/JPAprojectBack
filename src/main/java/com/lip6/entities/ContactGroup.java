package com.lip6.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ContactGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idContactGroup;
	@ManyToMany(mappedBy="groupContact")
	private List<Contact> contact = new ArrayList<>();

	public ContactGroup() {
	}

	public long getIdContactGroup() {
		return idContactGroup;
	}

	public void setIdContactGroup(long idContactGroup) {
		this.idContactGroup = idContactGroup;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact.add(contact);
		contact.setGroupContact(this);
	}
	
}
