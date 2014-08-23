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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.blog.data.repository.PostCategoryRepository;
import br.com.uaijug.chronos.blog.data.repository.PostRepository;
import br.com.uaijug.chronos.blog.model.Post;
import br.com.uaijug.chronos.blog.model.PostCategory;
import br.com.uaijug.chronos.blog.service.PostRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.model.types.PostScheduleType;
import br.com.uaijug.chronos.model.types.PostType;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class PostController extends AbstractManageBeans {

	@Inject
	private PostRegistration postRegistration;

	@Inject
	private PostRepository postRepository;

	@Inject
	private PostCategoryRepository postCategoryRepository;

	private Long idPost;

	private Post post;

	List<Post> posts;

	private List<PostCategory> listPostCategory = null;

	/** The itens state. */
	List<SelectItem> itensPostCategory = null;

	@PostConstruct
	public void initNewPost() {
		post = new Post();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPost = null;

		itensPostCategory = new ArrayList<SelectItem>();
		listPostCategory = new ArrayList<PostCategory>();
	}

	public List<Post> getPosts() {
		return postRepository.findAllOrderedByName();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idPost != null) {
				post = postRepository.findById(idPost);
				if (post == null) {
					redirect("list.jsf");
				}
			} else {
				post = new Post();
			}
		}
	}

	public String register() throws Exception {
		try {

			postRegistration.register(post);
			successMessage("label.post.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.post-exists");
		}
		return null;
	}

	public List<SelectItem> getPostTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PostType type : PostType.values()) {
			items.add(new SelectItem(type, type.getPostType()));
		}
		return items;
	}

	public List<SelectItem> getPostScheduleTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PostScheduleType type : PostScheduleType.values()) {
			items.add(new SelectItem(type, type.getPostScheduleType()));
		}
		return items;
	}

	public List<SelectItem> getPostCategorys() {

		setListPostCategory(postCategoryRepository.findAllOrderedByName());
		for (PostCategory p : listPostCategory) {
			itensPostCategory.add(new SelectItem(p, p.getDescription()));
		}
		return itensPostCategory;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idPost = null;
		post = new Post();
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<PostCategory> getListPostCategory() {
		return listPostCategory;
	}

	public void setListPostCategory(List<PostCategory> listPostCategory) {
		this.listPostCategory = listPostCategory;
	}

	public List<SelectItem> getItensPostCategory() {
		return itensPostCategory;
	}

	public void setItensPostCategory(List<SelectItem> itensPostCategory) {
		this.itensPostCategory = itensPostCategory;
	}

}
