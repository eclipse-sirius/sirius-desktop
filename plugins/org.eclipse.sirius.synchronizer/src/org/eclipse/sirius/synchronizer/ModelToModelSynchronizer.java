/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import java.util.Collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The core of the process, this class orchestrate the whole synchronization
 * mechanism between models.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class ModelToModelSynchronizer {

    private SemanticPartitionInvalidator evaluator;

    private MappingHiearchyTable table;

    private PreRefreshStatus pre;

    private SignatureProvider signatureProvider;

    public ModelToModelSynchronizer(SemanticPartitionInvalidator evaluator, MappingHiearchyTable table, PreRefreshStatus pre, SignatureProvider signProvider) {
        super();
        this.evaluator = evaluator;
        this.table = table;
        this.pre = pre;
        this.signatureProvider = signProvider;
    }

    public void update(CreatedOutput container) {
        RefreshPlan plan = new RefreshPlanner(this.table, this.evaluator, this.pre, this.signatureProvider).computePlan(container);

        if (container.getChildSupport().some()) {

            ChildCreationSupport containerChildSupport = container.getChildSupport().get();
            for (CreatedOutput outDesc : plan.getDescriptorsToDelete()) {
                containerChildSupport.deleteChild(outDesc);
            }

            Collection<CreatedOutput> newlyCreated = Lists.newArrayList();
            for (OutputDescriptor outDesc : plan.getDescriptorsToCreate()) {
                CreatedOutput newOne = containerChildSupport.createChild(outDesc);
                newOne.refresh();
               // update(newOne);
                newlyCreated.add(newOne);
            }

            Iterable<CreatedOutput> createdOrRefreshed = Iterables.concat(plan.getDescriptorsToRefresh(), newlyCreated);
            containerChildSupport.reorderChilds(createdOrRefreshed);

            for (CreatedOutput createdOutput : createdOrRefreshed) {
                update(createdOutput);
            }
        }

        for (CreatedOutput outDesc : plan.getDescriptorToUpdateMapping()) {
            outDesc.updateMapping();
            outDesc.refresh();
        }

        for (CreatedOutput outDesc : plan.getDescriptorsToRefresh()) {
            outDesc.refresh();
        }

    }

}
