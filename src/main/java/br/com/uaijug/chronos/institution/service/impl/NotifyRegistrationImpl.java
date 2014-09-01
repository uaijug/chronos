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
package br.com.uaijug.chronos.institution.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.institution.data.repository.MemberRepository;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.institution.model.Notify;
import br.com.uaijug.chronos.institution.service.NotifyRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;
import br.com.uaijug.chronos.service.SendMail;

// TODO: Auto-generated Javadoc
// The @Stateless annotation eliminates the need for manual transaction demarcation
/**
 * The Class NotifyRegistrationImpl.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Stateless
public class NotifyRegistrationImpl extends PersistenceBase<Notify, Long> implements NotifyRegistration {

    /** The log. */
    @Inject
    private Logger log;
    
    /** The send mail. */
    @Inject
    private SendMail sendMail;
    
    /** The member repository. */
    @Inject
    private MemberRepository memberRepository;
    
    /** The notify event src. */
    @Inject
    private Event<Notify> notifyEventSrc;

    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.service.GenericRegistration#register(java.lang.Object)
     */
    @Override
   	public Boolean register(Notify notify) {
   		Boolean saved = false;
   		if (exists(notify.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			notify.setCreateTime(new Date());
   			notify.setUpdateTime(new Date());

   			if (notify.getId() != null) {
   				log.info("Updating " + notify.getSubject());
   				update(notify);
   			} else {
   				log.info("Registering " + notify.getSubject());
   				save(notify);
   			}
   		}
   		notifyEventSrc.fire(notify);
   		return saved;
   	}
    
    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.institution.service.NotifyRegistration#sendEmail(java.lang.String, java.lang.String)
     */
    public void sendEmail(String subject, String message) throws Exception {
    	
    	List<Member> members = memberRepository.findAll();
    	for (Member member : members) {
    		sendMail.send(member.getName(), member.getEmail(), subject, message);	
		}    	
    }
} 
