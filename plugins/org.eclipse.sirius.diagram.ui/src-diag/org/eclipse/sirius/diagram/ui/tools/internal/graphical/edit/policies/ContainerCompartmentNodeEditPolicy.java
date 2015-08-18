/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerNodeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * Specific Edit Policy for compartments of containers.
 * 
 * @author ymortier
 */
public class ContainerCompartmentNodeEditPolicy extends ContainerNodeEditPolicy {
    private RectangleFigure highlightFigure;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy#getTargetConnectionAnchor(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected ConnectionAnchor getTargetConnectionAnchor(final CreateConnectionRequest request) {
        final EditPart target = request.getTargetEditPart();
        if (target instanceof ShapeCompartmentEditPart && target.getParent() instanceof INodeEditPart) {
            return ((INodeEditPart) target.getParent()).getTargetConnectionAnchor(request);
        }
        return super.getTargetConnectionAnchor(request);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy#getSourceConnectionAnchor(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected ConnectionAnchor getSourceConnectionAnchor(final CreateConnectionRequest request) {
        final EditPart source = request.getSourceEditPart();
        if (source instanceof ShapeCompartmentEditPart && source.getParent() instanceof INodeEditPart) {
            return ((INodeEditPart) source.getParent()).getSourceConnectionAnchor(request);
        }
        return super.getSourceConnectionAnchor(request);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CONNECTION_START.equals(request.getType()) && request instanceof CreateConnectionViewRequest
                && "NoteAttachment".equals(((CreateConnectionViewRequest) request).getConnectionViewDescriptor().getSemanticHint())) { //$NON-NLS-1$
            // The note attachment requires a INodeEditPart as source.
            return getHost().getParent();
        } else {
            return super.getTargetEditPart(request);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#showTargetConnectionFeedback(org.eclipse.gef.requests.DropRequest)
     */
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        removeHighlight();
        boolean reveal = false;
        if (request instanceof ReconnectRequest) {
            reveal = true;
            addHighlight(reveal);
        }
    }

    /**
     * Add a highlight feedback figure on element reconnect.
     * 
     * @param reveal
     *            boolean to know if it's a reconnect request or not.
     */
    private void addHighlight(boolean reveal) {
        Rectangle bounds = getHostFigure().getBounds().getCopy();
        getHostFigure().getParent().translateToAbsolute(bounds);
        getFeedbackLayer().translateToRelative(bounds);

        highlightFigure = new RectangleFigure() {
            public void paint(Graphics graphics) {
                graphics.setAlpha(128);
                super.paint(graphics);
            }
        };
        highlightFigure.setBounds(bounds);
        highlightFigure.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
        addFeedback(highlightFigure);
        if (reveal) {
            getHost().getViewer().reveal(getHost());
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#eraseTargetConnectionFeedback(org.eclipse.gef.requests.DropRequest)
     */
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        removeHighlight();
    }

    /**
     * Remove highLight figure.
     */
    private void removeHighlight() {
        if (highlightFigure != null) {
            removeFeedback(highlightFigure);
            highlightFigure = null;
        }
        if (getHostFigure() instanceof ViewEdgeFigure) {
            ((IDiagramEdgeEditPart) getHost()).refreshForegroundColor();
            ((IDiagramEdgeEditPart) getHost()).refreshLineStyle();
        }
    }

}
