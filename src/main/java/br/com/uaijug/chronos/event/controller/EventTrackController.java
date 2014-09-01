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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventTrackController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventTrackController extends AbstractManageBeans {

	/** The event track registration. */
	@Inject
	private EventTrackRegistration eventTrackRegistration;

	/** The event track repository. */
	@Inject
	private EventTrackRepository eventTrackRepository;
	
	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id event track. */
	private Long idEventTrack;

	/** The event track. */
	private EventTrack eventTrack;

	/** The event tracks. */
	List<EventTrack> eventTracks;
	
	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new event track.
	 */
	@PostConstruct
	public void initNewEventTrack() {
		eventTrack = new EventTrack();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventTrack = null;
		
		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the event tracks.
	 *
	 * @return the event tracks
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idEventTrack = null;
		eventTrack = new EventTrack();
	}
	
	/**
	 * Gets the event mains.
	 *
	 * @return the event mains
	 */
	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	/**
	 * Gets the id event track.
	 *
	 * @return the id event track
	 */
	public Long getIdEventTrack() {
		return idEventTrack;
	}

	/**
	 * Sets the id event track.
	 *
	 * @param idEventTrack the new id event track
	 */
	public void setIdEventTrack(Long idEventTrack) {
		this.idEventTrack = idEventTrack;
	}

	/**
	 * Gets the event track.
	 *
	 * @return the event track
	 */
	public EventTrack getEventTrack() {
		return eventTrack;
	}

	/**
	 * Sets the event track.
	 *
	 * @param eventTrack the new event track
	 */
	public void setEventTrack(EventTrack eventTrack) {
		this.eventTrack = eventTrack;
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
}
