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
package br.com.uaijug.chronos.admin.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.admin.data.repository.AuthorizationRepository;
import br.com.uaijug.chronos.admin.model.Authorization;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorizationListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class AuthorizationListProducer {

	/** The authorization repository. */
	@Inject
	private AuthorizationRepository authorizationRepository;

    /** The authorizations. */
    private List<Authorization> authorizations;

    // @Named provides access the return value via the EL variable name "authorizations" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the authorizations.
     *
     * @return the authorizations
     */
    @Produces
    @Named
    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    /**
     * On authorization list changed.
     *
     * @param authorization the authorization
     */
    public void onAuthorizationListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Authorization authorization) {
        retrieveAllAuthorizationsOrderedByName();
    }

    /**
     * Retrieve all authorizations ordered by name.
     */
    @PostConstruct
    public void retrieveAllAuthorizationsOrderedByName() {
        authorizations = authorizationRepository.findAllOrderedByName();
    }

}
