/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.Handle;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.BendpointMoveHandle;
import org.eclipse.gef.handles.ConnectionEndHandle;
import org.eclipse.gef.handles.ConnectionStartHandle;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramEdgeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

/**
 * An abstract edit policy to handle feedback for selection of
 * {@link AbstractDiagramEdgeEditPart}.
 * 
 * @author mPorhel
 * 
 */
public abstract class AbstractEdgeSelectionFeedbackEditPolicy extends SelectionHandlesEditPolicy {

    private static final int WIDTH_FEEDBACK = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showSelection() {
        if (getEdgeEditPart() == null) {
            return;
        }

        // virtual selection is done here thanks to createSelectionHandles()
        super.showSelection();

        final ViewEdgeFigure fig = getViewEdgeFigure();

        if (fig != null && Display.getCurrent() != null) {
            fig.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
            fig.setLineWidth(DiagramEdgeEditPartOperation.getLineWidth(getEdgeEditPart()) + WIDTH_FEEDBACK);
            for (final Object child : fig.getChildren()) {
                if (child instanceof PolylineDecoration) {
                    final PolylineDecoration decoration = (PolylineDecoration) child;
                    decoration.setLineWidth(DiagramEdgeEditPartOperation.getLineWidth(getEdgeEditPart()) + WIDTH_FEEDBACK);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles()
     */
    @Override
    protected List<Handle> createSelectionHandles() {
        final List<Handle> list = Lists.newArrayList();

        list.addAll(createNameSelectionHandles());

        final AbstractDiagramEdgeEditPart edgeEditPart = getEdgeEditPart();

        if (edgeEditPart != null) {
            list.add(new ConnectionEndHandle(edgeEditPart));
            list.add(new ConnectionStartHandle(edgeEditPart));

            final PointList points = ((Connection) edgeEditPart.getFigure()).getPoints();
            for (int i = 1; i < points.size() - 1; i++) {
                list.add(new BendpointMoveHandle(edgeEditPart, i, new BendpointLocator((Connection) edgeEditPart.getFigure(), i)));
            }
        }
        return list;

    }

    private ViewEdgeFigure getViewEdgeFigure() {
        if (getEdgeEditPart() != null && getEdgeEditPart().getFigure() instanceof ViewEdgeFigure) {
            return (ViewEdgeFigure) getEdgeEditPart().getFigure();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideSelection() {
        super.hideSelection();
        getEdgeEditPart().refreshForegroundColor();
        getEdgeEditPart().refreshLineStyle();
        getEdgeEditPart().refreshSourceDecoration();
        getEdgeEditPart().refreshTargetDecoration();
    }

    /**
     * Create feedback handles for the name figure.
     * 
     * @return list of handles corresponding to the name figure
     */
    protected List<Handle> createNameSelectionHandles() {
        final List<Handle> list = Lists.newArrayList();
        for (AbstractDiagramNameEditPart edgeNameEditPart : getEdgeNameEditPart()) {
            if (edgeNameEditPart != null && edgeNameEditPart.getEditText() != null && !"".equals(edgeNameEditPart.getEditText())) { //$NON-NLS-1$
                NonResizableHandleKit.addMoveHandle(edgeNameEditPart, list);
            }
        }

        for (final Object fig : list) {
            if (fig instanceof IFigure) {
                ((IFigure) fig).setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
            }
        }
        return list;
    }

    /**
     * Return the AbstractDiagramEdgeEditPart linked to the host of this edit
     * policy.
     * 
     * @return the AbstractDiagramEdgeEditPart linked to the host of this edit
     *         policy.
     */
    protected abstract AbstractDiagramEdgeEditPart getEdgeEditPart();

    /**
     * Return the DEdgeNameEditPart linked to the host of this edit policy.
     * 
     * @return the DEdgeNameEditPart linked to the host of this edit policy.
     */
    protected abstract List<AbstractDEdgeNameEditPart> getEdgeNameEditPart();
}
