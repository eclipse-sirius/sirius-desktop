/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.graphical.edit.styles;

import org.eclipse.draw2d.geometry.Insets;

import org.eclipse.sirius.DNode;
import org.eclipse.sirius.Square;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.SimpleStyleConfiguration;

/**
 * The style configuration for the simple square.
 * 
 * @author ymortier
 */
public class SimpleSquareStyleConfiguration extends SimpleStyleConfiguration {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.SimpleStyleConfiguration#getBorderDimension(org.eclipse.sirius.DNode)
     */
    @Override
    public Insets getBorderDimension(final DNode node) {
        final Insets result = new Insets(0, 0, 0, 0);
        if (node.getStyle() instanceof Square) {
            final Square square = (Square) node.getStyle();
            result.left = square.getBorderSize().intValue();
            result.right = square.getBorderSize().intValue();
            result.top = square.getBorderSize().intValue();
            result.bottom = square.getBorderSize().intValue();
        }
        return result;
    }

}
