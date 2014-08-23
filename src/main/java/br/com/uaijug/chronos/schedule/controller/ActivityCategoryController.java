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
import br.com.uaijug.chronos.schedule.data.repository.ActivityCategoryRepository;
import br.com.uaijug.chronos.schedule.model.ActivityCategory;
import br.com.uaijug.chronos.schedule.service.ActivityCategoryRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ActivityCategoryController extends AbstractManageBeans {

	@Inject
	private ActivityCategoryRegistration activityCategoryRegistration;

	@Inject
	private ActivityCategoryRepository activityCategoryRepository;

	@Inject
	private EventMainRepository eventMainRepository;

	private Long idActivityCategory;

	private ActivityCategory activityCategory;

	List<ActivityCategory> activityCategorys;

	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	@PostConstruct
	public void initNewActivityCategory() {
		activityCategory = new ActivityCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idActivityCategory = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	public List<ActivityCategory> getActivityCategorys() {
		return activityCategoryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idActivityCategory != null) {
				activityCategory = activityCategoryRepository.findById(idActivityCategory);
				if (activityCategory == null) {
					redirect("list.jsf");
				}
			} else {
				activityCategory = new ActivityCategory();
			}
		}
	}

	public String register() throws Exception {
		try {

			activityCategoryRegistration.register(activityCategory);
			successMessage("label.activityCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.activityCategory-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idActivityCategory = null;
		activityCategory = new ActivityCategory();
	}

	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}
	
	public Long getIdActivityCategory() {
		return idActivityCategory;
	}

	public void setIdActivityCategory(Long idActivityCategory) {
		this.idActivityCategory = idActivityCategory;
	}

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

}
