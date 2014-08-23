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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class DailyActivityCategoryController extends AbstractManageBeans {

	@Inject
	private DailyActivityCategoryRegistration dailyActivityCategoryRegistration;

	@Inject
	private DailyActivityCategoryRepository dailyActivityCategoryRepository;

	private Long idDailyActivityCategory;

	private DailyActivityCategory dailyActivityCategory;

	List<DailyActivityCategory> dailyActivityCategorys;

	@PostConstruct
	public void initNewDailyActivityCategory() {
		dailyActivityCategory = new DailyActivityCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idDailyActivityCategory = null;
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idDailyActivityCategory = null;
		dailyActivityCategory = new DailyActivityCategory();
	}

	public Long getIdDailyActivityCategory() {
		return idDailyActivityCategory;
	}

	public void setIdDailyActivityCategory(Long idDailyActivityCategory) {
		this.idDailyActivityCategory = idDailyActivityCategory;
	}

	public DailyActivityCategory getDailyActivityCategory() {
		return dailyActivityCategory;
	}

	public void setDailyActivityCategory(DailyActivityCategory dailyActivityCategory) {
		this.dailyActivityCategory = dailyActivityCategory;
	}

}
