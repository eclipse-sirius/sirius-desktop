/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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

package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;

/**
 * A property tester to know whether we should activate or not the
 * "Remove bendpoints" action.
 * 
 * @author Florian Barbin
 *
 */
public class CanRemoveBendpoints extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof ConnectionNodeEditPart) {
            Object model = ((ConnectionNodeEditPart) receiver).getModel();
            if (model instanceof Edge) {
                EdgeQuery edgeQuery = new EdgeQuery((Edge) model);
                return edgeQuery.isEdgeWithObliqueRoutingStyle() || edgeQuery.isEdgeWithRectilinearRoutingStyle();
            }
        }
        return false;
    }

}
