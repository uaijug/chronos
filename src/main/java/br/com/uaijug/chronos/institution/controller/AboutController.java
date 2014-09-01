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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class AboutController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class AboutController extends AbstractManageBeans {

	/** The about registration. */
	@Inject
	private AboutRegistration aboutRegistration;

	/** The about repository. */
	@Inject
	private AboutRepository aboutRepository;

	/** The institution repository. */
	@Inject
	private InstitutionRepository institutionRepository;

	/** The id about. */
	private Long idAbout;

	/** The about. */
	private About about;

	/** The abouts. */
	List<About> abouts;

	/** The list institution. */
	private List<Institution> listInstitution = null;

	/** The itens state. */
	List<SelectItem> itensInstitution = null;

	/**
	 * Inits the new about.
	 */
	@PostConstruct
	public void initNewAbout() {
		about = new About();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idAbout = null;

		itensInstitution = new ArrayList<SelectItem>();
		listInstitution = new ArrayList<Institution>();
	}

	/**
	 * Gets the abouts.
	 *
	 * @return the abouts
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idAbout = null;
		about = new About();
	}

	/**
	 * Gets the institutions.
	 *
	 * @return the institutions
	 */
	public List<SelectItem> getInstitutions() {

		setListInstitution(institutionRepository.findAll());
		for (Institution p : listInstitution) {
			itensInstitution.add(new SelectItem(p, p.getName()));
		}
		return itensInstitution;
	}

	/**
	 * Gets the approves.
	 *
	 * @return the approves
	 */
	public List<SelectItem> getApproves() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Approve type : Approve.values()) {
			items.add(new SelectItem(type, type.getApprove()));
		}
		return items;
	}

	/**
	 * Gets the id about.
	 *
	 * @return the id about
	 */
	public Long getIdAbout() {
		return idAbout;
	}

	/**
	 * Sets the id about.
	 *
	 * @param idAbout the new id about
	 */
	public void setIdAbout(Long idAbout) {
		this.idAbout = idAbout;
	}

	/**
	 * Gets the about.
	 *
	 * @return the about
	 */
	public About getAbout() {
		return about;
	}

	/**
	 * Sets the about.
	 *
	 * @param about the new about
	 */
	public void setAbout(About about) {
		this.about = about;
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

	/**
	 * Gets the itens institution.
	 *
	 * @return the itens institution
	 */
	public List<SelectItem> getItensInstitution() {
		return itensInstitution;
	}

	/**
	 * Sets the itens institution.
	 *
	 * @param itensInstitution the new itens institution
	 */
	public void setItensInstitution(List<SelectItem> itensInstitution) {
		this.itensInstitution = itensInstitution;
	}

}
