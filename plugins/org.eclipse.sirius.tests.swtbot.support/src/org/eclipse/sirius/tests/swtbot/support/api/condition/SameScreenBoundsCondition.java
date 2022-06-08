/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Condition to wait that a specific edit part remains several times with the same bounds. This condition is used, for
 * example, to detect if a move is really finished.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class SameScreenBoundsCondition extends DefaultCondition {
    /** The bounds during the previous check or the initial bounds before the "expected move". */
    private Rectangle previousBounds;

    /** The edit part to check. */
    private IGraphicalEditPart editPart;

    /** The number of consecutive tests that have the same bounds. */
    private int nbTestsWithoutMoving;

    /**
     * Default constructor.
     * 
     * @param editPart
     *            The edit part to check.
     * @param initialBounds
     *            The initial bounds before the "expected move".
     */
    public SameScreenBoundsCondition(IGraphicalEditPart editPart, Rectangle initialBounds) {
        this.editPart = editPart;
        this.previousBounds = initialBounds;
    }

    @Override
    public boolean test() throws Exception {
        Rectangle newBounds = editPart.getFigure().getBounds().getCopy();
        GraphicalHelper.logical2screen(newBounds, editPart);
        if (previousBounds.equals(newBounds)) {
            nbTestsWithoutMoving++;
        } else {
            nbTestsWithoutMoving = 0;
        }
        previousBounds = newBounds;
        return nbTestsWithoutMoving == 2;
    }

    @Override
    public String getFailureMessage() {
        return "There is not 2 consecutive checks without a move."; //$NON-NLS-1$
    }
}
