/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.CombinedFragmentResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;

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
            super.installEditPolicy(key, new CombinedFragmentResizableEditPolicy());
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
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();
        forceCombinedFragmentDefaultSize(figure);
        return figure;
    }

    @Override
    protected void addDropShadow(NodeFigure figure, IFigure shape) {
        // Remove the shadow border to avoid unwanted spacing
        figure.setBorder(null);
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

    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getCombinedFragment(getNotationView()).get();
    }
}
