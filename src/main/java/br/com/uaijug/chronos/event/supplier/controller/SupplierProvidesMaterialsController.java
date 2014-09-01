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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more supplierProvidesMaterials the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SupplierProvidesMaterialsController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class SupplierProvidesMaterialsController extends AbstractManageBeans {

	/** The supplier provides materials registration. */
	@Inject
	private SupplierProvidesMaterialsRegistration supplierProvidesMaterialsRegistration;

	/** The supplier provides materials repository. */
	@Inject
	private SupplierProvidesMaterialsRepository supplierProvidesMaterialsRepository;

	/** The id supplier provides materials. */
	private Long idSupplierProvidesMaterials;

	/** The supplier provides materials. */
	private SupplierProvidesMaterials supplierProvidesMaterials;

	/** The supplier provides materialss. */
	List<SupplierProvidesMaterials> supplierProvidesMaterialss;

	/**
	 * Inits the new supplier provides materials.
	 */
	@PostConstruct
	public void initNewSupplierProvidesMaterials() {
		supplierProvidesMaterials = new SupplierProvidesMaterials();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSupplierProvidesMaterials = null;
	}

	/**
	 * Gets the supplier provides materialss.
	 *
	 * @return the supplier provides materialss
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idSupplierProvidesMaterials = null;
		supplierProvidesMaterials = new SupplierProvidesMaterials();
	}

	/**
	 * Gets the id supplier provides materials.
	 *
	 * @return the id supplier provides materials
	 */
	public Long getIdSupplierProvidesMaterials() {
		return idSupplierProvidesMaterials;
	}

	/**
	 * Sets the id supplier provides materials.
	 *
	 * @param idSupplierProvidesMaterials the new id supplier provides materials
	 */
	public void setIdSupplierProvidesMaterials(Long idSupplierProvidesMaterials) {
		this.idSupplierProvidesMaterials = idSupplierProvidesMaterials;
	}

	/**
	 * Gets the supplier provides materials.
	 *
	 * @return the supplier provides materials
	 */
	public SupplierProvidesMaterials getSupplierProvidesMaterials() {
		return supplierProvidesMaterials;
	}

	/**
	 * Sets the supplier provides materials.
	 *
	 * @param supplierProvidesMaterials the new supplier provides materials
	 */
	public void setSupplierProvidesMaterials(SupplierProvidesMaterials supplierProvidesMaterials) {
		this.supplierProvidesMaterials = supplierProvidesMaterials;
	}

}
