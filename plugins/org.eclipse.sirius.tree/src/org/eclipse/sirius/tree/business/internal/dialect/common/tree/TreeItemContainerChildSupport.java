/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.synchronizer.ChildCreationSupport;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.RGBValues;

import com.google.common.base.Function;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;

/**
 * A {@link ChildCreationSupport}.
 * 
 * @author cbrun
 */
class TreeItemContainerChildSupport implements ChildCreationSupport {

    private DTreeItemContainer container;

    private GlobalContext ctx;

    /**
     * Default constructor.
     * 
     * @param ctx
     *            a {@link GlobalContext}
     * @param container
     *            a {@link DTreeItemContainer}
     */
    TreeItemContainerChildSupport(GlobalContext ctx, DTreeItemContainer container) {
        this.container = container;
        this.ctx = ctx;
    }

    @Override
    public void reorderChilds(Iterable<CreatedOutput> outDesc) {
        final Multiset<TreeItemMapping> subMappings = LinkedHashMultiset.create();
        Set<TreeItemMapping> mappings = new HashSet<TreeItemMapping>();
        final Map<EObject, CreatedOutput> outputToItem = Maps.newHashMap();
        for (CreatedOutput createdOutput : outDesc) {
            EObject createdElement = createdOutput.getCreatedElement();
            outputToItem.put(createdElement, createdOutput);
            if (createdElement instanceof DTreeItem) {
                DTreeItem createdDTreeItem = (DTreeItem) createdElement;
                TreeItemMapping actualMapping = createdDTreeItem.getActualMapping();
                subMappings.add(actualMapping);
                mappings.add(actualMapping);
            }
        }

        // Does not need to sort DTreeItem according to their mapping if there
        // is only one mapping
        if (mappings.size() > 1) {

            // Counts subMappings to correctly sort tree items regarding mapping
            // order (items have been created regarding the semantic candidates
            // order)
            int startIndex = 0;
            final Map<TreeItemMapping, Integer> startIndexes = Maps.newHashMap();
            for (TreeItemMapping itemMapping : subMappings) {
                startIndexes.put(itemMapping, startIndex);
                startIndex += subMappings.count(itemMapping);
            }

            Function<DTreeItem, Integer> getNewIndex = new Function<DTreeItem, Integer>() {

                @Override
                public Integer apply(DTreeItem from) {
                    // init with element count : elements with unknown mapping
                    // will
                    // be placed at
                    // the end.
                    int index = outputToItem.size();
                    TreeItemMapping itemMapping = from.getActualMapping();
                    if (itemMapping != null && startIndexes.containsKey(itemMapping)) {
                        index = startIndexes.get(itemMapping);
                    }

                    CreatedOutput createdOutput = outputToItem.get(from);
                    if (createdOutput != null) {
                        return index + createdOutput.getNewIndex();
                    }
                    return -1;
                }
            };

            ECollections.sort(container.getOwnedTreeItems(), Ordering.natural().onResultOf(getNewIndex));
        }
    }

    @Override
    public void deleteChild(CreatedOutput outDesc) {
        /*
         * The cross referencer is actually optional for the eDelete method of
         * the model accessor.
         */
        ECrossReferenceAdapter xRef = ECrossReferenceAdapter.getCrossReferenceAdapter(container);
        ctx.getModelAccessor().eDelete(outDesc.getCreatedElement(), xRef);
    }

    @Override
    public CreatedOutput createChild(OutputDescriptor outDesc) {
        OutputTreeItemDescriptor descriptor = (OutputTreeItemDescriptor) outDesc;
        DTreeItem dTreeItem = TreeFactory.eINSTANCE.createDTreeItem();
        dTreeItem.setActualMapping(descriptor.getMapping().getDescription());
        dTreeItem.setTarget(outDesc.getSourceElement());
        dTreeItem.getSemanticElements().add(outDesc.getSourceElement());
        TreeItemStyle treeItemStyle = TreeFactory.eINSTANCE.createTreeItemStyle();
        RGBValues backgroundColor = RGBValues.DEFAULT_GRAY;
        RGBValues labelColor = RGBValues.DEFAULT_GRAY;
        treeItemStyle.setBackgroundColor(backgroundColor);
        treeItemStyle.setLabelColor(labelColor);
        dTreeItem.setOwnedStyle(treeItemStyle);
        container.getOwnedTreeItems().add(dTreeItem);
        CreatedTreeItem newOne = new CreatedTreeItem(ctx, dTreeItem, descriptor);
        return newOne;
    }
}
