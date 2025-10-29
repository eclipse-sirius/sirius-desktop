/*******************************************************************************
 * Copyright (c) 2024 CEA.
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
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.GateItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;

/**
 * Special edit part for the interaction container.
 * 
 * @author smonnier
 */
public class InteractionContainerEditPart extends DNodeContainerEditPart {

    /**
     * Default margin of the Interaction Container.
     */
    public static final int MARGIN = 50;

    /**
     * Default width of the Interaction Container.
     */
    private static final int DEFAULT_WIDTH = 30 + 80;

    /**
     * Default height of the Interaction Container.
     */
    private static final int DEFAULT_HEIGHT = 500;

    /**
     * Standard constructor, as expected by GMF.
     * 
     * @param view
     *            the view.
     */
    public InteractionContainerEditPart(View view) {
        super(view);
        setUseOverlayLabel(true);
    }

    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on combined fragment.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            // No user feedback wanted, don't install this policy
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    @Override
    protected void addDropShadow(NodeFigure figure, IFigure shape) {
        // Remove the shadow border to avoid unwanted spacing
        figure.setBorder(null);
    }

    @Override
    public DragTracker getDragTracker(final Request request) {
        DragTracker result = null;
        if (request instanceof SelectionRequest selectionRequest && selectionRequest.getLastButtonPressed() == 3) {
            result = new SequenceDragEditPartsTrackerEx(this);
        } else {
            if (request instanceof SelectionRequest selectionRequest && selectionRequest.isAltKeyPressed()) {
                result = new RubberbandDragTracker();
            } else {
                return new SequenceNoCopyDragEditPartsTrackerEx(this);
            }
        }
        return result;
    }

    @Override
    public EditPart getTargetEditPart(Request request) {
        return getParent();
    }
    

    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        if (Gate.viewpointElementPredicate().apply(vpElementBorderItem)) {
            return new GateItemLocator(this, figure);
        } else {
            return super.createBorderItemLocator(figure, vpElementBorderItem);
        }
    }

}
