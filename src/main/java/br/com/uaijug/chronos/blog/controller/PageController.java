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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class PageController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class PageController extends AbstractManageBeans {

	/** The page registration. */
	@Inject
	private PageRegistration pageRegistration;

	/** The page repository. */
	@Inject
	private PageRepository pageRepository;

	/** The id page. */
	private Long idPage;

	/** The page. */
	private Page page;

	/** The pages. */
	List<Page> pages;

	/**
	 * Inits the new page.
	 */
	@PostConstruct
	public void initNewPage() {
		page = new Page();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPage = null;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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
		idPage = null;
		page = new Page();
	}

	/**
	 * Gets the id page.
	 *
	 * @return the id page
	 */
	public Long getIdPage() {
		return idPage;
	}

	/**
	 * Sets the id page.
	 *
	 * @param idPage the new id page
	 */
	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}
