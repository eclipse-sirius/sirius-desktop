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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * An edit policy that can show feedback for selection of
 * {@link AbstractDiagramEdgeEditPart}.
 * 
 * @author mPorhel
 */
public class DEdgeSelectionFeedbackEditPolicy extends AbstractEdgeSelectionFeedbackEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles()
     */
    @Override
    protected List createSelectionHandles() {
        final List list = new ArrayList();

        for (AbstractDEdgeNameEditPart edgeNameEditPart : getEdgeNameEditPart()) {
            if (edgeNameEditPart != null && edgeNameEditPart.getFigure().isVisible()) {
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
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeEditPart()
     */
    @Override
    protected AbstractDiagramEdgeEditPart getEdgeEditPart() {
        if (getHost() instanceof AbstractDiagramEdgeEditPart) {
            return (AbstractDiagramEdgeEditPart) getHost();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeNameEditPart()
     */
    @Override
    protected List<AbstractDEdgeNameEditPart> getEdgeNameEditPart() {
        List<AbstractDEdgeNameEditPart> names = new ArrayList<AbstractDEdgeNameEditPart>();
        if (getHost() instanceof AbstractDiagramEdgeEditPart) {
            for (final Object editPart : getHost().getChildren()) {
                if (editPart instanceof AbstractDEdgeNameEditPart) {
                    names.add((AbstractDEdgeNameEditPart) editPart);
                }
            }
        }
        return names;
    }
}
