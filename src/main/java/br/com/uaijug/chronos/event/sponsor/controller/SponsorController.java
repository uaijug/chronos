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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more sponsor the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SponsorController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SponsorController extends AbstractManageBeans {

	/** The sponsor registration. */
	@Inject
	private SponsorRegistration sponsorRegistration;

	/** The sponsor repository. */
	@Inject
	private SponsorRepository sponsorRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The id sponsor. */
	private Long idSponsor;

	/** The sponsor. */
	private Sponsor sponsor;

	/** The sponsors. */
	List<Sponsor> sponsors;

	/** The list address. */
	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	/**
	 * Inits the new sponsor.
	 */
	@PostConstruct
	public void initNewSponsor() {
		sponsor = new Sponsor();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSponsor = null;

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	/**
	 * Gets the sponsors.
	 *
	 * @return the sponsors
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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

	/**
	 * Gets the sponsor levels.
	 *
	 * @return the sponsor levels
	 */
	public List<SelectItem> getSponsorLevels() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SponsorLevel type : SponsorLevel.values()) {
			items.add(new SelectItem(type, type.getSponsorLevel()));
		}
		return items;
	}

	/**
	 * Gets the sponsor opportunity levels.
	 *
	 * @return the sponsor opportunity levels
	 */
	public List<SelectItem> getSponsorOpportunityLevels() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SponsorOpportunityLevel type : SponsorOpportunityLevel.values()) {
			items.add(new SelectItem(type, type.getSponsorOpportunityLevel()));
		}
		return items;
	}

	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */
	public List<SelectItem> getAddresses() {

		setListAddress(addressRepository.findAll());
		for (Address p : listAddress) {
			itensAddress.add(new SelectItem(p, p.getStreet()));
		}
		return itensAddress;
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
		idSponsor = null;
		sponsor = new Sponsor();
	}

	/**
	 * Gets the id sponsor.
	 *
	 * @return the id sponsor
	 */
	public Long getIdSponsor() {
		return idSponsor;
	}

	/**
	 * Sets the id sponsor.
	 *
	 * @param idSponsor the new id sponsor
	 */
	public void setIdSponsor(Long idSponsor) {
		this.idSponsor = idSponsor;
	}

	/**
	 * Gets the sponsor.
	 *
	 * @return the sponsor
	 */
	public Sponsor getSponsor() {
		return sponsor;
	}

	/**
	 * Sets the sponsor.
	 *
	 * @param sponsor the new sponsor
	 */
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * Gets the list address.
	 *
	 * @return the list address
	 */
	public List<Address> getListAddress() {
		return listAddress;
	}

	/**
	 * Sets the list address.
	 *
	 * @param listAddress the new list address
	 */
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	/**
	 * Gets the itens address.
	 *
	 * @return the itens address
	 */
	public List<SelectItem> getItensAddress() {
		return itensAddress;
	}

	/**
	 * Sets the itens address.
	 *
	 * @param itensAddress the new itens address
	 */
	public void setItensAddress(List<SelectItem> itensAddress) {
		this.itensAddress = itensAddress;
	}

}
