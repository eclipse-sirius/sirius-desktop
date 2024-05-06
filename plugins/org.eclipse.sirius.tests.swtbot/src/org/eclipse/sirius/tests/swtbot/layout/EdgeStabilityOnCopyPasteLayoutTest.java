/*******************************************************************************
 * Copyright (c) 2014, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Check the stability of the edges during a "Copy/Paste layout" operation.
 *
 * See bugzilla 444734 for more details.
 *
 */
public class EdgeStabilityOnCopyPasteLayoutTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/copyPaste/bugzilla-444734/";

    private static final String SESSION_FILE = "testReconnect.aird";

    private static final String MODEL_FILE = "testReconnect.ecore";

    private static final String VSM_FILE = "testReconnect.odesign";

    private static final String DIAGRAM_INSTANCE_TO_COPY = "toCopy";

    private static final String DIAGRAM_INSTANCE_TO_PASTE = "toPaste";

    private static final String DIAGRAM_INSTANCE_COPY_PASTE = "copyPaste";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "testReconnect";

    private static final String ECLASS1 = "eClass1";

    private static final String ECLASS2 = "eClass2";

    private static final String ECLASS3 = "eClass3";

    private static final String ECLASS4 = "eClass4";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SESSION_FILE, MODEL_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    @Override
    protected void tearDown() throws Exception {
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * Copy and paste the layout and check the stability of the edges
     */
    public void testEdgeStability() {

        // Open the 2 representations
        SWTBotSiriusDiagramEditor diagramToPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, DIAGRAM_INSTANCE_TO_PASTE,
                DDiagram.class);
        SWTBotSiriusDiagramEditor diagramToCopy = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, DIAGRAM_INSTANCE_TO_COPY,
                DDiagram.class);
        try {
            // Step 1: copy the layout of the first diagram
            diagramToCopy.show();
            diagramToCopy.click(new Point(1, 1));
            diagramToCopy.clickContextMenu(Messages.CopyFormatAction_text);

            // Step 2: paste the layout on the second diagram
            diagramToPaste.show();
            diagramToPaste.click(new Point(450, 100));
            diagramToPaste.clickContextMenu(Messages.PasteLayoutAction_text);

            // Step 3: check the stability of the edges
            checkEdgeStability(ECLASS1, ECLASS3, getBendpoints(diagramToCopy, ECLASS1, ECLASS3), getBendpoints(diagramToPaste, ECLASS1, ECLASS3));
            checkEdgeStability(ECLASS2, ECLASS4, getBendpoints(diagramToCopy, ECLASS2, ECLASS4), getBendpoints(diagramToPaste, ECLASS2, ECLASS4));
        } finally {
            diagramToPaste.close();
            diagramToCopy.close();
        }
    }

    /**
     * Copy and paste the layout on the same diagram and check the statibility
     * of the centered edges with a rectilinear routing style.
     */
    public void testEdgeStabilityOnTheSameDiagram() {

        SWTBotSiriusDiagramEditor diagramToCopyPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, DIAGRAM_INSTANCE_COPY_PASTE,
                DDiagram.class);
        try {
            // Step 1: copy the diagram layout
            diagramToCopyPaste.show();

            PointList originalPointList1 = getBendpoints(diagramToCopyPaste, ECLASS1, ECLASS3).getCopy();
            PointList originalPointList2 = getBendpoints(diagramToCopyPaste, ECLASS2, ECLASS4).getCopy();

            diagramToCopyPaste.click(new Point(1, 1));
            diagramToCopyPaste.clickContextMenu(Messages.CopyFormatAction_text);

            // Step 2: paste the layout on the same diagram
            diagramToCopyPaste.click(new Point(1, 1));
            diagramToCopyPaste.clickContextMenu(Messages.PasteLayoutAction_text);

            // Step 3: check the stability of the edges
            checkEdgeStability(ECLASS1, ECLASS3, originalPointList1, getBendpoints(diagramToCopyPaste, ECLASS1, ECLASS3));
            checkEdgeStability(ECLASS2, ECLASS4, originalPointList2, getBendpoints(diagramToCopyPaste, ECLASS2, ECLASS4));
        } finally {
            diagramToCopyPaste.close();
        }
    }

    /**
     * Check the stability of the edge.
     * 
     * @param sourceEditPartName
     *            source edit part name of the edge
     * @param targetEditPartName
     *            target edit part name of the edge
     * @param expectedPointList
     *            the expected point list.
     * @param actualPointList
     *            the current point list to compare with expectedPointList.
     */
    private void checkEdgeStability(String sourceEditPartName, String targetEditPartName, PointList expectedPointList, PointList actualPointList) {

        assertEquals("The number of points of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", expectedPointList.size(), actualPointList.size());

        for (int i = 0; i < expectedPointList.size(); i++) {
            Point originalPoint = expectedPointList.getPoint(i);
            Point newPoint = actualPointList.getPoint(i);
            assertEquals("X position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", originalPoint.x, newPoint.x, 1);
            assertEquals("Y position of point number " + i + " of connection between " + sourceEditPartName + " and " + targetEditPartName + " is invalid.", originalPoint.y, newPoint.y, 1);
        }
    }

    /**
     * Get bendpoints of connection between <code>sourceEditPartName</code> and
     * <code>targetEditPartName</code>.
     * 
     * @param diagram
     *            the diagram in which the edit parts are searched.
     * @param sourceEditPartName
     *            source edit part name of the connection
     * @param targetEditPartName
     *            target edit part name of the connection
     * @return bendpoints of the connection
     */
    private PointList getBendpoints(SWTBotSiriusDiagramEditor diagram, String sourceEditPartName, String targetEditPartName) {
        SWTBotGefEditPart sourceEditPart = diagram.getEditPart(sourceEditPartName, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart targetEditPart = diagram.getEditPart(targetEditPartName, AbstractDiagramBorderNodeEditPart.class);
        List<SWTBotGefConnectionEditPart> connectionEditPartList = diagram.getConnectionEditPart(sourceEditPart, targetEditPart);
        ConnectionEditPart connectionEditPart = connectionEditPartList.get(0).part();
        return ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints();
    }

}
