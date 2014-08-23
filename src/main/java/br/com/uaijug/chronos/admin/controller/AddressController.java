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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class AddressController extends AbstractManageBeans {

	@Inject
	private AddressRegistration addressRegistration;

	@Inject
	private AddressRepository addressRepository;

	@Inject
	private CityRepository cityRepository;

	private Long idAddress;

	private Address address;

	List<Address> addresss;

	private List<City> listCity = null;

	/** The itens city. */
	List<SelectItem> itensCity = null;

	@PostConstruct
	public void initNewAddress() {
		address = new Address();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idAddress = null;

		itensCity = new ArrayList<SelectItem>();
		listCity = new ArrayList<City>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idAddress = null;
		address = new Address();
	}

	public List<SelectItem> getCitys() {

		setListCity(cityRepository.findAll());
		for (City p : listCity) {
			itensCity.add(new SelectItem(p, p.getDescription()));
		}
		return itensCity;
	}

	public Long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	public List<SelectItem> getItensCity() {
		return itensCity;
	}

	public void setItensCity(List<SelectItem> itensCity) {
		this.itensCity = itensCity;
	}

}
