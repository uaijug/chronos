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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class DailyActivityController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class DailyActivityController extends AbstractManageBeans {

	/** The daily activity registration. */
	@Inject
	private DailyActivityRegistration dailyActivityRegistration;

	/** The daily activity repository. */
	@Inject
	private DailyActivityRepository dailyActivityRepository;

	/** The id daily activity. */
	private Long idDailyActivity;

	/** The daily activity. */
	private DailyActivity dailyActivity;

	/** The daily activitys. */
	List<DailyActivity> dailyActivitys;

	/**
	 * Inits the new daily activity.
	 */
	@PostConstruct
	public void initNewDailyActivity() {
		dailyActivity = new DailyActivity();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idDailyActivity = null;
	}

	/**
	 * Gets the daily activitys.
	 *
	 * @return the daily activitys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idDailyActivity = null;
		dailyActivity = new DailyActivity();
	}

	/**
	 * Gets the id daily activity.
	 *
	 * @return the id daily activity
	 */
	public Long getIdDailyActivity() {
		return idDailyActivity;
	}

	/**
	 * Sets the id daily activity.
	 *
	 * @param idDailyActivity the new id daily activity
	 */
	public void setIdDailyActivity(Long idDailyActivity) {
		this.idDailyActivity = idDailyActivity;
	}

	/**
	 * Gets the daily activity.
	 *
	 * @return the daily activity
	 */
	public DailyActivity getDailyActivity() {
		return dailyActivity;
	}

	/**
	 * Sets the daily activity.
	 *
	 * @param dailyActivity the new daily activity
	 */
	public void setDailyActivity(DailyActivity dailyActivity) {
		this.dailyActivity = dailyActivity;
	}

}
