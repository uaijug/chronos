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
package br.com.uaijug.chronos.institution.cashFlow.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.institution.cashFlow.model.CashFlowCategory;
import br.com.uaijug.chronos.institution.cashFlow.service.CashFlowCategoryRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class CashFlowCategoryRegistrationImpl extends PersistenceBase<CashFlowCategory, Long> implements CashFlowCategoryRegistration {

    @Inject
    private Logger log;
    
    @Inject
    private Event<CashFlowCategory> cashFlowCategoryEventSrc;
    
    @Override
   	public Boolean register(CashFlowCategory cashFlowCategory) {
   		Boolean saved = false;
   		if (exists(cashFlowCategory.getId())) {
   			try {
   				saved = false;
   				throw new Exception("Register exits!");
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		} else {

   			cashFlowCategory.setCreateTime(new Date());
   			cashFlowCategory.setUpdateTime(new Date());

   			if (cashFlowCategory.getId() != null) {
   				log.info("Updating " + cashFlowCategory.getDescription());
   				update(cashFlowCategory);
   			} else {
   				log.info("Registering " + cashFlowCategory.getDescription());
   				save(cashFlowCategory);
   			}
   		}
   		cashFlowCategoryEventSrc.fire(cashFlowCategory);
   		return saved;
   	}
}
