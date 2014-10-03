/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.layout;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.NodeLayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

/**
 * Test class for tree folding.
 * 
 * @author smonnier
 */
public class TreeDiagramLayoutStabilityTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new TreeDiagram";

    private static final String REPRESENTATION_NAME = "TreeDiagram";

    private static final String MODEL = "treeDiagram.ecore";

    private static final String SESSION_FILE = "treeDiagram.aird";

    private static final String VSM_FILE = "treeDiagram.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/treeDiagram/";

    private static final String FILE_DIR = "/";

    private static final String[] FIRST_TREE = { "P1", "P1A", "P1B" };

    private static final String[] SECOND_TREE = { "P2", "P2A", "P2B", "P2C", "P2B1" };

    private static final String DELETE_CONTEXTUAL_MENU_NAME = "Delete from Model";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test that the layout is conserved after an undo of deleting root of a
     * tree.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testConservationOfLayoutAfterUndoDelete() throws Exception {
        // Check if the two trees are visible
        checkEditPartAreVisible(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        // Store the layout data before the delete
        final AdvancedSiriusLayoutDataManager layoutDataManager = new SiriusLayoutDataManagerForSemanticElements();
        storeLayoutData(SECOND_TREE, layoutDataManager);

        // Delete the second tree
        editor.clickCentered("P2");
        editor.clickContextMenu(DELETE_CONTEXTUAL_MENU_NAME);

        // Check that only one tree is visible
        checkEditPartAreVisible(FIRST_TREE);
        checkEditPartAreHidden(SECOND_TREE);

        // Undo the change
        bot.menu("Edit").menu("Undo " + DELETE_CONTEXTUAL_MENU_NAME).click();

        // Check if the two trees are visible
        checkEditPartAreVisible(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        // Store the layout data after the undo
        final AdvancedSiriusLayoutDataManager layoutDataManagerAfterUndo = new SiriusLayoutDataManagerForSemanticElements();
        storeLayoutData(SECOND_TREE, layoutDataManagerAfterUndo);

        // Compare the two layouts
        compareLayoutData(layoutDataManager, layoutDataManagerAfterUndo);
    }

    /**
     * Compare the data of two layoutDataManagers.
     * 
     * @param firstDataManager
     *            The first data manager to compare
     * @param secondDataManager
     *            The second data manager to compare
     */
    private void compareLayoutData(AdvancedSiriusLayoutDataManager firstDataManager, AdvancedSiriusLayoutDataManager secondDataManager) {
        Map<? extends NodeLayoutDataKey, NodeLayoutData> nodeLayoutData = firstDataManager.getNodeLayoutData();
        for (NodeLayoutDataKey key : nodeLayoutData.keySet()) {
            assertTrue(
                    "The element represented by the id " + key.getId() + " must have the same layout.",
                    LayoutHelper.INSTANCE.haveSameLayout((NodeLayoutData) firstDataManager.getLayoutData(key), (NodeLayoutData) secondDataManager.getLayoutData(key),
                            ConfigurationFactory.buildConfiguration()));
        }
    }

    /**
     * Store the layout of all the edit part witch names is in the
     * editPartLabels list.
     * 
     * @param editPartLabels
     *            List of edit part names
     * @param layoutDataManager
     *            The layout data manager to store the layout
     */
    private void storeLayoutData(String[] editPartLabels, AdvancedSiriusLayoutDataManager layoutDataManager) {
        for (final String editPartLabel : editPartLabels) {
            final SWTBotGefEditPart editPart = editor.getEditPart(editPartLabel, AbstractDiagramNodeEditPart.class);
            if (editPart.part() instanceof IGraphicalEditPart) {
                layoutDataManager.storeLayoutData((IGraphicalEditPart) editPart.part());
            }
        }
    }

    private void checkEditPartAreVisible(final String[] labels) {
        for (final String label : labels) {
            // Check edit part is came back
            final SWTBotGefEditPart editPart = editor.getEditPart(label);
            assertThat("The edit part named " + label + " should be visible but is not.", editPart, notNullValue());
        }
    }

    private void checkEditPartAreHidden(final String[] labels) {
        for (final String label : labels) {
            try {
                editor.getEditPart(label);
                fail(WidgetNotFoundException.class + " expected for element \"" + label + "\"");

            } catch (final WidgetNotFoundException e) {
                // Expected, the edit part must not be found
            }
        }
    }
}
