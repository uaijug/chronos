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
package br.com.uaijug.chronos.event.budget.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.budget.data.repository.MaterialRepository;
import br.com.uaijug.chronos.event.budget.model.Material;
import br.com.uaijug.chronos.event.budget.service.MaterialRegistration;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.types.ResourceType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class MaterialController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class MaterialController extends AbstractManageBeans {

	/** The material registration. */
	@Inject
	private MaterialRegistration materialRegistration;

	/** The material repository. */
	@Inject
	private MaterialRepository materialRepository;

	/** The event repository. */
	@Inject
	private EventMainRepository eventRepository;

	/** The id material. */
	private Long idMaterial;

	/** The material. */
	private Material material;

	/** The materials. */
	List<Material> materials;

	/** The list event. */
	private List<EventMain> listEvent = null;

	/** The itens event. */
	List<SelectItem> itensEvent = null;

	/**
	 * Inits the new material.
	 */
	@PostConstruct
	public void initNewMaterial() {
		material = new Material();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idMaterial = null;

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

	/**
	 * Gets the materials.
	 *
	 * @return the materials
	 */
	public List<Material> getMaterials() {
		return materialRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idMaterial != null) {
				material = materialRepository.findById(idMaterial);
				if (material == null) {
					redirect("list.jsf");
				}
			} else {
				material = new Material();
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

			materialRegistration.register(material);
			successMessage("label.material.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.material-exists");
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
		idMaterial = null;
		material = new Material();
	}

	/**
	 * Gets the resource types.
	 *
	 * @return the resource types
	 */
	public List<SelectItem> getResourceTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (ResourceType type : ResourceType.values()) {
			items.add(new SelectItem(type, type.getCompanyType()));
		}
		return items;
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
	 * Gets the id material.
	 *
	 * @return the id material
	 */
	public Long getIdMaterial() {
		return idMaterial;
	}

	/**
	 * Sets the id material.
	 *
	 * @param idMaterial the new id material
	 */
	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

}
