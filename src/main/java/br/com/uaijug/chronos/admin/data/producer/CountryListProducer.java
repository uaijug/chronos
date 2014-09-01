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

import br.com.uaijug.chronos.admin.data.repository.CountryRepository;
import br.com.uaijug.chronos.admin.model.Country;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class CountryListProducer {

	/** The country repository. */
	@Inject
	private CountryRepository countryRepository;

    /** The countrys. */
    private List<Country> countrys;

    // @Named provides access the return value via the EL variable name "countrys" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the countrys.
     *
     * @return the countrys
     */
    @Produces
    @Named
    public List<Country> getCountrys() {
        return countrys;
    }

    /**
     * On country list changed.
     *
     * @param country the country
     */
    public void onCountryListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Country country) {
        retrieveAllCountrysOrderedByName();
    }

    /**
     * Retrieve all countrys ordered by name.
     */
    @PostConstruct
    public void retrieveAllCountrysOrderedByName() {
        countrys = countryRepository.findAllOrderedByName();
    }

}
