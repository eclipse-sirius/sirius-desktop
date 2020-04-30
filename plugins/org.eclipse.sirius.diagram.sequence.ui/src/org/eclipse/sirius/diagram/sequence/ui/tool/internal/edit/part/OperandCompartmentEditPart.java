/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DCompartmentConnectionRefreshMgr;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.SiriusBlankSpacesDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;

/**
 * Custom edit part to customize what happens inside an operand.
 * 
 * @author pcdavid
 */
public class OperandCompartmentEditPart extends DNodeContainerViewNodeContainerCompartment2EditPart {
    /**
     * The visual ID. Same as a normal container compartment.
     * 
     * @see DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID
     */
    public static final int VISUAL_ID = 7002;

    /**
     * Constructor.
     * 
     * @param view
     *            the view <code>controlled</code> by this editpart.
     */
    public OperandCompartmentEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    @Override
    public DragTracker getDragTracker(final Request req) {
        SelectionRequest selectionRequest = (SelectionRequest) req;
        DragTracker result = SiriusBlankSpacesDragTracker.getDragTracker(this, (GraphicalViewer) getViewer(), req, true, true);
        if (result == null && req instanceof SelectionRequest && ((SelectionRequest) req).getLastButtonPressed() == 3) {
            result = new DeselectAllTracker(this);
        } else if (selectionRequest.isShiftKeyPressed() && selectionRequest.isControlKeyPressed() && result == null) {
            result = new RubberbandDragTracker();
        } else if (!(selectionRequest.isShiftKeyPressed() && selectionRequest.isControlKeyPressed())) {
            result = super.getDragTracker(req);
        }
        return result;
    }

    @Override
    protected ConnectionRefreshMgr createConnectionRefreshMgr() {
        /*
         * We need a custom ConnectionRefreshMgr to handle the special case of operands. Their figures are built in a
         * way that they have the exact same size of their container. There is no margin, inset, scrollbar or other
         * artifacts that would make the parent figure bigger than its children. However, by default the
         * Rectangle.contains implementation excludes all points from the right and bottom border (it uses x < (rect.x +
         * rect.width) instead of x <= (rect.x + rect.width)). For this figure we want those points to be considered as
         * belonging to the figure to avoid hiding edges that have a reference point located exactly on those edges. See
         * https://bugs.eclipse.org/bugs/show_bug.cgi?id=553321 for specific description.
         */
        return new DCompartmentConnectionRefreshMgr() {
            // CHECKSTYLE:OFF Copy of CMF Code
            /*
             * The following implementation is an exact copy of the super implementation except that the we use a custom
             * method to check if the given point is contains in the bounds
             */
            @Override
            protected boolean isFigureVisible(IFigure figure, Point loc, IFigure stopFigure) {
                if (!(figure.isShowing())) {
                    return false;
                } else {
                    Rectangle bounds = figure.getBounds().getCopy();
                    figure.translateToAbsolute(bounds);
                    if (!(customConstains(bounds, loc))) { // Initially if(!bounds.contains(loc))
                        return false;
                    }
                }

                IFigure parent = figure.getParent();
                while (parent != null && parent != stopFigure) {
                    return isFigureVisible(parent, loc, stopFigure);
                }
                return true;
            }
            // CHECKSTYLE:OFF Copy of CMF Code

            private boolean customConstains(Rectangle bounds, Point loc) {
                return loc.y >= bounds.y //
                        && loc.y <= bounds.y + bounds.height // Initially loc.y < bounds.y + bounds.height
                        && loc.x >= bounds.x //
                        && loc.x <= bounds.x + bounds.width; // Initially loc.x < bounds.x + bounds.width
            }
        };

    }
}
