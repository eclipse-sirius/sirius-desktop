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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;

/**
 * Same tests as {@link NoteCreationTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NoteCreationWithSnapToGridTest extends NoteCreationTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editor.setSnapToGrid(true, 100, 2);
    }

    @Override
    protected Point adaptLocation(Point expectedAbsoluteLocation) {
        // No adaptation, the Note is currently not affected by the snap to grid
        // during creation, as expected (see Specification)
        return expectedAbsoluteLocation;
    }
}
