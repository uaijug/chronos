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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class PostController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class PostController extends AbstractManageBeans {

	/** The post registration. */
	@Inject
	private PostRegistration postRegistration;

	/** The post repository. */
	@Inject
	private PostRepository postRepository;

	/** The post category repository. */
	@Inject
	private PostCategoryRepository postCategoryRepository;

	/** The id post. */
	private Long idPost;

	/** The post. */
	private Post post;

	/** The posts. */
	List<Post> posts;

	/** The list post category. */
	private List<PostCategory> listPostCategory = null;

	/** The itens state. */
	List<SelectItem> itensPostCategory = null;

	/**
	 * Inits the new post.
	 */
	@PostConstruct
	public void initNewPost() {
		post = new Post();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPost = null;

		itensPostCategory = new ArrayList<SelectItem>();
		listPostCategory = new ArrayList<PostCategory>();
	}

	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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

	/**
	 * Gets the post types.
	 *
	 * @return the post types
	 */
	public List<SelectItem> getPostTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PostType type : PostType.values()) {
			items.add(new SelectItem(type, type.getPostType()));
		}
		return items;
	}

	/**
	 * Gets the post schedule types.
	 *
	 * @return the post schedule types
	 */
	public List<SelectItem> getPostScheduleTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PostScheduleType type : PostScheduleType.values()) {
			items.add(new SelectItem(type, type.getPostScheduleType()));
		}
		return items;
	}

	/**
	 * Gets the post categorys.
	 *
	 * @return the post categorys
	 */
	public List<SelectItem> getPostCategorys() {

		setListPostCategory(postCategoryRepository.findAllOrderedByName());
		for (PostCategory p : listPostCategory) {
			itensPostCategory.add(new SelectItem(p, p.getName()));
		}
		return itensPostCategory;
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
		idPost = null;
		post = new Post();
	}

	/**
	 * Gets the id post.
	 *
	 * @return the id post
	 */
	public Long getIdPost() {
		return idPost;
	}

	/**
	 * Sets the id post.
	 *
	 * @param idPost the new id post
	 */
	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * Sets the post.
	 *
	 * @param post the new post
	 */
	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 * Gets the list post category.
	 *
	 * @return the list post category
	 */
	public List<PostCategory> getListPostCategory() {
		return listPostCategory;
	}

	/**
	 * Sets the list post category.
	 *
	 * @param listPostCategory the new list post category
	 */
	public void setListPostCategory(List<PostCategory> listPostCategory) {
		this.listPostCategory = listPostCategory;
	}

	/**
	 * Gets the itens post category.
	 *
	 * @return the itens post category
	 */
	public List<SelectItem> getItensPostCategory() {
		return itensPostCategory;
	}

	/**
	 * Sets the itens post category.
	 *
	 * @param itensPostCategory the new itens post category
	 */
	public void setItensPostCategory(List<SelectItem> itensPostCategory) {
		this.itensPostCategory = itensPostCategory;
	}

}
