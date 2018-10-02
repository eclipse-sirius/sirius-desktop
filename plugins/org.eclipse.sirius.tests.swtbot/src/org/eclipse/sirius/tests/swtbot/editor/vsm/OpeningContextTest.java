/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests the editor context just after being opened like the expandable status
 * of all its items.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class OpeningContextTest extends AbstractContentAssistTest {

    private static final String PATH = "data/unit/vsm/";

    private static final String VSM = "ecore.odesign";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, VSM);

    }

    /**
     * Tests that just after VSM editor opening, all tree items are expanded
     * until the level 4 without user involvement.
     */
    public void testExpandableItemStatusAfterOpening() {
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        SWTBotTreeItem[] treeItem = odesignEditor.bot().tree().getAllItems();
        assertEquals("There should be only one group node under resource node", 1, treeItem.length);
        // Level1
        SWTBotTreeItem resourceItem = treeItem[0];
        assertEquals("There should be only one viewpoint node under group node", 1, resourceItem.getItems().length);
        // Level2
        SWTBotTreeItem groupItem = resourceItem.getItems()[0];
        assertEquals(errorMessage(2, 6), 6, groupItem.getItems().length);
        // Level3
        SWTBotTreeItem viewpointItem = groupItem.getItems()[0];
        assertEquals(errorMessage(3, 7), 7, viewpointItem.getItems().length);
        SWTBotTreeItem viewpointItem2 = groupItem.getItems()[2];
        assertEquals(errorMessage(3, 4), 4, viewpointItem2.getItems().length);

        // Level4
        // We check without expanding the level 4 node that we have only one
        // sub node corresponding to the empty node.
        SWTBotTreeItem entitiesDiagramItem2 = viewpointItem2.getItems()[0];
        assertEquals("The level 4 node should not be expanded. It should contains only the empty node.", 1, entitiesDiagramItem2.getItems().length);
        SWTBotTreeItem entitiesDiagramItem = viewpointItem.getItems()[0];
        assertEquals("The level 4 node should not be expanded. It should contains only the empty node.", 1, entitiesDiagramItem.getItems().length);

        // Then we expand the level 4 nodes to verify that we have more than one
        // node
        // that means we don't have anymore the empty node and thus that is was
        // not expanded.
        SWTBotTreeItem expandedEntitiesDiagramItem2 = entitiesDiagramItem2.expand();
        assertEquals(errorMessage(4, 2), 2, expandedEntitiesDiagramItem2.getItems().length);
        SWTBotTreeItem expandedEntitiesDiagramItem = entitiesDiagramItem.expand();
        assertEquals(errorMessage(4, 9), 9, expandedEntitiesDiagramItem.getItems().length);
    }

    private String errorMessage(int level, int subNodeNumber) {
        return "The level " + level + " node should have " + subNodeNumber + " sub nodes";
    }

}
