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
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ConnectionAnchorOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.InstanceRoleResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.InstanceRoleSiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SouthCenteredBorderItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;

/**
 * The edit part for lifeline instance roles.
 * 
 * @author pcdavid, smonnier
 */
public class InstanceRoleEditPart extends DNodeEditPart {
    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public InstanceRoleEditPart(View view) {
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

    /**
     * This method has been overridden to use a specific BorderItemLocator to place the lifeline properly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        IBorderItemLocator result;
        if (vpElementBorderItem.getMapping() instanceof ExecutionMapping) {
            result = new SouthCenteredBorderItemLocator(figure, LayoutEditPartConstants.ROOT_EXECUTION_BORDER_ITEM_OFFSET);
        } else {
            result = super.createBorderItemLocator(figure, vpElementBorderItem);
        }
        return result;
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on lifelines.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, new InstanceRoleResizableEditPolicy());
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new InstanceRoleSiriusGraphicalNodeEditPolicy());

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    public InstanceRole getInstanceRole() {
        return ISequenceElementAccessor.getInstanceRole(getNotationView()).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new SequenceNoCopyDragEditPartsTrackerEx(this);
    }

}
