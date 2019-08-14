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
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
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
     * This new feature is optional. With the option activated, the label must be moved as the "user" want (check if
     * after a move of a label to the left, it is really moved).
     */
    public void testMoveLabelOnBorderWithOptionEnabled() {
        testMoveLabelOnBorder(true);
    }

    /**
     * This new feature is optional. With the option deactivated, the label must remain centered even if the "user"
     * moves it (check if after a move of the label, it remains centered).
     */
    public void testMoveLabelOnBorderWithOptionDisabled() {
        testMoveLabelOnBorder(false);
    }

    /**
     * Check if after a move of a label to the left, it is really moved.
     * 
     * @param enableLabelOnBorderImprovment
     *            true to enable option, false otherwise
     */
    public void testMoveLabelOnBorder(boolean enableLabelOnBorderImprovment) {
        // Store old vm arg value of this optional feature
        Boolean oldVMArgValue = Boolean.getBoolean(DBorderItemLocator.LABEL_ON_BORDER_IMPROVMENT);
        try {
            // Set to true the VM arg value of this optional feature
            System.setProperty(DBorderItemLocator.LABEL_ON_BORDER_IMPROVMENT, Boolean.toString(enableLabelOnBorderImprovment));
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
            if (!enableLabelOnBorderImprovment) {
                expectedCenterPoint = currentCenterPoint;
            }
            assertEquals("The label has not be moved as expected (original point=" + currentCenterPoint + ").", expectedCenterPoint, newCenterPoint);
        } finally {
            // Reset to previous value the VM arg value of this optional feature
            System.setProperty(DBorderItemLocator.LABEL_ON_BORDER_IMPROVMENT, Boolean.toString(oldVMArgValue));
        }
    }
}
