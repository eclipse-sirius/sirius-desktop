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
package org.eclipse.sirius.tests.swtbot.uml;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;

/**
 * This class test drag'and'drop ports
 * <UL>
 * <LI>from one element to another
 * <UL>
 * <LI>That has hidden edge: testDropPortFromComponentToAnotherComponent().</LI>
 * <LI>That is collapsed: testDropCollapsedPortFromComponentToAnotherComponent()
 * </LI>
 * <LI>That is collapsed and just near a bordered node:
 * testDropCollapsedPortFromComponentToAnotherComponentNearCollapsedPort()</LI>
 * <LI>That is expanded and just near a bordered node:
 * testDropPortFromComponentToAnotherComponentNearCollapsedPort</LI>
 * </UL>
 * </LI>
 * <LI>in the same element (move)
 * <UL>
 * <LI>That is collapsed and just near a bordered node:
 * testMoveCollapsedPortNearCollapsedPort()</LI>
 * <LI>That is expanded and just near a bordered node:
 * testMovePortNearCollapsedPort</LI>
 * </UL>
 * </LI>
 * </UL>
 * 
 * @author lredor
 */
public class UmlPortDragAndDropTest extends AbstractUmlDragAndDropTest {

    private static final String DND_DIAGRAM_NAME = "Component Diagram-DnDPortOnNode";

    private static final String DND_REPRESENTATION_DESCRIPTION_NAME = "Component Diagram-DnDPortOnNode";

