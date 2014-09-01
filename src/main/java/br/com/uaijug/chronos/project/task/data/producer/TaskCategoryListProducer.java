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

import br.com.uaijug.chronos.project.task.data.repository.TaskCategoryRepository;
import br.com.uaijug.chronos.project.task.model.TaskCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskCategoryListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class TaskCategoryListProducer {

	/** The task category repository. */
	@Inject
	private TaskCategoryRepository taskCategoryRepository;

    /** The task categorys. */
    private List<TaskCategory> taskCategorys;

    // @Named provides access the return value via the EL variable name "taskCategorys" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the task categorys.
     *
     * @return the task categorys
     */
    @Produces
    @Named
    public List<TaskCategory> getTaskCategorys() {
        return taskCategorys;
    }

    /**
     * On task category list changed.
     *
     * @param taskCategory the task category
     */
    public void onTaskCategoryListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TaskCategory taskCategory) {
        retrieveAllTaskCategorysOrderedByName();
    }

    /**
     * Retrieve all task categorys ordered by name.
     */
    @PostConstruct
    public void retrieveAllTaskCategorysOrderedByName() {
        taskCategorys = taskCategoryRepository.findAllOrderedByName();
    }

}
