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
import br.com.uaijug.chronos.event.data.repository.EventSessionRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.model.EventSession;
import br.com.uaijug.chronos.event.service.EventSessionRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventSessionController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventSessionController extends AbstractManageBeans {

	/** The event session registration. */
	@Inject
	private EventSessionRegistration eventSessionRegistration;

	/** The event session repository. */
	@Inject
	private EventSessionRepository eventSessionRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id event session. */
	private Long idEventSession;

	/** The event session. */
	private EventSession eventSession;

	/** The event sessions. */
	List<EventSession> eventSessions;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new event session.
	 */
	@PostConstruct
	public void initNewEventSession() {
		eventSession = new EventSession();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventSession = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the event sessions.
	 *
	 * @return the event sessions
	 */
	public List<EventSession> getEventSessions() {
		return eventSessionRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventSession != null) {
				eventSession = eventSessionRepository.findById(idEventSession);
				if (eventSession == null) {
					redirect("list.jsf");
				}
			} else {
				eventSession = new EventSession();
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

			eventSessionRegistration.register(eventSession);
			successMessage("label.eventSession.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventSession-exists");
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
		idEventSession = null;
		eventSession = new EventSession();
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<SelectItem> getEvents() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	/**
	 * Gets the id event session.
	 *
	 * @return the id event session
	 */
	public Long getIdEventSession() {
		return idEventSession;
	}

	/**
	 * Sets the id event session.
	 *
	 * @param idEventSession the new id event session
	 */
	public void setIdEventSession(Long idEventSession) {
		this.idEventSession = idEventSession;
	}

	/**
	 * Gets the event session.
	 *
	 * @return the event session
	 */
	public EventSession getEventSession() {
		return eventSession;
	}

	/**
	 * Sets the event session.
	 *
	 * @param eventSession the new event session
	 */
	public void setEventSession(EventSession eventSession) {
		this.eventSession = eventSession;
	}

	/**
	 * Gets the list event main.
	 *
	 * @return the list event main
	 */
	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	/**
	 * Sets the list event main.
	 *
	 * @param listEventMain the new list event main
	 */
	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	/**
	 * Gets the itens event main.
	 *
	 * @return the itens event main
	 */
	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	/**
	 * Sets the itens event main.
	 *
	 * @param itensEventMain the new itens event main
	 */
	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
	}

}
