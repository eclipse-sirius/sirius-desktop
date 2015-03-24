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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test the automatic creation of mandatory elements when creation their parent.
 *
 * @author bgrouhan
 */
public class CreateMandatoryElementsTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM = "autoCreateMandatoryElements.odesign";

    private static final String VIEWPOINT = "V";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/editor/vsm/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Test that Mandatory elements are directly created as children when the
     * parent is created.
     */
    public void testCreationMandatoryElements() {
        // open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // expands the tree
        SWTBotTreeItem tree = odesignEditor.bot().tree().expandNode(ODESIGN).expandNode("My").expandNode(VIEWPOINT);

        // test creation of a default layer
        createElement(tree.select(), "Diagram Description", "Default");
        createElement(tree.select(), "Sequence Diagram Description", "Default");

        // test creation of a concern
        createElement(tree.expandNode("Diagram").select(), "Concerns", "Concern");
        createElement(tree.expandNode("Diagram Import DiagramImport").select(), "Concerns", "Concern");
        createElement(tree.expandNode("Diagram Extension DiagramExtension").select(), "Concerns", "Concern");

        // test creation of a tree item style
        createElement(tree.expandNode("Tree").expandNode("TreeItem").select(), "Conditional Style", "feature:name");

        // test creation of a foreground style
        createElement(tree.expandNode("EditionTable").expandNode("Line").select(), "Conditional Foreground", "Foreground 12");
        createElement(tree.expandNode("EditionTable").expandNode("FeatureColumn").select(), "Conditional Foreground", "Foreground 12");
        createElement(tree.expandNode("CrossTable").expandNode("ElementColumn").select(), "Conditional Foreground", "Foreground 12");
        createElement(tree.expandNode("CrossTable").expandNode("Intersection").select(), "Conditional Foreground", "Foreground 12");

        // test creation of a background style
        createElement(tree.expandNode("EditionTable").expandNode("Line").select(), "Conditional Background", "Background");
        createElement(tree.expandNode("EditionTable").expandNode("FeatureColumn").select(), "Conditional Background", "Background");
        createElement(tree.expandNode("CrossTable").expandNode("ElementColumn").select(), "Conditional Background", "Background");
        createElement(tree.expandNode("CrossTable").expandNode("Intersection").select(), "Conditional Background", "Background");

        // test creation of an edge style (not bracket)
        createElement(tree.expandNode("Diagram").expandNode("Default").expandNode("ElementEdge").select(), "Conditional Style", "Edge Style solid");
        // test creation of a bracket edge style
        createElement(tree.expandNode("Diagram").expandNode("Default").expandNode("RelationEdge").select(), "Conditional Style", "Bracket Edge Style solid");

    }

    /**
     * Method to create a sub-element of a selected SWTBotTreeItem given the
     * element name and test if the created element has the expected child.
     * 
     * @param treeItem
     *            the starting SWTBotTreeItem.
     * @param elementName
     *            the name of the element to create.
     * @param childName
     *            the name of the child element that must have been created.
     */
    private void createElement(SWTBotTreeItem treeItem, String elementName, String childName) {
        SWTBotUtils.clickContextMenu(treeItem, elementName);
        try {
            treeItem.expandNode(elementName).expandNode(childName);
        } catch (WidgetNotFoundException e) {
            assertTrue("The element '" + childName + "' (or its parent, '" + elementName + "') has not been created", false);
        }
    }
}
