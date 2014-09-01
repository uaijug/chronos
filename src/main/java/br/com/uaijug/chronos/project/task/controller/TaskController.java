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
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.project.data.repository.ProjectRepository;
import br.com.uaijug.chronos.project.model.Project;
import br.com.uaijug.chronos.project.task.data.repository.TaskCategoryRepository;
import br.com.uaijug.chronos.project.task.data.repository.TaskRepository;
import br.com.uaijug.chronos.project.task.model.Task;
import br.com.uaijug.chronos.project.task.model.TaskCategory;
import br.com.uaijug.chronos.project.task.service.TaskRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class TaskController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class TaskController extends AbstractManageBeans {

	/** The task registration. */
	@Inject
	private TaskRegistration taskRegistration;

	/** The task repository. */
	@Inject
	private TaskRepository taskRepository;

	/** The project repository. */
	@Inject
	private ProjectRepository projectRepository;

	/** The task category repository. */
	@Inject
	private TaskCategoryRepository taskCategoryRepository;

	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The id task. */
	private Long idTask;

	/** The task. */
	private Task task;

	/** The tasks. */
	List<Task> tasks;

	/** The list project. */
	private List<Project> listProject = null;

	/** The itens state. */
	List<SelectItem> itensProject = null;

	/** The list task category. */
	private List<TaskCategory> listTaskCategory = null;

	/** The itens state. */
	List<SelectItem> itensTaskCategory = null;

	/** The list member. */
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	/**
	 * Inits the new task.
	 */
	@PostConstruct
	public void initNewTask() {
		task = new Task();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idTask = null;

		itensProject = new ArrayList<SelectItem>();
		listProject = new ArrayList<Project>();

		itensTaskCategory = new ArrayList<SelectItem>();
		listTaskCategory = new ArrayList<TaskCategory>();

		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();
	}

	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idTask != null) {
				task = taskRepository.findById(idTask);
				if (task == null) {
					redirect("list.jsf");
				}
			} else {
				task = new Task();
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

			taskRegistration.register(task);
			successMessage("label.task.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.task-exists");
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
		idTask = null;
		task = new Task();
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
	 * Gets the task categorys.
	 *
	 * @return the task categorys
	 */
	public List<SelectItem> getTaskCategorys() {

		setListTaskCategory(taskCategoryRepository.findAll());
		for (TaskCategory p : listTaskCategory) {
			itensTaskCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensTaskCategory;
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	/**
	 * Gets the prioritys.
	 *
	 * @return the prioritys
	 */
	public List<SelectItem> getPrioritys() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Alto"));
		lst.add(new SelectItem("1", "Medio"));
		lst.add(new SelectItem("2", "Baixo"));
		return lst;
	}

	/**
	 * Gets the id task.
	 *
	 * @return the id task
	 */
	public Long getIdTask() {
		return idTask;
	}

	/**
	 * Sets the id task.
	 *
	 * @param idTask the new id task
	 */
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}

	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * Sets the task.
	 *
	 * @param task the new task
	 */
	public void setTask(Task task) {
		this.task = task;
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
	 * Gets the list task category.
	 *
	 * @return the list task category
	 */
	public List<TaskCategory> getListTaskCategory() {
		return listTaskCategory;
	}

	/**
	 * Sets the list task category.
	 *
	 * @param listTaskCategory the new list task category
	 */
	public void setListTaskCategory(List<TaskCategory> listTaskCategory) {
		this.listTaskCategory = listTaskCategory;
	}

	/**
	 * Gets the itens task category.
	 *
	 * @return the itens task category
	 */
	public List<SelectItem> getItensTaskCategory() {
		return itensTaskCategory;
	}

	/**
	 * Sets the itens task category.
	 *
	 * @param itensTaskCategory the new itens task category
	 */
	public void setItensTaskCategory(List<SelectItem> itensTaskCategory) {
		this.itensTaskCategory = itensTaskCategory;
	}

	/**
	 * Gets the list member.
	 *
	 * @return the list member
	 */
	public List<Member> getListMember() {
		return listMember;
	}

	/**
	 * Sets the list member.
	 *
	 * @param listMember the new list member
	 */
	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	/**
	 * Gets the itens member.
	 *
	 * @return the itens member
	 */
	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	/**
	 * Sets the itens member.
	 *
	 * @param itensMember the new itens member
	 */
	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}

}
