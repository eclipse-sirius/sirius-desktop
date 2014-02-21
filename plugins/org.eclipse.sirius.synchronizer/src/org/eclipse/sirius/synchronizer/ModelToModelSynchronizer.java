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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;

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

    public void update(CreatedOutput container, boolean fullRefresh) {
        RefreshPlan plan = new RefreshPlanner(this.table, this.evaluator, this.pre, this.signatureProvider).computePlan(container);

        Collection<CreatedOutput> descriptorsToDelete = plan.getDescriptorsToDelete();
        Collection<OutputDescriptor> descriptorsToCreate = plan.getDescriptorsToCreate();
        Collection<CreatedOutput> descriptorsToRefresh = plan.getDescriptorsToRefresh();
        // Refresh children only if needed
        Option<? extends ChildCreationSupport> childSupport = container.getChildSupport();
        if (container.synchronizeChildren() || fullRefresh) {
            if (childSupport.some()) {

                ChildCreationSupport containerChildSupport = childSupport.get();
                for (CreatedOutput outDesc : descriptorsToDelete) {
                    containerChildSupport.deleteChild(outDesc);
                }

                Collection<CreatedOutput> newlyCreated = Lists.newArrayList();
                for (OutputDescriptor outDesc : descriptorsToCreate) {
                    CreatedOutput newOne = containerChildSupport.createChild(outDesc);
                    newOne.refresh();
                    // update(newOne);
                    newlyCreated.add(newOne);
                }

                Iterable<CreatedOutput> createdOrRefreshed = Iterables.concat(descriptorsToRefresh, newlyCreated);
                containerChildSupport.reorderChilds(createdOrRefreshed);

                for (CreatedOutput createdOutput : createdOrRefreshed) {
                    update(createdOutput, fullRefresh);
                }
            }

            for (CreatedOutput outDesc : plan.getDescriptorToUpdateMapping()) {
                outDesc.updateMapping();
                outDesc.refresh();
            }

            for (CreatedOutput outDesc : descriptorsToRefresh) {
                outDesc.refresh();
            }
        } else {
            if (childSupport.some()) {
                EObject createdElement = container.getCreatedElement();
                if (createdElement != null && descriptorsToRefresh.isEmpty() && !descriptorsToCreate.isEmpty()) {
                    // Create only one children not refreshed to have
                    // ITreeContentProvider.hasChildren() returning true
                    ChildCreationSupport containerChildSupport = childSupport.get();
                    OutputDescriptor outDesc = descriptorsToCreate.iterator().next();
                    containerChildSupport.createChild(outDesc);
                }
            }
        }
    }

}
