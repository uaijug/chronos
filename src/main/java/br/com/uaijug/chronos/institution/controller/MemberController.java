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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import br.com.uaijug.chronos.admin.data.repository.AddressRepository;
import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.controller.AbstractManageBeans;
import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.data.repository.MemberTypeRepository;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.institution.model.MemberType;
import br.com.uaijug.chronos.institution.service.MemberRegistration;
import br.com.uaijug.chronos.institution.service.NotifyRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more member the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController extends AbstractManageBeans {

	@Inject
	private MemberRegistration memberRegistration;

	@Inject
	private MemberRepository memberRepository;

	@Inject
	private MemberTypeRepository memberTypeRepository;

	@Inject
	private AddressRepository addressRepository;

	@Inject
	private NotifyRegistration notifyRegistration;

	private Long idMember;

	private Member member;

	private String subject;

	private String message;

	List<Member> members;

	private List<MemberType> listMemberType = null;

	/** The itens state. */
	List<SelectItem> itensMemberType = null;

	private DualListModel<Member> memberList;

	@PostConstruct
	public void initNewMember() {
		member = new Member();

		this.bundle = ResourceBundle.getBundle("br.com.uaijug.bundle.messages",
				getFacesContext().getViewRoot().getLocale());

		idMember = null;

		itensMemberType = new ArrayList<SelectItem>();
		listMemberType = new ArrayList<MemberType>();

		// Themes
		List<Member> themesSource = memberRepository.findAllOrderedByName();
		List<Member> themesTarget = new ArrayList<Member>();

		memberList = new DualListModel<Member>(themesSource, themesTarget);
	}

	public List<Member> getMembers() {
		return memberRepository.findAllOrderedByName();
	}

	/**
	 * Metodo executado antes da renderizacao do form.xhtml
	 */
	public void load() {
		if (!getFacesContext().isPostback()) {
			if (idMember != null) {
				member = memberRepository.findById(idMember);
				if (member == null) {
					redirect("list.jsf");
				}
			} else {
				member = new Member();
			}
		}
	}

	public String register() throws Exception {
		try {

			memberRegistration.register(member);
			
			for (Member attendees : memberList.getTarget()) {
				System.out.println("Atendess" + attendees.getName());
			}
			
			successMessage("label.member.save");
			limpar();
			return "list?faces-redirect=true";

		} catch (Exception e) {
			unSuccessMessage("label.member-exists");
		}
		return null;
	}

	public void sendMail() throws Exception {
		notifyRegistration.sendEmail(getSubject(), getMessage());
		successMessage("label.member.save");
	}

	public String cancelar() {
		limpar();
		return "list?faces-redirect=true";
	}

	private void limpar() {
		idMember = null;
		member = new Member();
	}

	public List<SelectItem> getMemberTypes() {

		setListMemberType(memberTypeRepository.findAllOrderedByName());
		for (MemberType p : listMemberType) {
			itensMemberType.add(new SelectItem(p, p.getDescription()));
		}
		return itensMemberType;
	}

	public List<Address> completeAddress(String query) {
		List<Address> allAddress = addressRepository.findAllOrderedByName();
		List<Address> filteredAddress = new ArrayList<Address>();

		for (int i = 0; i < allAddress.size(); i++) {
			Address address = allAddress.get(i);
			if (address.getStreet().toLowerCase().startsWith(query)) {
				filteredAddress.add(address);
			}
		}

		return filteredAddress;
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MemberType> getListMemberType() {
		return listMemberType;
	}

	public void setListMemberType(List<MemberType> listMemberType) {
		this.listMemberType = listMemberType;
	}

	public List<SelectItem> getItensMemberType() {
		return itensMemberType;
	}

	public void setItensMemberType(List<SelectItem> itensMemberType) {
		this.itensMemberType = itensMemberType;
	}

	public DualListModel<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(DualListModel<Member> memberList) {
		this.memberList = memberList;
	}

}
