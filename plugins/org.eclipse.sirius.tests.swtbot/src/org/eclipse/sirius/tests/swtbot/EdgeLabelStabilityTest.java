/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * 
 * @author smonnier
 */
public class EdgeLabelStabilityTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME_MULTI_LINES = "new 2179Diag_multi_lines";

    private static final String REPRESENTATION_INSTANCE_NAME_SINGLE_LINE = "new 2179Diag_single_line";

    private static final String REPRESENTATION_INSTANCE_NAME_TEMP = "new 2179Diag";

    private static final String REPRESENTATION_NAME = "2179Diag";

    private static final String VIEWPOINT_NAME = "2179Viewpoint";

    private static final String MODEL = "2179.ecore";

    private static final String SESSION_FILE = "2179.aird";

    private static final String VSM_FILE = "2179.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelStability/";

    private static final String FILE_DIR = "/";

    private static final String C2 = "C2Too lonnnnnnnnnnnng name";

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
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Validate edge label dimension stability on drag and drop with a label on
     * multiple lines.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeLabelStabilityOnDragAndDrop() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_MULTI_LINES, DDiagram.class);

            Point location = editor.getLocation("P1", AbstractDiagramContainerEditPart.class);
            Dimension dimension = editor.getDimension("P1", AbstractDiagramContainerEditPart.class);

            Dimension edgeLabelDimension = editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class);

            // Drag and drop C1 to package P1
            editor.drag("C1", location.x + dimension.width / 2, location.y + dimension.height / 2);

            assertEquals("The edge label width has changed", edgeLabelDimension.width, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).width);
            assertEquals("The edge label height has changed", edgeLabelDimension.height, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).height);

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Validate edge label dimension stability on copy paste layout with a label
     * on multiple lines and then on a single line.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeLabelStabilityOnCopyPasteLayout() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;

            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_MULTI_LINES, DDiagram.class);

            Dimension edgeLabelDimension = editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class);

            // Copy layout
            bot.toolbarButtonWithTooltip(Messages.CopyFormatAction_toolTipText_diagram).click();

            // Open temp diagram
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_TEMP, DDiagram.class);

            // Paste layout
            bot.toolbarButtonWithTooltip(Messages.PasteFormatAction_toolTipText_diagram).click();

            assertEquals("The edge label width has changed", edgeLabelDimension.width, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).width);
            assertEquals("The edge label height has changed", edgeLabelDimension.height, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).height);

            // open single line diagram
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME_SINGLE_LINE, DDiagram.class);

            // Update reference label dimension with the label on a single line
            edgeLabelDimension = editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class);

            // Copy layout
            bot.toolbarButtonWithTooltip(Messages.CopyFormatAction_toolTipText_diagram).click();

            // Close this editor to toggle back to temp diagram
            bot.activeEditor().close();

            assertEquals("The active editor is not the expected one", REPRESENTATION_INSTANCE_NAME_TEMP, bot.activeEditor().getTitle());

            // To give focus to editor
            diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
                    .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_TEMP, UIDiagramRepresentation.class).open();

            // Paste layout
            bot.toolbarButtonWithTooltip(Messages.PasteFormatAction_toolTipText_diagram).click();

            assertEquals("The edge label width has changed", edgeLabelDimension.width, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).width);
            assertEquals("The edge label height has changed", edgeLabelDimension.height, editor.getDimension("extends " + C2, AbstractDiagramNameEditPart.class).height);

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagram = null;
        super.tearDown();
    }
}
