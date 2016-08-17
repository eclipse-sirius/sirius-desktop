/*******************************************************************************
 * Copyright (c) 2015, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import java.util.Comparator;
import java.util.Map;

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

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

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
        final Map<EObject, CreatedOutput> outputToItem = Maps.newHashMap();
        for (CreatedOutput createdOutput : outDesc) {
            EObject createdElement = createdOutput.getCreatedElement();
            outputToItem.put(createdElement, createdOutput);
            if (createdElement instanceof DTreeItem) {
                DTreeItem createdDTreeItem = (DTreeItem) createdElement;
                TreeItemMapping actualMapping = createdDTreeItem.getActualMapping();
                subMappings.add(actualMapping);
            }
        }

        // Counts subMappings to correctly sort tree items regarding mapping
        // order (items have been created regarding the semantic candidates
        // order)
        int startIndex = 0;
        final Map<TreeItemMapping, Integer> startIndexes = Maps.newHashMap();
        for (TreeItemMapping itemMapping : subMappings) {
            startIndexes.put(itemMapping, startIndex);
            startIndex += subMappings.count(itemMapping);
        }

        // Pre-compute the new indices
        final Map<DTreeItem, Integer> newIndices = Maps.newHashMap();
        for (DTreeItem treeItem : container.getOwnedTreeItems()) {
            // init with element count : elements with unknown mapping
            // will be placed at the end.
            int index = outputToItem.size();
            TreeItemMapping itemMapping = treeItem.getActualMapping();
            if (itemMapping != null && startIndexes.containsKey(itemMapping)) {
                index = startIndexes.get(itemMapping);
            }

            CreatedOutput createdOutput = outputToItem.get(treeItem);
            if (createdOutput != null) {
                index = index + createdOutput.getNewIndex();
            } else {
                index = -1;
            }

            newIndices.put(treeItem, index);
        }

        ECollections.sort(container.getOwnedTreeItems(), new Comparator<DTreeItem>() {
            @Override
            public int compare(DTreeItem o1, DTreeItem o2) {
                return newIndices.get(o1).compareTo(newIndices.get(o2));
            }
        });
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
