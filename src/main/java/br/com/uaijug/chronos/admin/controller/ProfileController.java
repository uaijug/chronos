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
import javax.inject.Inject;

import br.com.uaijug.chronos.admin.data.repository.ProfileRepository;
import br.com.uaijug.chronos.admin.model.Profile;
import br.com.uaijug.chronos.admin.service.ProfileRegistration;
import br.com.uaijug.chronos.controller.AbstractManageBeans;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ProfileController extends AbstractManageBeans {

	@Inject
	private ProfileRegistration profileRegistration;

	@Inject
	private ProfileRepository profileRepository;

	private Long idProfile;

	private Profile profile;

	List<Profile> profiles;

	@PostConstruct
	public void initNewProfile() {
		profile = new Profile();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idProfile = null;
	}

	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idProfile != null) {
				profile = profileRepository.findById(idProfile);
				if (profile == null) {
					redirect("list.jsf");
				}
			} else {
				profile = new Profile();
			}
		}
	}

	public String register() throws Exception {
		try {

			profileRegistration.register(profile);
			successMessage("label.profile.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.profile-exists");
		}
		return null;
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idProfile = null;
		profile = new Profile();
	}

	public Long getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Long idProfile) {
		this.idProfile = idProfile;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
