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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class EventScheduleController extends AbstractManageBeans {

	@Inject
	private EventScheduleRegistration eventScheduleRegistration;

	@Inject
	private EventScheduleRepository eventScheduleRepository;

	private Long idEventSchedule;

	private EventSchedule eventSchedule;

	List<EventSchedule> eventSchedules;

	@PostConstruct
	public void initNewEventSchedule() {
		eventSchedule = new EventSchedule();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idEventSchedule = null;
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idEventSchedule = null;
		eventSchedule = new EventSchedule();
	}

	public Long getIdEventSchedule() {
		return idEventSchedule;
	}

	public void setIdEventSchedule(Long idEventSchedule) {
		this.idEventSchedule = idEventSchedule;
	}

	public EventSchedule getEventSchedule() {
		return eventSchedule;
	}

	public void setEventSchedule(EventSchedule eventSchedule) {
		this.eventSchedule = eventSchedule;
	}

}
