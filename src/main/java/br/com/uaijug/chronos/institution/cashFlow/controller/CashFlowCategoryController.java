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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more cashFlowCategory the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class CashFlowCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class CashFlowCategoryController extends AbstractManageBeans {

	/** The cash flow category registration. */
	@Inject
	private CashFlowCategoryRegistration cashFlowCategoryRegistration;

	/** The cash flow category repository. */
	@Inject
	private CashFlowCategoryRepository cashFlowCategoryRepository;

	/** The id cash flow category. */
	private Long idCashFlowCategory;

	/** The cash flow category. */
	private CashFlowCategory cashFlowCategory;

	/** The cash flow categorys. */
	List<CashFlowCategory> cashFlowCategorys;

	/**
	 * Inits the new cash flow category.
	 */
	@PostConstruct
	public void initNewCashFlowCategory() {
		cashFlowCategory = new CashFlowCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCashFlowCategory = null;
	}

	/**
	 * Gets the cash flow categorys.
	 *
	 * @return the cash flow categorys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idCashFlowCategory = null;
		cashFlowCategory = new CashFlowCategory();
	}

	/**
	 * Gets the id cash flow category.
	 *
	 * @return the id cash flow category
	 */
	public Long getIdCashFlowCategory() {
		return idCashFlowCategory;
	}

	/**
	 * Sets the id cash flow category.
	 *
	 * @param idCashFlowCategory the new id cash flow category
	 */
	public void setIdCashFlowCategory(Long idCashFlowCategory) {
		this.idCashFlowCategory = idCashFlowCategory;
	}

	/**
	 * Gets the cash flow category.
	 *
	 * @return the cash flow category
	 */
	public CashFlowCategory getCashFlowCategory() {
		return cashFlowCategory;
	}

	/**
	 * Sets the cash flow category.
	 *
	 * @param cashFlowCategory the new cash flow category
	 */
	public void setCashFlowCategory(CashFlowCategory cashFlowCategory) {
		this.cashFlowCategory = cashFlowCategory;
	}

}
