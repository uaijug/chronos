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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class EventSessionController extends AbstractManageBeans {

	@Inject
	private EventSessionRegistration eventSessionRegistration;

	@Inject
	private EventSessionRepository eventSessionRepository;

	@Inject
	private EventMainRepository eventMainRepository;

	private Long idEventSession;

	private EventSession eventSession;

	List<EventSession> eventSessions;

	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	@PostConstruct
	public void initNewEventSession() {
		eventSession = new EventSession();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventSession = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idEventSession = null;
		eventSession = new EventSession();
	}

	public List<SelectItem> getEvents() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	public Long getIdEventSession() {
		return idEventSession;
	}

	public void setIdEventSession(Long idEventSession) {
		this.idEventSession = idEventSession;
	}

	public EventSession getEventSession() {
		return eventSession;
	}

	public void setEventSession(EventSession eventSession) {
		this.eventSession = eventSession;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
	}

}
