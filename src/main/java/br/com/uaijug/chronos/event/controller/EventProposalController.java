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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class EventProposalController extends AbstractManageBeans {

	@Inject
	private EventProposalRegistration eventProposalRegistration;

	@Inject
	private EventProposalRepository eventProposalRepository;

	@Inject
	private EventMainRepository eventRepository;

	private Long idEventProposal;

	private EventProposal eventProposal;

	List<EventProposal> eventProposals;

	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	@PostConstruct
	public void initNewEventProposal() {
		eventProposal = new EventProposal();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventProposal = null;

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}
	
	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	private void limpar() {
		idEventProposal = null;
		eventProposal = new EventProposal();
	}

	public Long getIdEventProposal() {
		return idEventProposal;
	}

	public void setIdEventProposal(Long idEventProposal) {
		this.idEventProposal = idEventProposal;
	}

	public EventProposal getEventProposal() {
		return eventProposal;
	}

	public void setEventProposal(EventProposal eventProposal) {
		this.eventProposal = eventProposal;
	}

	public List<EventMain> getListEvent() {
		return listEvent;
	}

	public void setListEvent(List<EventMain> listEvent) {
		this.listEvent = listEvent;
	}

	public List<SelectItem> getItensEvent() {
		return itensEvent;
	}

	public void setItensEvent(List<SelectItem> itensEvent) {
		this.itensEvent = itensEvent;
	}

}
