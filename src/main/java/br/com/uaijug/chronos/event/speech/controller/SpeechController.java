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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class SpeechController extends AbstractManageBeans {

	@Inject
	private SpeechRegistration speechRegistration;

	@Inject
	private SpeechRepository speechRepository;

	@Inject
	private SpeakerRepository speakerRepository;

	@Inject
	private EventMainRepository eventRepository;

	@Inject
	private SpeechTypeRepository speechTypeRepository;

	private Long idSpeech;

	private Speech speech;

	List<Speech> speechs;

	private List<Speaker> listSpeaker = null;

	/** The itens state. */
	List<SelectItem> itensSpeaker = null;

	private List<EventMain> listEvent = null;

	/** The itens state. */
	List<SelectItem> itensEvent = null;

	private List<SpeechType> listSpeechType = null;

	/** The itens state. */
	List<SelectItem> itensSpeechType = null;

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idSpeech = null;
		speech = new Speech();
	}

	public List<SelectItem> getSpeakers() {

		setListSpeaker(speakerRepository.findAll());
		for (Speaker p : listSpeaker) {
			itensSpeaker.add(new SelectItem(p, p.getName()));
		}
		return itensSpeaker;
	}

	public List<SelectItem> getSpeechTypes() {

		setListSpeechType(speechTypeRepository.findAll());
		for (SpeechType p : listSpeechType) {
			itensSpeechType.add(new SelectItem(p, p.getDescription()));
		}
		return itensSpeechType;
	}

	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
	}

	public List<SelectItem> getApproves() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Approve type : Approve.values()) {
			items.add(new SelectItem(type, type.toString()));
		}
		return items;
	}

	public Long getIdSpeech() {
		return idSpeech;
	}

	public void setIdSpeech(Long idSpeech) {
		this.idSpeech = idSpeech;
	}

	public Speech getSpeech() {
		return speech;
	}

	public void setSpeech(Speech speech) {
		this.speech = speech;
	}

	public List<Speaker> getListSpeaker() {
		return listSpeaker;
	}

	public void setListSpeaker(List<Speaker> listSpeaker) {
		this.listSpeaker = listSpeaker;
	}

	public List<SelectItem> getItensSpeaker() {
		return itensSpeaker;
	}

	public void setItensSpeaker(List<SelectItem> itensSpeaker) {
		this.itensSpeaker = itensSpeaker;
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

	public List<SpeechType> getListSpeechType() {
		return listSpeechType;
	}

	public void setListSpeechType(List<SpeechType> listSpeechType) {
		this.listSpeechType = listSpeechType;
	}

	public List<SelectItem> getItensSpeechType() {
		return itensSpeechType;
	}

	public void setItensSpeechType(List<SelectItem> itensSpeechType) {
		this.itensSpeechType = itensSpeechType;
	}

}
