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

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.DStylizable;
import org.eclipse.sirius.FlatContainerStyle;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.OperandResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.OperandFigure;

/**
 * Special edit part for operands inside combined fragments.
 * 
 * @author pcdavid
 */
public class OperandEditPart extends DNodeContainer2EditPart implements ISequenceEventEditPart {
    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public OperandEditPart(View view) {
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
     * Overridden to install a specific edit policy managing the moving and
     * resizing requests on operands.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, new OperandResizableEditPolicy());
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

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addDropShadow(NodeFigure figure, IFigure shape) {
        // Removes the shadow border to have operands border overlapping
        // combined fragment border
        figure.setBorder(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createNodePlate() {
        NodeFigure nodePlate = super.createNodePlate();

        final EObject eObj = this.resolveSemanticElement();
        if (nodePlate instanceof DefaultSizeNodeFigure && eObj instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
            if (container.getOwnedStyle() instanceof FlatContainerStyle) {
                ((DefaultSizeNodeFigure) nodePlate).setDefaultSize(LayoutConstants.DEFAULT_OPERAND_WIDTH, LayoutConstants.DEFAULT_OPERAND_HEIGHT);
            }
        }

        return nodePlate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();

        return figure;
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to create our own figure without border but only a bottom dash
     * line.
     */
    @Override
    protected IFigure createNodeShape() {
        // We recover the combined fragment to have the operand separator with
        // the same style as the combined fragment border
        Operand operand = ISequenceElementAccessor.getOperand(getNotationView()).get();
        final EObject eObj = operand.getCombinedFragment().getNotationNode().getElement();
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            return new OperandFigure(DiagramContainerEditPartOperation.getCornerDimension(this), DiagramContainerEditPartOperation.getBackgroundStyle(this), operand);
        } else {
            return super.createNodeShape();
        }
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getOperand(getNotationView()).get();
    }

    /**
     * Overridden.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void refreshVisuals() {
        if (getParent() != null) {
            super.refreshVisuals();
        }
    }

    /**
     * Finds the parent {@link CombinedFragmentEditPart}.
     * 
     * @return the parent {@link CombinedFragmentEditPart}
     */
    public CombinedFragmentEditPart getParentCombinedFragmentEditPart() {
        Preconditions.checkArgument(getParent() instanceof CombinedFragmentCompartmentEditPart);
        Preconditions.checkArgument(getParent().getParent() instanceof CombinedFragmentEditPart);
        return (CombinedFragmentEditPart) getParent().getParent();
    }
}
