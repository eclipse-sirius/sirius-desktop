/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.gef.EditPart;

/**
 * A specific dragEditPartTracket that disable the clone feature. Indeed, in
 * Sirius it's not natural to clone a graphical element that will be removed on
 * the next refresh.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class NoCopyDragEditPartsTrackerEx extends SnapToAllDragEditPartsTracker {

    /**
     * Default constructor.
     * 
     * @param sourceEditPart
     *            the source edit part
     */
    public NoCopyDragEditPartsTrackerEx(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Always disable the clone with Ctrl key in Sirius because it only clone
     * the graphical element and not the semantic element.
     * 
     * @param cloneActive
     *            true if cloning should be active (never considered here)
     * @see org.eclipse.gef.tools.DragEditPartsTracker#setCloneActive(boolean)
     */
    @Override
    protected void setCloneActive(boolean cloneActive) {
        super.setCloneActive(false);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx#reveal
     * (org.eclipse.gef.EditPart)
     */
    @Override
    protected void reveal(EditPart editpart) {
        // In Sirius, the drag'n'drop can change (delete and create a new
        // one) the previous container of the drag'n'droped element. In this
        // case, the reveal causes a NPE because the hierarchy of edit part is
        // broken.
        if (editpart.getRoot() != null) {
            super.reveal(editpart);
        }
    }
}
