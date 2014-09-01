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

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.sponsor.data.repository.SponsorRepository;
import br.com.uaijug.chronos.event.sponsor.data.repository.SponsorshipRepository;
import br.com.uaijug.chronos.event.sponsor.model.Sponsor;
import br.com.uaijug.chronos.event.sponsor.model.Sponsorship;
import br.com.uaijug.chronos.event.sponsor.service.SponsorshipRegistration;
import br.com.uaijug.chronos.model.types.PaimentType;
import br.com.uaijug.chronos.model.types.SponsorshipLevel;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more sponsorship the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SponsorshipController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SponsorshipController extends AbstractManageBeans {

	/** The sponsorship registration. */
	@Inject
	private SponsorshipRegistration sponsorshipRegistration;

	/** The sponsorship repository. */
	@Inject
	private SponsorshipRepository sponsorshipRepository;
	
	/** The sponsor repository. */
	@Inject
	private SponsorRepository sponsorRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The id sponsorship. */
	private Long idSponsorship;

	/** The sponsorship. */
	private Sponsorship sponsorship;

	/** The sponsorships. */
	List<Sponsorship> sponsorships;
	
	/** The list sponsor. */
	private List<Sponsor> listSponsor = null;

	/** The itens state. */
	List<SelectItem> itensSponsor = null;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;


	/**
	 * Inits the new sponsorship.
	 */
	@PostConstruct
	public void initNewSponsorship() {
		sponsorship = new Sponsorship();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSponsorship = null;
		
		
		itensSponsor = new ArrayList<SelectItem>();
		listSponsor = new ArrayList<Sponsor>();

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

	/**
	 * Gets the sponsorships.
	 *
	 * @return the sponsorships
	 */
	public List<Sponsorship> getSponsorships() {
		return sponsorshipRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSponsorship != null) {
				sponsorship = sponsorshipRepository.findById(idSponsorship);
				if (sponsorship == null) {
					redirect("list.jsf");
				}
			} else {
				sponsorship = new Sponsorship();
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

			sponsorshipRegistration.register(sponsorship);
			successMessage("label.sponsorship.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.sponsorship-exists");
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
		idSponsorship = null;
		sponsorship = new Sponsorship();
	}
	
	/**
	 * Gets the sponsorship levels.
	 *
	 * @return the sponsorship levels
	 */
	public List<SelectItem> getSponsorshipLevels() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (SponsorshipLevel type : SponsorshipLevel.values()) {
			items.add(new SelectItem(type, type.getSponsorshipLevel()));
		}
		return items;
	}
	
	/**
	 * Gets the paiment types.
	 *
	 * @return the paiment types
	 */
	public List<SelectItem> getPaimentTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PaimentType type : PaimentType.values()) {
			items.add(new SelectItem(type, type.getPaimentType()));
		}
		return items;
	}
	
	
	/**
	 * Gets the sponsors.
	 *
	 * @return the sponsors
	 */
	public List<SelectItem> getSponsors() {

		setListSponsor(sponsorRepository.findAll());
		for (Sponsor p : listSponsor) {
			itensSponsor.add(new SelectItem(p, p.getDescription()));
		}
		return itensSponsor;
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
	 * Gets the id sponsorship.
	 *
	 * @return the id sponsorship
	 */
	public Long getIdSponsorship() {
		return idSponsorship;
	}

	/**
	 * Sets the id sponsorship.
	 *
	 * @param idSponsorship the new id sponsorship
	 */
	public void setIdSponsorship(Long idSponsorship) {
		this.idSponsorship = idSponsorship;
	}

	/**
	 * Gets the sponsorship.
	 *
	 * @return the sponsorship
	 */
	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	/**
	 * Sets the sponsorship.
	 *
	 * @param sponsorship the new sponsorship
	 */
	public void setSponsorship(Sponsorship sponsorship) {
		this.sponsorship = sponsorship;
	}

	/**
	 * Gets the list sponsor.
	 *
	 * @return the list sponsor
	 */
	public List<Sponsor> getListSponsor() {
		return listSponsor;
	}

	/**
	 * Sets the list sponsor.
	 *
	 * @param listSponsor the new list sponsor
	 */
	public void setListSponsor(List<Sponsor> listSponsor) {
		this.listSponsor = listSponsor;
	}

	/**
	 * Gets the itens sponsor.
	 *
	 * @return the itens sponsor
	 */
	public List<SelectItem> getItensSponsor() {
		return itensSponsor;
	}

	/**
	 * Sets the itens sponsor.
	 *
	 * @param itensSponsor the new itens sponsor
	 */
	public void setItensSponsor(List<SelectItem> itensSponsor) {
		this.itensSponsor = itensSponsor;
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
	
	

}
