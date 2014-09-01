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
package br.com.uaijug.chronos.institution.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.MemberTypeRepository;
import br.com.uaijug.chronos.institution.model.MemberType;
import br.com.uaijug.chronos.institution.service.MemberTypeRegistration;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more memberType the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class MemberTypeController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class MemberTypeController extends AbstractManageBeans {

	/** The member type registration. */
	@Inject
	private MemberTypeRegistration memberTypeRegistration;

	/** The member type repository. */
	@Inject
	private MemberTypeRepository memberTypeRepository;

	/** The id member type. */
	private Long idMemberType;

	/** The member type. */
	private MemberType memberType;

	/** The member types. */
	List<MemberType> memberTypes;

	/**
	 * Inits the new member type.
	 */
	@PostConstruct
	public void initNewMemberType() {
		memberType = new MemberType();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idMemberType = null;
	}

	/**
	 * Gets the member types.
	 *
	 * @return the member types
	 */
	public List<MemberType> getMemberTypes() {
		return memberTypeRepository.findAllOrderedByName();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idMemberType != null) {
				memberType = memberTypeRepository.findById(idMemberType);
				if (memberType == null) {
					redirect("list.jsf");
				}
			} else {
				memberType = new MemberType();
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

			memberTypeRegistration.register(memberType);
			successMessage("label.memberType.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.memberType-exists");
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
		idMemberType = null;
		memberType = new MemberType();
	}

	/**
	 * Gets the id member type.
	 *
	 * @return the id member type
	 */
	public Long getIdMemberType() {
		return idMemberType;
	}

	/**
	 * Sets the id member type.
	 *
	 * @param idMemberType the new id member type
	 */
	public void setIdMemberType(Long idMemberType) {
		this.idMemberType = idMemberType;
	}

	/**
	 * Gets the member type.
	 *
	 * @return the member type
	 */
	public MemberType getMemberType() {
		return memberType;
	}

	/**
	 * Sets the member type.
	 *
	 * @param memberType the new member type
	 */
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

}
