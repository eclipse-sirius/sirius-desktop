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
package org.eclipse.sirius.diagram.ui.business.api.query;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link ConnectionEditPart} as a starting point.
 * 
 * @author lredor
 */
public class ConnectionEditPartQuery {

    private ConnectionEditPart connectionEditPart;

    /**
     * Create a new query.
     * 
     * @param connectionEditPart
     *            the starting point.
     */
    public ConnectionEditPartQuery(ConnectionEditPart connectionEditPart) {
        this.connectionEditPart = connectionEditPart;
    }

    /**
     * Check if this ConnectionEditPart is in diagram that is described with an
     * OrderedTreeLayout or a ComponentLayout.
     * 
     * @return true if ConnectionEditPart is in diagram that is described with
     *         an OrderedTreeLayout or a ComponentLayout, false otherwise.
     */
    public boolean isLayoutComponent() {
        boolean isLayoutComponent = false;
        if (isEdgeWithTreeRoutingStyle()) {
            Diagram diagram = getDiagram();
            if (diagram != null && diagram.getElement() instanceof DSemanticDiagram) {
                DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) diagram.getElement();
                Layout layout = dSemanticDiagram.getDescription().getLayout();
                isLayoutComponent = isOrderedTreeLayoutOrComponentLayout(layout);
            }
        }
        return isLayoutComponent;
    }

    /**
     * Check if this ConnectionEditPart has a tree routing style.
     * 
     * @return true if ConnectionEditPart has a tree routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithTreeRoutingStyle() {
        boolean isEdgeTreeRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeTreeRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithTreeRoutingStyle();
        }
        return isEdgeTreeRoutingStyle;
    }

    /**
     * Check if this ConnectionEditPart has a rectilinear routing style.
     * 
     * @return true if ConnectionEditPart has a rectilinear routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithRectilinearRoutingStyle() {
        boolean isEdgeWithRectilinearRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeWithRectilinearRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithRectilinearRoutingStyle();
        }
        return isEdgeWithRectilinearRoutingStyle;
    }

    /**
     * Check if this ConnectionEditPart has an oblique routing style.
     * 
     * @return true if ConnectionEditPart has an oblique routing style, false
     *         otherwise.
     */
    public boolean isEdgeWithObliqueRoutingStyle() {
        boolean isEdgeWithObliqueRoutingStyle = false;
        if (connectionEditPart.getModel() instanceof Edge) {
            isEdgeWithObliqueRoutingStyle = new EdgeQuery((Edge) connectionEditPart.getModel()).isEdgeWithObliqueRoutingStyle();
        }
        return isEdgeWithObliqueRoutingStyle;
    }

    private Diagram getDiagram() {
        Diagram diagram = null;
        if (connectionEditPart.getParent() instanceof DiagramRootEditPart) {
            DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart) connectionEditPart.getParent();
            if (diagramRootEditPart.getChildren().get(0) instanceof DiagramEditPart) {
                DiagramEditPart diagramEditPart = (DiagramEditPart) diagramRootEditPart.getChildren().get(0);
                if (diagramEditPart.getModel() instanceof Diagram) {
                    diagram = (Diagram) diagramEditPart.getModel();
                }
            }
        }
        return diagram;
    }

    private boolean isOrderedTreeLayoutOrComponentLayout(Layout layout) {
        boolean isLayout = false;
        if (layout instanceof OrderedTreeLayout || layout instanceof CompositeLayout) {
            if (layout instanceof CompositeLayout) {
                // This code is commented because left to right run not
                // correctly see ticket.
                // CompositeLayout compositeLayout = (CompositeLayout) layout;
                // if
                // (!LayoutDirection.LEFT_TO_RIGHT.getLiteral().equals(compositeLayout.getDirection().getName()))
                // {
                isLayout = true;
                // }
            } else {
                isLayout = true;
            }
        }
        return isLayout;
    }
}
