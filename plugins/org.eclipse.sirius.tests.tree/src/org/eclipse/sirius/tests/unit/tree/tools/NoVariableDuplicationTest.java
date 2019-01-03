/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree.tools;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.tree.business.internal.metamodel.TreeToolVariables;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeVariable;

import junit.framework.TestCase;

/**
 * Test copy paste tool not duplicates variables
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class NoVariableDuplicationTest extends TestCase {

    /**
     * Test that the renaming and copy of createTreeItemCreationTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateTreeItemCreationTool() {
        // Create TreeItemmCreationTool
        TreeItemCreationTool treeItemCreationTool = DescriptionFactory.eINSTANCE.createTreeItemCreationTool();
        assertEquals("No variable must be present in the treeItemCreationTool", 0, treeItemCreationTool.getVariables().size());

        // Add variables.
        new TreeToolVariables().doSwitch(treeItemCreationTool);
        assertEquals("The number of variable of treeItemCreationTool must be 3", 3, treeItemCreationTool.getVariables().size());
        for (TreeVariable variable : treeItemCreationTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName()) && !"container".equals(variable.getName())) {
                fail("The treeItemCreationTool variables' must be naming element, root and container, not " + variable.getName());
            }
        }

        // Name customization.
        for (TreeVariable variable : treeItemCreationTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName()) && !"container1".equals(variable.getName())) {
                fail("The treeItemCreationTool variables' must be naming element1, root1 and container1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(treeItemCreationTool);
    }

    /**
     * Test that the renaming and copy of treeItemDeletionTool not duplicates variables.
     */
    public void testNoVariableDuplicationTreeItemDeletionTool() {
        // Create treeItemDeletionTool
        TreeItemDeletionTool treeItemDeletionTool = DescriptionFactory.eINSTANCE.createTreeItemDeletionTool();
        assertEquals("No variable must be present in the treeItemDeletionTool", 0, treeItemDeletionTool.getVariables().size());

        // Add variables.
        new TreeToolVariables().doSwitch(treeItemDeletionTool);
        assertEquals("The number of variable of treeItemDeletionTool must be 2", 2, treeItemDeletionTool.getVariables().size());
        for (TreeVariable variable : treeItemDeletionTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName())) {
                fail("The treeItemDeletionTool variables' must be naming element and root, not " + variable.getName());
            }
        }

        // Name customization.
        for (TreeVariable variable : treeItemDeletionTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName())) {
                fail("The treeItemDeletionTool variables' must be naming element1, and root1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(treeItemDeletionTool);
    }

    /**
     * Test that the renaming and copy of treeItemEditionTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateTreeItemEditionTool() {
        // Create treeItemEditionTool
        TreeItemEditionTool treeItemEditionTool = DescriptionFactory.eINSTANCE.createTreeItemEditionTool();
        assertEquals("No variable must be present in the treeItemEditionTool", 0, treeItemEditionTool.getVariables().size());

        // Add variables.
        // new TreeToolVariables().doSwitch(treeItemEditionTool);
        new TreeToolVariables().doSwitch(treeItemEditionTool);
        String elementName = treeItemEditionTool.getElement().getName();
        String rootName = treeItemEditionTool.getRoot().getName();
        assertNotNull("The variable element should not be null", treeItemEditionTool.getElement());
        assertEquals("The name of variable element must be \"element\" not " + elementName, "element", elementName);
        assertNotNull("The variable root should not be null", treeItemEditionTool.getRoot());
        assertEquals("The name of variable root must be \"root\" not " + rootName, "root", rootName);

        // Name customization.
        treeItemEditionTool.getElement().setName(elementName + "1");
        treeItemEditionTool.getRoot().setName(rootName + "1");

        elementName = treeItemEditionTool.getElement().getName();
        rootName = treeItemEditionTool.getRoot().getName();

        assertEquals("The name of variable element must be \"element1\" not " + elementName, "element1", elementName);
        assertEquals("The name of variable root must be \"root1\" not " + rootName, "root1", rootName);

        checkNoModifOncopy(treeItemEditionTool);
    }

    /**
     * Test that the renaming and copy of treeItemDragTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateTreeItemDragTool() {
        // Create treeItemDragTool
        TreeItemContainerDropTool dropTool = DescriptionFactory.eINSTANCE.createTreeItemContainerDropTool();
        assertEquals("No variable must be present in the treeItemDragTool", 0, dropTool.getVariables().size());

        // Add variables.
        new TreeToolVariables().doSwitch(dropTool);
        String elementName = dropTool.getElement().getName();
        String oldContainerName = dropTool.getOldContainer().getName();
        String newContainerName = dropTool.getNewContainer().getName();
        String newViewContainerName = dropTool.getNewViewContainer().getName();
        String precedingSiblingName = dropTool.getPrecedingSiblings().getName();
        assertNotNull("The variable element should not be null", dropTool.getElement());
        assertEquals("The name of variable element must be \"element\" not " + elementName, "element", elementName);
        assertNotNull("The variable oldContainer should not be null", dropTool.getOldContainer());
        assertEquals("The name of variable oldContainer must be \"oldContainer\" not " + oldContainerName, "oldContainer", oldContainerName);
        assertNotNull("The variable newContainer should not be null", dropTool.getNewContainer());
        assertEquals("The name of variable newContainer must be \"newContainer\" not " + newContainerName, "newContainer", newContainerName);
        assertNotNull("The variable newViewContainer should not be null", dropTool.getNewViewContainer());
        assertEquals("The name of variable newViewContainer must be \"newViewContainer\" not " + newViewContainerName, "newViewContainer", newViewContainerName);
        assertNotNull("The variable precedingSiblings should not be null", dropTool.getPrecedingSiblings());
        assertEquals("The name of variable precedingSiblings must be \"precedingSiblings\" not " + precedingSiblingName, "precedingSiblings", precedingSiblingName);

        // Name customization.
        dropTool.getElement().setName(elementName + "1");
        dropTool.getOldContainer().setName(oldContainerName + "1");
        dropTool.getNewContainer().setName(newContainerName + "1");
        dropTool.getNewViewContainer().setName(newViewContainerName + "1");
        dropTool.getPrecedingSiblings().setName(precedingSiblingName + "1");

        elementName = dropTool.getElement().getName();
        oldContainerName = dropTool.getOldContainer().getName();
        newContainerName = dropTool.getNewContainer().getName();
        newViewContainerName = dropTool.getNewViewContainer().getName();
        precedingSiblingName = dropTool.getPrecedingSiblings().getName();

        assertEquals("The name of variable element must be \"element1\" not " + elementName, "element1", elementName);
        assertEquals("The name of variable oldContainer must be \"oldContainer1\" not " + oldContainerName, "oldContainer1", oldContainerName);
        assertEquals("The name of variable newContainer must be \"newContainer1\" not " + newContainerName, "newContainer1", newContainerName);
        assertEquals("The name of variable newViewContainer must be \"newViewContainer1\" not " + newViewContainerName, "newViewContainer1", newViewContainerName);
        assertEquals("The name of variable precedingSiblings must be \"precedingSiblings1\" not " + precedingSiblingName, "precedingSiblings1", precedingSiblingName);

        checkNoModifOncopy(dropTool);
    }

    private void checkNoModifOncopy(EObject tool) {
        // Copy createLineTool
        EObject copy = SiriusCopierHelper.copyWithNoUidDuplication(tool);

        assertTrue("The copy must be same that origin", EcoreUtil.equals(tool, copy));
    }

}
