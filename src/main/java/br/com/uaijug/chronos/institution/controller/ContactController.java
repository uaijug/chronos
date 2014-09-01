/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.uaijug.chronos.institution.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.ContactRepository;
import br.com.uaijug.chronos.institution.model.Contact;
import br.com.uaijug.chronos.institution.service.ContactRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more contact the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ContactController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class ContactController extends AbstractManageBeans {

	/** The contact registration. */
	@Inject
	private ContactRegistration contactRegistration;

	/** The contact repository. */
	@Inject
	private ContactRepository contactRepository;

	/** The id contact. */
	private Long idContact;
	
	/** The contact. */
	private Contact contact;

	/** The contacts. */
	List<Contact> contacts;

	/**
	 * Inits the new contact.
	 */
	@PostConstruct
	public void initNewContact() {
		contact = new Contact();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idContact = null;
	}

	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idContact != null) {
				contact = contactRepository.findById(idContact);
				if (contact == null) {
					redirect("list.jsf");
				}
			} else {
				contact = new Contact();
			}
		}
	}

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	public String register() throws Exception {
		try {

			contactRegistration.register(contact);
			successMessage("label.contact.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.contact-exists");
		}
		return null;
	}

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	/**
	 * Limpar.
	 */
	private void limpar() {
		idContact = null;
		contact = new Contact();
	}

	/**
	 * Gets the id contact.
	 *
	 * @return the id contact
	 */
	public Long getIdContact() {
		return idContact;
	}

	/**
	 * Sets the id contact.
	 *
	 * @param idContact the new id contact
	 */
	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}

	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
