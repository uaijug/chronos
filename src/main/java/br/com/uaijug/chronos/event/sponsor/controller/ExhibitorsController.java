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
package br.com.uaijug.chronos.event.sponsor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.CompanyRepository;
import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.sponsor.data.repository.ExhibitorsRepository;
import br.com.uaijug.chronos.event.sponsor.model.Exhibitors;
import br.com.uaijug.chronos.event.sponsor.service.ExhibitorsRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more exhibitors the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class ExhibitorsController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class ExhibitorsController extends AbstractManageBeans {

	/** The exhibitors registration. */
	@Inject
	private ExhibitorsRegistration exhibitorsRegistration;

	/** The exhibitors repository. */
	@Inject
	private ExhibitorsRepository exhibitorsRepository;

	/** The id exhibitors. */
	private Long idExhibitors;

	/** The exhibitors. */
	private Exhibitors exhibitors;

	/** The exhibitorss. */
	List<Exhibitors> exhibitorss;

	/** The company repository. */
	@Inject
	private CompanyRepository companyRepository;

	/** The list company. */
	private List<Company> listCompany = null;

	/** The itens state. */
	List<SelectItem> itensCompany = null;

	/**
	 * Inits the new exhibitors.
	 */
	@PostConstruct
	public void initNewExhibitors() {
		exhibitors = new Exhibitors();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idExhibitors = null;

		itensCompany = new ArrayList<SelectItem>();
		listCompany = new ArrayList<Company>();
	}

	/**
	 * Gets the exhibitorss.
	 *
	 * @return the exhibitorss
	 */
	public List<Exhibitors> getExhibitorss() {
		return exhibitorsRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idExhibitors != null) {
				exhibitors = exhibitorsRepository.findById(idExhibitors);
				if (exhibitors == null) {
					redirect("list.jsf");
				}
			} else {
				exhibitors = new Exhibitors();
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

			exhibitorsRegistration.register(exhibitors);
			successMessage("label.exhibitors.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.exhibitors-exists");
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
		idExhibitors = null;
		exhibitors = new Exhibitors();
	}

	/**
	 * Gets the companys.
	 *
	 * @return the companys
	 */
	public List<SelectItem> getCompanys() {

		setListCompany(companyRepository.findAll());
		for (Company p : listCompany) {
			itensCompany.add(new SelectItem(p, p.getDescription()));
		}
		return itensCompany;
	}

	/**
	 * Gets the id exhibitors.
	 *
	 * @return the id exhibitors
	 */
	public Long getIdExhibitors() {
		return idExhibitors;
	}

	/**
	 * Sets the id exhibitors.
	 *
	 * @param idExhibitors the new id exhibitors
	 */
	public void setIdExhibitors(Long idExhibitors) {
		this.idExhibitors = idExhibitors;
	}

	/**
	 * Gets the exhibitors.
	 *
	 * @return the exhibitors
	 */
	public Exhibitors getExhibitors() {
		return exhibitors;
	}

	/**
	 * Sets the exhibitors.
	 *
	 * @param exhibitors the new exhibitors
	 */
	public void setExhibitors(Exhibitors exhibitors) {
		this.exhibitors = exhibitors;
	}

	/**
	 * Gets the list company.
	 *
	 * @return the list company
	 */
	public List<Company> getListCompany() {
		return listCompany;
	}

	/**
	 * Sets the list company.
	 *
	 * @param listCompany the new list company
	 */
	public void setListCompany(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

	/**
	 * Gets the itens company.
	 *
	 * @return the itens company
	 */
	public List<SelectItem> getItensCompany() {
		return itensCompany;
	}

	/**
	 * Sets the itens company.
	 *
	 * @param itensCompany the new itens company
	 */
	public void setItensCompany(List<SelectItem> itensCompany) {
		this.itensCompany = itensCompany;
	}

}
