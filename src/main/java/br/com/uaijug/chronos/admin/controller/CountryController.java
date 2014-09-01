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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.CountryRepository;
import br.com.uaijug.chronos.admin.model.Country;
import br.com.uaijug.chronos.admin.service.CountryRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class CountryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class CountryController extends AbstractManageBeans {

	/** The country registration. */
	@Inject
	private CountryRegistration countryRegistration;

	/** The country repository. */
	@Inject
	private CountryRepository countryRepository;

	/** The id country. */
	private Long idCountry;

	/** The country. */
	private Country country;

	/** The countrys. */
	List<Country> countrys;

	/**
	 * Inits the new country.
	 */
	@PostConstruct
	public void initNewCountry() {
		country = new Country();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCountry = null;
	}

	/**
	 * Gets the countrys.
	 *
	 * @return the countrys
	 */
	public List<Country> getCountrys() {
		return countryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idCountry != null) {
				country = countryRepository.findById(idCountry);
				if (country == null) {
					redirect("list.jsf");
				}
			} else {
				country = new Country();
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

			countryRegistration.register(country);
			successMessage("label.country.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.country-exists");
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
		idCountry = null;
		country = new Country();
	}

	/**
	 * Gets the id country.
	 *
	 * @return the id country
	 */
	public Long getIdCountry() {
		return idCountry;
	}

	/**
	 * Sets the id country.
	 *
	 * @param idCountry the new id country
	 */
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
}
