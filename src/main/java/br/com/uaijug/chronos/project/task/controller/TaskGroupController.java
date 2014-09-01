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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class TaskGroupController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class TaskGroupController extends AbstractManageBeans {

	/** The task group registration. */
	@Inject
	private TaskGroupRegistration taskGroupRegistration;

	/** The task group repository. */
	@Inject
	private TaskGroupRepository taskGroupRepository;

	/** The task group category repository. */
	@Inject
	private TaskGroupCategoryRepository taskGroupCategoryRepository;

	/** The id task group. */
	private Long idTaskGroup;

	/** The task group. */
	private TaskGroup taskGroup;

	/** The task groups. */
	List<TaskGroup> taskGroups;

	/** The list task group category. */
	private List<TaskGroupCategory> listTaskGroupCategory = null;

	/** The itens state. */
	List<SelectItem> itensTaskGroupCategory = null;

	/**
	 * Inits the new task group.
	 */
	@PostConstruct
	public void initNewTaskGroup() {
		taskGroup = new TaskGroup();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTaskGroup = null;

		itensTaskGroupCategory = new ArrayList<SelectItem>();
		listTaskGroupCategory = new ArrayList<TaskGroupCategory>();
	}

	/**
	 * Gets the task groups.
	 *
	 * @return the task groups
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idTaskGroup = null;
		taskGroup = new TaskGroup();
	}

	/**
	 * Gets the task group categorys.
	 *
	 * @return the task group categorys
	 */
	public List<SelectItem> getTaskGroupCategorys() {

		setListTaskGroupCategory(taskGroupCategoryRepository.findAll());
		for (TaskGroupCategory p : listTaskGroupCategory) {
			itensTaskGroupCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensTaskGroupCategory;
	}

	/**
	 * Gets the id task group.
	 *
	 * @return the id task group
	 */
	public Long getIdTaskGroup() {
		return idTaskGroup;
	}

	/**
	 * Sets the id task group.
	 *
	 * @param idTaskGroup the new id task group
	 */
	public void setIdTaskGroup(Long idTaskGroup) {
		this.idTaskGroup = idTaskGroup;
	}

	/**
	 * Gets the task group.
	 *
	 * @return the task group
	 */
	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	/**
	 * Sets the task group.
	 *
	 * @param taskGroup the new task group
	 */
	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}

	/**
	 * Gets the list task group category.
	 *
	 * @return the list task group category
	 */
	public List<TaskGroupCategory> getListTaskGroupCategory() {
		return listTaskGroupCategory;
	}

	/**
	 * Sets the list task group category.
	 *
	 * @param listTaskGroupCategory the new list task group category
	 */
	public void setListTaskGroupCategory(
			List<TaskGroupCategory> listTaskGroupCategory) {
		this.listTaskGroupCategory = listTaskGroupCategory;
	}

	/**
	 * Gets the itens task group category.
	 *
	 * @return the itens task group category
	 */
	public List<SelectItem> getItensTaskGroupCategory() {
		return itensTaskGroupCategory;
	}

	/**
	 * Sets the itens task group category.
	 *
	 * @param itensTaskGroupCategory the new itens task group category
	 */
	public void setItensTaskGroupCategory(
			List<SelectItem> itensTaskGroupCategory) {
		this.itensTaskGroupCategory = itensTaskGroupCategory;
	}

}
