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

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberParticipationController extends AbstractManageBeans {

	@Inject
	private MemberParticipationRegistration memberParticipationRegistration;

	@Inject
	private MemberParticipationRepository memberParticipationRepository;

	@Inject
	private MemberRepository memberRepository;

	@Inject
	private EventMainRepository eventMainRepository;

	private Long idMemberParticipation;

	private MemberParticipation memberParticipation;

	List<MemberParticipation> memberParticipations;

	private List<Member> listMember = null;

	/** The itens state. */
	List<SelectItem> itensMember = null;

	private List<EventMain> listEventMain = null;

	/** The itens state. */
	List<SelectItem> itensEventMain = null;

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

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	public List<SelectItem> getMembers() {

		setListMember(memberRepository.findAll());
		for (Member p : listMember) {
			itensMember.add(new SelectItem(p, p.getName()));
		}
		return itensMember;
	}

	public List<SelectItem> getEventMains() {

		setListEventMain(eventMainRepository.findAll());
		for (EventMain p : listEventMain) {
			itensEventMain.add(new SelectItem(p, p.getDescription()));
		}
		return itensEventMain;
	}

	public List<SelectItem> getPaimentTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PaimentType type : PaimentType.values()) {
			items.add(new SelectItem(type, type.getPaimentType()));
		}
		return items;
	}
	
	public List<SelectItem> getPaimentStatusTypes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (PaimentStatusType type : PaimentStatusType.values()) {
			items.add(new SelectItem(type, type.getPaimentStatusType()));
		}
		return items;
	}

	private void limpar() {
		idMemberParticipation = null;
		memberParticipation = new MemberParticipation();
	}

	public Long getIdMemberParticipation() {
		return idMemberParticipation;
	}

	public void setIdMemberParticipation(Long idMemberParticipation) {
		this.idMemberParticipation = idMemberParticipation;
	}

	public MemberParticipation getMemberParticipation() {
		return memberParticipation;
	}

	public void setMemberParticipation(MemberParticipation memberParticipation) {
		this.memberParticipation = memberParticipation;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	public List<SelectItem> getItensMember() {
		return itensMember;
	}

	public void setItensMember(List<SelectItem> itensMember) {
		this.itensMember = itensMember;
	}

	public MemberParticipationRegistration getMemberParticipationRegistration() {
		return memberParticipationRegistration;
	}

	public void setMemberParticipationRegistration(
			MemberParticipationRegistration memberParticipationRegistration) {
		this.memberParticipationRegistration = memberParticipationRegistration;
	}

	public MemberParticipationRepository getMemberParticipationRepository() {
		return memberParticipationRepository;
	}

	public void setMemberParticipationRepository(
			MemberParticipationRepository memberParticipationRepository) {
		this.memberParticipationRepository = memberParticipationRepository;
	}

	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public EventMainRepository getEventMainRepository() {
		return eventMainRepository;
	}

	public void setEventMainRepository(
			EventMainRepository eventMainRepository) {
		this.eventMainRepository = eventMainRepository;
	}

	public List<EventMain> getListEventMain() {
		return listEventMain;
	}

	public void setListEventMain(List<EventMain> listEventMain) {
		this.listEventMain = listEventMain;
	}

	public List<SelectItem> getItensEventMain() {
		return itensEventMain;
	}

	public void setItensEventMain(List<SelectItem> itensEventMain) {
		this.itensEventMain = itensEventMain;
	}

	public void setMemberParticipations(
			List<MemberParticipation> memberParticipations) {
		this.memberParticipations = memberParticipations;
	}

}
