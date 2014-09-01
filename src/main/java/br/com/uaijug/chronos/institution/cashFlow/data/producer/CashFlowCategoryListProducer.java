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
package br.com.uaijug.chronos.institution.cashFlow.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.institution.cashFlow.data.repository.CashFlowCategoryRepository;
import br.com.uaijug.chronos.institution.cashFlow.model.CashFlowCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class CashFlowCategoryListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class CashFlowCategoryListProducer {

	/** The cash flow category repository. */
	@Inject
	private CashFlowCategoryRepository cashFlowCategoryRepository;

    /** The cash flow categorys. */
    private List<CashFlowCategory> cashFlowCategorys;

    // @Named provides access the return value via the EL variable name "cashFlowCategorys" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the cash flow categorys.
     *
     * @return the cash flow categorys
     */
    @Produces
    @Named
    public List<CashFlowCategory> getCashFlowCategorys() {
        return cashFlowCategorys;
    }

    /**
     * On cash flow category list changed.
     *
     * @param cashFlowCategory the cash flow category
     */
    public void onCashFlowCategoryListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final CashFlowCategory cashFlowCategory) {
        retrieveAllCashFlowCategorysOrderedByName();
    }

    /**
     * Retrieve all cash flow categorys ordered by name.
     */
    @PostConstruct
    public void retrieveAllCashFlowCategorysOrderedByName() {
        cashFlowCategorys = cashFlowCategoryRepository.findAllOrderedByName();
    }

}
