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
package br.com.uaijug.chronos.event.budget.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.event.budget.data.repository.BudgetItemRepository;
import br.com.uaijug.chronos.event.budget.model.BudgetItem;

// TODO: Auto-generated Javadoc
/**
 * The Class BudgetItemListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class BudgetItemListProducer {

    /** The budget item repository. */
    @Inject
    private BudgetItemRepository budgetItemRepository;

    /** The budget items. */
    private List<BudgetItem> budgetItems;

    // @Named provides access the return value via the EL variable name "events" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the budget items.
     *
     * @return the budget items
     */
    @Produces
    @Named
    public List<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    /**
     * On event list changed.
     *
     * @param budgetItem the budget item
     */
    public void onEventListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final BudgetItem budgetItem) {
        retrieveAllEventsOrderedByName();
    }

    /**
     * Retrieve all events ordered by name.
     */
    @PostConstruct
    public void retrieveAllEventsOrderedByName() {
    	budgetItems = budgetItemRepository.findAllOrderedByName();
    }

}
