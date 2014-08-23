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

import br.com.uaijug.chronos.blog.data.repository.PostCategoryRepository;
import br.com.uaijug.chronos.blog.model.PostCategory;
import br.com.uaijug.chronos.blog.service.PostCategoryRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class PostCategoryController extends AbstractManageBeans {

	@Inject
	private PostCategoryRegistration postCategoryRegistration;

	@Inject
	private PostCategoryRepository postCategoryRepository;

	private Long idPostCategory;

	private PostCategory postCategory;

	List<PostCategory> postCategorys;

	@PostConstruct
	public void initNewPostCategory() {
		postCategory = new PostCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPostCategory = null;
	
	}

	public List<PostCategory> getPostCategorys() {
		List<PostCategory> cds = postCategoryRepository.findAllOrderedByName();

		return cds;
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idPostCategory != null) {
				postCategory = postCategoryRepository.findById(idPostCategory);
				if (postCategory == null) {
					redirect("list.jsf");
				}
			} else {
				postCategory = new PostCategory();
			}
		}
	}

	public String register() throws Exception {
		try {

			postCategoryRepository.save(postCategory);
			successMessage("label.postCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.postCategory-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idPostCategory = null;
		postCategory = new PostCategory();
	}

	public Long getIdPostCategory() {
		return idPostCategory;
	}

	public void setIdPostCategory(Long idPostCategory) {
		this.idPostCategory = idPostCategory;
	}

	public PostCategory getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

}
