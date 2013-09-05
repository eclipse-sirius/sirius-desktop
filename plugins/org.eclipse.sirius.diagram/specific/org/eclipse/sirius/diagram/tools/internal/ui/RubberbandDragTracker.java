/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
// CHECKSTYLE:OFF
package org.eclipse.sirius.diagram.tools.internal.ui;

import org.eclipse.gef.DragTracker;

/**
 * @author tisrar
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.*
 * 
 * 
 */
public class RubberbandDragTracker extends RubberbandSelectionTool implements DragTracker {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.tools.AbstractTool#handleFinished()
     */
    @Override
    protected void handleFinished() {
        // nothing goes here

    }

}
// CHECKSTYLE:ON
