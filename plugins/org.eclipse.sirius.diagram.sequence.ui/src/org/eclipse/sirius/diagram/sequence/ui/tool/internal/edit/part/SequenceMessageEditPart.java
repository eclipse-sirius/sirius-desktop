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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;
import org.eclipse.gmf.runtime.diagram.ui.util.EditPartUtil;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceMessageEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.tools.SequenceMessageSelectConnectionEditPartTracker;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SequenceMessageLabelLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceMessagesRouter;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;

/**
 * The edit part for sequence diagrams messages.
 * 
 * @author pcdavid, smonnier
 */
public class SequenceMessageEditPart extends DEdgeEditPart implements ISequenceEventEditPart {
    /**
     * id for extended meta data.
     */
    public static final String MSG_TO_SELF_TOP_MOVE = "messageToSelfTopMove"; //$NON-NLS-1$

    /**
     * The global router for all the messages.
     */
    private static final ConnectionRouter ROUTER = new SequenceMessagesRouter();

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public SequenceMessageEditPart(final View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveSemanticElement());
    }

    /**
     * Force our own router for sequence messages.
     */
    @SuppressWarnings("restriction")
    @Override
    protected void installRouter() {
        getPrimaryShape().setConnectionRouter(ROUTER);
        setCursor(org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors.CURSOR_SEG_MOVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * Override to install the MessageEditPolicy.
     * <p>
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.CONNECTION_BENDPOINTS_ROLE.equals(key)) {
            super.installEditPolicy(key, new SequenceMessageEditPolicy());
            setCursor(org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors.CURSOR_SEG_MOVE);
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    /**
     * Overridden to hide feedback on reconnect attempts, which are not allowed.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void showSourceFeedback(Request request) {
        if (!(request instanceof ReconnectRequest)) {
            super.showSourceFeedback(request);
        }
    }

    /**
     * Overridden to hide feedback on reconnect attempts, which are not allowed.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void showTargetFeedback(Request request) {
        if (!(request instanceof ReconnectRequest)) {
            super.showTargetFeedback(request);
        }
    }

    /**
     * Sets the mouse cursor when the mouse is over this edit part.
     * 
     * @param cursor
     *            the cursor shape.
     */
    public void setCursor(final org.eclipse.swt.graphics.Cursor cursor) {
        EditPartUtil.synchronizeRunnableToMainThread(this, new Runnable() {
            public void run() {
                Connection connectionFigure = SequenceMessageEditPart.this.getConnectionFigure();
                connectionFigure.setCursor(cursor);
            };
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (request instanceof SelectionRequest) {
            this.setCursor(org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors.CURSOR_SEG_MOVE);
        }
        return super.getTargetEditPart(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshBendpoints() {
        RelativeBendpoints bendpoints = (RelativeBendpoints) getEdge().getBendpoints();
        List<?> modelConstraint = bendpoints.getPoints();
        List<org.eclipse.draw2d.RelativeBendpoint> figureConstraint = new ArrayList<org.eclipse.draw2d.RelativeBendpoint>();
        for (int i = 0; i < modelConstraint.size(); i++) {
            RelativeBendpoint wbp = (RelativeBendpoint) modelConstraint.get(i);
            org.eclipse.draw2d.RelativeBendpoint rbp = new org.eclipse.draw2d.RelativeBendpoint(getConnectionFigure());
            rbp.setRelativeDimensions(new Dimension(wbp.getSourceX(), wbp.getSourceY()), new Dimension(wbp.getTargetX(), wbp.getTargetY()));
            rbp.setWeight(0);
            figureConstraint.add(rbp);
        }
        getConnectionFigure().setRoutingConstraint(figureConstraint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
        if (constraint instanceof LabelLocator) {
            // custom locator to avoid vertical label position change during
            // horizontal move of lifelines.
            SequenceMessageLabelLocator smll = new SequenceMessageLabelLocator(childFigure.getParent(), (LabelLocator) constraint);
            super.setLayoutConstraint(child, childFigure, smll);
        } else {
            super.setLayoutConstraint(child, childFigure, constraint);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request req) {
        return new SequenceMessageSelectConnectionEditPartTracker(this);
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getMessage(getNotationView()).get();
    }
}
