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

import br.com.uaijug.chronos.admin.data.repository.CompanyRepository;
import br.com.uaijug.chronos.admin.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class CompanyListProducer {

	/** The company repository. */
	@Inject
	private CompanyRepository companyRepository;

    /** The companys. */
    private List<Company> companys;

    // @Named provides access the return value via the EL variable name "companys" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the companys.
     *
     * @return the companys
     */
    @Produces
    @Named
    public List<Company> getCompanys() {
        return companys;
    }

    /**
     * On company list changed.
     *
     * @param company the company
     */
    public void onCompanyListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Company company) {
        retrieveAllCompanysOrderedByName();
    }

    /**
     * Retrieve all companys ordered by name.
     */
    @PostConstruct
    public void retrieveAllCompanysOrderedByName() {
        companys = companyRepository.findAllOrderedByName();
    }

}
