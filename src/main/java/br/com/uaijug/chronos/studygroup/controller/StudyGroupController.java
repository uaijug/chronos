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
package br.com.uaijug.chronos.studygroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.InstitutionRepository;
import br.com.uaijug.chronos.institution.model.Institution;
import br.com.uaijug.chronos.studygroup.data.repository.StudyGroupRepository;
import br.com.uaijug.chronos.studygroup.model.StudyGroup;
import br.com.uaijug.chronos.studygroup.service.StudyGroupRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class StudyGroupController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class StudyGroupController extends AbstractManageBeans {

	/** The study group registration. */
	@Inject
	private StudyGroupRegistration studyGroupRegistration;

	/** The study group repository. */
	@Inject
	private StudyGroupRepository studyGroupRepository;

	/** The institution repository. */
	@Inject
	private InstitutionRepository institutionRepository;

	/** The id study group. */
	private Long idStudyGroup;

	/** The study group. */
	private StudyGroup studyGroup;

	/** The study groups. */
	List<StudyGroup> studyGroups;

	/** The list institution. */
	private List<Institution> listInstitution = null;

	/** The itens state. */
	List<SelectItem> itensInstitution = null;

	/**
	 * Inits the new study group.
	 */
	@PostConstruct
	public void initNewStudyGroup() {
		studyGroup = new StudyGroup();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idStudyGroup = null;

		itensInstitution = new ArrayList<SelectItem>();
		listInstitution = new ArrayList<Institution>();
	}

	/**
	 * Gets the study groups.
	 *
	 * @return the study groups
	 */
	public List<StudyGroup> getStudyGroups() {
		return studyGroupRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idStudyGroup != null) {
				studyGroup = studyGroupRepository.findById(idStudyGroup);
				if (studyGroup == null) {
					redirect("list.jsf");
				}
			} else {
				studyGroup = new StudyGroup();
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

			studyGroupRegistration.register(studyGroup);
			successMessage("label.studyGroup.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.studyGroup-exists");
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
		idStudyGroup = null;
		studyGroup = new StudyGroup();
	}

	/**
	 * Gets the institutions.
	 *
	 * @return the institutions
	 */
	public List<SelectItem> getInstitutions() {

		setListInstitution(institutionRepository.findAll());
		for (Institution p : listInstitution) {
			itensInstitution.add(new SelectItem(p, p.getDescription()));
		}
		return itensInstitution;
	}

	/**
	 * Gets the id study group.
	 *
	 * @return the id study group
	 */
	public Long getIdStudyGroup() {
		return idStudyGroup;
	}

	/**
	 * Sets the id study group.
	 *
	 * @param idStudyGroup the new id study group
	 */
	public void setIdStudyGroup(Long idStudyGroup) {
		this.idStudyGroup = idStudyGroup;
	}

	/**
	 * Gets the study group.
	 *
	 * @return the study group
	 */
	public StudyGroup getStudyGroup() {
		return studyGroup;
	}

	/**
	 * Sets the study group.
	 *
	 * @param studyGroup the new study group
	 */
	public void setStudyGroup(StudyGroup studyGroup) {
		this.studyGroup = studyGroup;
	}

	/**
	 * Gets the list institution.
	 *
	 * @return the list institution
	 */
	public List<Institution> getListInstitution() {
		return listInstitution;
	}

	/**
	 * Sets the list institution.
	 *
	 * @param listInstitution the new list institution
	 */
	public void setListInstitution(List<Institution> listInstitution) {
		this.listInstitution = listInstitution;
	}

}
