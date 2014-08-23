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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class StudyGroupController extends AbstractManageBeans {

	@Inject
	private StudyGroupRegistration studyGroupRegistration;

	@Inject
	private StudyGroupRepository studyGroupRepository;

	@Inject
	private InstitutionRepository institutionRepository;

	private Long idStudyGroup;

	private StudyGroup studyGroup;

	List<StudyGroup> studyGroups;

	private List<Institution> listInstitution = null;

	/** The itens state. */
	List<SelectItem> itensInstitution = null;

	@PostConstruct
	public void initNewStudyGroup() {
		studyGroup = new StudyGroup();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idStudyGroup = null;

		itensInstitution = new ArrayList<SelectItem>();
		listInstitution = new ArrayList<Institution>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idStudyGroup = null;
		studyGroup = new StudyGroup();
	}

	public List<SelectItem> getInstitutions() {

		setListInstitution(institutionRepository.findAll());
		for (Institution p : listInstitution) {
			itensInstitution.add(new SelectItem(p, p.getDescription()));
		}
		return itensInstitution;
	}

	public Long getIdStudyGroup() {
		return idStudyGroup;
	}

	public void setIdStudyGroup(Long idStudyGroup) {
		this.idStudyGroup = idStudyGroup;
	}

	public StudyGroup getStudyGroup() {
		return studyGroup;
	}

	public void setStudyGroup(StudyGroup studyGroup) {
		this.studyGroup = studyGroup;
	}

	public List<Institution> getListInstitution() {
		return listInstitution;
	}

	public void setListInstitution(List<Institution> listInstitution) {
		this.listInstitution = listInstitution;
	}

}
