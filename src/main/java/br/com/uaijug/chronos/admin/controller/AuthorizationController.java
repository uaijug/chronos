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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.AuthorizationRepository;
import br.com.uaijug.chronos.admin.data.repository.ProfileRepository;
import br.com.uaijug.chronos.admin.model.Authorization;
import br.com.uaijug.chronos.admin.model.Profile;
import br.com.uaijug.chronos.admin.service.AuthorizationRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class AuthorizationController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class AuthorizationController extends AbstractManageBeans {

	/** The authorization registration. */
	@Inject
	private AuthorizationRegistration authorizationRegistration;

	/** The authorization repository. */
	@Inject
	private AuthorizationRepository authorizationRepository;

	/** The profile repository. */
	@Inject
	private ProfileRepository profileRepository;

	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The id authorization. */
	private Long idAuthorization;

	/** The authorization. */
	private Authorization authorization;

	/** The authorizations. */
	List<Authorization> authorizations;

	/** The list profile. */
	private List<Profile> listProfile = null;

	/** The itens state. */
	List<SelectItem> itensProfile = null;

	/** The list member. */
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	/**
	 * Inits the new authorization.
	 */
	@PostConstruct
	public void initNewAuthorization() {
		authorization = new Authorization();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idAuthorization = null;

		itensProfile = new ArrayList<SelectItem>();
		listProfile = new ArrayList<Profile>();

		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();
	}

	/**
	 * Gets the authorizations.
	 *
	 * @return the authorizations
	 */
	public List<Authorization> getAuthorizations() {
		return authorizationRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idAuthorization != null) {
				authorization = authorizationRepository
						.findById(idAuthorization);
				if (authorization == null) {
					redirect("list.jsf");
				}
			} else {
				authorization = new Authorization();
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

			authorizationRegistration.register(authorization);
			successMessage("label.authorization.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.authorization-exists");
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
		idAuthorization = null;
		authorization = new Authorization();
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
	 * Gets the members.
	 *
	 * @return the members
	 */
	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	/**
	 * Gets the id authorization.
	 *
	 * @return the id authorization
	 */
	public Long getIdAuthorization() {
		return idAuthorization;
	}

	/**
	 * Sets the id authorization.
	 *
	 * @param idAuthorization the new id authorization
	 */
	public void setIdAuthorization(Long idAuthorization) {
		this.idAuthorization = idAuthorization;
	}

	/**
	 * Gets the authorization.
	 *
	 * @return the authorization
	 */
	public Authorization getAuthorization() {
		return authorization;
	}

	/**
	 * Sets the authorization.
	 *
	 * @param authorization the new authorization
	 */
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
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
	 * Gets the list member.
	 *
	 * @return the list member
	 */
	public List<Member> getListMember() {
		return listMember;
	}

	/**
	 * Sets the list member.
	 *
	 * @param listMember the new list member
	 */
	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	/**
	 * Gets the itens member.
	 *
	 * @return the itens member
	 */
	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	/**
	 * Sets the itens member.
	 *
	 * @param itensMember the new itens member
	 */
	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}

}
