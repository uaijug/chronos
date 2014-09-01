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

import br.com.uaijug.chronos.project.task.data.repository.TaskRepository;
import br.com.uaijug.chronos.project.task.model.Task;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class TaskListProducer {

	/** The task repository. */
	@Inject
	private TaskRepository taskRepository;

    /** The tasks. */
    private List<Task> tasks;

    // @Named provides access the return value via the EL variable name "tasks" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the tasks.
     *
     * @return the tasks
     */
    @Produces
    @Named
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * On task list changed.
     *
     * @param task the task
     */
    public void onTaskListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Task task) {
        retrieveAllTasksOrderedByName();
    }

    /**
     * Retrieve all tasks ordered by name.
     */
    @PostConstruct
    public void retrieveAllTasksOrderedByName() {
        tasks = taskRepository.findAllOrderedByName();
    }

}
