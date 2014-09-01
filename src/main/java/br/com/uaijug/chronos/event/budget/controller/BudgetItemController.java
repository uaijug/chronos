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
package br.com.uaijug.chronos.event.budget.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.budget.data.repository.BudgetItemRepository;
import br.com.uaijug.chronos.event.budget.model.BudgetItem;
import br.com.uaijug.chronos.event.budget.service.BudgetItemRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class BudgetItemController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class BudgetItemController extends AbstractManageBeans {

	/** The budget item registration. */
	@Inject
	private BudgetItemRegistration budgetItemRegistration;

	/** The budget item repository. */
	@Inject
	private BudgetItemRepository budgetItemRepository;

	/** The id budget item. */
	private Long idBudgetItem;

	/** The budget item. */
	private BudgetItem budgetItem;

	/** The budget items. */
	List<BudgetItem> budgetItems;

	/**
	 * Inits the new budget item.
	 */
	@PostConstruct
	public void initNewBudgetItem() {
		budgetItem = new BudgetItem();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idBudgetItem = null;
	}

	/**
	 * Gets the budget items.
	 *
	 * @return the budget items
	 */
	public List<BudgetItem> getBudgetItems() {
		return budgetItemRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idBudgetItem != null) {
				budgetItem = budgetItemRepository.findById(idBudgetItem);
				if (budgetItem == null) {
					redirect("list.jsf");
				}
			} else {
				budgetItem = new BudgetItem();
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

			budgetItemRegistration.register(budgetItem);
			successMessage("label.budgetItem.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.budgetItem-exists");
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
		idBudgetItem = null;
		budgetItem = new BudgetItem();
	}

	/**
	 * Gets the id budget item.
	 *
	 * @return the id budget item
	 */
	public Long getIdBudgetItem() {
		return idBudgetItem;
	}

	/**
	 * Sets the id budget item.
	 *
	 * @param idBudgetItem the new id budget item
	 */
	public void setIdBudgetItem(Long idBudgetItem) {
		this.idBudgetItem = idBudgetItem;
	}

	/**
	 * Gets the budget item.
	 *
	 * @return the budget item
	 */
	public BudgetItem getBudgetItem() {
		return budgetItem;
	}

	/**
	 * Sets the budget item.
	 *
	 * @param budgetItem the new budget item
	 */
	public void setBudgetItem(BudgetItem budgetItem) {
		this.budgetItem = budgetItem;
	}

}
