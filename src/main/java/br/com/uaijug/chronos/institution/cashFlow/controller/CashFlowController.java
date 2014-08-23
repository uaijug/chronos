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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more cashFlow the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class CashFlowController extends AbstractManageBeans {

	@Inject
	private CashFlowRegistration cashFlowRegistration;

	@Inject
	private CashFlowRepository cashFlowRepository;

	@Inject
	private EventMainRepository eventRepository;

	@Inject
	private CashFlowCategoryRepository cashFlowCategoryRepository;

	@Inject
	private CompanyRepository companyRepository;

	private Long idCashFlow;

	private CashFlow cashFlow;

	List<CashFlow> cashFlows;

	private List<Company> listCompany = null;

	private List<Company> handleListCompany = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensCompany = null;

	private List<CashFlowCategory> listCashFlowCategory = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensCashFlowCategory = null;

	private List<EventMain> listEvent = null;

	/** The itens cashFlowCategory. */
	List<SelectItem> itensEvent = null;

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

	public BigDecimal calcularDesconto(BigDecimal valor, int porcentagem) {
		BigDecimal fator = new BigDecimal(porcentagem).divide(new BigDecimal(
				100));
		BigDecimal desconto = valor.multiply(fator);
		return desconto;
	}

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

	public List<SelectItem> getCashFlowCategorys() {

		setListCashFlowCategory(cashFlowCategoryRepository.findAll());
		for (CashFlowCategory p : listCashFlowCategory) {
			itensCashFlowCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensCashFlowCategory;
	}

	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}
	
	public List<SelectItem> getCashFlowTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (CashFlowType type : CashFlowType.values()) {
			items.add(new SelectItem(type, type.getCashFlowType()));
		}
		return items;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idCashFlow = null;
		cashFlow = new CashFlow();
	}

	public Long getIdCashFlow() {
		return idCashFlow;
	}

	public void setIdCashFlow(Long idCashFlow) {
		this.idCashFlow = idCashFlow;
	}

	public CashFlow getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;

	}

	public List<Company> getListCompany() {
		return listCompany;
	}

	public void setListCompany(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

	public List<SelectItem> getItensCompany() {
		return itensCompany;
	}

	public void setItensCompany(List<SelectItem> itensCompany) {
		this.itensCompany = itensCompany;
	}

	public List<CashFlowCategory> getListCashFlowCategory() {
		return listCashFlowCategory;
	}

	public void setListCashFlowCategory(
			List<CashFlowCategory> listCashFlowCategory) {
		this.listCashFlowCategory = listCashFlowCategory;
	}

	public List<SelectItem> getItensCashFlowCategory() {
		return itensCashFlowCategory;
	}

	public void setItensCashFlowCategory(List<SelectItem> itensCashFlowCategory) {
		this.itensCashFlowCategory = itensCashFlowCategory;
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

	public List<Company> getHandleListCompany() {
		return handleListCompany;
	}

	public void setHandleListCompany(List<Company> handleListCompany) {
		this.handleListCompany = handleListCompany;
	}

}
