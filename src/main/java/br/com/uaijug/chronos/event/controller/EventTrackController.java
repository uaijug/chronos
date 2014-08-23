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
import br.com.uaijug.chronos.event.data.repository.EventTrackRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.model.EventTrack;
import br.com.uaijug.chronos.event.service.EventTrackRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class EventTrackController extends AbstractManageBeans {

	@Inject
	private EventTrackRegistration eventTrackRegistration;

	@Inject
	private EventTrackRepository eventTrackRepository;
	
	@Inject
	private EventMainRepository eventMainRepository;

	private Long idEventTrack;

	private EventTrack eventTrack;

	List<EventTrack> eventTracks;
	
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	@PostConstruct
	public void initNewEventTrack() {
		eventTrack = new EventTrack();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventTrack = null;
		
		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	public List<EventTrack> getEventTracks() {
		return eventTrackRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventTrack != null) {
				eventTrack = eventTrackRepository.findById(idEventTrack);
				if (eventTrack == null) {
					redirect("list.jsf");
				}
			} else {
				eventTrack = new EventTrack();
			}
		}
	}

	public String register() throws Exception {
		try {

			eventTrackRegistration.register(eventTrack);
			successMessage("label.eventTrack.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventTrack-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idEventTrack = null;
		eventTrack = new EventTrack();
	}
	
	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	public Long getIdEventTrack() {
		return idEventTrack;
	}

	public void setIdEventTrack(Long idEventTrack) {
		this.idEventTrack = idEventTrack;
	}

	public EventTrack getEventTrack() {
		return eventTrack;
	}

	public void setEventTrack(EventTrack eventTrack) {
		this.eventTrack = eventTrack;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}
}
