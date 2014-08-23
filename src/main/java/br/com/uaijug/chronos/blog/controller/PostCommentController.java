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

import br.com.uaijug.chronos.blog.data.repository.PostCommentRepository;
import br.com.uaijug.chronos.blog.model.PostComment;
import br.com.uaijug.chronos.blog.service.PostCommentRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class PostCommentController extends AbstractManageBeans {

	@Inject
	private PostCommentRegistration postCommentRegistration;

	@Inject
	private PostCommentRepository postCommentRepository;

	private Long idPostComment;

	private PostComment postComment;

	List<PostComment> postComments;

	@PostConstruct
	public void initNewPostComment() {
		postComment = new PostComment();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idPostComment = null;
	}

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idPostComment = null;
		postComment = new PostComment();
	}

	public Long getIdPostComment() {
		return idPostComment;
	}

	public void setIdPostComment(Long idPostComment) {
		this.idPostComment = idPostComment;
	}

	public PostComment getPostComment() {
		return postComment;
	}

	public void setPostComment(PostComment postComment) {
		this.postComment = postComment;
	}

}
