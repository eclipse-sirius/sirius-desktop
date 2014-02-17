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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.StateSelectionEditPolicy;
import org.eclipse.sirius.diagram.ui.edit.internal.part.AbstractDiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;

/**
 * Special edit part for States. Implemented as bordered nodes, either directly
 * on a lifeline or on an execution.
 * 
 * @author smonnier
 */
public class StateEditPart extends ExecutionEditPart {

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public StateEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getState(getNotationView()).get();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IAbstractDiagramNodeEditPart#createBorderItemLocator(IFigure,
     *      DDiagramElement)
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(final IFigure figure, final DDiagramElement vpElementBorderItem) {
        return AbstractDiagramNodeEditPartOperation.createBorderItemLocator(this, figure, vpElementBorderItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        final ResizableEditPolicy result = new StateSelectionEditPolicy();
        DDiagramElement dde = this.resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;
            DiagramBorderNodeEditPartOperation.updateResizeKind(result, node);
        }
        return result;
    }
}
