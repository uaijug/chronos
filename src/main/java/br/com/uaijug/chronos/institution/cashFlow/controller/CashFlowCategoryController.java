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
package br.com.uaijug.chronos.institution.cashFlow.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.cashFlow.data.repository.CashFlowCategoryRepository;
import br.com.uaijug.chronos.institution.cashFlow.model.CashFlowCategory;
import br.com.uaijug.chronos.institution.cashFlow.service.CashFlowCategoryRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more cashFlowCategory the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class CashFlowCategoryController extends AbstractManageBeans {

	@Inject
	private CashFlowCategoryRegistration cashFlowCategoryRegistration;

	@Inject
	private CashFlowCategoryRepository cashFlowCategoryRepository;

	private Long idCashFlowCategory;

	private CashFlowCategory cashFlowCategory;

	List<CashFlowCategory> cashFlowCategorys;

	@PostConstruct
	public void initNewCashFlowCategory() {
		cashFlowCategory = new CashFlowCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCashFlowCategory = null;
	}

	public List<CashFlowCategory> getCashFlowCategorys() {
		return cashFlowCategoryRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idCashFlowCategory != null) {
				cashFlowCategory = cashFlowCategoryRepository.findById(idCashFlowCategory);
				if (cashFlowCategory == null) {
					redirect("list.jsf");
				}
			} else {
				cashFlowCategory = new CashFlowCategory();
			}
		}
	}

	public String register() throws Exception {
		try {

			cashFlowCategoryRegistration.register(cashFlowCategory);
			successMessage("label.cashFlowCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.cashFlowCategory-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idCashFlowCategory = null;
		cashFlowCategory = new CashFlowCategory();
	}

	public Long getIdCashFlowCategory() {
		return idCashFlowCategory;
	}

	public void setIdCashFlowCategory(Long idCashFlowCategory) {
		this.idCashFlowCategory = idCashFlowCategory;
	}

	public CashFlowCategory getCashFlowCategory() {
		return cashFlowCategory;
	}

	public void setCashFlowCategory(CashFlowCategory cashFlowCategory) {
		this.cashFlowCategory = cashFlowCategory;
	}

}
