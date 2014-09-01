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
import br.com.uaijug.chronos.project.data.repository.ProjectRepository;
import br.com.uaijug.chronos.project.model.Project;
import br.com.uaijug.chronos.schedule.data.repository.ActivityCategoryRepository;
import br.com.uaijug.chronos.schedule.data.repository.ActivityRepository;
import br.com.uaijug.chronos.schedule.model.Activity;
import br.com.uaijug.chronos.schedule.model.ActivityCategory;
import br.com.uaijug.chronos.schedule.service.ActivityRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ActivityController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class ActivityController extends AbstractManageBeans {

	/** The activity registration. */
	@Inject
	private ActivityRegistration activityRegistration;

	/** The activity repository. */
	@Inject
	private ActivityRepository activityRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The project repository. */
	@Inject
	private ProjectRepository projectRepository;

	/** The activity category repository. */
	@Inject
	private ActivityCategoryRepository activityCategoryRepository;

	/** The id activity. */
	private Long idActivity;

	/** The activity. */
	private Activity activity;

	/** The activitys. */
	List<Activity> activitys;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/** The list project. */
	private List<Project> listProject = null;

	/** The itens state. */
	List<SelectItem> itensProject = null;

	/** The list activity category. */
	private List<ActivityCategory> listActivityCategory = null;

	/** The itens state. */
	List<SelectItem> itensActivityCategory = null;

	/**
	 * Inits the new activity.
	 */
	@PostConstruct
	public void initNewActivity() {
		activity = new Activity();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idActivity = null;

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();

		itensProject = new ArrayList<SelectItem>();
		listProject = new ArrayList<Project>();

		itensActivityCategory = new ArrayList<SelectItem>();
		listActivityCategory = new ArrayList<ActivityCategory>();
	}

	/**
	 * Gets the activitys.
	 *
	 * @return the activitys
	 */
	public List<Activity> getActivitys() {
		return activityRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idActivity != null) {
				activity = activityRepository.findById(idActivity);
				if (activity == null) {
					redirect("list.jsf");
				}
			} else {
				activity = new Activity();
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

			activityRegistration.register(activity);
			successMessage("label.activity.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.activity-exists");
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
		idActivity = null;
		activity = new Activity();
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
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<SelectItem> getProjects() {

		setListProject(projectRepository.findAll());
		for (Project p : listProject) {
			itensProject.add(new SelectItem(p, p.getDescription()));
		}
		return itensProject;
	}

	/**
	 * Gets the activity categorys.
	 *
	 * @return the activity categorys
	 */
	public List<SelectItem> getActivityCategorys() {

		setListActivityCategory(activityCategoryRepository.findAll());
		for (ActivityCategory p : listActivityCategory) {
			itensActivityCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensActivityCategory;
	}

	/**
	 * Gets the completeds.
	 *
	 * @return the completeds
	 */
	public List<SelectItem> getCompleteds() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Sim"));
		lst.add(new SelectItem("1", "Nao"));
		return lst;
	}

	/**
	 * Gets the approveds.
	 *
	 * @return the approveds
	 */
	public List<SelectItem> getApproveds() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Sim"));
		lst.add(new SelectItem("1", "Nao"));
		return lst;
	}

	/**
	 * Gets the id activity.
	 *
	 * @return the id activity
	 */
	public Long getIdActivity() {
		return idActivity;
	}

	/**
	 * Sets the id activity.
	 *
	 * @param idActivity the new id activity
	 */
	public void setIdActivity(Long idActivity) {
		this.idActivity = idActivity;
	}

	/**
	 * Gets the activity.
	 *
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Sets the activity.
	 *
	 * @param activity the new activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
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
	 * Gets the list project.
	 *
	 * @return the list project
	 */
	public List<Project> getListProject() {
		return listProject;
	}

	/**
	 * Sets the list project.
	 *
	 * @param listProject the new list project
	 */
	public void setListProject(List<Project> listProject) {
		this.listProject = listProject;
	}

	/**
	 * Gets the itens project.
	 *
	 * @return the itens project
	 */
	public List<SelectItem> getItensProject() {
		return itensProject;
	}

	/**
	 * Sets the itens project.
	 *
	 * @param itensProject the new itens project
	 */
	public void setItensProject(List<SelectItem> itensProject) {
		this.itensProject = itensProject;
	}

	/**
	 * Gets the list activity category.
	 *
	 * @return the list activity category
	 */
	public List<ActivityCategory> getListActivityCategory() {
		return listActivityCategory;
	}

	/**
	 * Sets the list activity category.
	 *
	 * @param listActivityCategory the new list activity category
	 */
	public void setListActivityCategory(
			List<ActivityCategory> listActivityCategory) {
		this.listActivityCategory = listActivityCategory;
	}

	/**
	 * Gets the itens activity category.
	 *
	 * @return the itens activity category
	 */
	public List<SelectItem> getItensActivityCategory() {
		return itensActivityCategory;
	}

	/**
	 * Sets the itens activity category.
	 *
	 * @param itensActivityCategory the new itens activity category
	 */
	public void setItensActivityCategory(List<SelectItem> itensActivityCategory) {
		this.itensActivityCategory = itensActivityCategory;
	}

}
