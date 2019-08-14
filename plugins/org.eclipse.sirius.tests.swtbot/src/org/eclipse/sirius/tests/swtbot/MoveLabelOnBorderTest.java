/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This class tests moving label on border.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class MoveLabelOnBorderTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String SEMANTIC_MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "/data/unit/migration/do_not_migrate/labelOnBorder/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "diagramWithLabelsOnBorder", "diagramFreshlyCreated", DDiagram.class, true);
    }

    /**
     * Check if after a move of a label to the left, it is really moved.
     */
    public void testMoveLabelOnBorder() {
        // Select the label on border of the node
        SWTBotGefEditPart nodeLabelEditPart = editor.getEditPart("my.first.class", DNodeNameEditPart.class);
        editor.select(nodeLabelEditPart);
        // Get the center coordinates of the label
        Point currentCenterPoint = editor.getAbsoluteCenter((GraphicalEditPart) nodeLabelEditPart.part());
        // Compute the drop destination
        Point expectedCenterPoint = currentCenterPoint.getTranslated(20, 0);
        // Drag'and'drop with the mouse
        editor.drag(currentCenterPoint.x, currentCenterPoint.y, expectedCenterPoint.x, expectedCenterPoint.y);
        // Wait that the label has been moved
        bot.waitUntil(new OperationDoneCondition());
        // Check that the label has been graphically moved as expected
        Point newCenterPoint = editor.getAbsoluteCenter((GraphicalEditPart) nodeLabelEditPart.part());
        assertEquals("The label has not be moved as expected (original point=" + currentCenterPoint + ").", expectedCenterPoint, newCenterPoint);
    }
}
