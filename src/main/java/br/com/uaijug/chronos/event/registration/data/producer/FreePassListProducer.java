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
package br.com.uaijug.chronos.event.registration.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.event.registration.data.repository.FreePassRepository;
import br.com.uaijug.chronos.event.registration.model.FreePass;

// TODO: Auto-generated Javadoc
/**
 * The Class FreePassListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class FreePassListProducer {

    /** The free pass repository. */
    @Inject
    private FreePassRepository freePassRepository;

    /** The free passes. */
    private List<FreePass> freePasses;

    // @Named provides access the return value via the EL variable name "events" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the free passes.
     *
     * @return the free passes
     */
    @Produces
    @Named
    public List<FreePass> getFreePasses() {
        return freePasses;
    }

    /**
     * On event list changed.
     *
     * @param freePass the free pass
     */
    public void onEventListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final FreePass freePass) {
        retrieveAllEventsOrderedByName();
    }

    /**
     * Retrieve all events ordered by name.
     */
    @PostConstruct
    public void retrieveAllEventsOrderedByName() {
    	freePasses = freePassRepository.findAllOrderedByName();
    }

}
