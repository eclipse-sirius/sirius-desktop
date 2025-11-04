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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

/**
 * Same tests as {@link NodeCreationTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NodeCreationWithSnapToGridTest extends NodeCreationTest {

    // In vp-1174.odesign, 
    // Class mapping is un-resizable square.
    
    protected static final int VSM_SIDE_SIZE = 3; // as expressed 
    protected static final int SIDE_SIZE = VSM_SIDE_SIZE * LayoutUtils.SCALE; // as expressed 
    protected static final int GRID_SIZE = 100;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        editor.setSnapToGrid(true, GRID_SIZE, RulerProvider.UNIT_PIXELS);
    }

    @Override
    protected Point adaptNodeLocation(Point expectedAbsoluteLocation) {
        Point onGridLocation = editor.adaptLocationToSnap(expectedAbsoluteLocation);
        // As the element is not resizable,
        // its position is shifted so the node center is on a grid point.
        int shift = - SIDE_SIZE % GRID_SIZE / 2;
        // Negative shift is consistent with NodePositionHelper#shiftLocationToCenter(...) implementation.
                
        return onGridLocation.translate(shift, shift);
    }
}
