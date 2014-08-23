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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class InstitutionController extends AbstractManageBeans {

	@Inject
	private InstitutionRegistration institutionRegistration;

	@Inject
	private InstitutionRepository institutionRepository;

	@Inject
	private AddressRepository addressRepository;

	private Long idInstitution;

	private Institution institution;

	List<Institution> institutions;

	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	@PostConstruct
	public void initNewInstitution() {
		institution = new Institution();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idInstitution = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idInstitution = null;
		institution = new Institution();
	}

	public List<SelectItem> getInstitutionTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (InstitutionType type : InstitutionType.values()) {
			items.add(new SelectItem(type, type.toString()));
		}
		return items;
	}

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
	
	public Long getIdInstitution() {
		return idInstitution;
	}

	public void setIdInstitution(Long idInstitution) {
		this.idInstitution = idInstitution;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public List<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	public List<SelectItem> getItensAddress() {
		return itensAddress;
	}

	public void setItensAddress(List<SelectItem> itensAddress) {
		this.itensAddress = itensAddress;
	}

}
