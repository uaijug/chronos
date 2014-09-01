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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class UserController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class UserController extends AbstractManageBeans {

	/** The user registration. */
	@Inject
	private UserRegistration userRegistration;

	/** The user repository. */
	@Inject
	private UserRepository userRepository;

	/** The profile repository. */
	@Inject
	private ProfileRepository profileRepository;

	/** The id user. */
	private Long idUser;

	/** The user. */
	private User user;

	/** The users. */
	List<User> users;

	/** The itens state. */
	List<SelectItem> itensProfile = null;

	/** The list profile. */
	private List<Profile> listProfile = null;

	/**
	 * Inits the new user.
	 */
	@PostConstruct
	public void initNewUser() {
		user = new User();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idUser = null;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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

	/**
	 * Gets the profiles.
	 *
	 * @return the profiles
	 */
	public List<SelectItem> getProfiles() {

		setListProfile(profileRepository.findAll());
		for (Profile p : listProfile) {
			itensProfile.add(new SelectItem(p, p.getDescription()));
		}
		return itensProfile;
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
		idUser = null;
		user = new User();
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the logged users.
	 *
	 * @return the logged users
	 */
	public User getLoggedUsers() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String userEmail = context.getUserPrincipal().getName();

			//user = userRepository.findSpecificUserByEmail(userEmail);
		}

		return user;
	}

	/**
	 * Gets the logged user.
	 *
	 * @return the logged user
	 */
	public User getLoggedUser() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		// user =
		// userRepository.findSpecificUserByUsername(context.getUserPrincipal().getName());

		return null;
	}

	/**
	 * Checks if is user admin.
	 *
	 * @return true, if is user admin
	 */
	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	/**
	 * Checks if is user root.
	 *
	 * @return true, if is user root
	 */
	public boolean isUserRoot() {
		return getRequest().isUserInRole("ROOT");
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

	}

	/**
	 * Gets the itens profile.
	 *
	 * @return the itens profile
	 */
	public List<SelectItem> getItensProfile() {
		return itensProfile;
	}

	/**
	 * Sets the itens profile.
	 *
	 * @param itensProfile the new itens profile
	 */
	public void setItensProfile(List<SelectItem> itensProfile) {
		this.itensProfile = itensProfile;
	}

	/**
	 * Gets the list profile.
	 *
	 * @return the list profile
	 */
	public List<Profile> getListProfile() {
		return listProfile;
	}

	/**
	 * Sets the list profile.
	 *
	 * @param listProfile the new list profile
	 */
	public void setListProfile(List<Profile> listProfile) {
		this.listProfile = listProfile;
	}

}
