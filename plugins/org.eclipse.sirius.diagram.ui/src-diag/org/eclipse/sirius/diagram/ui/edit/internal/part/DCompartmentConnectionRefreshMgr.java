/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

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
        @Override
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
     * Return the set of {@link ConnectionNodeEditPart}s contained in the supplied shape compartment and in the parent
     * edit part.<BR>
     * This method returns the same result as {@link ConnectionRefreshMgr#getConnectionNodes(ShapeCompartmentEditPart)}
     * but it also considers edges having the parent of the <code>scep</code> as source or target.<BR>
     * The modified code is only the addition of the condition <code>endPoint.equals(scep.getParent())</code> to the 2
     * statements <code>if (isChildOf(scep, endPoint))</code>.
     * 
     * @param scep
     *            a shape compartment.
     * @return a {@link Set} of {@link ConnectionNodeEditPart}.
     */

    protected Set getSpecificConnectionNodes(ShapeCompartmentEditPart scep) {
        Set<Object> endPoints = new HashSet<>();
        Object modelObject = scep.getModel();
        if (scep.getViewer() != null && modelObject instanceof View && ((View) modelObject).getDiagram() != null) {
            Diagram diagram = ((View) modelObject).getDiagram();
            Map registry = scep.getViewer().getEditPartRegistry();
            List edges = diagram.getEdges();
            Iterator edgesIterator = edges.iterator();

            while (edgesIterator.hasNext()) {
                Edge edge = (Edge) edgesIterator.next();
                EditPart endPoint = (EditPart) registry.get(edge.getSource());
                if (isChildOf(scep, endPoint) || (endPoint != null && endPoint.equals(scep.getParent()))) {
                    Object cep = registry.get(edge);
                    if (cep != null) {
                        endPoints.add(cep);
                    }
                    continue;
                }
                endPoint = (EditPart) registry.get(edge.getTarget());
                if (isChildOf(scep, endPoint) || (endPoint != null && endPoint.equals(scep.getParent()))) {
                    Object cep = registry.get(edge);
                    if (cep != null) {
                        endPoints.add(cep);
                    }
                }
            }
        }
        return endPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Set getConnectionNodes(ShapeCompartmentEditPart scep) {
        Set<?> connectionsNodes = getSpecificConnectionNodes(scep);

        if (connectionsNodes != null) {
            Iterable<?> filteredConnectionNodes = Iterables.filter(connectionsNodes, safeConnection);
            return Sets.newHashSet(filteredConnectionNodes);
        }
        return connectionsNodes;
    }
}
