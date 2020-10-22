/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

/**
 * Queries on sequence elements which are represented by GMF nodes.
 * 
 * @author pcdavid
 */
public class SequenceNodeQuery {

    private final Node node;

    /**
     * Constructor.
     * 
     * @param node
     *            the node to query.
     */
    public SequenceNodeQuery(Node node) {
        this.node = node;
    }

    /**
     * Returns the vertical range of a sequence diagram element from its GMF notation node.
     * 
     * @return the vertical range of the element.
     */
    public Range getVerticalRange() {
        Range result = null;
        EObject element = node.getElement();
        if (!(element instanceof DDiagramElement)) {
            result = null;
        } else {
            if (CacheHelper.isVerticalRangeCacheEnabled()) {
                result = CacheHelper.getViewToRangeCache().get(node);
            }
            if (result == null) {
                Rectangle absoluteBounds = GMFHelper.getAbsoluteBounds(node);
                int y = absoluteBounds.y;
                int height = absoluteBounds.height;
                // GMFHelper.getAbsoluteBounds() use default
                // DDiagramElementContainer (DNodeContainer/DNodeList) dimension if
                // size == (-1,-1) and here we need to have the real GMF size if ==
                // to (-1,-1)
                if (node.getLayoutConstraint() instanceof Size) {
                    Size size = (Size) node.getLayoutConstraint();
                    height = size.getHeight();
                }
                // handle container auto size -> range.widht = 0, next layout will
                // set the good value
                // check in interaction use view factory, that it cannot be there
                if (element instanceof DNodeContainer && height == -1) {
                    height = 0;
                }
                if (height == -1 && element instanceof DNode && ((DNode) element).getOwnedStyle() instanceof WorkspaceImage) {
                    height = new DNodeQuery((DNode) element).getDefaultDimension().height;
                }

                result = new Range(y, y + height);

                if (isShifted()) {
                    result = result.shifted(IBorderItemOffsets.DEFAULT_OFFSET.height);
                }
                if (CacheHelper.isVerticalRangeCacheEnabled()) {
                    CacheHelper.getViewToRangeCache().put(node, result);
                }
            }
        }
        return result;
    }

    private boolean isShifted() {
        return Lifeline.notationPredicate().apply(node) || AbstractNodeEvent.notationPredicate().apply(node) || EndOfLife.notationPredicate().apply(node);
    }

}
