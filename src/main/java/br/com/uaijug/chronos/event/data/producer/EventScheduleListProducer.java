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

import br.com.uaijug.chronos.event.data.repository.EventScheduleRepository;
import br.com.uaijug.chronos.event.model.EventSchedule;

// TODO: Auto-generated Javadoc
/**
 * The Class EventScheduleListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class EventScheduleListProducer {

    /** The event schedule repository. */
    @Inject
    private EventScheduleRepository eventScheduleRepository;

    /** The event schedules. */
    private List<EventSchedule> eventSchedules;

    // @Named provides access the return value via the EL variable name "eventSchedules" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the event schedules.
     *
     * @return the event schedules
     */
    @Produces
    @Named
    public List<EventSchedule> getEventSchedules() {
        return eventSchedules;
    }

    /**
     * On event schedule list changed.
     *
     * @param eventSchedule the event schedule
     */
    public void onEventScheduleListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final EventSchedule eventSchedule) {
        retrieveAllEventSchedulesOrderedByName();
    }

    /**
     * Retrieve all event schedules ordered by name.
     */
    @PostConstruct
    public void retrieveAllEventSchedulesOrderedByName() {
        eventSchedules = eventScheduleRepository.findAllOrderedByName();
    }

}
