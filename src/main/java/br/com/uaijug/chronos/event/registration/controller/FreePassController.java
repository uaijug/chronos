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
package br.com.uaijug.chronos.event.registration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.registration.data.repository.FreePassRepository;
import br.com.uaijug.chronos.event.registration.model.FreePass;
import br.com.uaijug.chronos.event.registration.service.FreePassRegistration;
import br.com.uaijug.chronos.model.types.PercentageValue;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class FreePassController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class FreePassController extends AbstractManageBeans {

	/** The free pass registration. */
	@Inject
	private FreePassRegistration freePassRegistration;

	/** The free pass repository. */
	@Inject
	private FreePassRepository freePassRepository;

	/** The id free pass. */
	private Long idFreePass;

	/** The free pass. */
	private FreePass freePass;

	/** The free passs. */
	List<FreePass> freePasss;

	/**
	 * Inits the new free pass.
	 */
	@PostConstruct
	public void initNewFreePass() {
		freePass = new FreePass();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idFreePass = null;
	}

	/**
	 * Gets the free passs.
	 *
	 * @return the free passs
	 */
	public List<FreePass> getFreePasss() {
		return freePassRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idFreePass != null) {
				freePass = freePassRepository.findById(idFreePass);
				if (freePass == null) {
					redirect("list.jsf");
				}
			} else {
				freePass = new FreePass();
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

			freePassRegistration.register(freePass);
			successMessage("label.freePass.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.freePass-exists");
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
		idFreePass = null;
		freePass = new FreePass();
	}

	/**
	 * Gets the percentage values.
	 *
	 * @return the percentage values
	 */
	public List<SelectItem> getPercentageValues() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PercentageValue type : PercentageValue.values()) {
			items.add(new SelectItem(type, type.getPercentageValue()));
		}
		return items;
	}
	
	/**
	 * Gets the id free pass.
	 *
	 * @return the id free pass
	 */
	public Long getIdFreePass() {
		return idFreePass;
	}

	/**
	 * Sets the id free pass.
	 *
	 * @param idFreePass the new id free pass
	 */
	public void setIdFreePass(Long idFreePass) {
		this.idFreePass = idFreePass;
	}

	/**
	 * Gets the free pass.
	 *
	 * @return the free pass
	 */
	public FreePass getFreePass() {
		return freePass;
	}

	/**
	 * Sets the free pass.
	 *
	 * @param freePass the new free pass
	 */
	public void setFreePass(FreePass freePass) {
		this.freePass = freePass;
	}

}
