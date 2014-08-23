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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more sponsorFollowup the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SponsorFollowupController extends AbstractManageBeans {

	@Inject
	private SponsorFollowupRegistration sponsorFollowupRegistration;

	@Inject
	private SponsorFollowupRepository sponsorFollowupRepository;

	@Inject
	private SponsorRepository sponsorRepository;

	@Inject
	private EventProposalRepository eventProposalRepository;

	private Long idSponsorFollowup;

	private SponsorFollowup sponsorFollowup;

	List<SponsorFollowup> sponsorFollowups;

	private List<Sponsor> listSponsor = null;

	/** The itens state. */
	List<SelectItem> itensSponsor = null;

	private List<EventProposal> listEventProposal = null;

	/** The itens state. */
	List<SelectItem> itensEventProposal = null;

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

	public List<SelectItem> getSponsors() {

		setListSponsor(sponsorRepository.findAll());
		for (Sponsor p : listSponsor) {
			itensSponsor.add(new SelectItem(p, p.getDescription()));
		}
		return itensSponsor;
	}
	
	
	public List<SelectItem> getEventProposals() {

		setListEventProposal(eventProposalRepository.findAll());
		for (EventProposal p : listEventProposal) {
			itensEventProposal.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventProposal;
	}
	
	public List<SelectItem> getContactTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (ContactType type : ContactType.values()) {
			items.add(new SelectItem(type, type.getContactType()));
		}
		return items;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSponsorFollowup = null;
		sponsorFollowup = new SponsorFollowup();
	}

	public Long getIdSponsorFollowup() {
		return idSponsorFollowup;
	}

	public void setIdSponsorFollowup(Long idSponsorFollowup) {
		this.idSponsorFollowup = idSponsorFollowup;
	}

	public SponsorFollowup getSponsorFollowup() {
		return sponsorFollowup;
	}

	public void setSponsorFollowup(SponsorFollowup sponsorFollowup) {
		this.sponsorFollowup = sponsorFollowup;
	}

	public List<Sponsor> getListSponsor() {
		return listSponsor;
	}

	public void setListSponsor(List<Sponsor> listSponsor) {
		this.listSponsor = listSponsor;
	}

	public List<SelectItem> getItensSponsor() {
		return itensSponsor;
	}

	public void setItensSponsor(List<SelectItem> itensSponsor) {
		this.itensSponsor = itensSponsor;
	}

	public List<EventProposal> getListEventProposal() {
		return listEventProposal;
	}

	public void setListEventProposal(List<EventProposal> listEventProposal) {
		this.listEventProposal = listEventProposal;
	}

	public List<SelectItem> getItensEventProposal() {
		return itensEventProposal;
	}

	public void setItensEventProposal(List<SelectItem> itensEventProposal) {
		this.itensEventProposal = itensEventProposal;
	}

}
