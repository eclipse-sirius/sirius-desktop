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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.FlatContainerStyle;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.CombinedFragmentResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;

/**
 * Special edit part for combined fragments.
 * 
 * @author pcdavid
 */
public class CombinedFragmentEditPart extends DNodeContainerEditPart implements ISequenceEventEditPart {
    /**
     * Standard constructor, as expected by GMF.
     * 
     * @param view
     *            the view.
     */
    public CombinedFragmentEditPart(View view) {
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
     * resizing requests on combined fragment.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, new CombinedFragmentResizableEditPolicy());
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
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();
        // Remove the shadow border to avoid unwanted spacing
        figure.setBorder(null);
        forceCombinedFragmentDefaultSize(figure);
        return figure;
    }

    private void forceCombinedFragmentDefaultSize(NodeFigure figure) {
        if (figure instanceof DefaultSizeNodeFigure) {
            EObject eObj = this.resolveSemanticElement();
            if (eObj instanceof DDiagramElementContainer) {
                DDiagramElementContainer container = (DDiagramElementContainer) eObj;
                if (container.getOwnedStyle() instanceof FlatContainerStyle) {
                    ((DefaultSizeNodeFigure) figure).setDefaultSize(LayoutConstants.DEFAULT_COMBINED_FRAGMENT_WIDTH, LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getCombinedFragment(getNotationView()).get();
    }
}
