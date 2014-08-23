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
import br.com.uaijug.chronos.project.task.data.repository.TaskCategoryRepository;
import br.com.uaijug.chronos.project.task.model.TaskCategory;
import br.com.uaijug.chronos.project.task.service.TaskCategoryRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TaskCategoryController extends AbstractManageBeans {

	@Inject
	private TaskCategoryRegistration taskCategoryRegistration;

	@Inject
	private TaskCategoryRepository taskCategoryRepository;

	private Long idTaskCategory;

	private TaskCategory taskCategory;

	List<TaskCategory> taskCategorys;

	@PostConstruct
	public void initNewTaskCategory() {
		taskCategory = new TaskCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskCategory = null;
	}

	public List<TaskCategory> getTaskCategorys() {
		return taskCategoryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idTaskCategory != null) {
				taskCategory = taskCategoryRepository.findById(idTaskCategory);
				if (taskCategory == null) {
					redirect("list.jsf");
				}
			} else {
				taskCategory = new TaskCategory();
			}
		}
	}

	public String register() throws Exception {
		try {

			taskCategoryRegistration.register(taskCategory);
			successMessage("label.taskCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.taskCategory-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idTaskCategory = null;
		taskCategory = new TaskCategory();
	}

	public Long getIdTaskCategory() {
		return idTaskCategory;
	}

	public void setIdTaskCategory(Long idTaskCategory) {
		this.idTaskCategory = idTaskCategory;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

}
