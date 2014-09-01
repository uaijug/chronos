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
import br.com.uaijug.chronos.project.model.Project;
import br.com.uaijug.chronos.project.service.ProjectRegistration;

// TODO: Auto-generated Javadoc
// The @Stateless annotation eliminates the need for manual transaction demarcation
/**
 * The Class ProjectRegistrationImpl.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Stateless
public class ProjectRegistrationImpl extends PersistenceBase<Project, Long> implements ProjectRegistration {

    /** The log. */
    @Inject
    private Logger log;
    
    /** The project event src. */
    @Inject
    private Event<Project> projectEventSrc;

    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.service.GenericRegistration#register(java.lang.Object)
     */
    @Override
   	public Boolean register(Project project) {
   		Boolean saved = false;
   		if (exists(project.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			project.setCreateTime(new Date());
   			project.setUpdateTime(new Date());

   			if (project.getId() != null) {
   				log.info("Updating " + project.getTitle());
   				update(project);
   			} else {
   				log.info("Registering " + project.getTitle());
   				save(project);
   			}
   		}
   		projectEventSrc.fire(project);
   		return saved;
   	}
}
