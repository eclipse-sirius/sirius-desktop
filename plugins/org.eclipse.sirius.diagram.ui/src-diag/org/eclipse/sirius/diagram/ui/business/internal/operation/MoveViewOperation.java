/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.operation;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Shift a view by the delta.
 * 
 * @author edugueperoux
 */
public class MoveViewOperation extends AbstractModelChangeOperation<Void> {

    private IAdaptable adapter;

    private Point moveDelta;

    /**
     * Default constructor.
     * 
     * @param label
     *            label to display in the undo menu item
     * @param adapter
     *            adapter for the view
     * @param moveDelta
     *            delta by which view is shifted
     * 
     */
    public MoveViewOperation(String label, IAdaptable adapter, Point moveDelta) {
        super(label);
        Assert.isNotNull(adapter, "view cannot be null"); //$NON-NLS-1$
        Assert.isNotNull(moveDelta, "moveDelta cannot be null"); //$NON-NLS-1$
        this.adapter = adapter;
        this.moveDelta = moveDelta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        if (adapter != null) {
            View view = (View) adapter.getAdapter(View.class);
            if (moveDelta != null) {
                if (view instanceof Node) {
                    Node node = (Node) view;
                    LayoutConstraint constraint = node.getLayoutConstraint();
                    if (constraint != null && NotationPackage.eINSTANCE.getLocation().isInstance(constraint)) {
                        Location location = (Location) constraint;
                        location.setX(location.getX() + moveDelta.x);
                        location.setY(location.getY() + moveDelta.y);
                    }
                }
            }
        }
        return null;
    }

}
