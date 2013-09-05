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
package org.eclipse.sirius.diagram.business.internal.query;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.EdgeStyle;
import org.eclipse.sirius.EdgeTarget;
import org.eclipse.sirius.description.FoldingStyle;

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
        this.edge = Preconditions.checkNotNull(edge);
    }

    /**
     * Returns a predicate which checks the folding style of a {@link DEdge}.
     * 
     * @param style
     *            the style to test for.
     * @return a predicate on DEdge which returns <code>true</code> if the DEdge
     *         has the specified folding style.
     */
    public static Predicate<DEdge> hasFoldingStyle(final FoldingStyle style) {
        return new Predicate<DEdge>() {
            public boolean apply(DEdge input) {
                return new DEdgeQuery(input).getFoldingStyle() == style;
            }
        };
    }

    /**
     * Returns the folding style of the edge.
     * 
     * @return the folding style of the edge, or
     *         {@link FoldingStyle#NONE_LITERAL} if none is specified. Never
     *         returns <code>null</code>.
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
}
