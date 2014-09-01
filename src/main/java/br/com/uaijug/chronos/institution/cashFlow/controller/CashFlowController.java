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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.CompanyRepository;
import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.institution.cashFlow.data.repository.CashFlowCategoryRepository;
import br.com.uaijug.chronos.institution.cashFlow.data.repository.CashFlowRepository;
import br.com.uaijug.chronos.institution.cashFlow.model.CashFlow;
import br.com.uaijug.chronos.institution.cashFlow.model.CashFlowCategory;
import br.com.uaijug.chronos.institution.cashFlow.service.CashFlowRegistration;
import br.com.uaijug.chronos.model.types.CashFlowType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more cashFlow the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class CashFlowController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class CashFlowController extends AbstractManageBeans {

	/** The cash flow registration. */
	@Inject
	private CashFlowRegistration cashFlowRegistration;

	/** The cash flow repository. */
	@Inject
	private CashFlowRepository cashFlowRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The cash flow category repository. */
	@Inject
	private CashFlowCategoryRepository cashFlowCategoryRepository;

	/** The company repository. */
	@Inject
	private CompanyRepository companyRepository;

	/** The id cash flow. */
	private Long idCashFlow;

	/** The cash flow. */
	private CashFlow cashFlow;

	/** The cash flows. */
	List<CashFlow> cashFlows;

	/** The list company. */
	private List<Company> listCompany = null;

	/** The handle list company. */
	private List<Company> handleListCompany = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensCompany = null;

	/** The list cash flow category. */
	private List<CashFlowCategory> listCashFlowCategory = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensCashFlowCategory = null;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensEvent = null;

	/**
	 * Inits the new cash flow.
	 */
	@PostConstruct
	public void initNewCashFlow() {
		cashFlow = new CashFlow();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idCashFlow = null;

		itensCompany = new ArrayList<SelectItem>();
		listCompany = new ArrayList<Company>();

		itensCashFlowCategory = new ArrayList<SelectItem>();
		listCashFlowCategory = new ArrayList<CashFlowCategory>();

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

	/**
	 * Gets the cash flows.
	 *
	 * @return the cash flows
	 */
	public List<CashFlow> getCashFlows() {
		return cashFlowRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idCashFlow != null) {
				cashFlow = cashFlowRepository.findById(idCashFlow);
				if (cashFlow == null) {
					redirect("list.jsf");
				}
			} else {
				cashFlow = new CashFlow();
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

			cashFlowRegistration.register(cashFlow);
			successMessage("label.cashFlow.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.cashFlow-exists");
		}
		return null;
	}

	/**
	 * Gets the last value total.
	 *
	 * @return the last value total
	 */
	public int getLastValueTotal() {
		int totalRevenue = 0;
		int totalExpense = 0;

		for (CashFlow cf : getCashFlows()) {

			if (cf.getCashFlowType() == "revenue")
				totalRevenue += cf.getValueTotalPayment();
			if (cf.getCashFlowType() == "expense")
				totalExpense += cf.getValueTotalPayment();
		}

		return (totalRevenue - totalExpense);
	}

	/**
	 * Calcular desconto.
	 *
	 * @param valor the valor
	 * @param porcentagem the porcentagem
	 * @return the big decimal
	 */
	public BigDecimal calcularDesconto(BigDecimal valor, int porcentagem) {
		BigDecimal fator = new BigDecimal(porcentagem).divide(new BigDecimal(
				100));
		BigDecimal desconto = valor.multiply(fator);
		return desconto;
	}

	/**
	 * Gets the companys.
	 *
	 * @return the companys
	 */
	public List<SelectItem> getCompanys() {

		if (getHandleListCompany() != null) {
			for (Company p : handleListCompany) {
				itensCompany.add(new SelectItem(p, p.getDescription()));
			}
		} else {
			setListCompany(companyRepository.findAll());
			for (Company p : listCompany) {
				itensCompany.add(new SelectItem(p, p.getDescription()));
			}
		}
		return itensCompany;
	}

	/**
	 * Gets the cash flow categorys.
	 *
	 * @return the cash flow categorys
	 */
	public List<SelectItem> getCashFlowCategorys() {

		setListCashFlowCategory(cashFlowCategoryRepository.findAll());
		for (CashFlowCategory p : listCashFlowCategory) {
			itensCashFlowCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensCashFlowCategory;
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
		idCashFlow = null;
		cashFlow = new CashFlow();
	}

	/**
	 * Gets the id cash flow.
	 *
	 * @return the id cash flow
	 */
	public Long getIdCashFlow() {
		return idCashFlow;
	}

	/**
	 * Sets the id cash flow.
	 *
	 * @param idCashFlow the new id cash flow
	 */
	public void setIdCashFlow(Long idCashFlow) {
		this.idCashFlow = idCashFlow;
	}

	/**
	 * Gets the cash flow.
	 *
	 * @return the cash flow
	 */
	public CashFlow getCashFlow() {
		return cashFlow;
	}

	/**
	 * Sets the cash flow.
	 *
	 * @param cashFlow the new cash flow
	 */
	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;

	}

	/**
	 * Gets the list company.
	 *
	 * @return the list company
	 */
	public List<Company> getListCompany() {
		return listCompany;
	}

	/**
	 * Sets the list company.
	 *
	 * @param listCompany the new list company
	 */
	public void setListCompany(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

	/**
	 * Gets the itens company.
	 *
	 * @return the itens company
	 */
	public List<SelectItem> getItensCompany() {
		return itensCompany;
	}

	/**
	 * Sets the itens company.
	 *
	 * @param itensCompany the new itens company
	 */
	public void setItensCompany(List<SelectItem> itensCompany) {
		this.itensCompany = itensCompany;
	}

	/**
	 * Gets the list cash flow category.
	 *
	 * @return the list cash flow category
	 */
	public List<CashFlowCategory> getListCashFlowCategory() {
		return listCashFlowCategory;
	}

	/**
	 * Sets the list cash flow category.
	 *
	 * @param listCashFlowCategory the new list cash flow category
	 */
	public void setListCashFlowCategory(
			List<CashFlowCategory> listCashFlowCategory) {
		this.listCashFlowCategory = listCashFlowCategory;
	}

	/**
	 * Gets the itens cash flow category.
	 *
	 * @return the itens cash flow category
	 */
	public List<SelectItem> getItensCashFlowCategory() {
		return itensCashFlowCategory;
	}

	/**
	 * Sets the itens cash flow category.
	 *
	 * @param itensCashFlowCategory the new itens cash flow category
	 */
	public void setItensCashFlowCategory(List<SelectItem> itensCashFlowCategory) {
		this.itensCashFlowCategory = itensCashFlowCategory;
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
	 * Gets the handle list company.
	 *
	 * @return the handle list company
	 */
	public List<Company> getHandleListCompany() {
		return handleListCompany;
	}

	/**
	 * Sets the handle list company.
	 *
	 * @param handleListCompany the new handle list company
	 */
	public void setHandleListCompany(List<Company> handleListCompany) {
		this.handleListCompany = handleListCompany;
	}

}
