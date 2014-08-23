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
import br.com.uaijug.chronos.event.budget.data.repository.PurchaseCostRepository;
import br.com.uaijug.chronos.event.budget.model.Budget;
import br.com.uaijug.chronos.event.budget.model.Material;
import br.com.uaijug.chronos.event.budget.model.PurchaseCost;
import br.com.uaijug.chronos.event.budget.service.PurchaseCostRegistration;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.supplier.data.repository.SupplierRepository;
import br.com.uaijug.chronos.event.supplier.model.Supplier;
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class PurchaseCostController extends AbstractManageBeans {

	@Inject
	private PurchaseCostRegistration purchaseCostRegistration;

	@Inject
	private PurchaseCostRepository purchaseCostRepository;
	
	@Inject
	private EventMainRepository eventRepository;

	@Inject
	private BudgetRepository budgetRepository;

	@Inject
	private MaterialRepository materialRepository;

	@Inject
	private SupplierRepository supplierRepository;
	
	@Inject
	private MemberRepository memberRepository;

	private Long idPurchaseCost;

	private PurchaseCost purchaseCost;

	List<PurchaseCost> purchaseCosts;

	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	private List<Budget> listBudget = null;

	/** The itens state. */
	List<SelectItem> itensBudget = null;

	private List<Material> listMaterial = null;

	/** The itens state. */
	List<SelectItem> itensMaterial = null;

	private List<Supplier> listSupplier = null;

	/** The itens state. */
	List<SelectItem> itensSupplier = null;
	
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;
	
	@PostConstruct
	public void initNewPurchaseCost() {
		purchaseCost = new PurchaseCost();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPurchaseCost = null;
		
		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();

		itensBudget = new ArrayList<SelectItem>();
		listBudget = new ArrayList<Budget>();

		itensMaterial = new ArrayList<SelectItem>();
		listMaterial = new ArrayList<Material>();

		itensSupplier = new ArrayList<SelectItem>();
		listSupplier = new ArrayList<Supplier>();
		
		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();
	}

	public List<PurchaseCost> getPurchaseCosts() {
		return purchaseCostRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idPurchaseCost != null) {
				purchaseCost = purchaseCostRepository.findById(idPurchaseCost);
				if (purchaseCost == null) {
					redirect("list.jsf");
				}
			} else {
				purchaseCost = new PurchaseCost();
			}
		}
	}

	public String register() throws Exception {
		try {

			purchaseCostRegistration.register(purchaseCost);
			successMessage("label.purchaseCost.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.purchaseCost-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idPurchaseCost = null;
		purchaseCost = new PurchaseCost();
	}

	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	public List<SelectItem> getBudgets() {

		setListBudget(budgetRepository.findAll());
		for (Budget p : listBudget) {
			itensBudget
					.add(new SelectItem(p, p.getMaterial().getDescription()));
		}
		return itensBudget;
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
	
	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	public int getLastValueTotal() {
		int total = 0;

		for (PurchaseCost cf : getPurchaseCosts()) {
			total += cf.getTotalValue();
		}

		return total;
	}

	
	public Long getIdPurchaseCost() {
		return idPurchaseCost;
	}

	public void setIdPurchaseCost(Long idPurchaseCost) {
		this.idPurchaseCost = idPurchaseCost;
	}

	public PurchaseCost getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(PurchaseCost purchaseCost) {
		this.purchaseCost = purchaseCost;
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

	public List<Budget> getListBudget() {
		return listBudget;
	}

	public void setListBudget(List<Budget> listBudget) {
		this.listBudget = listBudget;
	}

	public List<SelectItem> getItensBudget() {
		return itensBudget;
	}

	public void setItensBudget(List<SelectItem> itensBudget) {
		this.itensBudget = itensBudget;
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

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}		

}
