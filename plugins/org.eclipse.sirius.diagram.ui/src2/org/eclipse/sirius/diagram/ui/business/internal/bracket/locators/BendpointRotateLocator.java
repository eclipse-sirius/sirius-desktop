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
package org.eclipse.sirius.diagram.ui.business.internal.bracket.locators;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;

/**
 * A BendpointHandle that is used to rotate an existing bendpoint.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BendpointRotateLocator extends BendpointLocator {

    /**
     * Default constructor.
     * 
     * @param connection
     *            the dimension {@link Connection} on which do a rotate.
     * @param index
     *            the index of the bendpoint.
     */
    public BendpointRotateLocator(Connection connection, int index) {
        super(connection, index);
    }

    /**
     * Overridden to change the index in case we have only 2 point in the
     * {@link Connection#getPoints()}. {@inheritDoc}
     */
    @Override
    protected int getIndex() {
        int index = super.getIndex();
        final Connection connection = getConnection();
        if (connection.getPoints().size() == 2) {
            if (index == BracketConnectionQuery.ORIGIN_POINT_INDEX) {
                index = 0;
            } else if (index == BracketConnectionQuery.TARGET_POINT_INDEX) {
                index = 1;
            }
        }
        return index;
    }

}
