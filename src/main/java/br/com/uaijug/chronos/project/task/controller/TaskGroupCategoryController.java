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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class TaskGroupCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class TaskGroupCategoryController extends AbstractManageBeans {

	/** The task group category registration. */
	@Inject
	private TaskGroupCategoryRegistration taskGroupCategoryRegistration;

	/** The task group category repository. */
	@Inject
	private TaskGroupCategoryRepository taskGroupCategoryRepository;

	/** The id task group category. */
	private Long idTaskGroupCategory;

	/** The task group category. */
	private TaskGroupCategory taskGroupCategory;

	/** The task group categorys. */
	List<TaskGroupCategory> taskGroupCategorys;

	/**
	 * Inits the new task group category.
	 */
	@PostConstruct
	public void initNewTaskGroupCategory() {
		taskGroupCategory = new TaskGroupCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskGroupCategory = null;
	}

	/**
	 * Gets the task group categorys.
	 *
	 * @return the task group categorys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idTaskGroupCategory = null;
		taskGroupCategory = new TaskGroupCategory();
	}

	/**
	 * Gets the id task group category.
	 *
	 * @return the id task group category
	 */
	public Long getIdTaskGroupCategory() {
		return idTaskGroupCategory;
	}

	/**
	 * Sets the id task group category.
	 *
	 * @param idTaskGroupCategory the new id task group category
	 */
	public void setIdTaskGroupCategory(Long idTaskGroupCategory) {
		this.idTaskGroupCategory = idTaskGroupCategory;
	}

	/**
	 * Gets the task group category.
	 *
	 * @return the task group category
	 */
	public TaskGroupCategory getTaskGroupCategory() {
		return taskGroupCategory;
	}

	/**
	 * Sets the task group category.
	 *
	 * @param taskGroupCategory the new task group category
	 */
	public void setTaskGroupCategory(TaskGroupCategory taskGroupCategory) {
		this.taskGroupCategory = taskGroupCategory;
	}
}
