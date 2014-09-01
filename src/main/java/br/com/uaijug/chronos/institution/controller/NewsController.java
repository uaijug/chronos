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
package br.com.uaijug.chronos.institution.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.NewsRepository;
import br.com.uaijug.chronos.institution.model.News;
import br.com.uaijug.chronos.institution.service.NewsRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more news the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class NewsController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class NewsController extends AbstractManageBeans {

	/** The news registration. */
	@Inject
	private NewsRegistration newsRegistration;

	/** The news repository. */
	@Inject
	private NewsRepository newsRepository;

	/** The id news. */
	private Long idNews;

	/** The news. */
	private News news;

	/** The newss. */
	List<News> newss;

	/**
	 * Inits the new news.
	 */
	@PostConstruct
	public void initNewNews() {
		news = new News();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idNews = null;
	}

	/**
	 * Gets the newss.
	 *
	 * @return the newss
	 */
	public List<News> getNewss() {
		return newsRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idNews != null) {
				news = newsRepository.findById(idNews);
				if (news == null) {
					redirect("list.jsf");
				}
			} else {
				news = new News();
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

			newsRegistration.register(news);
			successMessage("label.news.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.news-exists");
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
		idNews = null;
		news = new News();
	}

	/**
	 * Gets the id news.
	 *
	 * @return the id news
	 */
	public Long getIdNews() {
		return idNews;
	}

	/**
	 * Sets the id news.
	 *
	 * @param idNews the new id news
	 */
	public void setIdNews(Long idNews) {
		this.idNews = idNews;
	}

	/**
	 * Gets the news.
	 *
	 * @return the news
	 */
	public News getNews() {
		return news;
	}

	/**
	 * Sets the news.
	 *
	 * @param news the new news
	 */
	public void setNews(News news) {
		this.news = news;
	}

}
