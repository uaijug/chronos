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
package br.com.uaijug.chronos.blog.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.blog.data.repository.PageRepository;
import br.com.uaijug.chronos.blog.model.Page;
import br.com.uaijug.chronos.blog.service.PageRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class PageController extends AbstractManageBeans {

	@Inject
	private PageRegistration pageRegistration;

	@Inject
	private PageRepository pageRepository;

	private Long idPage;

	private Page page;

	List<Page> pages;

	@PostConstruct
	public void initNewPage() {
		page = new Page();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPage = null;
	}

	public List<Page> getPages() {
		return pageRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idPage != null) {
				page = pageRepository.findById(idPage);
				if (page == null) {
					redirect("list.jsf");
				}
			} else {
				page = new Page();
			}
		}
	}

	public String register() throws Exception {
		try {

			pageRegistration.register(page);
			successMessage("label.page.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.page-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idPage = null;
		page = new Page();
	}

	public Long getIdPage() {
		return idPage;
	}

	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
