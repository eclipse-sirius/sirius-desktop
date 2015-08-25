/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.refresh.LabelAndIconRefresher;

/**
 * <p>
 * A standard ShapeNodeEditPart that have the peculiarity of not being
 * selectable.
 * </p>
 * <p>
 * Typically, all IStyleEditPart should extends this class, so that the
 * <i>Select All Shapes</i> action doesn't select them.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
// Select Object not clearly update
public abstract class AbstractNotSelectableShapeNodeEditPart extends ShapeNodeEditPart {

    private LabelAndIconRefresher labelAndIconRefresher;

    /**
     * Creates a new AbstractUnselectableShapeNodeEditPart.
     * 
     * @param view
     *            the view controlled by this EditPart
     */
    public AbstractNotSelectableShapeNodeEditPart(View view) {
        super(view);
    }

    @Override
    public void activate() {
        super.activate();
        labelAndIconRefresher = new LabelAndIconRefresher(this);
    }

    @Override
    public boolean isSelectable() {
        // We simply always return false so that this EditPart cannot be
        // selected manually or automatically
        return false;
    }

    @Override
    public void deactivate() {
        labelAndIconRefresher.dispose();
        labelAndIconRefresher = null;
        super.deactivate();
    }
}
