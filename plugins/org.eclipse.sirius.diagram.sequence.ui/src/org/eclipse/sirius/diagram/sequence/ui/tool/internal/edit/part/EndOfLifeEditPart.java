/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ConnectionAnchorOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.EndOfLifeSelectionPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;

/**
 * The edit part for lifeline end of lives. Implemented as a bordered node on the lifeline, and constrained on the south
 * side of the lifeline. Such a node can be used either to represent the actual "End of Life" of the lifeline when it is
 * explicitly destroyed or a "handle" which can be used to resize the lifeline vertically.
 * 
 * @author pcdavid
 */
public class EndOfLifeEditPart extends DNode2EditPart {
    /**
     * Standard constructor, as expected by GMF.
     * 
     * @param view
     *            the view.
     */
    public EndOfLifeEditPart(View view) {
        super(view);
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
     * {@inheritDoc}
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        ResizableEditPolicy result = new EndOfLifeSelectionPolicy();
        DDiagramElement dde = resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;
            DiagramBorderNodeEditPartOperation.updateResizeKind(result, node);
        }
        return result;
    }

    /**
     * This method is overridden to have the EndOfLife (bordered node) starting from the border of the Instance Role.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        super.refresh();
        SequenceEditPartsOperations.setBorderItemLocation(this, LayoutEditPartConstants.EOL_SIDE, LayoutEditPartConstants.EOL_BORDER_ITEM_OFFSET);
    }

    /**
     * Overridden to register the diagram element.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    /**
     * Overridden to unregister the diagram element.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        ConnectionAnchor anchor = super.getTargetConnectionAnchor(request);
        if (anchor != null && new RequestQuery(request).isSequenceMessageCreation()) {
            anchor = ConnectionAnchorOperation.safeCenterAnchor(anchor);
        }
        return anchor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        ConnectionAnchor anchor = super.getSourceConnectionAnchor(request);
        if (anchor != null && new RequestQuery(request).isSequenceMessageCreation()) {
            anchor = ConnectionAnchorOperation.safeCenterAnchor(anchor);
        }
        return anchor;
    }

    /***
     * Get the lifeline edit part of the current end of life edit part.
     * 
     * @return the lifeline edit part.
     */
    public LifelineEditPart getLifelineEditPart() {
        EditPart parent = getParent();
        if (parent instanceof LifelineEditPart) {
            return (LifelineEditPart) parent;
        }
        return null;
    }

    @Override
    public DragTracker getDragTracker(final Request request) {
        return new SequenceDragEditPartsTrackerEx(this);
    }
}
