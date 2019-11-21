/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;

import com.google.common.collect.Iterables;

/**
 * Test that checks the visibility of edges on operand figure when the reference point are located on the edges of the
 * figure. (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=553321)
 * 
 * @author <a href="mailto:arthur.daussy@obeo.fr">Arthur Daussy</a>
 *
 */
public class OperandFigureAndEdgesTest extends AbstractSequenceSiriusDiagramTests {
    private static final String DIAG_NAME = "Sequence Diagram on ";

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "operandFigure/";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "MyInteractions.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return null;
    }

    @Override
    protected String getSessionModel() {
        return "representations.aird";
    }

    @Override
    protected String getRepresentationId() {
        return "_6RnvcAt9Eeqh59JiJQjCPw";
    }

    /**
     * Tests that a note attachment linked to an operand is displayed even if its reference points are located on the
     * right edge of the figure. In the following test case, the model is built so the reference point of the target of
     * the connection is located to the right border of the operand figure. Even in that case, the connection figure
     * should be displayed.
     */
    public void testNoteAttachmentIsVisible() {
        openSequenceDiagramOfType(DIAG_NAME, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        Iterable<DDiagramElement> operands = Iterables.filter(sequenceDDiagram.getDiagramElements(), Operand.viewpointElementPredicate());

        // There is only one operand
        assertEquals(1, Iterables.size(operands));
        DDiagramElement operandNode = operands.iterator().next();

        IGraphicalEditPart operandEditPart = getEditPart(operandNode);

        TestsUtil.synchronizationWithUIThread();

        // Get the note attachment edit part (by construct we know that there is only one connection which the note
        // attachment)
        List<ConnectionEditPart> connectionEditParts = getConnections(diagramView, diagramEditPart.getViewer(), operandEditPart);
        assertEquals(1, connectionEditParts.size());
        ConnectionNodeEditPart cep = (ConnectionNodeEditPart) connectionEditParts.get(0);
        assertTrue(cep instanceof NoteAttachmentEditPart);

        // Checks that the target ref point is indeed on the right border of the figure
        IFigure operandFigure = operandEditPart.getFigure();
        Rectangle bounds = operandFigure.getBounds().getCopy();
        operandFigure.translateToAbsolute(bounds);

        Point targetRefPoint = getRefPoint(cep);
        assertEquals(bounds.getBottomRight().x(), targetRefPoint.x());

        // The original code would test that...
        assertFalse(bounds.contains(targetRefPoint.x(), targetRefPoint.y()));// And so hide the figure

        // Instead we want the figure to be visible in that case
        assertTrue(cep.getFigure().isVisible());
    }

    private Point getRefPoint(ConnectionNodeEditPart cep) {

        Connection connection = (Connection) cep.getFigure();

        IGraphicalEditPart target = (IGraphicalEditPart) cep.getTarget();
        // ShapeCompartmentEditPart tContainer = getOwningShapeCompartment(target);

        ConnectionAnchor tc = ((NodeEditPart) target).getTargetConnectionAnchor(cep);
        List<?> bendpoints = (List<?>) connection.getConnectionRouter().getConstraint(connection);
        // Get the target ref point
        Point tRefPoint = ((Bendpoint) bendpoints.get(bendpoints.size() - 1)).getLocation().getCopy();
        connection.translateToAbsolute(tRefPoint);
        return tc.getLocation(tRefPoint);
    }

    /**
     * Gets the {@link ConnectionEditPart}s targeting a specific edit part
     * 
     * @param diagram
     *            the diagram holding the edges
     * @param viewer
     *            the editpart viewer
     * @param target
     *            the target edit part
     * @return a collection of {@link ConnectionEditPart}s
     */
    // Code inspired from
    // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr.getConnectionNodes(ShapeCompartmentEditPart)
    private List<ConnectionEditPart> getConnections(Diagram diagram, EditPartViewer viewer, EditPart target) {
        Map<?, ?> registry = viewer.getEditPartRegistry();
        List<?> edges = diagram.getEdges();
        Iterator<?> edgesIterator = edges.iterator();

        List<ConnectionEditPart> result = new ArrayList<ConnectionEditPart>();
        while (edgesIterator.hasNext()) {
            Edge edge = (Edge) edgesIterator.next();
            EditPart endPoint = (EditPart) registry.get(edge.getTarget());
            if (isChildOf(target, endPoint)) {
                Object cep = registry.get(edge);
                if (cep instanceof ConnectionEditPart) {
                    result.add((ConnectionEditPart) cep);
                }
            }
        }

        return result;
    }

    // Code copied from
    // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr.getConnectionNodes(ShapeCompartmentEditPart)
    private boolean isChildOf(EditPart parent, EditPart child) {
        EditPart walker = child;
        while (walker != null && walker != parent) {
            walker = walker.getParent();
        }
        return walker != null;
    }

}
