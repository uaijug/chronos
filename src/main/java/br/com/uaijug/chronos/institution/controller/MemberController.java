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

// TODO: Auto-generated Javadoc
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more member the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
/**
 * The Class MemberController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Model
public class MemberController extends AbstractManageBeans {

	/** The member registration. */
	@Inject
	private MemberRegistration memberRegistration;

	/** The member repository. */
	@Inject
	private MemberRepository memberRepository;

	/** The member type repository. */
	@Inject
	private MemberTypeRepository memberTypeRepository;

	/** The address repository. */
	@Inject
	private AddressRepository addressRepository;

	/** The notify registration. */
	@Inject
	private NotifyRegistration notifyRegistration;

	/** The id member. */
	private Long idMember;

	/** The member. */
	private Member member;

	/** The subject. */
	private String subject;

	/** The message. */
	private String message;

	/** The members. */
	List<Member> members;

	/** The list member type. */
	private List<MemberType> listMemberType = null;

	/** The itens state. */
	List<SelectItem> itensMemberType = null;

	/** The member list. */
	private DualListModel<Member> memberList;

	/**
	 * Inits the new member.
	 */
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

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
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

	/**
	 * Register.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
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

	/**
	 * Send mail.
	 *
	 * @throws Exception the exception
	 */
	public void sendMail() throws Exception {
		notifyRegistration.sendEmail(getSubject(), getMessage());
		successMessage("label.member.save");
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
		idMember = null;
		member = new Member();
	}

	/**
	 * Gets the member types.
	 *
	 * @return the member types
	 */
	public List<SelectItem> getMemberTypes() {

		setListMemberType(memberTypeRepository.findAllOrderedByName());
		for (MemberType p : listMemberType) {
			itensMemberType.add(new SelectItem(p, p.getDescription()));
		}
		return itensMemberType;
	}

	/**
	 * Complete address.
	 *
	 * @param query the query
	 * @return the list
	 */
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

	/**
	 * Gets the id member.
	 *
	 * @return the id member
	 */
	public Long getIdMember() {
		return idMember;
	}

	/**
	 * Sets the id member.
	 *
	 * @param idMember the new id member
	 */
	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the new member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the list member type.
	 *
	 * @return the list member type
	 */
	public List<MemberType> getListMemberType() {
		return listMemberType;
	}

	/**
	 * Sets the list member type.
	 *
	 * @param listMemberType the new list member type
	 */
	public void setListMemberType(List<MemberType> listMemberType) {
		this.listMemberType = listMemberType;
	}

	/**
	 * Gets the itens member type.
	 *
	 * @return the itens member type
	 */
	public List<SelectItem> getItensMemberType() {
		return itensMemberType;
	}

	/**
	 * Sets the itens member type.
	 *
	 * @param itensMemberType the new itens member type
	 */
	public void setItensMemberType(List<SelectItem> itensMemberType) {
		this.itensMemberType = itensMemberType;
	}

	/**
	 * Gets the member list.
	 *
	 * @return the member list
	 */
	public DualListModel<Member> getMemberList() {
		return memberList;
	}

	/**
	 * Sets the member list.
	 *
	 * @param memberList the new member list
	 */
	public void setMemberList(DualListModel<Member> memberList) {
		this.memberList = memberList;
	}

}
