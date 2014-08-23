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
package br.com.uaijug.chronos.event.speech.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.event.speech.model.Speech;
import br.com.uaijug.chronos.event.speech.service.SpeechRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class SpeechRegistrationImpl extends PersistenceBase<Speech, Long> implements SpeechRegistration {

    @Inject
    private Logger log;
    
    @Inject
    private Event<Speech> speechEventSrc;

    @Override
   	public Boolean register(Speech speech) {
   		Boolean saved = false;
   		if (exists(speech.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			speech.setCreateTime(new Date());
   			speech.setUpdateTime(new Date());

   			if (speech.getId() != null) {
   				log.info("Updating " + speech.getDescription());
   				update(speech);
   			} else {
   				log.info("Registering " + speech.getDescription());
   				save(speech);
   			}
   		}
   		speechEventSrc.fire(speech);
   		return saved;
   	}
}
