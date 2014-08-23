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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more news the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class NewsController extends AbstractManageBeans {

	@Inject
	private NewsRegistration newsRegistration;

	@Inject
	private NewsRepository newsRepository;

	private Long idNews;

	private News news;

	List<News> newss;

	@PostConstruct
	public void initNewNews() {
		news = new News();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idNews = null;
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idNews = null;
		news = new News();
	}

	public Long getIdNews() {
		return idNews;
	}

	public void setIdNews(Long idNews) {
		this.idNews = idNews;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
