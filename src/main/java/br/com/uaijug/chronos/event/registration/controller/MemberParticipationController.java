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
package br.com.uaijug.chronos.event.registration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.event.data.repository.EventMainRepository;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.registration.data.repository.MemberParticipationRepository;
import br.com.uaijug.chronos.event.registration.model.MemberParticipation;
import br.com.uaijug.chronos.event.registration.service.MemberParticipationRegistration;
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.types.PaimentStatusType;
import br.com.uaijug.chronos.model.types.PaimentType;

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class MemberParticipationController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class MemberParticipationController extends AbstractManageBeans {

	/** The member participation registration. */
	@Inject
	private MemberParticipationRegistration memberParticipationRegistration;

	/** The member participation repository. */
	@Inject
	private MemberParticipationRepository memberParticipationRepository;

	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The event main repository. */
	@Inject
	private EventMainRepository eventMainRepository;

	/** The id member participation. */
	private Long idMemberParticipation;

	/** The member participation. */
	private MemberParticipation memberParticipation;

	/** The member participations. */
	List<MemberParticipation> memberParticipations;

	/** The list member. */
	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	/** The list event main. */
	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

	/**
	 * Inits the new member participation.
	 */
	@PostConstruct
	public void initNewMemberParticipation() {
		memberParticipation = new MemberParticipation();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idMemberParticipation = null;

		itensMember = new ArrayList<SelectItem>();
		listMember = new ArrayList<Member>();

		itensEventMain = new ArrayList<SelectItem>();
		listEventMain = new ArrayList<EventMain>();
	}

	/**
	 * Gets the member participations.
	 *
	 * @return the member participations
	 */
	public List<MemberParticipation> getMemberParticipations() {
		return memberParticipationRepository.findAll();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idMemberParticipation != null) {
				memberParticipation = memberParticipationRepository
						.findById(idMemberParticipation);
				if (memberParticipation == null) {
					redirect("list.jsf");
				}
			} else {
				memberParticipation = new MemberParticipation();
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

			memberParticipationRegistration.register(memberParticipation);
			successMessage("label.memberParticipation.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.memberParticipation-exists");
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
	 * Gets the event mains.
	 *
	 * @return the event mains
	 */
	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	/**
	 * Gets the paiment types.
	 *
	 * @return the paiment types
	 */
	public List<SelectItem> getPaimentTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PaimentType type : PaimentType.values()) {
			items.add(new SelectItem(type, type.getPaimentType()));
		}
		return items;
	}
	
	/**
	 * Gets the paiment status types.
	 *
	 * @return the paiment status types
	 */
	public List<SelectItem> getPaimentStatusTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PaimentStatusType type : PaimentStatusType.values()) {
			items.add(new SelectItem(type, type.getPaimentStatusType()));
		}
		return items;
	}

	/**
	 * Limpar.
	 */
	private void limpar() {
		idMemberParticipation = null;
		memberParticipation = new MemberParticipation();
	}

	/**
	 * Gets the id member participation.
	 *
	 * @return the id member participation
	 */
	public Long getIdMemberParticipation() {
		return idMemberParticipation;
	}

	/**
	 * Sets the id member participation.
	 *
	 * @param idMemberParticipation the new id member participation
	 */
	public void setIdMemberParticipation(Long idMemberParticipation) {
		this.idMemberParticipation = idMemberParticipation;
	}

	/**
	 * Gets the member participation.
	 *
	 * @return the member participation
	 */
	public MemberParticipation getMemberParticipation() {
		return memberParticipation;
	}

	/**
	 * Sets the member participation.
	 *
	 * @param memberParticipation the new member participation
	 */
	public void setMemberParticipation(MemberParticipation memberParticipation) {
		this.memberParticipation = memberParticipation;
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

	/**
	 * Gets the member participation registration.
	 *
	 * @return the member participation registration
	 */
	public MemberParticipationRegistration getMemberParticipationRegistration() {
		return memberParticipationRegistration;
	}

	/**
	 * Sets the member participation registration.
	 *
	 * @param memberParticipationRegistration the new member participation registration
	 */
	public void setMemberParticipationRegistration(
			MemberParticipationRegistration memberParticipationRegistration) {
		this.memberParticipationRegistration = memberParticipationRegistration;
	}

	/**
	 * Gets the member participation repository.
	 *
	 * @return the member participation repository
	 */
	public MemberParticipationRepository getMemberParticipationRepository() {
		return memberParticipationRepository;
	}

	/**
	 * Sets the member participation repository.
	 *
	 * @param memberParticipationRepository the new member participation repository
	 */
	public void setMemberParticipationRepository(
			MemberParticipationRepository memberParticipationRepository) {
		this.memberParticipationRepository = memberParticipationRepository;
	}

	/**
	 * Gets the member repository.
	 *
	 * @return the member repository
	 */
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

	/**
	 * Sets the member repository.
	 *
	 * @param memberRepository the new member repository
	 */
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * Gets the event main repository.
	 *
	 * @return the event main repository
	 */
	public EventMainRepository getEventMainRepository() {
		return eventMainRepository;
	}

	/**
	 * Sets the event main repository.
	 *
	 * @param eventMainRepository the new event main repository
	 */
	public void setEventMainRepository(
			EventMainRepository eventMainRepository) {
		this.eventMainRepository = eventMainRepository;
	}

	/**
	 * Gets the list event main.
	 *
	 * @return the list event main
	 */
	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	/**
	 * Sets the list event main.
	 *
	 * @param listEventMain the new list event main
	 */
	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	/**
	 * Gets the itens event main.
	 *
	 * @return the itens event main
	 */
	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	/**
	 * Sets the itens event main.
	 *
	 * @param itensEventMain the new itens event main
	 */
	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
	}

	/**
	 * Sets the member participations.
	 *
	 * @param memberParticipations the new member participations
	 */
	public void setMemberParticipations(
			List<MemberParticipation> memberParticipations) {
		this.memberParticipations = memberParticipations;
	}

}
