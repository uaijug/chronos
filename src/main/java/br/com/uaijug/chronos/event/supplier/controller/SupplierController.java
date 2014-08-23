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
package br.com.uaijug.chronos.event.supplier.controller;

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
import br.com.uaijug.chronos.event.supplier.data.repository.SupplierRepository;
import br.com.uaijug.chronos.event.supplier.model.Supplier;
import br.com.uaijug.chronos.event.supplier.service.SupplierRegistration;
import br.com.uaijug.chronos.model.types.SupplierType;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more supplier the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SupplierController extends AbstractManageBeans {

	@Inject
	private SupplierRegistration supplierRegistration;

	@Inject
	private SupplierRepository supplierRepository;

	@Inject
	private AddressRepository addressRepository;

	private Long idSupplier;

	private Supplier supplier;

	List<Supplier> suppliers;

	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	@PostConstruct
	public void initNewSupplier() {
		supplier = new Supplier();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSupplier = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	public List<Supplier> getSuppliers() {
		return supplierRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSupplier != null) {
				supplier = supplierRepository.findById(idSupplier);
				if (supplier == null) {
					redirect("list.jsf");
				}
			} else {
				supplier = new Supplier();
			}
		}
	}

	public String register() throws Exception {
		try {

			supplierRegistration.register(supplier);
			successMessage("label.supplier.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.supplier-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSupplier = null;
		supplier = new Supplier();
	}

	public List<SelectItem> getSupplierTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SupplierType type : SupplierType.values()) {
			items.add(new SelectItem(type, type.getSupplierType()));
		}
		return items;
	}

	public List<SelectItem> getAddresses() {

		setListAddress(addressRepository.findAll());
		for (Address p : listAddress) {
			itensAddress.add(new SelectItem(p, p.getStreet()));
		}
		return itensAddress;
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

	public Long getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
