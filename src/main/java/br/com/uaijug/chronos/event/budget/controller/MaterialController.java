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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MaterialController extends AbstractManageBeans {

	@Inject
	private MaterialRegistration materialRegistration;

	@Inject
	private MaterialRepository materialRepository;

	@Inject
	private EventMainRepository eventRepository;

	private Long idMaterial;

	private Material material;

	List<Material> materials;

	private List<EventMain> listEvent = null;

	List<SelectItem> itensEvent = null;

	@PostConstruct
	public void initNewMaterial() {
		material = new Material();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idMaterial = null;

		itensEvent = new ArrayList<SelectItem>();
		listEvent = new ArrayList<EventMain>();
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idMaterial = null;
		material = new Material();
	}

	public List<SelectItem> getResourceTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (ResourceType type : ResourceType.values()) {
			items.add(new SelectItem(type, type.getCompanyType()));
		}
		return items;
	}

	public List<SelectItem> getEvents() {

		setListEvent(eventRepository.findAll());
		for (EventMain p : listEvent) {
			itensEvent.add(new SelectItem(p, p.getDescription()));
		}
		return itensEvent;
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

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
