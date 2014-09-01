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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class PostCategoryController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class PostCategoryController extends AbstractManageBeans {

	/** The post category registration. */
	@Inject
	private PostCategoryRegistration postCategoryRegistration;

	/** The post category repository. */
	@Inject
	private PostCategoryRepository postCategoryRepository;

	/** The id post category. */
	private Long idPostCategory;

	/** The post category. */
	private PostCategory postCategory;

	/** The post categorys. */
	List<PostCategory> postCategorys;

	/**
	 * Inits the new post category.
	 */
	@PostConstruct
	public void initNewPostCategory() {
		postCategory = new PostCategory();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPostCategory = null;
	
	}

	/**
	 * Gets the post categorys.
	 *
	 * @return the post categorys
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	public String register() throws Exception {
		try {

			postCategoryRegistration.register(postCategory);
			successMessage("label.postCategory.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.postCategory-exists");
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
		idPostCategory = null;
		postCategory = new PostCategory();
	}

	/**
	 * Gets the id post category.
	 *
	 * @return the id post category
	 */
	public Long getIdPostCategory() {
		return idPostCategory;
	}

	/**
	 * Sets the id post category.
	 *
	 * @param idPostCategory the new id post category
	 */
	public void setIdPostCategory(Long idPostCategory) {
		this.idPostCategory = idPostCategory;
	}

	/**
	 * Gets the post category.
	 *
	 * @return the post category
	 */
	public PostCategory getPostCategory() {
		return postCategory;
	}

	/**
	 * Sets the post category.
	 *
	 * @param postCategory the new post category
	 */
	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

}
