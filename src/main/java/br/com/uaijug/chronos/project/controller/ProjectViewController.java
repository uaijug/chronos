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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ProjectViewController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class ProjectViewController extends AbstractManageBeans {

	/** The project repository. */
	@Inject
	private ProjectRepository projectRepository;

	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The id project. */
	private Long idProject;

	/** The project. */
	private Project project;

	/** The projects. */
	List<Project> projects;

	/** The list member. */
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	/**
	 * Inits the new view project.
	 */
	@PostConstruct
	public void initNewViewProject() {
		project = new Project();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idProject = null;

		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		
		Long projectId = Long.parseLong(paramMap.get("projectId"));
		if (projectId != null){
			System.out.println("Project-ID"+projectId);
			getProjectById(projectId);
		}
		
	}

	/**
	 * Gets the project by id.
	 *
	 * @param projectId the project id
	 * @return the project by id
	 */
	public void getProjectById(Long projectId){
		if (projectId != null)
			project = projectRepository.findById(projectId);
		else {	
			project = new Project();
			redirect("list.jsf");
		}
		
		System.out.println("Project->" + project.toString());
	}
	
	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projectRepository.findAllOrderedByName();
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAllOrderedByName());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	/**
	 * Gets the id project.
	 *
	 * @return the id project
	 */
	public Long getIdProject() {
		return idProject;
	}

	/**
	 * Sets the id project.
	 *
	 * @param idProject the new id project
	 */
	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
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
}
