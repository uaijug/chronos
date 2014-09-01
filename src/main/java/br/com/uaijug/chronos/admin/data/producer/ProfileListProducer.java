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

import br.com.uaijug.chronos.admin.data.repository.ProfileRepository;
import br.com.uaijug.chronos.admin.model.Profile;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class ProfileListProducer {

	/** The profile repository. */
	@Inject
	private ProfileRepository profileRepository;

    /** The profiles. */
    private List<Profile> profiles;

    // @Named provides access the return value via the EL variable name "profiles" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the profiles.
     *
     * @return the profiles
     */
    @Produces
    @Named
    public List<Profile> getProfiles() {
        return profiles;
    }

    /**
     * On profile list changed.
     *
     * @param profile the profile
     */
    public void onProfileListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Profile profile) {
        retrieveAllProfilesOrderedByName();
    }

    /**
     * Retrieve all profiles ordered by name.
     */
    @PostConstruct
    public void retrieveAllProfilesOrderedByName() {
        profiles = profileRepository.findAllOrderedByName();
    }

}
