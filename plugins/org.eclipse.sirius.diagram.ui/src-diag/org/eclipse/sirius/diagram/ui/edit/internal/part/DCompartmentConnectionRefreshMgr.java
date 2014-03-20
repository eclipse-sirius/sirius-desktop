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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Specific Connection refresh manager to remove invalid connection parts.
 * 
 * @author mporhel
 * 
 */
public class DCompartmentConnectionRefreshMgr extends ConnectionRefreshMgr {

    private Predicate safeConnection = new Predicate() {
        public boolean apply(Object input) {
            boolean selected = true;
            if (input instanceof ConnectionEditPart) {
                ConnectionEditPart part = (ConnectionEditPart) input;
                selected = false;
                if (part.getSource() != null && part.getTarget() != null) {
                    if (part.getFigure() instanceof Connection) {
                        Connection connection = (Connection) part.getFigure();
                        selected = connection.getConnectionRouter().getConstraint(connection) != null;
                    }
                }
            }
            return selected;
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected Set getConnectionNodes(ShapeCompartmentEditPart scep) {
        Set<?> connectionsNodes = super.getConnectionNodes(scep);
        if (connectionsNodes != null) {
            Iterable<?> filteredConnectionNodes = Iterables.filter(connectionsNodes, safeConnection);
            return Sets.newHashSet(filteredConnectionNodes);
        }
        return connectionsNodes;
    }
}
