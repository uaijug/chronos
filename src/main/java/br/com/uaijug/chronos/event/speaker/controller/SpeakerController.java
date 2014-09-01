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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SpeakerController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SpeakerController extends AbstractManageBeans {

	/** The speaker registration. */
	@Inject
	private SpeakerRegistration speakerRegistration;

	/** The speaker repository. */
	@Inject
	private SpeakerRepository speakerRepository;

	/** The company repository. */
	@Inject
	private CompanyRepository companyRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The id speaker. */
	private Long idSpeaker;

	/** The speaker. */
	private Speaker speaker;

	/** The speakers. */
	List<Speaker> speakers;

	/** The list company. */
	private List<Company> listCompany = null;

	/** The itens state. */
	List<SelectItem> itensCompany = null;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/** The list address. */
	private List<Address> listAddress = null;

	/** The itens state. */
	List<SelectItem> itensAddress = null;

	/**
	 * Inits the new speaker.
	 */
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

	/**
	 * Gets the speakers.
	 *
	 * @return the speakers
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idSpeaker = null;
		speaker = new Speaker();
	}

	/**
	 * Gets the companys.
	 *
	 * @return the companys
	 */
	public List<SelectItem> getCompanys() {

		setListCompany(companyRepository.findAll());
		for (Company p : listCompany) {
			itensCompany.add(new SelectItem(p, p.getDescription()));
		}
		return itensCompany;
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
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<SelectItem> getEvents() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	/**
	 * Gets the id speaker.
	 *
	 * @return the id speaker
	 */
	public Long getIdSpeaker() {
		return idSpeaker;
	}

	/**
	 * Sets the id speaker.
	 *
	 * @param idSpeaker the new id speaker
	 */
	public void setIdSpeaker(Long idSpeaker) {
		this.idSpeaker = idSpeaker;
	}

	/**
	 * Gets the speaker.
	 *
	 * @return the speaker
	 */
	public Speaker getSpeaker() {
		return speaker;
	}

	/**
	 * Sets the speaker.
	 *
	 * @param speaker the new speaker
	 */
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
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
	 * Gets the list event main.
	 *
	 * @return the list event main
	 */
	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	/**
	 * Sets the list event main.
	 *
	 * @param listEventMain the new list event main
	 */
	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	/**
	 * Gets the itens event main.
	 *
	 * @return the itens event main
	 */
	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	/**
	 * Sets the itens event main.
	 *
	 * @param itensEventMain the new itens event main
	 */
	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
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
