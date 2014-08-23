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
package br.com.uaijug.chronos.project.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.project.task.data.repository.TaskGroupCategoryRepository;
import br.com.uaijug.chronos.project.task.data.repository.TaskGroupRepository;
import br.com.uaijug.chronos.project.task.model.TaskGroup;
import br.com.uaijug.chronos.project.task.model.TaskGroupCategory;
import br.com.uaijug.chronos.project.task.service.TaskGroupRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TaskGroupController extends AbstractManageBeans {

	@Inject
	private TaskGroupRegistration taskGroupRegistration;

	@Inject
	private TaskGroupRepository taskGroupRepository;

	@Inject
	private TaskGroupCategoryRepository taskGroupCategoryRepository;

	private Long idTaskGroup;

	private TaskGroup taskGroup;

	List<TaskGroup> taskGroups;

	private List<TaskGroupCategory> listTaskGroupCategory = null;

	/** The itens state. */
	List<SelectItem> itensTaskGroupCategory = null;

	@PostConstruct
	public void initNewTaskGroup() {
		taskGroup = new TaskGroup();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskGroup = null;

		itensTaskGroupCategory = new ArrayList<SelectItem>();
		listTaskGroupCategory = new ArrayList<TaskGroupCategory>();
	}

	public List<TaskGroup> getTaskGroups() {
		return taskGroupRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idTaskGroup != null) {
				taskGroup = taskGroupRepository.findById(idTaskGroup);
				if (taskGroup == null) {
					redirect("list.jsf");
				}
			} else {
				taskGroup = new TaskGroup();
			}
		}
	}

	public String register() throws Exception {
		try {

			taskGroupRegistration.register(taskGroup);
			successMessage("label.taskGroup.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.taskGroup-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idTaskGroup = null;
		taskGroup = new TaskGroup();
	}

	public List<SelectItem> getTaskGroupCategorys() {

		setListTaskGroupCategory(taskGroupCategoryRepository.findAll());
		for (TaskGroupCategory p : listTaskGroupCategory) {
			itensTaskGroupCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensTaskGroupCategory;
	}

	public Long getIdTaskGroup() {
		return idTaskGroup;
	}

	public void setIdTaskGroup(Long idTaskGroup) {
		this.idTaskGroup = idTaskGroup;
	}

	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}

	public List<TaskGroupCategory> getListTaskGroupCategory() {
		return listTaskGroupCategory;
	}

	public void setListTaskGroupCategory(
			List<TaskGroupCategory> listTaskGroupCategory) {
		this.listTaskGroupCategory = listTaskGroupCategory;
	}

	public List<SelectItem> getItensTaskGroupCategory() {
		return itensTaskGroupCategory;
	}

	public void setItensTaskGroupCategory(
			List<SelectItem> itensTaskGroupCategory) {
		this.itensTaskGroupCategory = itensTaskGroupCategory;
	}

}
