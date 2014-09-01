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

import br.com.uaijug.chronos.blog.data.repository.PostRepository;
import br.com.uaijug.chronos.blog.data.repository.PostCommentRepository;
import br.com.uaijug.chronos.blog.model.Post;
import br.com.uaijug.chronos.blog.model.PostComment;
import br.com.uaijug.chronos.blog.service.PostCommentRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class PostCommentController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at
 *         rogeriofontes dot inf dot br
 * 
 */
@Model
public class PostCommentController extends AbstractManageBeans {

	/** The post comment registration. */
	@Inject
	private PostCommentRegistration postCommentRegistration;

	/** The post comment repository. */
	@Inject
	private PostCommentRepository postCommentRepository;

	/** The post  repository. */
	@Inject
	private PostRepository postRepository;

	/** The id post comment. */
	private Long idPostComment;

	/** The post comment. */
	private PostComment postComment;

	/** The post comments. */
	List<PostComment> postComments;

	/** The list post . */
	private List<Post> listPost = null;

	/** The itens state. */
	List<SelectItem> itensPost = null;

	/**
	 * Inits the new post comment.
	 */
	@PostConstruct
	public void initNewPostComment() {
		postComment = new PostComment();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPostComment = null;

		itensPost = new ArrayList<SelectItem>();
		listPost = new ArrayList<Post>();
	}

	/**
	 * Gets the post comments.
	 * 
	 * @return the post comments
	 */
	public List<PostComment> getPostComments() {
		return postCommentRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idPostComment != null) {
				postComment = postCommentRepository.findById(idPostComment);
				if (postComment == null) {
					redirect("list.jsf");
				}
			} else {
				postComment = new PostComment();
			}
		}
	}

	/**
	 * Register.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String register() throws Exception {
		try {

			postCommentRegistration.register(postComment);
			successMessage("label.postComment.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.postComment-exists");
		}
		return null;
	}

	/**
	 * Gets the post s.
	 * 
	 * @return the post s
	 */
	public List<SelectItem> getPosts() {

		setListPost(postRepository.findAllOrderedByName());
		for (Post p : listPost) {
			itensPost.add(new SelectItem(p, p.getTitle()));
		}
		return itensPost;
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
		idPostComment = null;
		postComment = new PostComment();
	}

	/**
	 * Gets the id post comment.
	 * 
	 * @return the id post comment
	 */
	public Long getIdPostComment() {
		return idPostComment;
	}

	/**
	 * Sets the id post comment.
	 * 
	 * @param idPostComment
	 *            the new id post comment
	 */
	public void setIdPostComment(Long idPostComment) {
		this.idPostComment = idPostComment;
	}

	/**
	 * Gets the post comment.
	 * 
	 * @return the post comment
	 */
	public PostComment getPostComment() {
		return postComment;
	}

	/**
	 * Sets the post comment.
	 * 
	 * @param postComment
	 *            the new post comment
	 */
	public void setPostComment(PostComment postComment) {
		this.postComment = postComment;
	}

	public List<Post> getListPost() {
		return listPost;
	}

	public void setListPost(List<Post> listPost) {
		this.listPost = listPost;
	}

	public List<SelectItem> getItensPost() {
		return itensPost;
	}

	public void setItensPost(List<SelectItem> itensPost) {
		this.itensPost = itensPost;
	}

}
