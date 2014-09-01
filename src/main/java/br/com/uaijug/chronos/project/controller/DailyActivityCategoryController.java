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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.project.data.repository.DailyActivityCategoryRepository;
import br.com.uaijug.chronos.project.model.DailyActivityCategory;
import br.com.uaijug.chronos.project.service.DailyActivityCategoryRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class DailyActivityCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class DailyActivityCategoryController extends AbstractManageBeans {

	/** The daily activity category registration. */
	@Inject
	private DailyActivityCategoryRegistration dailyActivityCategoryRegistration;

	/** The daily activity category repository. */
	@Inject
	private DailyActivityCategoryRepository dailyActivityCategoryRepository;

	/** The id daily activity category. */
	private Long idDailyActivityCategory;

	/** The daily activity category. */
	private DailyActivityCategory dailyActivityCategory;

	/** The daily activity categorys. */
	List<DailyActivityCategory> dailyActivityCategorys;

	/**
	 * Inits the new daily activity category.
	 */
	@PostConstruct
	public void initNewDailyActivityCategory() {
		dailyActivityCategory = new DailyActivityCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idDailyActivityCategory = null;
	}

	/**
	 * Gets the daily activity categorys.
	 *
	 * @return the daily activity categorys
	 */
	public List<DailyActivityCategory> getDailyActivityCategorys() {
		return dailyActivityCategoryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idDailyActivityCategory != null) {
				dailyActivityCategory = dailyActivityCategoryRepository.findById(idDailyActivityCategory);
				if (dailyActivityCategory == null) {
					redirect("list.jsf");
				}
			} else {
				dailyActivityCategory = new DailyActivityCategory();
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

			dailyActivityCategoryRegistration.register(dailyActivityCategory);
			successMessage("label.dailyActivityCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.dailyActivityCategory-exists");
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
		idDailyActivityCategory = null;
		dailyActivityCategory = new DailyActivityCategory();
	}

	/**
	 * Gets the id daily activity category.
	 *
	 * @return the id daily activity category
	 */
	public Long getIdDailyActivityCategory() {
		return idDailyActivityCategory;
	}

	/**
	 * Sets the id daily activity category.
	 *
	 * @param idDailyActivityCategory the new id daily activity category
	 */
	public void setIdDailyActivityCategory(Long idDailyActivityCategory) {
		this.idDailyActivityCategory = idDailyActivityCategory;
	}

	/**
	 * Gets the daily activity category.
	 *
	 * @return the daily activity category
	 */
	public DailyActivityCategory getDailyActivityCategory() {
		return dailyActivityCategory;
	}

	/**
	 * Sets the daily activity category.
	 *
	 * @param dailyActivityCategory the new daily activity category
	 */
	public void setDailyActivityCategory(DailyActivityCategory dailyActivityCategory) {
		this.dailyActivityCategory = dailyActivityCategory;
	}

}
