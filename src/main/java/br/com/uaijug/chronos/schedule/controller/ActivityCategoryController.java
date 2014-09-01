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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ActivityCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class ActivityCategoryController extends AbstractManageBeans {

	/** The activity category registration. */
	@Inject
	private ActivityCategoryRegistration activityCategoryRegistration;

	/** The activity category repository. */
	@Inject
	private ActivityCategoryRepository activityCategoryRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id activity category. */
	private Long idActivityCategory;

	/** The activity category. */
	private ActivityCategory activityCategory;

	/** The activity categorys. */
	List<ActivityCategory> activityCategorys;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new activity category.
	 */
	@PostConstruct
	public void initNewActivityCategory() {
		activityCategory = new ActivityCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idActivityCategory = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the activity categorys.
	 *
	 * @return the activity categorys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idActivityCategory = null;
		activityCategory = new ActivityCategory();
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
	 * Gets the id activity category.
	 *
	 * @return the id activity category
	 */
	public Long getIdActivityCategory() {
		return idActivityCategory;
	}

	/**
	 * Sets the id activity category.
	 *
	 * @param idActivityCategory the new id activity category
	 */
	public void setIdActivityCategory(Long idActivityCategory) {
		this.idActivityCategory = idActivityCategory;
	}

	/**
	 * Gets the activity category.
	 *
	 * @return the activity category
	 */
	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	/**
	 * Sets the activity category.
	 *
	 * @param activityCategory the new activity category
	 */
	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
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
