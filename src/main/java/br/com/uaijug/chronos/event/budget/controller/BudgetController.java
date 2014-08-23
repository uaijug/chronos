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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class BudgetController extends AbstractManageBeans {

	@Inject
	private BudgetRegistration budgetRegistration;

	@Inject
	private BudgetRepository budgetRepository;

	@Inject
	private EventMainRepository eventRepository;

	@Inject
	private MaterialRepository materialRepository;

	@Inject
	private SupplierRepository supplierRepository;

	private Long idBudget;

	private Budget budget;

	List<Budget> budgets;

	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	private List<Material> listMaterial = null;

	/** The itens state. */
	List<SelectItem> itensMaterial = null;

	private List<Supplier> listSupplier = null;

	/** The itens state. */
	List<SelectItem> itensSupplier = null;

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idBudget = null;
		budget = new Budget();
	}

	public List<SelectItem> getCashFlowTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CashFlowType type : CashFlowType.values()) {
			items.add(new SelectItem(type, type.getCashFlowType()));
		}
		return items;
	}

	public List<SelectItem> getCatchmentTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CatchmentType type : CatchmentType.values()) {
			items.add(new SelectItem(type, type.getCatchmentType()));
		}
		return items;
	}

	public List<SelectItem> getYesNo() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (YesNo type : YesNo.values()) {
			items.add(new SelectItem(type, type.getYesNo()));
		}
		return items;
	}

	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	public List<SelectItem> getMaterials() {

		setListMaterial(materialRepository.findAll());
		for (Material p : listMaterial) {
			itensMaterial.add(new SelectItem(p, p.getDescription()));
		}
		return itensMaterial;
	}

	public List<SelectItem> getSuppliers() {

		setListSupplier(supplierRepository.findAll());
		for (Supplier p : listSupplier) {
			itensSupplier.add(new SelectItem(p, p.getDescription()));
		}
		return itensSupplier;
	}

	public Long getIdBudget() {
		return idBudget;
	}

	public void setIdBudget(Long idBudget) {
		this.idBudget = idBudget;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public List<EventMain> getListEvent() {
		return listEvent;
	}

	public void setListEvent(List<EventMain> listEvent) {
		this.listEvent = listEvent;
	}

	public List<SelectItem> getItensEvent() {
		return itensEvent;
	}

	public void setItensEvent(List<SelectItem> itensEvent) {
		this.itensEvent = itensEvent;
	}

	public List<Material> getListMaterial() {
		return listMaterial;
	}

	public void setListMaterial(List<Material> listMaterial) {
		this.listMaterial = listMaterial;
	}

	public List<SelectItem> getItensMaterial() {
		return itensMaterial;
	}

	public void setItensMaterial(List<SelectItem> itensMaterial) {
		this.itensMaterial = itensMaterial;
	}

	public List<Supplier> getListSupplier() {
		return listSupplier;
	}

	public void setListSupplier(List<Supplier> listSupplier) {
		this.listSupplier = listSupplier;
	}

	public List<SelectItem> getItensSupplier() {
		return itensSupplier;
	}

	public void setItensSupplier(List<SelectItem> itensSupplier) {
		this.itensSupplier = itensSupplier;
	}

}
