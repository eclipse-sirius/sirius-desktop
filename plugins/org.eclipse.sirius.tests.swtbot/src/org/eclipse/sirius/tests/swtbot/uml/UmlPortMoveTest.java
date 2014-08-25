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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry.PointAround;

/**
 * This class test moving and drag'and'drop ports.
 * 
 * @author dlecan
 * @author lredor
 */
public class UmlPortMoveTest extends AbstractUmlDragAndDropTest {

    private static final String DND_DIAGRAM_NAME = "DnD Component Diagram";

    private static final String DND_REPRESENTATION_NAME = "Component Diagram";

    private static final String MOVE_DIAGRAM_NAME_PREFIX = "Move ";

    private static final String RESIZE_DIAGRAM_NAME_PREFIX = "Resize ";

    private static final String MOVE_REPRESENTATION_PORT_ON_CONTAINER_NAME = "Component Diagram-MovePortOnContainer";

    private static final String MOVE_REPRESENTATION_PORT_ON_CONTAINER_IN_CONTAINER_NAME = "Component Diagram-MovePortOnContainerInContainer";

    private static final String MOVE_REPRESENTATION_PORT_ON_NODE_NAME = "Component Diagram-MovePortOnNode";

    private static final String MOVE_REPRESENTATION_PORT_ON_NODE_IN_CONTAINER_NAME = "Component Diagram-MovePortOnNodeInContainer";

    private static final String MOVE_REPRESENTATION_PORT_ON_PORT_ON_CONTAINER_NAME = "Component Diagram-MovePortOnPortOnContainer";

    private static final String MOVE_REPRESENTATION_PORT_ON_PORT_ON_NODE_NAME = "Component Diagram-MovePortOnPortOnNode";

    private static final String RESIZE_REPRESENTATION_PORT_ON_CONTAINER_NAME = "Component Diagram-ResizePortOnContainer";

    private static final String RESIZE_REPRESENTATION_PORT_ON_CONTAINER_IN_CONTAINER_NAME = "Component Diagram-ResizePortOnContainerInContainer";

    private static final String RESIZE_REPRESENTATION_PORT_ON_NODE_NAME = "Component Diagram-ResizePortOnNode";

    private static final String RESIZE_REPRESENTATION_PORT_ON_NODE_IN_CONTAINER_NAME = "Component Diagram-ResizePortOnNodeInContainer";

    private static final String RESIZE_REPRESENTATION_PORT_ON_PORT_ON_CONTAINER_NAME = "Component Diagram-ResizePortOnPortOnContainer";

    private static final String RESIZE_REPRESENTATION_PORT_ON_PORT_ON_NODE_NAME = "Component Diagram-ResizePortOnPortOnNode";

    private static final String DROP_PORT_NAME = "DropPort";

    private static final Dimension VERTICAL_TRANSLATION = new Dimension(0, 20);

    private static final Dimension HORIZONTAL_TRANSLATION = new Dimension(30, 0);

