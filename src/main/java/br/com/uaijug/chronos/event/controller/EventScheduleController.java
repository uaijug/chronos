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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventScheduleRepository;
import br.com.uaijug.chronos.event.model.EventSchedule;
import br.com.uaijug.chronos.event.service.EventScheduleRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class EventScheduleController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class EventScheduleController extends AbstractManageBeans {

	/** The event schedule registration. */
	@Inject
	private EventScheduleRegistration eventScheduleRegistration;

	/** The event schedule repository. */
	@Inject
	private EventScheduleRepository eventScheduleRepository;

	/** The id event schedule. */
	private Long idEventSchedule;

	/** The event schedule. */
	private EventSchedule eventSchedule;

	/** The event schedules. */
	List<EventSchedule> eventSchedules;

	/**
	 * Inits the new event schedule.
	 */
	@PostConstruct
	public void initNewEventSchedule() {
		eventSchedule = new EventSchedule();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventSchedule = null;
	}

	/**
	 * Gets the event schedules.
	 *
	 * @return the event schedules
	 */
	public List<EventSchedule> getEventSchedules() {
		return eventScheduleRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idEventSchedule != null) {
				eventSchedule = eventScheduleRepository.findById(idEventSchedule);
				if (eventSchedule == null) {
					redirect("list.jsf");
				}
			} else {
				eventSchedule = new EventSchedule();
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

			eventScheduleRegistration.register(eventSchedule);
			successMessage("label.eventSchedule.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.eventSchedule-exists");
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
		idEventSchedule = null;
		eventSchedule = new EventSchedule();
	}

	/**
	 * Gets the id event schedule.
	 *
	 * @return the id event schedule
	 */
	public Long getIdEventSchedule() {
		return idEventSchedule;
	}

	/**
	 * Sets the id event schedule.
	 *
	 * @param idEventSchedule the new id event schedule
	 */
	public void setIdEventSchedule(Long idEventSchedule) {
		this.idEventSchedule = idEventSchedule;
	}

	/**
	 * Gets the event schedule.
	 *
	 * @return the event schedule
	 */
	public EventSchedule getEventSchedule() {
		return eventSchedule;
	}

	/**
	 * Sets the event schedule.
	 *
	 * @param eventSchedule the new event schedule
	 */
	public void setEventSchedule(EventSchedule eventSchedule) {
		this.eventSchedule = eventSchedule;
	}

}
