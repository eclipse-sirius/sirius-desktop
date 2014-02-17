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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;

import com.google.common.base.Preconditions;

/**
 * Specific locator for sequence message to avoid vertical move of labels when
 * lifelines are moved. (Avoid offset negation by GMF)
 * 
 * @author mporhel
 * 
 */
public class SequenceMessageLabelLocator extends LabelLocator {

    LabelLocator labelLocator;

    /**
     * Constructor for figure who are located and sized.
     * 
     * @param parent
     *            the parent figure
     * @param wrappedlocator
     *            default locator
     */
    public SequenceMessageLabelLocator(IFigure parent, LabelLocator wrappedlocator) {
        super(parent, new Rectangle(wrappedlocator.getOffset(), wrappedlocator.getSize()), wrappedlocator.getAlignment());
        Preconditions.checkNotNull(wrappedlocator);
        this.labelLocator = wrappedlocator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void relocate(IFigure target) {
        if (SequenceMessageLabelLocator.isRightToLeft((ViewEdgeFigure) target.getParent())) {
            labelLocator.setOffset(getOffset().getCopy().negate());
        }

        labelLocator.relocate(target);
        labelLocator.setOffset(getOffset());
    }

    /**
     * Test if the given ViewEdgeFigure is from right to left.
     * 
     * @param parent
     *            a ViewEdgeFigure
     * @return true if the given connection figure has its first point on thr
     *         right of its left point.
     */
    public static boolean isRightToLeft(ViewEdgeFigure parent) {
        if (parent != null) {
            PointList points = parent.getPoints();
            int fpX = points.getFirstPoint().x;
            int lpX = points.getLastPoint().x;

            return fpX > lpX;
        }
        return false;
    }

}
