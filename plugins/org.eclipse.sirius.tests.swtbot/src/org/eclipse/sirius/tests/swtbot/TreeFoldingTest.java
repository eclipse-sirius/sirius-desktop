/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test class for tree folding.
 *
 * @author smonnier
 */
public class TreeFoldingTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String UNDO_FOLDING_LABEL = "Undo " + Messages.ToggleFoldingStateCommand_label;

    private static final String REPRESENTATION_INSTANCE_NAME = "TC 1852";

    private static final String REPRESENTATION_NAME = "Package Hierarchy With Nodes Target";

    private static final String MODEL = "tc1852.ecore";

    private static final String SESSION_FILE = "tc1852.aird";

    private static final String VSM_FILE = "tc1852.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/TreeFolding/";

    private static final String FILE_DIR = "/";

    private static final String[] FIRST_TREE = { "P1A1", "P1A2", "P1A3", "P1A1A", "P1A1B" };

    private static final String[] FIRST_TREE_WITHOUT_LEAVES = { "P1A1", "P1A2", "P1A3" };

    private static final String[] FIRST_TREE_LEAVES = { "P1A1A", "P1A1B" };

    private static final String[] SECOND_TREE = { "P1B1", "P1B2" };

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

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
     * Test method.
     *
     * @throws Exception
     *             Test error.
     */
    public void testUndoRedoFoldignOnDifferentsBranches() throws Exception {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.drag("P3", 150, 140);
            bot.sleep(500);

            checkEditPartAreVisible(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            clickFoldingToggle("P1B");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreHidden(SECOND_TREE);

            clickFoldingToggle("P1B");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreHidden(SECOND_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            undo("Set Location or Size");
            bot.sleep(500);

            redo("Set Location or Size");
            bot.sleep(500);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreHidden(SECOND_TREE);

            redo(Messages.ToggleFoldingStateCommand_label);
            checkEditPartAreHidden(FIRST_TREE);
            checkEditPartAreVisible(SECOND_TREE);
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test method.
     *
     * @throws Exception
     *             Test error.
     */
    public void testUndoRedoFoldignOnSameBranchDifferentsLevels1() throws Exception {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;

            editor.drag("P3", 150, 140);
            bot.sleep(500);

            checkEditPartAreVisible(FIRST_TREE);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            clickFoldingToggle("P1A1");
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            undo("Set Location or Size");
            bot.sleep(500);

            redo("Set Location or Size");
            bot.sleep(500);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Test method.
     *
     * @throws Exception
     *             Test error.
     */
    public void testUndoRedoFoldignOnSameBranchDifferentsLevels2() throws Exception {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;

            editor.drag("P3", 150, 140);
            bot.sleep(500);

            checkEditPartAreVisible(FIRST_TREE);

            clickFoldingToggle("P1A1");
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            clickFoldingToggle("P1A");
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            clickFoldingToggle("P1A1");
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            undo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);

            undo("Set Location or Size");
            bot.sleep(500);

            redo("Set Location or Size");
            bot.sleep(500);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreHidden(FIRST_TREE);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE_WITHOUT_LEAVES);
            checkEditPartAreHidden(FIRST_TREE_LEAVES);

            redo(Messages.ToggleFoldingStateCommand_label);
            bot.sleep(500);
            checkEditPartAreVisible(FIRST_TREE);
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    private void clickFoldingToggle(String name) {
        SWTBotGefEditPart editPart = editor.getEditPart(name);
        Rectangle bounds = ((IGraphicalEditPart) editPart.part()).getFigure().getBounds();
        Point p = bounds.getTopLeft().getTranslated(5, 5);
        editor.click(p);
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
