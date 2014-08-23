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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class StateController extends AbstractManageBeans {

	@Inject
	private StateRegistration stateRegistration;

	@Inject
	private StateRepository stateRepository;

	@Inject
	private CountryRepository countryRepository;

	private Long idState;

	private State state;

	List<State> states;

	private List<Country> listCountry = null;

	/** The itens state. */
	List<SelectItem> itensCountry = null;

	@PostConstruct
	public void initNewState() {
		state = new State();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idState = null;

		itensCountry = new ArrayList<SelectItem>();
		listCountry = new ArrayList<Country>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	public List<SelectItem> getCountrys() {

		setListCountry(countryRepository.findAll());
		for (Country p : listCountry) {
			itensCountry.add(new SelectItem(p, p.getDescription()));
		}
		return itensCountry;
	}

	private void limpar() {
		idState = null;
		state = new State();
	}

	public Long getIdState() {
		return idState;
	}

	public void setIdState(Long idState) {
		this.idState = idState;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Country> getListCountry() {
		return listCountry;
	}

	public void setListCountry(List<Country> listCountry) {
		this.listCountry = listCountry;
	}

	public List<SelectItem> getItensCountry() {
		return itensCountry;
	}

	public void setItensCountry(List<SelectItem> itensCountry) {
		this.itensCountry = itensCountry;
	}

}
