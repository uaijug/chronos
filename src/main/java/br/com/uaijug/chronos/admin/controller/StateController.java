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

import br.com.uaijug.chronos.admin.data.repository.CountryRepository;
import br.com.uaijug.chronos.admin.data.repository.StateRepository;
import br.com.uaijug.chronos.admin.model.Country;
import br.com.uaijug.chronos.admin.model.State;
import br.com.uaijug.chronos.admin.service.StateRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class StateController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class StateController extends AbstractManageBeans {

	/** The state registration. */
	@Inject
	private StateRegistration stateRegistration;

	/** The state repository. */
	@Inject
	private StateRepository stateRepository;

	/** The country repository. */
	@Inject
	private CountryRepository countryRepository;

	/** The id state. */
	private Long idState;

	/** The state. */
	private State state;

	/** The states. */
	List<State> states;

	/** The list country. */
	private List<Country> listCountry = null;

	/** The itens state. */
	List<SelectItem> itensCountry = null;

	/**
	 * Inits the new state.
	 */
	@PostConstruct
	public void initNewState() {
		state = new State();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idState = null;

		itensCountry = new ArrayList<SelectItem>();
		listCountry = new ArrayList<Country>();
	}

	/**
	 * Gets the states.
	 *
	 * @return the states
	 */
	public List<State> getStates() {
		return stateRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idState != null) {
				state = stateRepository.findById(idState);
				if (state == null) {
					redirect("list.jsf");
				}
			} else {
				state = new State();
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

			stateRegistration.register(state);
			successMessage("label.state.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.state-exists");
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
	 * Gets the countrys.
	 *
	 * @return the countrys
	 */
	public List<SelectItem> getCountrys() {

		setListCountry(countryRepository.findAll());
		for (Country p : listCountry) {
			itensCountry.add(new SelectItem(p, p.getDescription()));
		}
		return itensCountry;
	}

	/**
	 * Limpar.
	 */
	private void limpar() {
		idState = null;
		state = new State();
	}

	/**
	 * Gets the id state.
	 *
	 * @return the id state
	 */
	public Long getIdState() {
		return idState;
	}

	/**
	 * Sets the id state.
	 *
	 * @param idState the new id state
	 */
	public void setIdState(Long idState) {
		this.idState = idState;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets the list country.
	 *
	 * @return the list country
	 */
	public List<Country> getListCountry() {
		return listCountry;
	}

	/**
	 * Sets the list country.
	 *
	 * @param listCountry the new list country
	 */
	public void setListCountry(List<Country> listCountry) {
		this.listCountry = listCountry;
	}

	/**
	 * Gets the itens country.
	 *
	 * @return the itens country
	 */
	public List<SelectItem> getItensCountry() {
		return itensCountry;
	}

	/**
	 * Sets the itens country.
	 *
	 * @param itensCountry the new itens country
	 */
	public void setItensCountry(List<SelectItem> itensCountry) {
		this.itensCountry = itensCountry;
	}

}
