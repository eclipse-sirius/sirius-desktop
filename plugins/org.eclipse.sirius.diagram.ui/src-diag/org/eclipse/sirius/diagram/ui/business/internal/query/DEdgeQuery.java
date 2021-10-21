/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.query;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.FoldingStyle;

import com.google.common.base.Predicate;

/**
 * Queries relative to a DEdge.
 * 
 * @author pcdavid
 */
public class DEdgeQuery {
    private final DEdge edge;

    /**
     * Constructor.
     * 
     * @param edge
     *            the edge to query.
     */
    public DEdgeQuery(DEdge edge) {
        this.edge = Objects.requireNonNull(edge);
    }

    /**
     * Returns a predicate which checks the folding style of a {@link DEdge}.
     * 
     * @param style
     *            the style to test for.
     * @return a predicate on DEdge which returns <code>true</code> if the DEdge has the specified folding style.
     */
    public static Predicate<DEdge> hasFoldingStyle(final FoldingStyle style) {
        return new Predicate<DEdge>() {
            @Override
            public boolean apply(DEdge input) {
                return new DEdgeQuery(input).getFoldingStyle() == style;
            }
        };
    }

    /**
     * Returns the folding style of the edge.
     * 
     * @return the folding style of the edge, or {@link FoldingStyle#NONE_LITERAL} if none is specified. Never returns
     *         <code>null</code>.
     */
    public FoldingStyle getFoldingStyle() {
        EdgeStyle style = edge.getOwnedStyle();
        if (style != null) {
            return style.getFoldingStyle();
        } else {
            return FoldingStyle.NONE_LITERAL;
        }
    }

    /**
     * Returns the element to hide/show when the DEdge is folded/unfolded.
     * 
     * @return the element to hide/show when the DEdge is folded/unfolded.
     */
    public EdgeTarget getFoldingTarget() {
        if (getFoldingStyle() == FoldingStyle.SOURCE_LITERAL) {
            return edge.getTargetNode();
        } else {
            return edge.getSourceNode();
        }
    }

    /**
     * Convert the Viewpoint routing as GMF routing.
     * 
     * @return the GMF routing style of this DEdge.
     */
    public Routing getRouting() {
        Routing routing = null;
        EdgeRouting newRoutingStyle = edge.getOwnedStyle().getRoutingStyle();
        if (newRoutingStyle == EdgeRouting.MANHATTAN_LITERAL) {
            routing = Routing.RECTILINEAR_LITERAL;
        } else if (newRoutingStyle == EdgeRouting.TREE_LITERAL) {
            routing = Routing.TREE_LITERAL;
        } else {
            routing = Routing.MANUAL_LITERAL;
        }
        return routing;
    }

    /**
     * Return the {@link DDiagram} recursively parent of the edge if such element exists.
     * 
     * @return the {@link DDiagram} recursively parent of the edge if such element exists.
     */
    public Optional<DDiagram> getDDiagram() {
        return getDDiagram(edge.eContainer());
    }

    /**
     * Return the {@link DDiagram} recursively parent of given object if such element exists.
     * 
     * @param object
     *            the object from which we want to retrieve the {@link DDiagram} that is either the direct parent or
     *            recursively the parent.
     * @return the {@link DDiagram} recursively parent of given object if such element exists.
     */
    private Optional<DDiagram> getDDiagram(EObject object) {
        Optional<DDiagram> result = Optional.empty();
        if (object instanceof DDiagram) {
            result = Optional.of((DDiagram) object);
        } else if (object != null) {
            result = getDDiagram(object.eContainer());
        }
        return result;

    }
}
