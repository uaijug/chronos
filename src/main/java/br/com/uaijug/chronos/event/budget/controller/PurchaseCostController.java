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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class PurchaseCostController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class PurchaseCostController extends AbstractManageBeans {

	/** The purchase cost registration. */
	@Inject
	private PurchaseCostRegistration purchaseCostRegistration;

	/** The purchase cost repository. */
	@Inject
	private PurchaseCostRepository purchaseCostRepository;
	
	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The budget repository. */
	@Inject
	private BudgetRepository budgetRepository;

	/** The material repository. */
	@Inject
	private MaterialRepository materialRepository;

	/** The supplier repository. */
	@Inject
	private SupplierRepository supplierRepository;
	
	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The id purchase cost. */
	private Long idPurchaseCost;

	/** The purchase cost. */
	private PurchaseCost purchaseCost;

	/** The purchase costs. */
	List<PurchaseCost> purchaseCosts;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	/** The list budget. */
	private List<Budget> listBudget = null;

	/** The itens state. */
	List<SelectItem> itensBudget = null;

	/** The list material. */
	private List<Material> listMaterial = null;

	/** The itens state. */
	List<SelectItem> itensMaterial = null;

	/** The list supplier. */
	private List<Supplier> listSupplier = null;

	/** The itens state. */
	List<SelectItem> itensSupplier = null;
	
	/** The list member. */
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;
	
	/**
	 * Inits the new purchase cost.
	 */
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

	/**
	 * Gets the purchase costs.
	 *
	 * @return the purchase costs
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idPurchaseCost = null;
		purchaseCost = new PurchaseCost();
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
	 * Gets the budgets.
	 *
	 * @return the budgets
	 */
	public List<SelectItem> getBudgets() {

		setListBudget(budgetRepository.findAll());
		for (Budget p : listBudget) {
			itensBudget
					.add(new SelectItem(p, p.getMaterial().getDescription()));
		}
		return itensBudget;
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
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	/**
	 * Gets the last value total.
	 *
	 * @return the last value total
	 */
	public int getLastValueTotal() {
		int total = 0;

		for (PurchaseCost cf : getPurchaseCosts()) {
			total += cf.getTotalValue();
		}

		return total;
	}

	
	/**
	 * Gets the id purchase cost.
	 *
	 * @return the id purchase cost
	 */
	public Long getIdPurchaseCost() {
		return idPurchaseCost;
	}

	/**
	 * Sets the id purchase cost.
	 *
	 * @param idPurchaseCost the new id purchase cost
	 */
	public void setIdPurchaseCost(Long idPurchaseCost) {
		this.idPurchaseCost = idPurchaseCost;
	}

	/**
	 * Gets the purchase cost.
	 *
	 * @return the purchase cost
	 */
	public PurchaseCost getPurchaseCost() {
		return purchaseCost;
	}

	/**
	 * Sets the purchase cost.
	 *
	 * @param purchaseCost the new purchase cost
	 */
	public void setPurchaseCost(PurchaseCost purchaseCost) {
		this.purchaseCost = purchaseCost;
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
	 * Gets the list budget.
	 *
	 * @return the list budget
	 */
	public List<Budget> getListBudget() {
		return listBudget;
	}

	/**
	 * Sets the list budget.
	 *
	 * @param listBudget the new list budget
	 */
	public void setListBudget(List<Budget> listBudget) {
		this.listBudget = listBudget;
	}

	/**
	 * Gets the itens budget.
	 *
	 * @return the itens budget
	 */
	public List<SelectItem> getItensBudget() {
		return itensBudget;
	}

	/**
	 * Sets the itens budget.
	 *
	 * @param itensBudget the new itens budget
	 */
	public void setItensBudget(List<SelectItem> itensBudget) {
		this.itensBudget = itensBudget;
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

	/**
	 * Gets the list member.
	 *
	 * @return the list member
	 */
	public List<Member> getListMember() {
		return listMember;
	}

	/**
	 * Sets the list member.
	 *
	 * @param listMember the new list member
	 */
	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	/**
	 * Gets the itens member.
	 *
	 * @return the itens member
	 */
	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	/**
	 * Sets the itens member.
	 *
	 * @param itensMember the new itens member
	 */
	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}		

}
