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
package br.com.uaijug.chronos.admin.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.uaijug.chronos.admin.data.repository.ProfileRepository;
import br.com.uaijug.chronos.admin.data.repository.UserRepository;
import br.com.uaijug.chronos.admin.model.Profile;
import br.com.uaijug.chronos.admin.model.User;
import br.com.uaijug.chronos.admin.service.UserRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class UserController extends AbstractManageBeans {

	@Inject
	private UserRegistration userRegistration;

	@Inject
	private UserRepository userRepository;

	@Inject
	private ProfileRepository profileRepository;

	private Long idUser;

	private User user;

	List<User> users;

	/** The itens state. */
	List<SelectItem> itensProfile = null;

	private List<Profile> listProfile = null;

	@PostConstruct
	public void initNewUser() {
		user = new User();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idUser = null;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idUser != null) {
				user = userRepository.findById(idUser);
				if (user == null) {
					redirect("list.jsf");
				}
			} else {
				user = new User();
			}
		}
	}

	public String register() throws Exception {
		try {

			userRegistration.register(user);
			successMessage("label.user.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.user-exists");
		}
		return null;
	}

	public List<SelectItem> getProfiles() {

		setListProfile(profileRepository.findAll());
		for (Profile p : listProfile) {
			itensProfile.add(new SelectItem(p, p.getDescription()));
		}
		return itensProfile;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idUser = null;
		user = new User();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getLoggedUsers() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String userEmail = context.getUserPrincipal().getName();

			//user = userRepository.findSpecificUserByEmail(userEmail);
		}

		return user;
	}

	public User getLoggedUser() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		// user =
		// userRepository.findSpecificUserByUsername(context.getUserPrincipal().getName());

		return null;
	}

	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	public boolean isUserRoot() {
		return getRequest().isUserInRole("ROOT");
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

	}

	public List<SelectItem> getItensProfile() {
		return itensProfile;
	}

	public void setItensProfile(List<SelectItem> itensProfile) {
		this.itensProfile = itensProfile;
	}

	public List<Profile> getListProfile() {
		return listProfile;
	}

	public void setListProfile(List<Profile> listProfile) {
		this.listProfile = listProfile;
	}

}
