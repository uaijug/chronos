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
package br.com.uaijug.chronos.institution.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.institution.data.repository.AboutRepository;
import br.com.uaijug.chronos.institution.model.About;

// TODO: Auto-generated Javadoc
/**
 * The Class AboutListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class AboutListProducer {

	/** The about repository. */
	@Inject
	private AboutRepository aboutRepository;

    /** The abouts. */
    private List<About> abouts;

    // @Named provides access the return value via the EL variable name "abouts" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the abouts.
     *
     * @return the abouts
     */
    @Produces
    @Named
    public List<About> getAbouts() {
        return abouts;
    }

    /**
     * On about list changed.
     *
     * @param about the about
     */
    public void onAboutListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final About about) {
        retrieveAllAboutsOrderedByName();
    }

    /**
     * Retrieve all abouts ordered by name.
     */
    @PostConstruct
    public void retrieveAllAboutsOrderedByName() {
        abouts = aboutRepository.findAllOrderedByName();
    }

}
