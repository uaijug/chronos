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
import br.com.uaijug.chronos.event.data.repository.EventRoomRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.model.EventRoom;
import br.com.uaijug.chronos.event.service.EventRoomRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class EventRoomController extends AbstractManageBeans {

	@Inject
	private EventRoomRegistration eventRoomRegistration;

	@Inject
	private EventRoomRepository eventRoomRepository;
	
	@Inject
	private EventMainRepository eventMainRepository;

	private Long idEventRoom;

	private EventRoom eventRoom;

	List<EventRoom> eventRooms;
	
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	@PostConstruct
	public void initNewEventRoom() {
		eventRoom = new EventRoom();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventRoom = null;
		
		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	public List<EventRoom> getEventRooms() {
		return eventRoomRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventRoom != null) {
				eventRoom = eventRoomRepository.findById(idEventRoom);
				if (eventRoom == null) {
					redirect("list.jsf");
				}
			} else {
				eventRoom = new EventRoom();
			}
		}
	}

	public String register() throws Exception {
		try {

			eventRoomRegistration.register(eventRoom);
			successMessage("label.eventRoom.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventRoom-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idEventRoom = null;
		eventRoom = new EventRoom();
	}

	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}
	
	public Long getIdEventRoom() {
		return idEventRoom;
	}

	public void setIdEventRoom(Long idEventRoom) {
		this.idEventRoom = idEventRoom;
	}

	public EventRoom getEventRoom() {
		return eventRoom;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}
	public void setEventRoom(EventRoom eventRoom) {
		this.eventRoom = eventRoom;
	}

}
