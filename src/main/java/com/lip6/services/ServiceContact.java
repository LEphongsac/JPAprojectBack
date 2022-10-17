package com.lip6.services;

import com.lip6.daos.DAOContact;
import com.lip6.daos.IDAOContact;

public class ServiceContact {
	
	public void createContact(long id, String fname, String lname, String email) {
		
		IDAOContact daoc=new DAOContact();
		boolean ok=daoc.addContact(id, fname, lname, email);
		if (ok)
			System.out.println("Contact ajouté!");
		else
			System.out.println("Contact non ajouté!");
		
	}

}