    private static final int HORIZONTAL_TRANSLATION = 300;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        editor.setSnapToGrid(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return DND_DIAGRAM_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return DND_REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * Drop port (with hidden edge) from a component to another component.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropPortFromComponentToAnotherComponent_WithHiddenEdge() throws Exception {
        String portToDropName = "Port2";
        final Rectangle originalPortBounds = getEditPartBounds(portToDropName);

        final Point originalCenter = originalPortBounds.getCenter();
        final Point endpoint = originalCenter.getTranslated(HORIZONTAL_TRANSLATION, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        final Rectangle newPortBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newPortBounds.getCenter();

        // The port must have change of container
        assertFalse("The port should have different coordinates.", newCenter.equals(originalCenter));
        assertEquals("The port should be moved at the expected location.", endpoint, newCenter);
    }

    /**
     * Drop port (with straight edge) from a component to another component.
     * Also check the stability of the edge, the last point should be the only
     * one moving.
     */
    public void testDropPortFromComponentToAnotherComponent_WithStraightEdge() {
        String portToDropName = "Port5";
        final Rectangle originalPortBounds = getEditPartBounds(portToDropName);

        PointList originalEdgeBendpoints = getBendpoints(portToDropName, AbstractDiagramBorderNodeEditPart.class);
        List<Point> originalGmfPointsFromSource = getGMFBendpointsFromSource(portToDropName, AbstractDiagramBorderNodeEditPart.class);

        final Point originalCenter = originalPortBounds.getCenter();
        final Point endpoint = originalCenter.getTranslated(-HORIZONTAL_TRANSLATION, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        final Rectangle newPortBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newPortBounds.getCenter();

        // The port must have changed of container
        assertFalse("The port should have different coordinates.", newCenter.equals(originalCenter));

        // Check edge stability
        checkEdgeStability(portToDropName, AbstractDiagramBorderNodeEditPart.class, originalEdgeBendpoints, originalGmfPointsFromSource);
    }

    /**
     * Drop port (with rectilinear edge) from a component to another component.
     * Also check the stability of the edge, the last point should be the only
     * one moving.
     */
    public void testDropPortFromComponentToAnotherComponent_WithRectilinearEdge() {
        String portToDropName = "Port6";
        final Rectangle originalPortBounds = getEditPartBounds(portToDropName);

        PointList originalEdgeBendpoints = getBendpoints(portToDropName, AbstractDiagramBorderNodeEditPart.class);
        List<Point> originalGmfPointsFromSource = getGMFBendpointsFromSource(portToDropName, AbstractDiagramBorderNodeEditPart.class);

        final Point originalCenter = originalPortBounds.getCenter();
        final Point endpoint = originalCenter.getTranslated(-HORIZONTAL_TRANSLATION, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        final Rectangle newPortBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newPortBounds.getCenter();

        // The port must have changed of container
        assertFalse("The port should have different coordinates.", newCenter.equals(originalCenter));

        // Check edge stability
        checkEdgeStability(portToDropName, AbstractDiagramBorderNodeEditPart.class, originalEdgeBendpoints, originalGmfPointsFromSource);
    }

    /**
     * Drop expanded port A from a component to another component near a
     * collapsed port B. The bordered node A should not be created near
     * collapsed bordered node B but at expected locations (like if the
     * collapsed bordered node B is expanded).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropPortFromComponentToAnotherComponentNearCollapsedPort() throws Exception {
        String portToDropName = "Port3";
        String referencePortName = "collapsedPort1";

        final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
        Rectangle referencePortBounds = getEditPartBounds(referencePortName);
        final Point endpoint = referencePortBounds.getCenter().getTranslated(2, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        Rectangle newBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newBounds.getCenter();

        assertFalse("The port should not be moved at the drop location because it is to near from the existing collapsed bordered node.", endpoint.equals(newCenter));
        Rectangle expandedReferencePortBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), referencePortBounds, null);
        assertEquals("The port should be moved near the bordered node as if it is expanded.", expandedReferencePortBounds.x + expandedReferencePortBounds.width + 1, newBounds.x);
    }

    /**
     * Drop expanded port A from a component to another component near a
     * collapsed port B. The bordered node A should not be created near
     * collapsed bordered node B but at expected locations (like if the
     * collapsed bordered node B is expanded).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropPortFromComponentToAnotherComponentNearCollapsedPortWithZoom() throws Exception {
        String portToDropName = "Port3";
        String referencePortName = "collapsedPort1";

        editor.zoom(ZoomLevel.ZOOM_200);

        try {
            final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
            Rectangle referencePortBounds = getEditPartBounds(referencePortName);
            final Point endpoint = referencePortBounds.getCenter().getTranslated(2, 0);

            editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

            Rectangle newBounds = getEditPartBounds(portToDropName);
            final Point newCenter = newBounds.getCenter();

            assertFalse("The port should not be moved at the drop location because it is to near from the existing collapsed bordered node.", endpoint.equals(newCenter));
            // Get bounds in logical (as if the zoom is set to 100%)
            Rectangle newBoundsIn100Percent = editor.getAbsoluteBounds(editor.getEditPart(portToDropName, AbstractDiagramBorderNodeEditPart.class));
            Rectangle referencePortBoundsIn100Percent = editor.getAbsoluteBounds(editor.getEditPart(referencePortName, AbstractDiagramBorderNodeEditPart.class));
            // Check new location
            Rectangle parentBoundsIn100Percent = editor.getAbsoluteBounds(editor.getEditPart("InnerComponent3", IDiagramNodeEditPart.class));
            Rectangle expandedReferencePortBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), referencePortBoundsIn100Percent, parentBoundsIn100Percent);
            assertEquals("The port should be moved near the bordered node as if it is expanded.", expandedReferencePortBounds.x + expandedReferencePortBounds.width + 1, newBoundsIn100Percent.x);
            assertEquals("The port should be moved at the same y axis.", expandedReferencePortBounds.y, newBoundsIn100Percent.y);
        } finally {
            // Go to the origin to avoid scroll bar
            editor.scrollTo(0, 0);
            // Restore the default zoom level
            editor.click(1, 1); // Set the focus on the diagram
            editor.zoom(ZoomLevel.ZOOM_100);
            // Go to the origin to avoid scroll bar
            editor.scrollTo(0, 0);
        }
    }

    /**
     * Drop collapsed port from a component to another component.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropCollapsedPortFromComponentToAnotherComponent() throws Exception {
        String portToDropName = "collapsedPortToMove1";

        final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
        final Point endpoint = originalCenter.getTranslated(HORIZONTAL_TRANSLATION, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        final Point newCenter = getEditPartBounds(portToDropName).getCenter();

        assertEquals("The port should be moved at the expected location.", endpoint, newCenter);
    }

    /**
     * Drop collapsed port A from a component to another component near another
     * collapsed port B. The bordered node A should not be created near
     * collapsed bordered node B but at expected locations (like if the
     * collapsed bordered nodes A and B are expanded).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropCollapsedPortFromComponentToAnotherComponentNearCollapsedPort() throws Exception {
        String portToDropName = "collapsedPortToMove1";
        String referencePortName = "collapsedPort1";

        final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
        Rectangle referencePortBounds = getEditPartBounds(referencePortName);
        final Point endpoint = referencePortBounds.getCenter().getTranslated(2, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        Rectangle newBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newBounds.getCenter();

        assertFalse("The port should not be moved at the drop location because it is to near from the existing collapsed bordered node.", endpoint.equals(newCenter));
        Rectangle expandedReferencePortBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), referencePortBounds, null);
        Rectangle expandedNewBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), newBounds, null);
        assertEquals("The port should be moved near the bordered node as if it is expanded.", expandedReferencePortBounds.x + expandedReferencePortBounds.width + 1, expandedNewBounds.x);
    }

    /**
     * Move collapsed port A in the same component near another collapsed port
     * B. The bordered node A should not be created near collapsed bordered node
     * B but at expected locations (like if the collapsed bordered node B is
     * expanded).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMoveCollapsedPortNearCollapsedPort() throws Exception {
        String portToDropName = "collapsedPortToMove1";
        String referencePortName = "collapsedPort2";

        final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
        Rectangle referencePortBounds = getEditPartBounds(referencePortName);
        final Point endpoint = referencePortBounds.getCenter().getTranslated(2, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        Rectangle newBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newBounds.getCenter();

        assertFalse("The port should not be moved at the drop location because it is to near from the existing collapsed bordered node.", endpoint.equals(newCenter));
        Rectangle expandedReferencePortBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), referencePortBounds, null);
        Rectangle expandedNewBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), newBounds, null);
        assertEquals("The port should be moved near the bordered node as if it is expanded.", expandedReferencePortBounds.x + expandedReferencePortBounds.width + 1, expandedNewBounds.x);
        assertEquals("The port should be moved at the same y axis.", expandedReferencePortBounds.y, expandedNewBounds.y);
    }

    /**
     * Move expanded port A in the same component near a collapsed port B. The
     * bordered node A should not be created near collapsed bordered node B but
     * at expected locations (like if the collapsed bordered node B is
     * expanded).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMovePortNearCollapsedPort() throws Exception {
        String portToDropName = "Port3";
        String referencePortName = "collapsedPort2";
        Rectangle parentBounds = getEditPartBounds("InnerComponent2");
        final Point originalCenter = getEditPartBounds(portToDropName).getCenter();
        Rectangle referencePortBounds = getEditPartBounds(referencePortName);
        final Point endpoint = referencePortBounds.getCenter().getTranslated(2, 0);

        editor.drag(originalCenter.x, originalCenter.y, endpoint.x, endpoint.y);

        Rectangle newBounds = getEditPartBounds(portToDropName);
        final Point newCenter = newBounds.getCenter();

        assertFalse("The port should not be moved at the drop location because it is to near from the existing collapsed bordered node.", endpoint.equals(newCenter));
        Rectangle expandedReferencePortBounds = PortLayoutHelper.getUncollapseCandidateLocation(new Dimension(10, 10), referencePortBounds, parentBounds);
        assertEquals("The port should be moved near the bordered node as if it is expanded.", expandedReferencePortBounds.x + expandedReferencePortBounds.width + 1, newBounds.x);
        assertEquals("The port should be moved at the same y axis.", expandedReferencePortBounds.y, newBounds.y);
    }
}
