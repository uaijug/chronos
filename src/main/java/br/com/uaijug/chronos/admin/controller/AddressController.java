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
import br.com.uaijug.chronos.admin.data.repository.CityRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.admin.model.City;
import br.com.uaijug.chronos.admin.service.AddressRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class AddressController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class AddressController extends AbstractManageBeans {

	/** The address registration. */
	@Inject
	private AddressRegistration addressRegistration;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The city repository. */
	@Inject
	private CityRepository cityRepository;

	/** The id address. */
	private Long idAddress;

	/** The address. */
	private Address address;

	/** The addresss. */
	List<Address> addresss;

	/** The list city. */
	private List<City> listCity = null;

	/** The itens city. */
	List<SelectItem> itensCity = null;

	/**
	 * Inits the new address.
	 */
	@PostConstruct
	public void initNewAddress() {
		address = new Address();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idAddress = null;

		itensCity = new ArrayList<SelectItem>();
		listCity = new ArrayList<City>();
	}

	/**
	 * Gets the addresss.
	 *
	 * @return the addresss
	 */
	public List<Address> getAddresss() {
		return addressRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idAddress != null) {
				address = addressRepository.findById(idAddress);
				if (address == null) {
					redirect("list.jsf");
				}
			} else {
				address = new Address();
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

			addressRegistration.register(address);
			successMessage("label.address.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.address-exists");
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
		idAddress = null;
		address = new Address();
	}

	/**
	 * Gets the citys.
	 *
	 * @return the citys
	 */
	public List<SelectItem> getCitys() {

		setListCity(cityRepository.findAll());
		for (City p : listCity) {
			itensCity.add(new SelectItem(p, p.getDescription()));
		}
		return itensCity;
	}

	/**
	 * Gets the id address.
	 *
	 * @return the id address
	 */
	public Long getIdAddress() {
		return idAddress;
	}

	/**
	 * Sets the id address.
	 *
	 * @param idAddress the new id address
	 */
	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the list city.
	 *
	 * @return the list city
	 */
	public List<City> getListCity() {
		return listCity;
	}

	/**
	 * Sets the list city.
	 *
	 * @param listCity the new list city
	 */
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	/**
	 * Gets the itens city.
	 *
	 * @return the itens city
	 */
	public List<SelectItem> getItensCity() {
		return itensCity;
	}

	/**
	 * Sets the itens city.
	 *
	 * @param itensCity the new itens city
	 */
	public void setItensCity(List<SelectItem> itensCity) {
		this.itensCity = itensCity;
	}

}
