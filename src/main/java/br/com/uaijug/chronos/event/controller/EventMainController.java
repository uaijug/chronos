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
package br.com.uaijug.chronos.event.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.service.EventMainRegistration;
import br.com.uaijug.chronos.institution.data.repository.InstitutionRepository;
import br.com.uaijug.chronos.institution.model.Institution;
import br.com.uaijug.chronos.model.types.EventType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventMainController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventMainController extends AbstractManageBeans {

	/** The event main registration. */
	@Inject
	private EventMainRegistration eventMainRegistration;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The institution repository. */
	@Inject
	private InstitutionRepository institutionRepository;

	/** The id event main. */
	private Long idEventMain;

	/** The event main. */
	private EventMain eventMain;

	/** The event mains. */
	List<EventMain> eventMains;

	/** The list institution. */
	private List<Institution> listInstitution = null;

	/** The itens state. */
	List<SelectItem> itensInstitution = null;

	/**
	 * Inits the new event main.
	 */
	@PostConstruct
	public void initNewEventMain() {
		eventMain = new EventMain();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventMain = null;

		itensInstitution = new ArrayList<SelectItem>();
		listInstitution = new ArrayList<Institution>();
	}

	/**
	 * Gets the event mains.
	 *
	 * @return the event mains
	 */
	public List<EventMain> getEventMains() {
		return eventMainRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventMain != null) {
				eventMain = eventMainRepository.findById(idEventMain);
				if (eventMain == null) {
					redirect("list.jsf");
				}
			} else {
				eventMain = new EventMain();
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

			eventMainRegistration.register(eventMain);
			successMessage("label.eventMain.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventMain-exists");
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
		idEventMain = null;
		eventMain = new EventMain();
	}

	/**
	 * Gets the institutions.
	 *
	 * @return the institutions
	 */
	public List<SelectItem> getInstitutions() {

		setListInstitution(institutionRepository.findAll());
		for (Institution p : listInstitution) {
			itensInstitution.add(new SelectItem(p, p.getDescription()));
		}
		return itensInstitution;
	}
	
	/**
	 * Gets the event types.
	 *
	 * @return the event types
	 */
	public List<SelectItem> getEventTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (EventType type : EventType.values()) {
			items.add(new SelectItem(type, type.getEventType()));
		}
		return items;
	}
	
	/**
	 * Gets the id event main.
	 *
	 * @return the id event main
	 */
	public Long getIdEventMain() {
		return idEventMain;
	}

	/**
	 * Sets the id event main.
	 *
	 * @param idEventMain the new id event main
	 */
	public void setIdEventMain(Long idEventMain) {
		this.idEventMain = idEventMain;
	}

	/**
	 * Gets the event main.
	 *
	 * @return the event main
	 */
	public EventMain getEventMain() {
		return eventMain;
	}

	/**
	 * Sets the event main.
	 *
	 * @param eventMain the new event main
	 */
	public void setEventMain(EventMain eventMain) {
		this.eventMain = eventMain;
	}

	/**
	 * Gets the list institution.
	 *
	 * @return the list institution
	 */
	public List<Institution> getListInstitution() {
		return listInstitution;
	}

	/**
	 * Sets the list institution.
	 *
	 * @param listInstitution the new list institution
	 */
	public void setListInstitution(List<Institution> listInstitution) {
		this.listInstitution = listInstitution;
	}

	/**
	 * Gets the itens institution.
	 *
	 * @return the itens institution
	 */
	public List<SelectItem> getItensInstitution() {
		return itensInstitution;
	}

	/**
	 * Sets the itens institution.
	 *
	 * @param itensInstitution the new itens institution
	 */
	public void setItensInstitution(List<SelectItem> itensInstitution) {
		this.itensInstitution = itensInstitution;
	}

}
