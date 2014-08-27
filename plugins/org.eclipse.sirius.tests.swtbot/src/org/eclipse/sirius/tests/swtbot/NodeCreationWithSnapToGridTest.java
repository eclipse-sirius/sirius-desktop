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
 * Same tests as {@link NodeCreationTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NodeCreationWithSnapToGridTest extends NodeCreationTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editor.setSnapToGrid(true, 100, 2);
    }

    @Override
    protected Point adaptLocation(Point expectedAbsoluteLocation) {
        return editor.adaptLocationToSnap(expectedAbsoluteLocation);
    }
}
