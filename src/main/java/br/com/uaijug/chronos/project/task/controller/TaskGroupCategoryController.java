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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.project.task.data.repository.TaskGroupCategoryRepository;
import br.com.uaijug.chronos.project.task.model.TaskGroupCategory;
import br.com.uaijug.chronos.project.task.service.TaskGroupCategoryRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TaskGroupCategoryController extends AbstractManageBeans {

	@Inject
	private TaskGroupCategoryRegistration taskGroupCategoryRegistration;

	@Inject
	private TaskGroupCategoryRepository taskGroupCategoryRepository;

	private Long idTaskGroupCategory;

	private TaskGroupCategory taskGroupCategory;

	List<TaskGroupCategory> taskGroupCategorys;

	@PostConstruct
	public void initNewTaskGroupCategory() {
		taskGroupCategory = new TaskGroupCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskGroupCategory = null;
	}

	public List<TaskGroupCategory> getTaskGroupCategorys() {
		return taskGroupCategoryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idTaskGroupCategory != null) {
				taskGroupCategory = taskGroupCategoryRepository.findById(idTaskGroupCategory);
				if (taskGroupCategory == null) {
					redirect("list.jsf");
				}
			} else {
				taskGroupCategory = new TaskGroupCategory();
			}
		}
	}

	public String register() throws Exception {
		try {

			taskGroupCategoryRegistration.register(taskGroupCategory);
			successMessage("label.taskGroupCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.taskGroupCategory-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idTaskGroupCategory = null;
		taskGroupCategory = new TaskGroupCategory();
	}

	public Long getIdTaskGroupCategory() {
		return idTaskGroupCategory;
	}

	public void setIdTaskGroupCategory(Long idTaskGroupCategory) {
		this.idTaskGroupCategory = idTaskGroupCategory;
	}

	public TaskGroupCategory getTaskGroupCategory() {
		return taskGroupCategory;
	}

	public void setTaskGroupCategory(TaskGroupCategory taskGroupCategory) {
		this.taskGroupCategory = taskGroupCategory;
	}
}
