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
package br.com.uaijug.chronos.event.speaker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.AddressRepository;
import br.com.uaijug.chronos.admin.data.repository.CompanyRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.speaker.data.repository.SpeakerRepository;
import br.com.uaijug.chronos.event.speaker.model.Speaker;
import br.com.uaijug.chronos.event.speaker.service.SpeakerRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SpeakerController extends AbstractManageBeans {

	@Inject
	private SpeakerRegistration speakerRegistration;

	@Inject
	private SpeakerRepository speakerRepository;

	@Inject
	private CompanyRepository companyRepository;

	@Inject
	private EventMainRepository eventMainRepository;

	@Inject
	private AddressRepository addressRepository;

	private Long idSpeaker;

	private Speaker speaker;

	List<Speaker> speakers;

	private List<Company> listCompany = null;

	/** The itens state. */
	List<SelectItem> itensCompany = null;

	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	@PostConstruct
	public void initNewSpeaker() {
		speaker = new Speaker();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSpeaker = null;

		itensCompany = new ArrayList<SelectItem>();
		listCompany = new ArrayList<Company>();

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();

		itensAddress = new ArrayList<SelectItem>();
		listAddress = new ArrayList<Address>();
	}

	public List<Speaker> getSpeakers() {
		return speakerRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSpeaker != null) {
				speaker = speakerRepository.findById(idSpeaker);
				if (speaker == null) {
					redirect("list.jsf");
				}
			} else {
				speaker = new Speaker();
			}
		}
	}

	public String register() throws Exception {
		try {

			speakerRegistration.register(speaker);
			successMessage("label.speaker.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.speaker-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSpeaker = null;
		speaker = new Speaker();
	}

	public List<SelectItem> getCompanys() {

		setListCompany(companyRepository.findAll());
		for (Company p : listCompany) {
			itensCompany.add(new SelectItem(p, p.getDescription()));
		}
		return itensCompany;
	}

	public List<SelectItem> getAddresses() {

		setListAddress(addressRepository.findAll());
		for (Address p : listAddress) {
			itensAddress.add(new SelectItem(p, p.getStreet()));
		}
		return itensAddress;
	}

	public List<SelectItem> getEvents() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	public Long getIdSpeaker() {
		return idSpeaker;
	}

	public void setIdSpeaker(Long idSpeaker) {
		this.idSpeaker = idSpeaker;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
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

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
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
