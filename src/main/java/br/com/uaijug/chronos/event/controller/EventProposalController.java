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
import br.com.uaijug.chronos.event.data.repository.EventProposalRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.model.EventProposal;
import br.com.uaijug.chronos.event.service.EventProposalRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventProposalController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventProposalController extends AbstractManageBeans {

	/** The event proposal registration. */
	@Inject
	private EventProposalRegistration eventProposalRegistration;

	/** The event proposal repository. */
	@Inject
	private EventProposalRepository eventProposalRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The id event proposal. */
	private Long idEventProposal;

	/** The event proposal. */
	private EventProposal eventProposal;

	/** The event proposals. */
	List<EventProposal> eventProposals;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	/**
	 * Inits the new event proposal.
	 */
	@PostConstruct
	public void initNewEventProposal() {
		eventProposal = new EventProposal();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventProposal = null;

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

	/**
	 * Gets the event proposals.
	 *
	 * @return the event proposals
	 */
	public List<EventProposal> getEventProposals() {
		return eventProposalRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventProposal != null) {
				eventProposal = eventProposalRepository
						.findById(idEventProposal);
				if (eventProposal == null) {
					redirect("list.jsf");
				}
			} else {
				eventProposal = new EventProposal();
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

			eventProposalRegistration.register(eventProposal);
			successMessage("label.eventProposal.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventProposal-exists");
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
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	/**
	 * Limpar.
	 */
	private void limpar() {
		idEventProposal = null;
		eventProposal = new EventProposal();
	}

	/**
	 * Gets the id event proposal.
	 *
	 * @return the id event proposal
	 */
	public Long getIdEventProposal() {
		return idEventProposal;
	}

	/**
	 * Sets the id event proposal.
	 *
	 * @param idEventProposal the new id event proposal
	 */
	public void setIdEventProposal(Long idEventProposal) {
		this.idEventProposal = idEventProposal;
	}

	/**
	 * Gets the event proposal.
	 *
	 * @return the event proposal
	 */
	public EventProposal getEventProposal() {
		return eventProposal;
	}

	/**
	 * Sets the event proposal.
	 *
	 * @param eventProposal the new event proposal
	 */
	public void setEventProposal(EventProposal eventProposal) {
		this.eventProposal = eventProposal;
	}

	/**
	 * Gets the list event.
	 *
	 * @return the list event
	 */
	public List<EventMain> getListEvent() {
		return listEvent;
	}

	/**
	 * Sets the list event.
	 *
	 * @param listEvent the new list event
	 */
	public void setListEvent(List<EventMain> listEvent) {
		this.listEvent = listEvent;
	}

	/**
	 * Gets the itens event.
	 *
	 * @return the itens event
	 */
	public List<SelectItem> getItensEvent() {
		return itensEvent;
	}

	/**
	 * Sets the itens event.
	 *
	 * @param itensEvent the new itens event
	 */
	public void setItensEvent(List<SelectItem> itensEvent) {
		this.itensEvent = itensEvent;
	}

}