    private static final Point POINT_TO_DRAG = new Point(222, 70);

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
        return DND_REPRESENTATION_NAME;
    }

    /**
     * Drop port from a component to another component.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDropPortFromComponentToAnotherComponent() throws Exception {
        final Rectangle originalPortBounds = getEditPartBounds(DROP_PORT_NAME);

        final Point originalCenter = originalPortBounds.getCenter();
        final Point endpoint = POINT_TO_DRAG.getTranslated(HORIZONTAL_TRANSLATION);

        editor.drag(POINT_TO_DRAG.x, POINT_TO_DRAG.y, endpoint.x, endpoint.y);

        final Rectangle newPortBounds = getEditPartBounds(DROP_PORT_NAME);
        final Point newCenter = newPortBounds.getCenter();

        // We made an horizontal DnD, new center should be at EAST position
        assertThat("x coord. of Port is not at expected position", newCenter.x, greaterThan(originalCenter.getTranslated(HORIZONTAL_TRANSLATION).x));

        // We made an horizontal translation, y shouldn't have changed (in fact,
        // between "originalCenter.y" and "originalCenter.y + 5"
        assertThat("y coord. of Port is not at expected position", newCenter.y, allOf(greaterThanOrEqualTo(originalCenter.y), lessThanOrEqualTo(originalCenter.y + 5)));

        editor.select(editor.mainEditPart());
        editor.refresh();

        final Rectangle newPortBoundsAfterRefresh = getEditPartBounds(DROP_PORT_NAME);
        final Point newCenterAfterRefresh = newPortBoundsAfterRefresh.getCenter();

        // We made an horizontal DnD, new center should be at EAST position
        assertThat("x coord. of Port is not at expected position after refresh", newCenterAfterRefresh.x, greaterThan(originalCenter.getTranslated(HORIZONTAL_TRANSLATION).x));

        // We made an horizontal translation, y shouldn't have changed (in fact,
        // between "originalCenter.y" and "originalCenter.y + 5"
        assertThat("y coord. of Port is not at expected position after refresh", newCenterAfterRefresh.y, allOf(greaterThanOrEqualTo(originalCenter.y), lessThanOrEqualTo(originalCenter.y + 5)));
    }

    /**
     * Drop port from a component to another component.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMovePortWithoutDrop() throws Exception {
        final ZoomLevel zoom = ZoomLevel.ZOOM_100;

        movePortWithoutDrop(zoom);
    }

    /**
     * Drop port from a component to another component.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMovePortWithoutDropAfterZoom() throws Exception {
        final ZoomLevel zoom = ZoomLevel.ZOOM_200;

        movePortWithoutDrop(zoom);
    }

    private void movePortWithoutDrop(final ZoomLevel zoom) {
        editor.zoom(zoom);
        editor.scrollTo(0, 0);

        final Point pointToDrag = POINT_TO_DRAG.getScaled(zoom.getAmount());
        final Dimension translation = VERTICAL_TRANSLATION.getScaled(zoom.getAmount());

        final Rectangle originalPortBounds = getEditPartBounds(DROP_PORT_NAME);
        assertThat("Point where you want to select port is not contain in port itself", originalPortBounds.contains(pointToDrag), is(true));

        final Point originalTopRight = originalPortBounds.getTopRight();
        final Point endpoint = pointToDrag.getTranslated(translation);

        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);

        final Rectangle newPortBounds = getEditPartBounds(DROP_PORT_NAME);

        assertThat("Port is not at expected position (it may have changed of container)", newPortBounds.getTopRight(), PointAround.around(originalTopRight.getTranslated(translation), 5));
    }

    /**
     * Move a port on a container by selecting a border of it. Test if port is
     * moved and not resized.
     */
    public void testMovePortOnContainer() {
        testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_CONTAINER_NAME, true);
    }

    /**
     * Move a port on a container in a container by selecting a border of it.
     * Test if port is moved and not resized.
     */
    public void testMovePortOnContainerInContainer() {
        testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_CONTAINER_IN_CONTAINER_NAME, true);
    }

    /**
     * Move a port on a node by selecting a border of it. Test if port is moved
     * and not resized.
     */
    public void testMovePortOnNode() {
        // TODO LRE Pb on hudson with this test...
        // testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_NODE_NAME, true);
    }

    /**
     * Move a port on a node in a container by selecting a border of it. Test if
     * port is moved and not resized.
     */
    public void testMovePortOnNodeInContainer() {
        testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_NODE_IN_CONTAINER_NAME, true);
    }

    /**
     * Move a port on a port on a container by selecting a border of it. Test if
     * port is moved and not resized.
     */
    public void testMovePortOnPortOnContainer() {
        testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_PORT_ON_CONTAINER_NAME, true);
    }

    /**
     * Move a port on a port on a node by selecting a border of it. Test if port
     * is moved and not resized.
     */
    public void testMovePortOnPortOnNode() {
        testResizeOrMovePort(MOVE_REPRESENTATION_PORT_ON_PORT_ON_NODE_NAME, true);
    }

    /**
     * Resize a port on a container by selecting a border of it. Test if port is
     * resized and not moved.
     */
    public void testResizePortOnContainer() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_CONTAINER_NAME, false);
    }

    /**
     * Resize a port on a container in a container by selecting a border of it.
     * Test if port is resized and not moved.
     */
    public void testResizePortOnContainerInContainer() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_CONTAINER_IN_CONTAINER_NAME, false);
    }

    /**
     * Resize a port on a node by selecting a border of it. Test if port is
     * moved and not resized.
     */
    public void testResizePortOnNode() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_NODE_NAME, false);
    }

    /**
     * Resize a port on a node in a container by selecting a border of it. Test
     * if port is resized and not moved.
     */
    public void testResizePortOnNodeInContainer() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_NODE_IN_CONTAINER_NAME, false);
    }

    /**
     * Resize a port on a port on a container by selecting a border of it. Test
     * if port is resized and not moved.
     */
    public void testResizePortOnPortOnContainer() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_PORT_ON_CONTAINER_NAME, false);
    }

    /**
     * Resize a port on a port on a node by selecting a border of it. Test if
     * port is resized and not moved.
     */
    public void testResizePortOnPortOnNode() {
        testResizeOrMovePort(RESIZE_REPRESENTATION_PORT_ON_PORT_ON_NODE_NAME, false);
    }

    /**
     * Move or resize a port by selecting a border of it. Test if port is moved
     * and not resized.
     * 
     * @param representationName
     *            The name of the representation to open
     * @param checkMoving
     *            true if the port must move, false otherwise (resize)
     */
    private void testResizeOrMovePort(String representationName, boolean checkMoving) {
        editor = openAndGetEditor(representationName, representationName);

        // Get the bottom center coordinates of the port
        final Rectangle originalPortBounds = getEditPartBounds(DROP_PORT_NAME);

        // if move, click the center of the port, else click between the center
        // and the bottom.
        int relativeClickedY = checkMoving ? -originalPortBounds.height / 2 : -1;

        // clicked point, drag start
        final Point pointToDrag = originalPortBounds.getBottom().getTranslated(0, relativeClickedY);

        // Select the port
        editor.select(editor.getEditPart(DROP_PORT_NAME, AbstractDiagramBorderNodeEditPart.class));
        // Compute the drop destination
        final Point endpoint = pointToDrag.getTranslated(VERTICAL_TRANSLATION);
        // Drag'and'drop with the mouse
        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        // Get the new bounds and compare with the expected
        final Rectangle newPortBounds = getEditPartBounds(DROP_PORT_NAME);
        if (checkMoving) {
            assertThat("Port is not at expected position (probably not moved but resized)", newPortBounds.getTopRight(),
                    PointAround.around(originalPortBounds.getTopRight().getTranslated(VERTICAL_TRANSLATION), 5));
        } else {
            assertThat("Port is not at expected position (probably not moved but resized)", newPortBounds.getTopRight(), PointAround.around(originalPortBounds.getTopRight(), 0));
        }
    }
}
