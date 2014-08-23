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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ActivityController extends AbstractManageBeans {

	@Inject
	private ActivityRegistration activityRegistration;

	@Inject
	private ActivityRepository activityRepository;

	@Inject
	private EventMainRepository eventMainRepository;

	@Inject
	private ProjectRepository projectRepository;

	@Inject
	private ActivityCategoryRepository activityCategoryRepository;

	private Long idActivity;

	private Activity activity;

	List<Activity> activitys;

	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	private List<Project> listProject = null;

	/** The itens state. */
	List<SelectItem> itensProject = null;

	private List<ActivityCategory> listActivityCategory = null;

	/** The itens state. */
	List<SelectItem> itensActivityCategory = null;

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idActivity = null;
		activity = new Activity();
	}

	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	public List<SelectItem> getProjects() {

		setListProject(projectRepository.findAll());
		for (Project p : listProject) {
			itensProject.add(new SelectItem(p, p.getDescription()));
		}
		return itensProject;
	}

	public List<SelectItem> getActivityCategorys() {

		setListActivityCategory(activityCategoryRepository.findAll());
		for (ActivityCategory p : listActivityCategory) {
			itensActivityCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensActivityCategory;
	}

	public List<SelectItem> getCompleteds() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Sim"));
		lst.add(new SelectItem("1", "Nao"));
		return lst;
	}

	public List<SelectItem> getApproveds() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Sim"));
		lst.add(new SelectItem("1", "Nao"));
		return lst;
	}

	public Long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Long idActivity) {
		this.idActivity = idActivity;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	public List<Project> getListProject() {
		return listProject;
	}

	public void setListProject(List<Project> listProject) {
		this.listProject = listProject;
	}

	public List<SelectItem> getItensProject() {
		return itensProject;
	}

	public void setItensProject(List<SelectItem> itensProject) {
		this.itensProject = itensProject;
	}

	public List<ActivityCategory> getListActivityCategory() {
		return listActivityCategory;
	}

	public void setListActivityCategory(
			List<ActivityCategory> listActivityCategory) {
		this.listActivityCategory = listActivityCategory;
	}

	public List<SelectItem> getItensActivityCategory() {
		return itensActivityCategory;
	}

	public void setItensActivityCategory(List<SelectItem> itensActivityCategory) {
		this.itensActivityCategory = itensActivityCategory;
	}

}
