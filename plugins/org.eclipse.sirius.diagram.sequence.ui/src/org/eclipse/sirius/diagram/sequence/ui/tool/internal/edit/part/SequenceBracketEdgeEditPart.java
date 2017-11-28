/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketRelativeBendpoint;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.Direction;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;

/**
 * A custom bracket edit part to force default bracket direction.
 * 
 * @author mporhel
 */
public class SequenceBracketEdgeEditPart extends BracketEdgeEditPart {

    /**
     * Default constructor.
     * 
     * @param view
     *            the underlying {@link View}.
     */
    public SequenceBracketEdgeEditPart(View view) {
        super(view);
    }

    /**
     * Get default draw2d bendpoints.
     * 
     * @return default draw2d bendpoints
     */
    @Override
    protected List<BracketRelativeBendpoint> getDefaultFigureConstraint() {
        final List<BracketRelativeBendpoint> defaultFigureConstraint = new ArrayList<>();
        defaultFigureConstraint.add(new BracketRelativeBendpoint(getConnectionFigure(), Direction.LEFT.ordinal(), Direction.LEFT.ordinal(), 50));
        return defaultFigureConstraint;
    }

}
