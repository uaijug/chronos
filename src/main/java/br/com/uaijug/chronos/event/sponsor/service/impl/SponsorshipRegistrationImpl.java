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

import br.com.uaijug.chronos.event.sponsor.model.Sponsorship;
import br.com.uaijug.chronos.event.sponsor.service.SponsorshipRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class SponsorshipRegistrationImpl extends PersistenceBase<Sponsorship, Long> implements SponsorshipRegistration {

    @Inject
    private Logger log;
    
    @Inject
    private Event<Sponsorship> sponsorshipEventSrc;

    @Override
   	public Boolean register(Sponsorship sponsorship) {
   		Boolean saved = false;
   		if (exists(sponsorship.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			sponsorship.setCreateTime(new Date());
   			sponsorship.setUpdateTime(new Date());

   			if (sponsorship.getId() != null) {
   				log.info("Updating " + sponsorship.getDescription());
   				update(sponsorship);
   			} else {
   				log.info("Registering " + sponsorship.getDescription());
   				save(sponsorship);
   			}
   		}
   		sponsorshipEventSrc.fire(sponsorship);
   		return saved;
   	}
}
