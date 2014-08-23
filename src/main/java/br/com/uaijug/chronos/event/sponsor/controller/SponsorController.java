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
package br.com.uaijug.chronos.event.sponsor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.AddressRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.sponsor.data.repository.SponsorRepository;
import br.com.uaijug.chronos.event.sponsor.model.Sponsor;
import br.com.uaijug.chronos.event.sponsor.service.SponsorRegistration;
import br.com.uaijug.chronos.model.types.SponsorLevel;
import br.com.uaijug.chronos.model.types.SponsorOpportunityLevel;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more sponsor the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SponsorController extends AbstractManageBeans {

	@Inject
	private SponsorRegistration sponsorRegistration;

	@Inject
	private SponsorRepository sponsorRepository;

	@Inject
	private AddressRepository addressRepository;

	private Long idSponsor;

	private Sponsor sponsor;

	List<Sponsor> sponsors;

	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	@PostConstruct
	public void initNewSponsor() {
		sponsor = new Sponsor();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSponsor = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	public List<Sponsor> getSponsors() {
		return sponsorRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSponsor != null) {
				sponsor = sponsorRepository.findById(idSponsor);
				if (sponsor == null) {
					redirect("list.jsf");
				}
			} else {
				sponsor = new Sponsor();
			}
		}
	}

	public String register() throws Exception {
		try {

			sponsorRegistration.register(sponsor);
			successMessage("label.sponsor.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.sponsor-exists");
		}
		return null;
	}

	public List<SelectItem> getSponsorLevels() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SponsorLevel type : SponsorLevel.values()) {
			items.add(new SelectItem(type, type.getSponsorLevel()));
		}
		return items;
	}

	public List<SelectItem> getSponsorOpportunityLevels() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SponsorOpportunityLevel type : SponsorOpportunityLevel.values()) {
			items.add(new SelectItem(type, type.getSponsorOpportunityLevel()));
		}
		return items;
	}

	public List<SelectItem> getAddresses() {

		setListAddress(addressRepository.findAll());
		for (Address p : listAddress) {
			itensAddress.add(new SelectItem(p, p.getStreet()));
		}
		return itensAddress;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSponsor = null;
		sponsor = new Sponsor();
	}

	public Long getIdSponsor() {
		return idSponsor;
	}

	public void setIdSponsor(Long idSponsor) {
		this.idSponsor = idSponsor;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public List<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	public List<SelectItem> getItensAddress() {
		return itensAddress;
	}

	public void setItensAddress(List<SelectItem> itensAddress) {
		this.itensAddress = itensAddress;
	}

}
