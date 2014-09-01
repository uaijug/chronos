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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more supplier the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SupplierController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class SupplierController extends AbstractManageBeans {

	/** The supplier registration. */
	@Inject
	private SupplierRegistration supplierRegistration;

	/** The supplier repository. */
	@Inject
	private SupplierRepository supplierRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The id supplier. */
	private Long idSupplier;

	/** The supplier. */
	private Supplier supplier;

	/** The suppliers. */
	List<Supplier> suppliers;

	/** The list address. */
	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	/**
	 * Inits the new supplier.
	 */
	@PostConstruct
	public void initNewSupplier() {
		supplier = new Supplier();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSupplier = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idSupplier = null;
		supplier = new Supplier();
	}

	/**
	 * Gets the supplier types.
	 *
	 * @return the supplier types
	 */
	public List<SelectItem> getSupplierTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SupplierType type : SupplierType.values()) {
			items.add(new SelectItem(type, type.getSupplierType()));
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
	 * Gets the id supplier.
	 *
	 * @return the id supplier
	 */
	public Long getIdSupplier() {
		return idSupplier;
	}

	/**
	 * Sets the id supplier.
	 *
	 * @param idSupplier the new id supplier
	 */
	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Sets the supplier.
	 *
	 * @param supplier the new supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
