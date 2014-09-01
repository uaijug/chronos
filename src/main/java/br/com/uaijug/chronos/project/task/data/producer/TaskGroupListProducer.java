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
package br.com.uaijug.chronos.project.task.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.project.task.data.repository.TaskGroupRepository;
import br.com.uaijug.chronos.project.task.model.TaskGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskGroupListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class TaskGroupListProducer {

	/** The task group repository. */
	@Inject
	private TaskGroupRepository taskGroupRepository;

    /** The task groups. */
    private List<TaskGroup> taskGroups;

    // @Named provides access the return value via the EL variable name "taskGroups" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the task groups.
     *
     * @return the task groups
     */
    @Produces
    @Named
    public List<TaskGroup> getTaskGroups() {
        return taskGroups;
    }

    /**
     * On task group list changed.
     *
     * @param taskGroup the task group
     */
    public void onTaskGroupListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TaskGroup taskGroup) {
        retrieveAllTaskGroupsOrderedByName();
    }

    /**
     * Retrieve all task groups ordered by name.
     */
    @PostConstruct
    public void retrieveAllTaskGroupsOrderedByName() {
        taskGroups = taskGroupRepository.findAllOrderedByName();
    }

}
