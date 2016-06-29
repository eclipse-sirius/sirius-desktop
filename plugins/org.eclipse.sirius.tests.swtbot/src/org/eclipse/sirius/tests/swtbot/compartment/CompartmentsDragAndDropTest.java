/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.business.api.session.SessionStatus;

/**
 * Tests ensure that elements are drag and drop in compartments or in diagram as
 * expected (by conserving their sizes).
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class CompartmentsDragAndDropTest extends AbstractCompartmentTest {

    /**
     * GMF bounds of the region to drag and drop in a VStack or HStack
     * container.
     */
    private static final Rectangle REGION_BOUNDS_GMF = new Rectangle(118, 30, -1, -1);

    /** Draw2D bounds of the region to drag and drop in a VStack container. */
    private static final Rectangle REGION_BOUNDS_DRAW2D_VSTACK = new Rectangle(118, 30, 173, 135);

    /**
     * Draw2D bounds auto-sized of the region to drag and drop in a container.
     */
    private static final Rectangle REGION_BOUNDS_DRAW2D_AUTO_SIZED = new Rectangle(118, 30, -1, -1);

    /** Draw2D bounds of the region to drag and drop in a HStack container. */
    private static final Rectangle REGION_BOUNDS_DRAW2D_HSTACK = new Rectangle(118, 30, 108, 76);

    /** Point used to drag region from HStack container to the diagram. */
    private static final Point HSTACK_DRAG_POINT = new Point(700, 80);

    /** Point used to drag region from VStack container to the diagram. */
    private static final Point VSTACK_DRAG_POINT = new Point(700, 220);

    /** Point used to drop region into the diagram. */
    private static final Point DROP_POINT = new Point(1000, 500);

    private String oldFont;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        oldFont = changeDefaultFontName("Comic Sans MS");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * Test to ensure that elements are drag from container with vertical stack
     * and drop into diagram with the same size. The reverse scenario is also
     * tested. Check specific size for region after drag and drop.
     */
    public void testDnDOfVerticalRegionFromContainerToDiag() {
        openRepresentation(VERTICAL_STACK_DND_REPRESENTATION_NAME, VERTICAL_STACK_DND_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check that the existing region is as expected (a delta of 1 is
        // tolerate for height because of font problem in such OS)
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_GMF, REGION_BOUNDS_DRAW2D_VSTACK, false, 0, 1);

        // Drag and Drop from region to diagram
        editor.drag(VSTACK_DRAG_POINT, DROP_POINT);

        // Check that the DnD region keeps its size.
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_DRAW2D_AUTO_SIZED, REGION_BOUNDS_DRAW2D_VSTACK, true, 0, 1);

        // Drag and Drop from diagram to region
        editor.drag(DROP_POINT, VSTACK_DRAG_POINT);

        // Check that the DnD region keeps its size.
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_DRAW2D_AUTO_SIZED, REGION_BOUNDS_DRAW2D_VSTACK, true, 0, 1);
    }

    /**
     * Test to ensure that elements are drag from container with horizontal
     * stack and drop into diagram with the same size. The reverse scenario is
     * also tested. Check specific size for region after drag and drop.
     */
    public void testDnDOfHorizontalRegionFromContainerToDiag() {
        openRepresentation(HORIZONTAL_STACK_DND_REPRESENTATION_NAME, HORIZONTAL_STACK_DND_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check that the existing region is as expected (a delta of 1 is
        // tolerate for height because of font problem in such OS)
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_GMF, REGION_BOUNDS_DRAW2D_HSTACK, false, 0, 1);

        // Drag and Drop from region to diagram
        editor.drag(HSTACK_DRAG_POINT, DROP_POINT);

        // Check that the DnD region keeps its size.
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_DRAW2D_AUTO_SIZED, REGION_BOUNDS_DRAW2D_HSTACK, true, 0, 1);

        // Drag and Drop from diagram to region
        editor.drag(DROP_POINT, HSTACK_DRAG_POINT);

        // Check that the DnD region keeps its size.
        checkBounds(FIRST_REGION_NAME, REGION_BOUNDS_DRAW2D_AUTO_SIZED, REGION_BOUNDS_DRAW2D_HSTACK, true, 0, 1);
    }

}
