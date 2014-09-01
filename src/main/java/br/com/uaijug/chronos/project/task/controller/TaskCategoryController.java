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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class TaskCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class TaskCategoryController extends AbstractManageBeans {

	/** The task category registration. */
	@Inject
	private TaskCategoryRegistration taskCategoryRegistration;

	/** The task category repository. */
	@Inject
	private TaskCategoryRepository taskCategoryRepository;

	/** The id task category. */
	private Long idTaskCategory;

	/** The task category. */
	private TaskCategory taskCategory;

	/** The task categorys. */
	List<TaskCategory> taskCategorys;

	/**
	 * Inits the new task category.
	 */
	@PostConstruct
	public void initNewTaskCategory() {
		taskCategory = new TaskCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskCategory = null;
	}

	/**
	 * Gets the task categorys.
	 *
	 * @return the task categorys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idTaskCategory = null;
		taskCategory = new TaskCategory();
	}

	/**
	 * Gets the id task category.
	 *
	 * @return the id task category
	 */
	public Long getIdTaskCategory() {
		return idTaskCategory;
	}

	/**
	 * Sets the id task category.
	 *
	 * @param idTaskCategory the new id task category
	 */
	public void setIdTaskCategory(Long idTaskCategory) {
		this.idTaskCategory = idTaskCategory;
	}

	/**
	 * Gets the task category.
	 *
	 * @return the task category
	 */
	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	/**
	 * Sets the task category.
	 *
	 * @param taskCategory the new task category
	 */
	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

}
