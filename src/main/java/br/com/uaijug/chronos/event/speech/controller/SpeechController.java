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
package br.com.uaijug.chronos.event.speech.controller;

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
import br.com.uaijug.chronos.event.speaker.data.repository.SpeakerRepository;
import br.com.uaijug.chronos.event.speaker.model.Speaker;
import br.com.uaijug.chronos.event.speech.data.repository.SpeechRepository;
import br.com.uaijug.chronos.event.speech.data.repository.SpeechTypeRepository;
import br.com.uaijug.chronos.event.speech.model.Speech;
import br.com.uaijug.chronos.event.speech.model.SpeechType;
import br.com.uaijug.chronos.event.speech.service.SpeechRegistration;
import br.com.uaijug.chronos.model.types.Approve;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SpeechController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SpeechController extends AbstractManageBeans {

	/** The speech registration. */
	@Inject
	private SpeechRegistration speechRegistration;

	/** The speech repository. */
	@Inject
	private SpeechRepository speechRepository;

	/** The speaker repository. */
	@Inject
	private SpeakerRepository speakerRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The speech type repository. */
	@Inject
	private SpeechTypeRepository speechTypeRepository;

	/** The id speech. */
	private Long idSpeech;

	/** The speech. */
	private Speech speech;

	/** The speechs. */
	List<Speech> speechs;

	/** The list speaker. */
	private List<Speaker> listSpeaker = null;

	/** The itens state. */
	List<SelectItem> itensSpeaker = null;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	/** The list speech type. */
	private List<SpeechType> listSpeechType = null;

	/** The itens state. */
	List<SelectItem> itensSpeechType = null;

	/**
	 * Inits the new speech.
	 */
	@PostConstruct
	public void initNewSpeech() {
		speech = new Speech();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSpeech = null;

		itensSpeaker = new ArrayList<SelectItem>();
		listSpeaker = new ArrayList<Speaker>();

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();

		itensSpeechType = new ArrayList<SelectItem>();
		listSpeechType = new ArrayList<SpeechType>();
	}

	/**
	 * Gets the speechs.
	 *
	 * @return the speechs
	 */
	public List<Speech> getSpeechs() {
		return speechRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSpeech != null) {
				speech = speechRepository.findById(idSpeech);
				if (speech == null) {
					redirect("list.jsf");
				}
			} else {
				speech = new Speech();
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

			speechRegistration.register(speech);
			successMessage("label.speech.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.speech-exists");
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
		idSpeech = null;
		speech = new Speech();
	}

	/**
	 * Gets the speakers.
	 *
	 * @return the speakers
	 */
	public List<SelectItem> getSpeakers() {

		setListSpeaker(speakerRepository.findAll());
		for (Speaker p : listSpeaker) {
			itensSpeaker.add(new SelectItem(p, p.getName()));
		}
		return itensSpeaker;
	}

	/**
	 * Gets the speech types.
	 *
	 * @return the speech types
	 */
	public List<SelectItem> getSpeechTypes() {

		setListSpeechType(speechTypeRepository.findAll());
		for (SpeechType p : listSpeechType) {
			itensSpeechType.add(new SelectItem(p, p.getDescription()));
		}
		return itensSpeechType;
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
	 * Gets the approves.
	 *
	 * @return the approves
	 */
	public List<SelectItem> getApproves() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Approve type : Approve.values()) {
			items.add(new SelectItem(type, type.toString()));
		}
		return items;
	}

	/**
	 * Gets the id speech.
	 *
	 * @return the id speech
	 */
	public Long getIdSpeech() {
		return idSpeech;
	}

	/**
	 * Sets the id speech.
	 *
	 * @param idSpeech the new id speech
	 */
	public void setIdSpeech(Long idSpeech) {
		this.idSpeech = idSpeech;
	}

	/**
	 * Gets the speech.
	 *
	 * @return the speech
	 */
	public Speech getSpeech() {
		return speech;
	}

	/**
	 * Sets the speech.
	 *
	 * @param speech the new speech
	 */
	public void setSpeech(Speech speech) {
		this.speech = speech;
	}

	/**
	 * Gets the list speaker.
	 *
	 * @return the list speaker
	 */
	public List<Speaker> getListSpeaker() {
		return listSpeaker;
	}

	/**
	 * Sets the list speaker.
	 *
	 * @param listSpeaker the new list speaker
	 */
	public void setListSpeaker(List<Speaker> listSpeaker) {
		this.listSpeaker = listSpeaker;
	}

	/**
	 * Gets the itens speaker.
	 *
	 * @return the itens speaker
	 */
	public List<SelectItem> getItensSpeaker() {
		return itensSpeaker;
	}

	/**
	 * Sets the itens speaker.
	 *
	 * @param itensSpeaker the new itens speaker
	 */
	public void setItensSpeaker(List<SelectItem> itensSpeaker) {
		this.itensSpeaker = itensSpeaker;
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
	 * Gets the list speech type.
	 *
	 * @return the list speech type
	 */
	public List<SpeechType> getListSpeechType() {
		return listSpeechType;
	}

	/**
	 * Sets the list speech type.
	 *
	 * @param listSpeechType the new list speech type
	 */
	public void setListSpeechType(List<SpeechType> listSpeechType) {
		this.listSpeechType = listSpeechType;
	}

	/**
	 * Gets the itens speech type.
	 *
	 * @return the itens speech type
	 */
	public List<SelectItem> getItensSpeechType() {
		return itensSpeechType;
	}

	/**
	 * Sets the itens speech type.
	 *
	 * @param itensSpeechType the new itens speech type
	 */
	public void setItensSpeechType(List<SelectItem> itensSpeechType) {
		this.itensSpeechType = itensSpeechType;
	}

}
