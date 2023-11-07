/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;

/**
 * Validate the stability of border labels on nodes.
 * 
 * @author smonnier
 */
public class NodeBorderLabelPositionStabilityTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new tc2216";

    private static final String REPRESENTATION_NAME = "tc2216";

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String LONG_LABEL = "loooooooooooong name is very long";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Validate the stability of the label of a DNodeEditPart.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNodeEditPartLabelStability() throws Exception {
        editor.maximize();
        try {
            validateLabelStability("DNEP_" + LONG_LABEL, AbstractDiagramNodeEditPart.class);
        } finally {
            editor.restore();
        }
    }

    /**
     * Validate the stability of the label of a DNode2EditPart.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNode2EditPartLabelStability() throws Exception {
        editor.maximize();
        try {
            validateLabelStability("DN2EP_" + LONG_LABEL, AbstractDiagramBorderNodeEditPart.class);
        } finally {
            editor.restore();
        }

    }

    /**
     * Validate the stability of the label of a DNode3EditPart.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNode3EditPartLabelStability() throws Exception {
        editor.maximize();
        try {
            validateLabelStability("DN3EP_" + LONG_LABEL, AbstractDiagramNodeEditPart.class);
        } finally {
            editor.restore();
        }
    }

    /**
     * Validate the stability of the label of a DNode4EditPart.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDNode4EditPartLabelStability() throws Exception {
        editor.maximize();
        try {
            validateLabelStability("DN4EP_" + LONG_LABEL, AbstractDiagramBorderNodeEditPart.class);
        } finally {
            editor.restore();
        }
    }

    /**
     * Move the label on each side of its parent node and validate the
     * positions.
     * 
     * @throws Exception
     *             Test error.
     */
    private void validateLabelStability(String label, Class<? extends EditPart> editPartClass) throws Exception {

        Point dnepUpperLeftLocation = editor.getLocation(label, editPartClass);
        Dimension dnepDimension = editor.getDimension(label, editPartClass);

        Point labelUpperLeftLocation = editor.getLocation(label, AbstractDiagramNameEditPart.class);
        Dimension labelDimension = editor.getDimension(label, AbstractDiagramNameEditPart.class);
        Point labelCenterLocation = new Point(labelUpperLeftLocation.x + labelDimension.width / 2, labelUpperLeftLocation.y + labelDimension.height / 2);

        int horizontalWidthDifference = labelDimension.width - dnepDimension.width;

        // Move label to south side of the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, label, AbstractDiagramNameEditPart.class);
        editor.click(labelCenterLocation);
        bot.waitUntil(cS);
        editor.drag(labelCenterLocation, dnepUpperLeftLocation.x + dnepDimension.width / 2, dnepUpperLeftLocation.y + dnepDimension.height);

        // Update label location and validate position
        labelUpperLeftLocation = editor.getLocation(label, AbstractDiagramNameEditPart.class);
        labelCenterLocation = new Point(labelUpperLeftLocation.x + labelDimension.width / 2, labelUpperLeftLocation.y + labelDimension.height / 2);
        assertEquals("The label is not at the expected X position", labelUpperLeftLocation.x, dnepUpperLeftLocation.x - horizontalWidthDifference / 2, 5);
        assertEquals("The label is not at the expected Y position", labelUpperLeftLocation.y, dnepUpperLeftLocation.y + dnepDimension.height, 5);

        // Move label to west side of the node
        editor.drag(labelCenterLocation, dnepUpperLeftLocation.x, dnepUpperLeftLocation.y + dnepDimension.height / 2);

        // Update label location and validate position
        labelUpperLeftLocation = editor.getLocation(label, AbstractDiagramNameEditPart.class);
        labelCenterLocation = new Point(labelUpperLeftLocation.x + labelDimension.width / 2, labelUpperLeftLocation.y + labelDimension.height / 2);
        assertEquals("The label is not at the expected X position", labelUpperLeftLocation.x + labelDimension.width, dnepUpperLeftLocation.x, 5);
        assertEquals("The label is not at the expected Y position", labelCenterLocation.y, dnepUpperLeftLocation.y + dnepDimension.height / 2, 5);

        // Move label to north side of the node
        editor.drag(labelCenterLocation, dnepUpperLeftLocation.x + dnepDimension.width / 2, dnepUpperLeftLocation.y);

        // Update label location and validate position
        labelUpperLeftLocation = editor.getLocation(label, AbstractDiagramNameEditPart.class);
        labelCenterLocation = new Point(labelUpperLeftLocation.x + labelDimension.width / 2, labelUpperLeftLocation.y + labelDimension.height / 2);
        assertEquals("The label is not at the expected X position", labelUpperLeftLocation.x, dnepUpperLeftLocation.x - horizontalWidthDifference / 2, 5);
        assertEquals("The label is not at the expected Y position", labelUpperLeftLocation.y + labelDimension.height, dnepUpperLeftLocation.y, 5);

        // Move label to east side of the node
        editor.drag(labelCenterLocation, dnepUpperLeftLocation.x + dnepDimension.width, dnepUpperLeftLocation.y + dnepDimension.height / 2);

        // Update label location and validate position
        labelUpperLeftLocation = editor.getLocation(label, AbstractDiagramNameEditPart.class);
        labelCenterLocation = new Point(labelUpperLeftLocation.x + labelDimension.width / 2, labelUpperLeftLocation.y + labelDimension.height / 2);
        assertEquals("The label is not at the expected X position", labelUpperLeftLocation.x, dnepUpperLeftLocation.x + dnepDimension.width, 5);
        assertEquals("The label is not at the expected Y position", labelCenterLocation.y, dnepUpperLeftLocation.y + dnepDimension.height / 2, 5);
    }

}
