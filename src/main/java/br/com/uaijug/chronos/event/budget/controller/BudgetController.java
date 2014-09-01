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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.budget.data.repository.BudgetRepository;
import br.com.uaijug.chronos.event.budget.data.repository.MaterialRepository;
import br.com.uaijug.chronos.event.budget.model.Budget;
import br.com.uaijug.chronos.event.budget.model.Material;
import br.com.uaijug.chronos.event.budget.service.BudgetRegistration;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.supplier.data.repository.SupplierRepository;
import br.com.uaijug.chronos.event.supplier.model.Supplier;
import br.com.uaijug.chronos.model.types.CashFlowType;
import br.com.uaijug.chronos.model.types.CatchmentType;
import br.com.uaijug.chronos.model.types.YesNo;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class BudgetController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class BudgetController extends AbstractManageBeans {

	/** The budget registration. */
	@Inject
	private BudgetRegistration budgetRegistration;

	/** The budget repository. */
	@Inject
	private BudgetRepository budgetRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The material repository. */
	@Inject
	private MaterialRepository materialRepository;

	/** The supplier repository. */
	@Inject
	private SupplierRepository supplierRepository;

	/** The id budget. */
	private Long idBudget;

	/** The budget. */
	private Budget budget;

	/** The budgets. */
	List<Budget> budgets;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	/** The list material. */
	private List<Material> listMaterial = null;

	/** The itens state. */
	List<SelectItem> itensMaterial = null;

	/** The list supplier. */
	private List<Supplier> listSupplier = null;

	/** The itens state. */
	List<SelectItem> itensSupplier = null;

	/**
	 * Inits the new budget.
	 */
	@PostConstruct
	public void initNewBudget() {
		budget = new Budget();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idBudget = null;

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();

		itensMaterial = new ArrayList<SelectItem>();
		listMaterial = new ArrayList<Material>();

		itensSupplier = new ArrayList<SelectItem>();
		listSupplier = new ArrayList<Supplier>();
	}

	/**
	 * Gets the budgets.
	 *
	 * @return the budgets
	 */
	public List<Budget> getBudgets() {
		return budgetRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idBudget != null) {
				budget = budgetRepository.findById(idBudget);
				if (budget == null) {
					redirect("list.jsf");
				}
			} else {
				budget = new Budget();
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

			budgetRegistration.register(budget);
			successMessage("label.budget.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.budget-exists");
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
		idBudget = null;
		budget = new Budget();
	}

	/**
	 * Gets the cash flow types.
	 *
	 * @return the cash flow types
	 */
	public List<SelectItem> getCashFlowTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CashFlowType type : CashFlowType.values()) {
			items.add(new SelectItem(type, type.getCashFlowType()));
		}
		return items;
	}

	/**
	 * Gets the catchment types.
	 *
	 * @return the catchment types
	 */
	public List<SelectItem> getCatchmentTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CatchmentType type : CatchmentType.values()) {
			items.add(new SelectItem(type, type.getCatchmentType()));
		}
		return items;
	}

	/**
	 * Gets the yes no.
	 *
	 * @return the yes no
	 */
	public List<SelectItem> getYesNo() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (YesNo type : YesNo.values()) {
			items.add(new SelectItem(type, type.getYesNo()));
		}
		return items;
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	/**
	 * Gets the materials.
	 *
	 * @return the materials
	 */
	public List<SelectItem> getMaterials() {

		setListMaterial(materialRepository.findAll());
		for (Material p : listMaterial) {
			itensMaterial.add(new SelectItem(p, p.getDescription()));
		}
		return itensMaterial;
	}

	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
	public List<SelectItem> getSuppliers() {

		setListSupplier(supplierRepository.findAll());
		for (Supplier p : listSupplier) {
			itensSupplier.add(new SelectItem(p, p.getDescription()));
		}
		return itensSupplier;
	}

	/**
	 * Gets the id budget.
	 *
	 * @return the id budget
	 */
	public Long getIdBudget() {
		return idBudget;
	}

	/**
	 * Sets the id budget.
	 *
	 * @param idBudget the new id budget
	 */
	public void setIdBudget(Long idBudget) {
		this.idBudget = idBudget;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	public Budget getBudget() {
		return budget;
	}

	/**
	 * Sets the budget.
	 *
	 * @param budget the new budget
	 */
	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	/**
	 * Gets the list event.
	 *
	 * @return the list event
	 */
	public List<EventMain> getListEvent() {
		return listEvent;
	}

	/**
	 * Sets the list event.
	 *
	 * @param listEvent the new list event
	 */
	public void setListEvent(List<EventMain> listEvent) {
		this.listEvent = listEvent;
	}

	/**
	 * Gets the itens event.
	 *
	 * @return the itens event
	 */
	public List<SelectItem> getItensEvent() {
		return itensEvent;
	}

	/**
	 * Sets the itens event.
	 *
	 * @param itensEvent the new itens event
	 */
	public void setItensEvent(List<SelectItem> itensEvent) {
		this.itensEvent = itensEvent;
	}

	/**
	 * Gets the list material.
	 *
	 * @return the list material
	 */
	public List<Material> getListMaterial() {
		return listMaterial;
	}

	/**
	 * Sets the list material.
	 *
	 * @param listMaterial the new list material
	 */
	public void setListMaterial(List<Material> listMaterial) {
		this.listMaterial = listMaterial;
	}

	/**
	 * Gets the itens material.
	 *
	 * @return the itens material
	 */
	public List<SelectItem> getItensMaterial() {
		return itensMaterial;
	}

	/**
	 * Sets the itens material.
	 *
	 * @param itensMaterial the new itens material
	 */
	public void setItensMaterial(List<SelectItem> itensMaterial) {
		this.itensMaterial = itensMaterial;
	}

	/**
	 * Gets the list supplier.
	 *
	 * @return the list supplier
	 */
	public List<Supplier> getListSupplier() {
		return listSupplier;
	}

	/**
	 * Sets the list supplier.
	 *
	 * @param listSupplier the new list supplier
	 */
	public void setListSupplier(List<Supplier> listSupplier) {
		this.listSupplier = listSupplier;
	}

	/**
	 * Gets the itens supplier.
	 *
	 * @return the itens supplier
	 */
	public List<SelectItem> getItensSupplier() {
		return itensSupplier;
	}

	/**
	 * Sets the itens supplier.
	 *
	 * @param itensSupplier the new itens supplier
	 */
	public void setItensSupplier(List<SelectItem> itensSupplier) {
		this.itensSupplier = itensSupplier;
	}

}
