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

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.speech.data.repository.SpeechTypeRepository;
import br.com.uaijug.chronos.event.speech.model.SpeechType;
import br.com.uaijug.chronos.event.speech.service.SpeechTypeRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class SpeechTypeController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Model
public class SpeechTypeController extends AbstractManageBeans {

	/** The speech type registration. */
	@Inject
	private SpeechTypeRegistration speechTypeRegistration;

	/** The speech type repository. */
	@Inject
	private SpeechTypeRepository speechTypeRepository;

	/** The id speech type. */
	private Long idSpeechType;

	/** The speech type. */
	private SpeechType speechType;

	/** The speech types. */
	List<SpeechType> speechTypes;

	/**
	 * Inits the new speech type.
	 */
	@PostConstruct
	public void initNewSpeechType() {
		speechType = new SpeechType();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idSpeechType = null;
	}

	/**
	 * Gets the speech types.
	 *
	 * @return the speech types
	 */
	public List<SpeechType> getSpeechTypes() {
		return speechTypeRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idSpeechType != null) {
				speechType = speechTypeRepository.findById(idSpeechType);
				if (speechType == null) {
					redirect("list.jsf");
				}
			} else {
				speechType = new SpeechType();
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

			speechTypeRegistration.register(speechType);
			successMessage("label.speechType.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.speechType-exists");
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
		idSpeechType = null;
		speechType = new SpeechType();
	}

	/**
	 * Gets the id speech type.
	 *
	 * @return the id speech type
	 */
	public Long getIdSpeechType() {
		return idSpeechType;
	}

	/**
	 * Sets the id speech type.
	 *
	 * @param idSpeechType the new id speech type
	 */
	public void setIdSpeechType(Long idSpeechType) {
		this.idSpeechType = idSpeechType;
	}

	/**
	 * Gets the speech type.
	 *
	 * @return the speech type
	 */
	public SpeechType getSpeechType() {
		return speechType;
	}

	/**
	 * Sets the speech type.
	 *
	 * @param speechType the new speech type
	 */
	public void setSpeechType(SpeechType speechType) {
		this.speechType = speechType;
	}
}
