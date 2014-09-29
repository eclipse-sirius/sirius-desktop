/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the name to describe node of a tree corresponds to the label name
 * of the item instead of its id.
 * 
 * @author jmallet
 */
public class DisplayLabelOnNodeTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "vp4604.odesign";

    /**
     * VSM path.
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/tree/displayLabelNameOnNode/vp-4604/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Group";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Test that the node name in the tree matches with the label name of the
     * item instead of its id.
     */
    public void testLabelNameOnNode() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // expand the tree : Node Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        // check that the label name is displayed instead of its id for creation
        // tool
        SWTBotTreeItem treeDescriptionNode = tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("testLabelDisplay").expandNode("Tree Description");
        String labelName = "Create createLabel";
        try {
            treeDescriptionNode.getNode(labelName);
        } catch (WidgetNotFoundException e) {
            fail("The tree node with name \"" + labelName + "\" has not been found (probably a wrong name is used to diplay it.");
        }
    }
}
