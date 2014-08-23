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

import br.com.uaijug.chronos.admin.data.repository.CityRepository;
import br.com.uaijug.chronos.admin.data.repository.StateRepository;
import br.com.uaijug.chronos.admin.model.City;
import br.com.uaijug.chronos.admin.model.State;
import br.com.uaijug.chronos.admin.service.CityRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class CityController extends AbstractManageBeans {

	@Inject
	private CityRegistration cityRegistration;

	@Inject
	private CityRepository cityRepository;
	
	@Inject
	private StateRepository stateRepository;

	private Long idCity;

	private City city;

	List<City> citys;
	
	private List<State> listState = null;

	/** The itens state. */
	List<SelectItem> itensState = null;

	@PostConstruct
	public void initNewCity() {
		city = new City();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCity = null;
		
		itensState = new ArrayList<SelectItem>();
		listState = new ArrayList<State>();
	}

	public List<City> getCitys() {
		return cityRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idCity != null) {
				city = cityRepository.findById(idCity);
				if (city == null) {
					redirect("list.jsf");
				}
			} else {
				city = new City();
			}
		}
	}

	public String register() throws Exception {
		try {

			cityRegistration.register(city);
			successMessage("label.city.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.city-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idCity = null;
		city = new City();
	}

	public List<SelectItem> getStates() {

		setListState(stateRepository.findAll());
		for (State p : listState) {
			itensState.add(new SelectItem(p, p.getDescription()));
		}
		return itensState;
	}
	
	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<State> getListState() {
		return listState;
	}

	public void setListState(List<State> listState) {
		this.listState = listState;
	}

	public List<SelectItem> getItensState() {
		return itensState;
	}

	public void setItensState(List<SelectItem> itensState) {
		this.itensState = itensState;
	}
}
