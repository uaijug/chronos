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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventRoomController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventRoomController extends AbstractManageBeans {

	/** The event room registration. */
	@Inject
	private EventRoomRegistration eventRoomRegistration;

	/** The event room repository. */
	@Inject
	private EventRoomRepository eventRoomRepository;
	
	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id event room. */
	private Long idEventRoom;

	/** The event room. */
	private EventRoom eventRoom;

	/** The event rooms. */
	List<EventRoom> eventRooms;
	
	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new event room.
	 */
	@PostConstruct
	public void initNewEventRoom() {
		eventRoom = new EventRoom();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventRoom = null;
		
		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the event rooms.
	 *
	 * @return the event rooms
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idEventRoom = null;
		eventRoom = new EventRoom();
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
	 * Gets the id event room.
	 *
	 * @return the id event room
	 */
	public Long getIdEventRoom() {
		return idEventRoom;
	}

	/**
	 * Sets the id event room.
	 *
	 * @param idEventRoom the new id event room
	 */
	public void setIdEventRoom(Long idEventRoom) {
		this.idEventRoom = idEventRoom;
	}

	/**
	 * Gets the event room.
	 *
	 * @return the event room
	 */
	public EventRoom getEventRoom() {
		return eventRoom;
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
	 * Sets the event room.
	 *
	 * @param eventRoom the new event room
	 */
	public void setEventRoom(EventRoom eventRoom) {
		this.eventRoom = eventRoom;
	}

}
