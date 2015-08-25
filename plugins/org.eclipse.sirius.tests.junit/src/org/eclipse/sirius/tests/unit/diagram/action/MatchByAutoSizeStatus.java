/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.action;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Predicate;

/**
 * A specific predicate to match edit part with their autosize status.
 * 
 * @author cbrun
 * 
 */
public class MatchByAutoSizeStatus implements Predicate<IGraphicalEditPart> {

    private final boolean expectedStatus;

    /**
     * Create the predicate.
     * 
     * @param expected
     *            true if you want to pick autosize-enabled elements, false
     *            otherwise.
     */
    public MatchByAutoSizeStatus(final boolean expected) {
        this.expectedStatus = expected;
    }

    /**
     * {@inheritDoc}
     */
    public boolean apply(final IGraphicalEditPart editpart) {
        final View view = editpart.getNotationView();
        if (view instanceof Node) {
            final boolean isAutosize = isAutosized((Node) view);
            return isAutosize == expectedStatus;
        }
        return false;
    }

    private boolean isAutosized(final Node view) {
        if (view.getLayoutConstraint() instanceof Bounds) {
            return ((Bounds) view.getLayoutConstraint()).getWidth() == -1 && ((Bounds) view.getLayoutConstraint()).getHeight() == -1;
        }
        return false;
    }
}
