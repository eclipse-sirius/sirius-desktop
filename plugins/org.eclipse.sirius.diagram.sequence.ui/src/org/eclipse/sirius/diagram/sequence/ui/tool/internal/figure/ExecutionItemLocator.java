/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.description.StateMapping;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LifelineNodeFigure;

import com.google.common.collect.Iterables;

/**
 * Specific DBorderItemLocator to handle border item offset and side
 * computation.
 * 
 * @author mporhel
 * 
 */
public class ExecutionItemLocator extends DBorderItemLocator {
    private final IGraphicalEditPart owner;

    /**
     * Create an {@link ExecutionItemLocator} with the specified parentFigure.
     * 
     * @param owner
     *            the owner edit part.
     * @param parentFigure
     *            the parent figure.
     */
    public ExecutionItemLocator(IGraphicalEditPart owner, IFigure parentFigure) {
        super(parentFigure);
        this.owner = owner;
        setCurrentSideOfParent(LayoutEditPartConstants.EXECUTION_SIDE);
        setPreferredSideOfParent(LayoutEditPartConstants.EXECUTION_SIDE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void relocate(IFigure borderItem) {
        super.relocate(borderItem);
        if (isFigureForStateElement(borderItem)) {
            centerFigureOnParent(borderItem);
        }

        // Force size
        borderItem.setSize(getCollapsedSize(borderItem));

        unfix();
    }

    private void centerFigureOnParent(IFigure borderItem) {
        Rectangle parentBounds = getParentFigure().getBounds();
        int parentCenter = parentBounds.getCenter().x;
        Rectangle myBounds = borderItem.getBounds().getCopy();
        int x = parentCenter - (myBounds.width / 2);
        borderItem.setLocation(new Point(x, myBounds.getLocation().y));
    }

    private boolean isFigureForStateElement(IFigure borderItem) {
        for (IGraphicalEditPart childPart : Iterables.filter(owner.getChildren(), IGraphicalEditPart.class)) {
            if (childPart.getFigure() == borderItem) {
                return isStateElement(childPart);
            }
        }
        return false;
    }

    private boolean isStateElement(IGraphicalEditPart childPart) {
        EObject obj = childPart.resolveSemanticElement();
        if (obj instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) obj;
            return dde.getMapping() instanceof StateMapping;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBorderItemOffset(Dimension borderItemOffset) {
        super.setBorderItemOffset(getExecutionOffset());
    }

    private Dimension getExecutionOffset() {
        Dimension offset = new Dimension(LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET);
        Rectangle currentBounds = getConstraint();
        IFigure parentFigure = getParentFigure();
        if (parentFigure instanceof LifelineNodeFigure) {
            // we need to have the center of the execution aligned with the
            // center of the lifeline
            Rectangle parentBounds = parentFigure.getBounds();
            offset.setWidth(parentBounds.width / 2 + currentBounds.width / 2);
        } else if (currentBounds.width == 0) {
            offset.setWidth(0);
        }
        return offset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSideOfParent(int side) {
        super.setCurrentSideOfParent(LayoutEditPartConstants.EXECUTION_SIDE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreferredSideOfParent(int side) {
        super.setPreferredSideOfParent(LayoutEditPartConstants.EXECUTION_SIDE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point locateOnParent(Point suggestedLocation, int suggestedSide, IFigure borderItem) {
        Point locateOnParent = super.locateOnParent(suggestedLocation, suggestedSide, borderItem);
        return new Point(locateOnParent.x, suggestedLocation.y);
    }

    /**
     * Gets the size of the border item figure.
     * 
     * @param borderItem
     *            {@link IFigure} representing a Execution
     * @return the size of the border item figure.
     */
    protected final Dimension getCollapsedSize(IFigure borderItem) {
        Dimension size = getConstraint().getSize().getCopy();

        // Width can be empty for collapsing, do not use size.isEmpty()
        if (size.height == 0) {
            Dimension preferredSize = borderItem.getPreferredSize();
            size.setHeight(preferredSize != null ? preferredSize.height : 0);
        }

        return size;
    }

}
