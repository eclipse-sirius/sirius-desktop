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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class to check the layout of the Edge with path. TODO : Complete this
 * test - First : See TO DO in code to correct the actual code. - Second :
 * Complete the test with the new manualBendpointsForEdgeWithPath2.odesign that
 * include a new use case.
 * 
 * @author lredor
 */
public class EdgeWithPathLayoutTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "manualBendpoints";

    private static final String REPRESENTATION_NAME = "TestTicket2094";

    private static final String MODEL = "manualBendpointsForEdgeWithPath.ecore";

    private static final String SESSION_FILE = "manualBendpointsForEdgeWithPath.aird";

    private static final String VSM_FILE = "manualBendpointsForEdgeWithPath.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/edgeWithPath/";

    private static final String FILE_DIR = "/";

    private static final String FIRST_CLASS_NAME = "C1";

    private static final String SECOND_CLASS_NAME = "C2";

    private static final String FOURTH_CLASS_NAME = "C4";

    private static final int NB_POINTS_EDGE_C4_TO_C1 = 4;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

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
     * @throws Exception
     */
    public void _testConservationOfManualBendpoints() throws Exception {
        // Refresh manual or auto ?
        // Check the points size of the edge (only point of path)
        final SWTBotGefEditPart sourceEditPart = editor.getEditPart(FOURTH_CLASS_NAME, DNodeContainerEditPart.class);
        assertEquals("Bad number outgoing edges for " + FOURTH_CLASS_NAME, 2, sourceEditPart.sourceConnections().size());
        final SWTBotGefEditPart targetEditPart = editor.getEditPart(FIRST_CLASS_NAME, DNodeContainerEditPart.class);
        SWTBotGefConnectionEditPart wirePart = sourceEditPart.sourceConnections().get(0);
        if (!wirePart.target().equals(targetEditPart)) {
            wirePart = sourceEditPart.sourceConnections().get(1);
        }
        // Edge edge = (Edge) wirePart.part().getModel();
        ConnectionEditPart connectionEditPart = wirePart.part();
        assertTrue("The figure is not a ViewEdgeFigure.", connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigure = (ViewEdgeFigure) connectionEditPart.getFigure();
        assertEquals("Bad number of points before creation of new bendpoint", NB_POINTS_EDGE_C4_TO_C1, connectionFigure.getPoints().size());
        // Create a new bendpoint on the edge
        Point startMove = connectionFigure.getPoints().getMidpoint().getCopy();
        editor.click(startMove.x, startMove.y);
        // We need to wait element selection before proceed or drag will fail
        SWTBotUtils.waitAllUiEvents();
        assertTrue("Bad selected connection.", connectionEditPart.equals(getSelectedConnection()));
        // TODO : The drag'n'drop is KO, see Mariot for more details (possible
        // debug ConnectionBendpointEditPolicy or
        // TreeConnectionBendpointEditPolicy)
        editor.drag(startMove.x, startMove.y, startMove.x + 10, startMove.y + 10);
        /* we need to wait the drag operates */
        SWTBotUtils.waitAllUiEvents();
        // Check the points size of the edge (points of path + one new point)
        assertEquals("Bad number of points after creation of new bendpoint", NB_POINTS_EDGE_C4_TO_C1 + 1, ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().size());
        // Move a node from the path of the edge (that must reinitialize the
        // edge points)
        editor.drag(SECOND_CLASS_NAME, 600, 150);
        SWTBotUtils.waitAllUiEvents();
        // Check the points size of the edge (only point of path)
        assertEquals("Bad number of points after moving a node from the path. That must reinitialize the edge points.", NB_POINTS_EDGE_C4_TO_C1, connectionFigure.getPoints().size());
        // Create a new bendpoint on the edge
        startMove = connectionFigure.getPoints().getMidpoint().getCopy();
        editor.click(startMove.x, startMove.y);
        // We need to wait element selection before proceed or drag will fail
        SWTBotUtils.waitAllUiEvents();
        assertTrue("Bad selected connection.", connectionEditPart.equals(getSelectedConnection()));
        editor.drag(startMove.x, startMove.y, startMove.x + 10, startMove.y + 10);
        /* we need to wait the drag operates */
        SWTBotUtils.waitAllUiEvents();
        // Check the points size of the edge (points of path + one new point)
        assertEquals("Bad number of points after creation of new bendpoint", NB_POINTS_EDGE_C4_TO_C1 + 1, ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().size());
        // Rename a node from the path of the edge (that must reinitialize the
        // edge points)
        final SWTBotGefEditPart secondEditPart = editor.getEditPart(SECOND_CLASS_NAME, DNodeContainerEditPart.class);
        Node node = (Node) secondEditPart.part().getModel();
        DNode dNode = (DNode) node.getElement();
        final EClass secondClass = (EClass) dNode.getTarget();
        final TransactionalEditingDomain editingDomain = localSession.getOpenedSession().getTransactionalEditingDomain();
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                secondClass.setName("C2Modified");
            }
        });
        // Check the points size of the edge (only point of path)
        assertEquals("Bad number of points after renaming a node from the path. That must reinitialize the edge points.", NB_POINTS_EDGE_C4_TO_C1, connectionFigure.getPoints().size());
    }

    protected ConnectionEditPart getSelectedConnection() {
        ISelection selection = editor.getSelection();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof ConnectionEditPart) {
                return (ConnectionEditPart) structuredSelection.getFirstElement();
            }
        }
        return null;
    }
}
