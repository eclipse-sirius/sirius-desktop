/*******************************************************************************
 * Copyright (c) 2025 CEA.
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ConnectionAnchorOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceGateSiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.SiriusBlankSpacesDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Special edit part for common behavior of Gates. They are treated as bordered nodes on containers.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceBordered4EditPart extends DNode4EditPart {

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public AbstractSequenceBordered4EditPart(final View view) {
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
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * Overridden to install a custom semantic edit policy and node creation policy.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ExecutionSemanticEditPolicy());
        // ExecutionOperations.installExecutionAwareNodeCreationPolicy(this);

        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.GRAPHICAL_NODE_ROLE, new SequenceGateSiriusGraphicalNodeEditPolicy(), SiriusGraphicalNodeEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * Overridden to make the whole figure's area a valid location for a SlidableAnchor. Executions are usually very
     * narrow vertically, and the default setting makes the zone usable to anchor a message too small to be usable.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();
        ExecutionOperations.adjustFigureSlidableArea(figure);
        return figure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        ConnectionAnchor anchor = super.getSourceConnectionAnchor(request);
        // if (new RequestQuery(request).isSequenceMessageCreation()) {
        // anchor = ConnectionAnchorOperation.getSourceConnectionAnchor(this, request, anchor);
        // }
        return anchor;
    }

    /**
     * Use the same y location as the corresponding source connection anchor, stored in ViewLocationHint, to improve
     * user feedback.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        boolean sequenceMessageCreation = new RequestQuery(request).isSequenceMessageCreation();
        if (sequenceMessageCreation && request instanceof DropRequest) {
            ConnectionAnchorOperation.matchRequestYLocationWithSourceAnchor((DropRequest) request);
        }

        ConnectionAnchor anchor = super.getTargetConnectionAnchor(request);

        // if (sequenceMessageCreation) {
        // anchor = ConnectionAnchorOperation.getTargetConnectionAnchor(this, request, anchor);
        // }
        return anchor;
    }

    /**
     * Overridden to have execution creation on existing execution and in same range of child execution, redirected to
     * the most nested child execution.
     * 
     * {@inheritDoc}
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        Set<String> retargetTypes = new HashSet<String>(Arrays.asList(RequestConstants.REQ_CREATE, RequestConstants.REQ_CONNECTION_START, RequestConstants.REQ_CONNECTION_END));
        if (retargetTypes.contains(request.getType()) && request instanceof CreateRequest && ((CreateRequest) request).getLocation() != null) {
            CreateRequest createRequest = (CreateRequest) request;
            Point location = createRequest.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, this);
            // Range insertionPoint = new Range(location.y, location.y);
            // ISequenceEvent sequenceEvent = getISequenceEvent();
            // EventFinder eventFinder = new EventFinder(sequenceEvent, sequenceEvent.getLifeline().get());
            // ISequenceEvent mostSpecificSequenceEvent = eventFinder.findMostSpecificEvent(insertionPoint);
            //
            // if (mostSpecificSequenceEvent instanceof AbstractNodeEvent &&
            // !sequenceEvent.equals(mostSpecificSequenceEvent)) {
            // Map<?, ?> editPartRegistry = getTopGraphicEditPart().getViewer().getEditPartRegistry();
            // Object obj = editPartRegistry.get(mostSpecificSequenceEvent.getNotationView());
            // if (obj instanceof ExecutionEditPart) {
            // ExecutionEditPart targetEditPart = (ExecutionEditPart) obj;
            // return targetEditPart;
            // }
            // }
        }
        return super.getTargetEditPart(request);
    }

    @Override
    public DragTracker getDragTracker(final Request req) {
        SelectionRequest selectionRequest = (SelectionRequest) req;
        DragTracker result = SiriusBlankSpacesDragTracker.getDragTracker(this, (GraphicalViewer) getViewer(), req, true, true);
        if (result == null && req instanceof SelectionRequest && ((SelectionRequest) req).getLastButtonPressed() == 3) {
            result = new SequenceDragEditPartsTrackerEx(this);
        } else if (selectionRequest.isShiftKeyPressed() && selectionRequest.isControlKeyPressed() && result == null) {
            result = new RubberbandDragTracker();
        } else if (!(selectionRequest.isShiftKeyPressed() && selectionRequest.isControlKeyPressed())) {
            result = new SequenceDragEditPartsTrackerEx(this);
        }
        return result;
    }
}
