/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.synchronizer.AutomaticCreator;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.description.TreeItemMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * A {@link AutomaticCreator}.
 * 
 * @author cbrun
 */
class TreeItemCreator implements AutomaticCreator {

    private TreeItemMapping nm;

    private TreeMappingProvider provider;

    /**
     * Default constructor.
     * 
     * @param nm
     *            a {@link TreeItemMapping}
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    TreeItemCreator(TreeItemMapping nm, TreeMappingProvider provider) {
        super();
        this.nm = nm;
        this.provider = provider;
    }

    @Override
    public Collection<? extends OutputDescriptor> computeDescriptors(final CreatedOutput context, Iterator<EObject> it) {
        final AbstractCreatedDTreeItemContainer castedContext = (AbstractCreatedDTreeItemContainer) context;
        Predicate<OutputTreeItemDescriptor> filterPredicates = new Predicate<OutputTreeItemDescriptor>() {

            @Override
            public boolean apply(OutputTreeItemDescriptor input) {
                return new TreeItemMappingExpression(castedContext.getGlobalContext(), input.getMapping().getDescription()).checkPrecondition(input.getSourceElement(), input.getViewContainer());
            }
        };
        List<OutputTreeItemDescriptor> outputs = new ArrayList<>();
        int i = 0;
        while (it.hasNext()) {
            EObject from = it.next();
            outputs.add(new OutputTreeItemDescriptor((DTreeItemContainer) context.getCreatedElement(), from, this.nm, i, this.provider));
            i++;
        }

        return Collections2.filter(outputs, filterPredicates);
    }
}
