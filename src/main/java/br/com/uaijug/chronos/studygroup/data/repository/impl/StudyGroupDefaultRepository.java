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
package br.com.uaijug.chronos.studygroup.data.repository.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.uaijug.chronos.persistence.base.PersistenceBase;
import br.com.uaijug.chronos.studygroup.data.repository.StudyGroupRepository;
import br.com.uaijug.chronos.studygroup.model.StudyGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class StudyGroupDefaultRepository.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@ApplicationScoped
public class StudyGroupDefaultRepository extends PersistenceBase<StudyGroup, Long> implements StudyGroupRepository {

    /* (non-Javadoc)
     * @see br.com.uaijug.chronos.studygroup.data.repository.StudyGroupRepository#findAllOrderedByName()
     */
    public List<StudyGroup> findAllOrderedByName() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StudyGroup> criteria = cb.createQuery(StudyGroup.class);
        Root<StudyGroup> studyGroup = criteria.from(StudyGroup.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(studyGroup).orderBy(cb.asc(studyGroup.get(StudyGroup_.name)));
        criteria.select(studyGroup).orderBy(cb.asc(studyGroup.get("description")));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
