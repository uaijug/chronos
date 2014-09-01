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
package br.com.uaijug.chronos.project.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.persistence.base.PersistenceBase;
import br.com.uaijug.chronos.project.model.DailyActivity;
import br.com.uaijug.chronos.project.service.DailyActivityRegistration;

// TODO: Auto-generated Javadoc
// The @Stateless annotation eliminates the need for manual transaction demarcation
/**
 * The Class DailyActivityRegistrationImpl.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Stateless
public class DailyActivityRegistrationImpl extends PersistenceBase<DailyActivity, Long> implements DailyActivityRegistration {

    /** The log. */
    @Inject
    private Logger log;
    
    /** The daily activity event src. */
    @Inject
    private Event<DailyActivity> dailyActivityEventSrc;

    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.service.GenericRegistration#register(java.lang.Object)
     */
    @Override
   	public Boolean register(DailyActivity dailyActivity) {
   		Boolean saved = false;
   		if (exists(dailyActivity.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			dailyActivity.setCreateTime(new Date());
   			dailyActivity.setUpdateTime(new Date());

   			if (dailyActivity.getId() != null) {
   				log.info("Updating " + dailyActivity.getId());
   				update(dailyActivity);
   			} else {
   				log.info("Registering " + dailyActivity.getId());
   				save(dailyActivity);
   			}
   		}
   		dailyActivityEventSrc.fire(dailyActivity);
   		return saved;
   	}
}
