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
import br.com.uaijug.chronos.project.data.repository.DailyActivityRepository;
import br.com.uaijug.chronos.project.model.DailyActivity;
import br.com.uaijug.chronos.project.service.DailyActivityRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class DailyActivityController extends AbstractManageBeans {

	@Inject
	private DailyActivityRegistration dailyActivityRegistration;

	@Inject
	private DailyActivityRepository dailyActivityRepository;

	private Long idDailyActivity;

	private DailyActivity dailyActivity;

	List<DailyActivity> dailyActivitys;

	@PostConstruct
	public void initNewDailyActivity() {
		dailyActivity = new DailyActivity();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idDailyActivity = null;
	}

	public List<DailyActivity> getDailyActivitys() {
		return dailyActivityRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idDailyActivity != null) {
				dailyActivity = dailyActivityRepository.findById(idDailyActivity);
				if (dailyActivity == null) {
					redirect("list.jsf");
				}
			} else {
				dailyActivity = new DailyActivity();
			}
		}
	}

	public String register() throws Exception {
		try {

			dailyActivityRegistration.register(dailyActivity);
			successMessage("label.dailyActivity.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.dailyActivity-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idDailyActivity = null;
		dailyActivity = new DailyActivity();
	}

	public Long getIdDailyActivity() {
		return idDailyActivity;
	}

	public void setIdDailyActivity(Long idDailyActivity) {
		this.idDailyActivity = idDailyActivity;
	}

	public DailyActivity getDailyActivity() {
		return dailyActivity;
	}

	public void setDailyActivity(DailyActivity dailyActivity) {
		this.dailyActivity = dailyActivity;
	}

}
