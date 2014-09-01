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
package br.com.uaijug.chronos.schedule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.types.ScheduleType;
import br.com.uaijug.chronos.schedule.data.repository.ScheduleRepository;
import br.com.uaijug.chronos.schedule.model.Schedule;
import br.com.uaijug.chronos.schedule.service.ScheduleRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ScheduleController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class ScheduleController extends AbstractManageBeans {

	/** The schedule registration. */
	@Inject
	private ScheduleRegistration scheduleRegistration;

	/** The schedule repository. */
	@Inject
	private ScheduleRepository scheduleRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id schedule. */
	private Long idSchedule;

	/** The schedule. */
	private Schedule schedule;

	/** The schedules. */
	List<Schedule> schedules;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new schedule.
	 */
	@PostConstruct
	public void initNewSchedule() {
		schedule = new Schedule();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSchedule = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the schedules.
	 *
	 * @return the schedules
	 */
	public List<Schedule> getSchedules() {
		return scheduleRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSchedule != null) {
				schedule = scheduleRepository.findById(idSchedule);
				if (schedule == null) {
					redirect("list.jsf");
				}
			} else {
				schedule = new Schedule();
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

			scheduleRegistration.register(schedule);
			successMessage("label.schedule.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.schedule-exists");
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
		idSchedule = null;
		schedule = new Schedule();
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
	 * Gets the schedule types.
	 *
	 * @return the schedule types
	 */
	public List<SelectItem> getScheduleTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (ScheduleType type : ScheduleType.values()) {
			items.add(new SelectItem(type, type.toString()));
		}
		return items;
	}

	/**
	 * Gets the id schedule.
	 *
	 * @return the id schedule
	 */
	public Long getIdSchedule() {
		return idSchedule;
	}

	/**
	 * Sets the id schedule.
	 *
	 * @param idSchedule the new id schedule
	 */
	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}

	/**
	 * Gets the schedule.
	 *
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * Sets the schedule.
	 *
	 * @param schedule the new schedule
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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
