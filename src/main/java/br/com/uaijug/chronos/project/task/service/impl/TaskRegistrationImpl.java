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
package br.com.uaijug.chronos.project.task.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.persistence.base.PersistenceBase;
import br.com.uaijug.chronos.project.task.model.Task;
import br.com.uaijug.chronos.project.task.service.TaskRegistration;

//The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class TaskRegistrationImpl extends
		PersistenceBase<Task, Long> implements TaskRegistration {

	@Inject
	private Logger log;

	@Inject
	private Event<Task> taskEventSrc;
	
	@Override
	public Boolean register(Task task) {
		Boolean saved = false;
		if (exists(task.getId())) {
			try {
				saved = false;
				throw new Exception("Register exits!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			task.setCreateTime(new Date());
			task.setUpdateTime(new Date());

			if (task.getId() != null) {
				log.info("Updating " + task.getDescription());
				update(task);
			} else {
				log.info("Registering " + task.getDescription());
				save(task);
			}
		}
		taskEventSrc.fire(task);
		return saved;
	}
}