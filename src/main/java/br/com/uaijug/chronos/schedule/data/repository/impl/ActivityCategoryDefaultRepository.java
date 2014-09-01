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
package br.com.uaijug.chronos.schedule.data.repository.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.uaijug.chronos.persistence.base.PersistenceBase;
import br.com.uaijug.chronos.schedule.data.repository.ActivityCategoryRepository;
import br.com.uaijug.chronos.schedule.model.ActivityCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class ActivityCategoryDefaultRepository.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@ApplicationScoped
public class ActivityCategoryDefaultRepository extends PersistenceBase<ActivityCategory, Long> implements ActivityCategoryRepository {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6579856773257420769L;

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.schedule.data.repository.ActivityCategoryRepository#findAllOrderedByName()
	 */
	public List<ActivityCategory> findAllOrderedByName() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ActivityCategory> criteria = cb.createQuery(ActivityCategory.class);
        Root<ActivityCategory> activityCategory = criteria.from(ActivityCategory.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(activityCategory).orderBy(cb.asc(activityCategory.get(ActivityCategory_.name)));
        criteria.select(activityCategory).orderBy(cb.asc(activityCategory.get("description")));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
