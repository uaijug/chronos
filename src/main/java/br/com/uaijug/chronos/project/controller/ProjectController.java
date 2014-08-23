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
package br.com.uaijug.chronos.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.project.data.repository.ProjectRepository;
import br.com.uaijug.chronos.project.model.Project;
import br.com.uaijug.chronos.project.service.ProjectRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ProjectController extends AbstractManageBeans {

	@Inject
	private ProjectRegistration projectRegistration;

	@Inject
	private ProjectRepository projectRepository;

	@Inject
	private MemberRepository memberRepository;

	private Long idProject;

	private Project project;

	List<Project> projects;

	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	@PostConstruct
	public void initNewProject() {
		project = new Project();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idProject = null;

		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();
	}

	public List<Project> getProjects() {
		return projectRepository.findAllOrderedByName();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idProject != null) {
				project = projectRepository.findById(idProject);
				if (project == null) {
					redirect("list.jsf");
				}
			} else {
				project = new Project();
			}
		}
	}

	public String register() throws Exception {
		try {

			projectRegistration.register(project);
			successMessage("label.project.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.project-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idProject = null;
		project = new Project();
	}

	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAllOrderedByName());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	public Long getIdProject() {
		return idProject;
	}

	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}
}
