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
import br.com.uaijug.chronos.event.data.repository.EventProposalRepository;
import br.com.uaijug.chronos.event.model.EventProposal;
import br.com.uaijug.chronos.event.sponsor.data.repository.SponsorFollowupRepository;
import br.com.uaijug.chronos.event.sponsor.data.repository.SponsorRepository;
import br.com.uaijug.chronos.event.sponsor.model.Sponsor;
import br.com.uaijug.chronos.event.sponsor.model.SponsorFollowup;
import br.com.uaijug.chronos.event.sponsor.service.SponsorFollowupRegistration;
import br.com.uaijug.chronos.model.types.ContactType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more sponsorFollowup the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SponsorFollowupController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SponsorFollowupController extends AbstractManageBeans {

	/** The sponsor followup registration. */
	@Inject
	private SponsorFollowupRegistration sponsorFollowupRegistration;

	/** The sponsor followup repository. */
	@Inject
	private SponsorFollowupRepository sponsorFollowupRepository;

	/** The sponsor repository. */
	@Inject
	private SponsorRepository sponsorRepository;

	/** The event proposal repository. */
	@Inject
	private EventProposalRepository eventProposalRepository;

	/** The id sponsor followup. */
	private Long idSponsorFollowup;

	/** The sponsor followup. */
	private SponsorFollowup sponsorFollowup;

	/** The sponsor followups. */
	List<SponsorFollowup> sponsorFollowups;

	/** The list sponsor. */
	private List<Sponsor> listSponsor = null;

	/** The itens state. */
	List<SelectItem> itensSponsor = null;

	/** The list event proposal. */
	private List<EventProposal> listEventProposal = null;

	/** The itens state. */
	List<SelectItem> itensEventProposal = null;

	/**
	 * Inits the new sponsor followup.
	 */
	@PostConstruct
	public void initNewSponsorFollowup() {
		sponsorFollowup = new SponsorFollowup();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSponsorFollowup = null;

		itensSponsor = new ArrayList<SelectItem>();
		listSponsor = new ArrayList<Sponsor>();

		itensEventProposal = new ArrayList<SelectItem>();
		listEventProposal = new ArrayList<EventProposal>();
	}

	/**
	 * Gets the sponsor followups.
	 *
	 * @return the sponsor followups
	 */
	public List<SponsorFollowup> getSponsorFollowups() {
		return sponsorFollowupRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSponsorFollowup != null) {
				sponsorFollowup = sponsorFollowupRepository
						.findById(idSponsorFollowup);
				if (sponsorFollowup == null) {
					redirect("list.jsf");
				}
			} else {
				sponsorFollowup = new SponsorFollowup();
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

			sponsorFollowupRegistration.register(sponsorFollowup);
			successMessage("label.sponsorFollowup.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.sponsorFollowup-exists");
		}
		return null;
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
	 * Gets the event proposals.
	 *
	 * @return the event proposals
	 */
	public List<SelectItem> getEventProposals() {

		setListEventProposal(eventProposalRepository.findAll());
		for (EventProposal p : listEventProposal) {
			itensEventProposal.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventProposal;
	}
	
	/**
	 * Gets the contact types.
	 *
	 * @return the contact types
	 */
	public List<SelectItem> getContactTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (ContactType type : ContactType.values()) {
			items.add(new SelectItem(type, type.getContactType()));
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
		idSponsorFollowup = null;
		sponsorFollowup = new SponsorFollowup();
	}

	/**
	 * Gets the id sponsor followup.
	 *
	 * @return the id sponsor followup
	 */
	public Long getIdSponsorFollowup() {
		return idSponsorFollowup;
	}

	/**
	 * Sets the id sponsor followup.
	 *
	 * @param idSponsorFollowup the new id sponsor followup
	 */
	public void setIdSponsorFollowup(Long idSponsorFollowup) {
		this.idSponsorFollowup = idSponsorFollowup;
	}

	/**
	 * Gets the sponsor followup.
	 *
	 * @return the sponsor followup
	 */
	public SponsorFollowup getSponsorFollowup() {
		return sponsorFollowup;
	}

	/**
	 * Sets the sponsor followup.
	 *
	 * @param sponsorFollowup the new sponsor followup
	 */
	public void setSponsorFollowup(SponsorFollowup sponsorFollowup) {
		this.sponsorFollowup = sponsorFollowup;
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
	 * Gets the list event proposal.
	 *
	 * @return the list event proposal
	 */
	public List<EventProposal> getListEventProposal() {
		return listEventProposal;
	}

	/**
	 * Sets the list event proposal.
	 *
	 * @param listEventProposal the new list event proposal
	 */
	public void setListEventProposal(List<EventProposal> listEventProposal) {
		this.listEventProposal = listEventProposal;
	}

	/**
	 * Gets the itens event proposal.
	 *
	 * @return the itens event proposal
	 */
	public List<SelectItem> getItensEventProposal() {
		return itensEventProposal;
	}

	/**
	 * Sets the itens event proposal.
	 *
	 * @param itensEventProposal the new itens event proposal
	 */
	public void setItensEventProposal(List<SelectItem> itensEventProposal) {
		this.itensEventProposal = itensEventProposal;
	}

}
