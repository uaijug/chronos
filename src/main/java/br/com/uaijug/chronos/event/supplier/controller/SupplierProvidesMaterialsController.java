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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.supplier.data.repository.SupplierProvidesMaterialsRepository;
import br.com.uaijug.chronos.event.supplier.model.SupplierProvidesMaterials;
import br.com.uaijug.chronos.event.supplier.service.SupplierProvidesMaterialsRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more supplierProvidesMaterials the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SupplierProvidesMaterialsController extends AbstractManageBeans {

	@Inject
	private SupplierProvidesMaterialsRegistration supplierProvidesMaterialsRegistration;

	@Inject
	private SupplierProvidesMaterialsRepository supplierProvidesMaterialsRepository;

	private Long idSupplierProvidesMaterials;

	private SupplierProvidesMaterials supplierProvidesMaterials;

	List<SupplierProvidesMaterials> supplierProvidesMaterialss;

	@PostConstruct
	public void initNewSupplierProvidesMaterials() {
		supplierProvidesMaterials = new SupplierProvidesMaterials();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSupplierProvidesMaterials = null;
	}

	public List<SupplierProvidesMaterials> getSupplierProvidesMaterialss() {
		return supplierProvidesMaterialsRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSupplierProvidesMaterials != null) {
				supplierProvidesMaterials = supplierProvidesMaterialsRepository.findById(idSupplierProvidesMaterials);
				if (supplierProvidesMaterials == null) {
					redirect("list.jsf");
				}
			} else {
				supplierProvidesMaterials = new SupplierProvidesMaterials();
			}
		}
	}

	public String register() throws Exception {
		try {

			supplierProvidesMaterialsRegistration.register(supplierProvidesMaterials);
			successMessage("label.supplierProvidesMaterials.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.supplierProvidesMaterials-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSupplierProvidesMaterials = null;
		supplierProvidesMaterials = new SupplierProvidesMaterials();
	}

	public Long getIdSupplierProvidesMaterials() {
		return idSupplierProvidesMaterials;
	}

	public void setIdSupplierProvidesMaterials(Long idSupplierProvidesMaterials) {
		this.idSupplierProvidesMaterials = idSupplierProvidesMaterials;
	}

	public SupplierProvidesMaterials getSupplierProvidesMaterials() {
		return supplierProvidesMaterials;
	}

	public void setSupplierProvidesMaterials(SupplierProvidesMaterials supplierProvidesMaterials) {
		this.supplierProvidesMaterials = supplierProvidesMaterials;
	}

}
