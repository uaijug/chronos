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

import br.com.uaijug.chronos.institution.data.repository.InstitutionRepository;
import br.com.uaijug.chronos.institution.model.Institution;

@RequestScoped
public class InstitutionListProducer {

	@Inject
	private InstitutionRepository institutionRepository;

    private List<Institution> institutions;

    // @Named provides access the return value via the EL variable name "institutions" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void onInstitutionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Institution institution) {
        retrieveAllInstitutionsOrderedByName();
    }

    @PostConstruct
    public void retrieveAllInstitutionsOrderedByName() {
        institutions = institutionRepository.findAllOrderedByName();
    }

}
