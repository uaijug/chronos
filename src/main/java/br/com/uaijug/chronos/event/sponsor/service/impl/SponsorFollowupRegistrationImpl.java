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
package br.com.uaijug.chronos.event.sponsor.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.event.sponsor.model.SponsorFollowup;
import br.com.uaijug.chronos.event.sponsor.service.SponsorFollowupRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;

// TODO: Auto-generated Javadoc
// The @Stateless annotation eliminates the need for manual transaction demarcation
/**
 * The Class SponsorFollowupRegistrationImpl.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Stateless
public class SponsorFollowupRegistrationImpl extends PersistenceBase<SponsorFollowup, Long> implements SponsorFollowupRegistration {

    /** The log. */
    @Inject
    private Logger log;
    
    /** The sponsor followup event src. */
    @Inject
    private Event<SponsorFollowup> sponsorFollowupEventSrc;

    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.service.GenericRegistration#register(java.lang.Object)
     */
    @Override
   	public Boolean register(SponsorFollowup sponsorFollowup) {
   		Boolean saved = false;
   		if (exists(sponsorFollowup.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			sponsorFollowup.setCreateTime(new Date());
   			sponsorFollowup.setUpdateTime(new Date());

   			if (sponsorFollowup.getId() != null) {
   				log.info("Updating " + sponsorFollowup.getDescription());
   				update(sponsorFollowup);
   			} else {
   				log.info("Registering " + sponsorFollowup.getDescription());
   				save(sponsorFollowup);
   			}
   		}
   		sponsorFollowupEventSrc.fire(sponsorFollowup);
   		return saved;
   	}
}
