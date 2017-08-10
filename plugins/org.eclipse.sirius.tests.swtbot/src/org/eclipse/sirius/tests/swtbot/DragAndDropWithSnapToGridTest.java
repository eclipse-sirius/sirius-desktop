/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.junit.Before;

/**
 * Test drag and drop with snapToGrid.
 * 
 * @author jmallet
 */
public class DragAndDropWithSnapToGridTest extends DragNDropTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        snapToGrid = true;
    }

    @Override
    protected void checkEditPartLocation(IGraphicalEditPart editPart) {
        assertNotNull("No container edit part found with this name", editPart);
        Point location = GraphicalHelper.getAbsoluteBoundsIn100Percent(editPart, true).getLocation();
        boolean isSnapped = (location.x % GRID_STEP) == 0 || (location.y % GRID_STEP) == 0;
        if (isSnapped) {
            // The location must not be the same x or y than its parent
            // otherwise, scrollbar appears in its parent.
            IGraphicalEditPart parentPart = (IGraphicalEditPart) editPart.getParent();
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
            isSnapped = location.x != parentBounds.x && location.y != parentBounds.y;
        }
        assertTrue("For container, the x or y coordinate of the top left corner should be on the grid (grid spacing = " + GRID_STEP + "), but was: " + location + ".", isSnapped);
    }
}
