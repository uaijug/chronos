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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TaskController extends AbstractManageBeans {

	@Inject
	private TaskRegistration taskRegistration;

	@Inject
	private TaskRepository taskRepository;

	@Inject
	private ProjectRepository projectRepository;

	@Inject
	private TaskCategoryRepository taskCategoryRepository;

	@Inject
	private MemberRepository memberRepository;

	private Long idTask;

	private Task task;

	List<Task> tasks;

	private List<Project> listProject = null;

	/** The itens state. */
	List<SelectItem> itensProject = null;

	private List<TaskCategory> listTaskCategory = null;

	/** The itens state. */
	List<SelectItem> itensTaskCategory = null;

	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idTask = null;
		task = new Task();
	}

	public List<SelectItem> getProjects() {

		setListProject(projectRepository.findAll());
		for (Project p : listProject) {
			itensProject.add(new SelectItem(p, p.getDescription()));
		}
		return itensProject;
	}

	public List<SelectItem> getTaskCategorys() {

		setListTaskCategory(taskCategoryRepository.findAll());
		for (TaskCategory p : listTaskCategory) {
			itensTaskCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensTaskCategory;
	}

	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	public List<SelectItem> getPrioritys() {
		List<SelectItem> lst = new ArrayList<SelectItem>();
		lst.add(new SelectItem("0", "Alto"));
		lst.add(new SelectItem("1", "Medio"));
		lst.add(new SelectItem("2", "Baixo"));
		return lst;
	}

	public Long getIdTask() {
		return idTask;
	}

	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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

	public List<TaskCategory> getListTaskCategory() {
		return listTaskCategory;
	}

	public void setListTaskCategory(List<TaskCategory> listTaskCategory) {
		this.listTaskCategory = listTaskCategory;
	}

	public List<SelectItem> getItensTaskCategory() {
		return itensTaskCategory;
	}

	public void setItensTaskCategory(List<SelectItem> itensTaskCategory) {
		this.itensTaskCategory = itensTaskCategory;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}

}
