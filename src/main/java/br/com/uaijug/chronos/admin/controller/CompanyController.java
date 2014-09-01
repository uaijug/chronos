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
package br.com.uaijug.chronos.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.AddressRepository;
import br.com.uaijug.chronos.admin.data.repository.CompanyRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.admin.service.CompanyRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.model.types.CompanyType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class CompanyController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class CompanyController extends AbstractManageBeans {

	/** The company registration. */
	@Inject
	private CompanyRegistration companyRegistration;

	/** The company repository. */
	@Inject
	private CompanyRepository companyRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The id company. */
	private Long idCompany;

	/** The company. */
	private Company company;

	/** The companys. */
	List<Company> companys;

	/** The list address. */
	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	/**
	 * Inits the new company.
	 */
	@PostConstruct
	public void initNewCompany() {
		company = new Company();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCompany = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	/**
	 * Gets the companys.
	 *
	 * @return the companys
	 */
	public List<Company> getCompanys() {
		return companyRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idCompany != null) {
				company = companyRepository.findById(idCompany);
				if (company == null) {
					redirect("list.jsf");
				}
			} else {
				company = new Company();
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

			companyRegistration.register(company);
			successMessage("label.company.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.company-exists");
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
		idCompany = null;
		company = new Company();
	}

	/**
	 * Gets the company types.
	 *
	 * @return the company types
	 */
	public List<SelectItem> getCompanyTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CompanyType type : CompanyType.values()) {
			items.add(new SelectItem(type, type.getCompanyType()));
		}
		return items;
	}

	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */
	public List<SelectItem> getAddresses() {

		setListAddress(addressRepository.findAll());
		for (Address p : listAddress) {
			itensAddress.add(new SelectItem(p, p.getStreet()));
		}
		return itensAddress;
	}

	/**
	 * Gets the id company.
	 *
	 * @return the id company
	 */
	public Long getIdCompany() {
		return idCompany;
	}

	/**
	 * Sets the id company.
	 *
	 * @param idCompany the new id company
	 */
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
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
