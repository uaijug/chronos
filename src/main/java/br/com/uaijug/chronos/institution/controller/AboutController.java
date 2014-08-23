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
package br.com.uaijug.chronos.institution.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.AboutRepository;
import br.com.uaijug.chronos.institution.data.repository.InstitutionRepository;
import br.com.uaijug.chronos.institution.model.About;
import br.com.uaijug.chronos.institution.model.Institution;
import br.com.uaijug.chronos.institution.service.AboutRegistration;
import br.com.uaijug.chronos.model.types.Approve;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class AboutController extends AbstractManageBeans {

	@Inject
	private AboutRegistration aboutRegistration;

	@Inject
	private AboutRepository aboutRepository;

	@Inject
	private InstitutionRepository institutionRepository;

	private Long idAbout;

	private About about;

	List<About> abouts;

	private List<Institution> listInstitution = null;

	/** The itens state. */
	List<SelectItem> itensInstitution = null;

	@PostConstruct
	public void initNewAbout() {
		about = new About();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idAbout = null;

		itensInstitution = new ArrayList<SelectItem>();
		listInstitution = new ArrayList<Institution>();
	}

	public List<About> getAbouts() {
		return aboutRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idAbout != null) {
				about = aboutRepository.findById(idAbout);
				if (about == null) {
					redirect("list.jsf");
				}
			} else {
				about = new About();
			}
		}
	}

	public String register() throws Exception {
		try {

			aboutRegistration.register(about);
			successMessage("label.about.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.about-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idAbout = null;
		about = new About();
	}

	public List<SelectItem> getInstitutions() {

		setListInstitution(institutionRepository.findAll());
		for (Institution p : listInstitution) {
			itensInstitution.add(new SelectItem(p, p.getDescription()));
		}
		return itensInstitution;
	}

	public List<SelectItem> getApproves() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Approve type : Approve.values()) {
			items.add(new SelectItem(type, type.getApprove()));
		}
		return items;
	}

	public Long getIdAbout() {
		return idAbout;
	}

	public void setIdAbout(Long idAbout) {
		this.idAbout = idAbout;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public List<Institution> getListInstitution() {
		return listInstitution;
	}

	public void setListInstitution(List<Institution> listInstitution) {
		this.listInstitution = listInstitution;
	}

	public List<SelectItem> getItensInstitution() {
		return itensInstitution;
	}

	public void setItensInstitution(List<SelectItem> itensInstitution) {
		this.itensInstitution = itensInstitution;
	}

}
