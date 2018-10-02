/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.bracket;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * An anchor which locates always in the middle of a side of the owner. The side
 * is chosen from the orientation.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketConnectionAnchor extends AbstractConnectionAnchor {

    /**
     * Constructs an {@link BracketConnectionAnchor}.
     * 
     * @param owner
     *            the owner
     */
    public BracketConnectionAnchor(final IFigure owner) {
        super(owner);
    }

    /**
     * {@inheritDoc}
     */
    public Point getLocation(Point reference) {
        Point location = getReferencePoint();
        return location;

    }
}
