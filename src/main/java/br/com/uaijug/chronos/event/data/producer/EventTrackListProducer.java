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
package br.com.uaijug.chronos.event.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.event.data.repository.EventTrackRepository;
import br.com.uaijug.chronos.event.model.EventTrack;

// TODO: Auto-generated Javadoc
/**
 * The Class EventTrackListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class EventTrackListProducer {

	/** The event track repository. */
	@Inject
    private EventTrackRepository eventTrackRepository;

    /** The event tracks. */
    private List<EventTrack> eventTracks;

    // @Named provides access the return value via the EL variable name "eventTracks" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the event tracks.
     *
     * @return the event tracks
     */
    @Produces
    @Named
    public List<EventTrack> getEventTracks() {
        return eventTracks;
    }

    /**
     * On event track list changed.
     *
     * @param eventTrack the event track
     */
    public void onEventTrackListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final EventTrack eventTrack) {
        retrieveAllEventTracksOrderedByName();
    }

    /**
     * Retrieve all event tracks ordered by name.
     */
    @PostConstruct
    public void retrieveAllEventTracksOrderedByName() {
        eventTracks = eventTrackRepository.findAllOrderedByName();
    }

}
