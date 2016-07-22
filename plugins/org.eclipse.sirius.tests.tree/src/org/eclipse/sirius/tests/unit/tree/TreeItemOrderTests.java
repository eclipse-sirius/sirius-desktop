/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.tests.unit.common.TreeCommonTest;
import org.eclipse.sirius.tests.unit.common.TreeEcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.business.api.interaction.DTreeUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TreeItemOrderTests extends TreeCommonTest implements TreeEcoreModeler {

    private GlobalContext ctx;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ctx = new GlobalContext(session.getModelAccessor(), session.getInterpreter(), null);
    }

    public void testItemOrders() {

        Collection<DRepresentation> trees = getRepresentations(TREE_DESCRIPTION_ID, semanticModel);

        // Check tests data.
        assertTrue("Tests data have changed, review this test.", trees.size() == 1);
        assertTrue("Tests data have changed, review this test.", trees.iterator().next() instanceof DTree);

        final DTree dTree = (DTree) trees.iterator().next();

        checkItemOrder(dTree);

        // Refresh should not change order
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                new DTreeUserInteraction(dTree, ctx).refreshContent(defaultProgress);
            }
        });

        checkItemOrder(dTree);

    }

    public void testItemOrdersOnTreeCreation() {

        Collection<DRepresentation> trees = getRepresentations(TREE_DESCRIPTION_ID, semanticModel);

        // Check tests data.
        assertTrue("Tests data have changed, review this test.", trees.size() == 1);
        assertTrue("Tests data have changed, review this test.", trees.iterator().next() instanceof DTree);

        final DTree newTree = TreeFactory.eINSTANCE.createDTree();
        newTree.setTarget(semanticModel);
        newTree.setDescription((TreeDescription) getRepresentationDescription(TREE_DESCRIPTION_ID, session.getSelectedViewpoints(false).iterator().next()));

        // Refresh should not change order
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                new DTreeUserInteraction(newTree, ctx).refreshContent(defaultProgress);
            }
        });

        checkItemOrder(newTree);

    }

    private void checkItemOrder(DTree dTree) {
        List<TreeItemMapping> subItemMappings = dTree.getDescription().getSubItemMappings();

        checkSubItemsOrder(dTree, subItemMappings);

    }

    /*
     * Refresh should creates items and maintain their order regarding mapping
     * order and semantic order.
     * 
     * -> For two consecutive items check that the next item has a different
     * mapping (next in mapping lists) or is the next element in semantics.
     */
    private void checkSubItemsOrder(DTreeItemContainer itemCont, List<TreeItemMapping> subItemMappings) {
        // Nothing to test if there is only on or no element.
        TreeItemMapping currentMapping = null;
        TreeItemMapping nextMapping = null;
        if (itemCont.getOwnedTreeItems().size() > 1) {
            List<DTreeItem> subItems = itemCont.getOwnedTreeItems();
            for (int i = 0; i < subItems.size() - 1; i++) {
                DTreeItem current = subItems.get(i);
                DTreeItem next = subItems.get(i + 1);

                // First element, nextMapping is still null, we get the mapping
                // of the current tree item as currentMapping and we get the
                // mapping of next as next.getActualMapping.

                // For the other elements (step i: current=subItems[i];
                // next=subItems[i+1]), we can use the value of nextMapping
                // computed during the previous step (step j=i-1) of the
                // iteration with current=subItems[i-1]; next=subItems[i]
                // So there is no need to get the the actual mapping another
                // time.
                currentMapping = nextMapping == null ? current.getActualMapping() : nextMapping;
                // Update nextMapping, now that previous value is known and used
                // as currentMapping.
                nextMapping = next.getActualMapping();

                if (currentMapping == nextMapping) {
                    // Check semantic order
                    EObject currentSem = current.getTarget();
                    EObject nextSem = next.getTarget();

                    EObject contTarget = itemCont.getTarget();
                    // Used description have mapping on concrete classes
                    List<?> conTargetContents = Lists.newArrayList(Iterables.filter(contTarget.eContents(), currentSem.getClass()));

                    int currentSemInd = conTargetContents.indexOf(currentSem);
                    int nextSemInd = conTargetContents.indexOf(nextSem);

                    assertEquals("Items order do not follow semantic candidates order.", currentSemInd + 1, nextSemInd);
                } else {

                    // Check mapping order
                    int currentSemInd = subItemMappings.indexOf(currentMapping);
                    int nextSemInd = subItemMappings.indexOf(nextMapping);

                    assertEquals("Items order do not follow mapping order", currentSemInd + 1, nextSemInd);
                }

                // check sub items
                checkSubItemsOrder(current, currentMapping.getAllSubMappings());
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        ctx = null;

        super.tearDown();
    }
}
