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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.AddressRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.InstitutionRepository;
import br.com.uaijug.chronos.institution.model.Institution;
import br.com.uaijug.chronos.institution.service.InstitutionRegistration;
import br.com.uaijug.chronos.model.types.InstitutionType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class InstitutionController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class InstitutionController extends AbstractManageBeans {

	/** The institution registration. */
	@Inject
	private InstitutionRegistration institutionRegistration;

	/** The institution repository. */
	@Inject
	private InstitutionRepository institutionRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The id institution. */
	private Long idInstitution;

	/** The institution. */
	private Institution institution;

	/** The institutions. */
	List<Institution> institutions;

	/** The list address. */
	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	/**
	 * Inits the new institution.
	 */
	@PostConstruct
	public void initNewInstitution() {
		institution = new Institution();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idInstitution = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	/**
	 * Gets the institutions.
	 *
	 * @return the institutions
	 */
	public List<Institution> getInstitutions() {
		return institutionRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idInstitution != null) {
				institution = institutionRepository.findById(idInstitution);
				if (institution == null) {
					redirect("list.jsf");
				}
			} else {
				institution = new Institution();
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

			institutionRegistration.register(institution);
			successMessage("label.institution.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.institution-exists");
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
		idInstitution = null;
		institution = new Institution();
	}

	/**
	 * Gets the institution types.
	 *
	 * @return the institution types
	 */
	public List<SelectItem> getInstitutionTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (InstitutionType type : InstitutionType.values()) {
			items.add(new SelectItem(type, type.getInstitutionType()));
		}
		return items;
	}

	/**
	 * Complete address.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Address> completeAddress(String query) {
		List<Address> allAddress = addressRepository.findAllOrderedByName();
		List<Address> filteredAddress = new ArrayList<Address>();

		for (int i = 0; i < allAddress.size(); i++) {
			Address address = allAddress.get(i);
			if (address.getStreet().toLowerCase().startsWith(query)) {
				filteredAddress.add(address);
			}
		}

		return filteredAddress;
	}
	
	/**
	 * Gets the id institution.
	 *
	 * @return the id institution
	 */
	public Long getIdInstitution() {
		return idInstitution;
	}

	/**
	 * Sets the id institution.
	 *
	 * @param idInstitution the new id institution
	 */
	public void setIdInstitution(Long idInstitution) {
		this.idInstitution = idInstitution;
	}

	/**
	 * Gets the institution.
	 *
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * Sets the institution.
	 *
	 * @param institution the new institution
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * Gets the list address.
	 *
	 * @return the list address
	 */
	public List<Address> getListAddress() {
		return listAddress;
	}

	/**
	 * Sets the list address.
	 *
	 * @param listAddress the new list address
	 */
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	/**
	 * Gets the itens address.
	 *
	 * @return the itens address
	 */
	public List<SelectItem> getItensAddress() {
		return itensAddress;
	}

	/**
	 * Sets the itens address.
	 *
	 * @param itensAddress the new itens address
	 */
	public void setItensAddress(List<SelectItem> itensAddress) {
		this.itensAddress = itensAddress;
	}

}
