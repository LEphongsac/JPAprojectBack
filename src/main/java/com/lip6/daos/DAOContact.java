	package com.lip6.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.Messages;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;

public class DAOContact implements IDAOContact {

	/**
	 * Rajoute un contact dans la base de donnees.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return renvoit le nouveau contact
	 */
	@Override
	public  boolean addContact(long idContact, String firstname, String lastname, String email) {

		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		EntityManager em=JpaUtil.getEmf().createEntityManager();
		// 2 : Instanciation Objet métier
		Contact contact = new Contact("Xavier", "Blanc");
		Contact contact2 = new Contact("Phong Sac", "Le");
		PhoneNumber phoneNumber = new PhoneNumber("0664252545");
		PhoneNumber phoneNumber2 = new PhoneNumber("0664252532");
		PhoneNumber phoneNumber3 = new PhoneNumber("0664255555");
		ContactGroup contactGroup = new ContactGroup();
		Address address = new Address("Rue de la Cerisaie");
		Address address2 = new Address("Place de la levriere");
		contact.setAddress(address);
		contact.setPhoneNumber(phoneNumber);
		contact2.setAddress(address2);
		contact2.setPhoneNumber(phoneNumber2);
		contact2.setPhoneNumber(phoneNumber3);
		contactGroup.setContact(contact);
		contactGroup.setContact(contact2);
		// 3 : Ouverture transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
		em.persist(contact);
		em.persist(contact2);
		// L’entité étant en mode managée, pas besoin de refaire un persist
		contact.setFirstName("Blanquito");
		contact2.setFirstName("Abc");
		// 5 : Fermeture transaction
		tx.commit();
		// 6 : Fermeture de l'EntityManager et de unité de travail JPA
		em.close();

		return true;
	}

	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	@Override
	public int deleteContact(long id) {
		int success = 0;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			String request = "DELETE FROM contacts WHERE id = " + id;
			success = stmt.executeUpdate(request);
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Contact getContact(long id) {
		ResultSet rec = null;
		Contact contact = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE id = " + id);

			if (rec.next() == false) {
				System.out.println("ResultSet in empty in Java");
			} else {
				do {
					contact=new Contact();
					contact.setIdContact(Long.parseLong(rec.getString("id")));
					contact.setFirstName(rec.getString("firstname"));
					contact.setLastName(rec.getString("lastname"));
					contact.setEmail(rec.getString("email"));
				} while (rec.next());
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * 
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @return
	 */
	@Override
	public boolean modifyContact(long id, String firstname, String lastname, String email) {
		boolean success = false;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			String sqlFirstName = "UPDATE contacts SET firstname = " + "'" + firstname + "'" + " WHERE id = " + id;
			String sqlLastName = "UPDATE contacts SET lastname = " + "'" + lastname + "'" + " WHERE id = " + id;
			String sqlEmail = "UPDATE contacts SET email = " + "'" + email + "'" + " WHERE id = " + id;

			if (firstname != "")
				stmt.executeUpdate(sqlFirstName);
			if (lastname != "")
				stmt.executeUpdate(sqlLastName);
			if (email != "")
				stmt.executeUpdate(sqlEmail);

			success = true;
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firrstname
	 * 
	 * @param firstname
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByFirstName(String firstname) {

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE firstname = " + "'" + firstname + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));

				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * 
	 * @param lastname
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByLastName(String lastname) {

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE lastname = " + "'" + lastname + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByEmail(String email) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE email = " + "'" + email + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
