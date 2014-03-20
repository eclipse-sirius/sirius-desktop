/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;

/**
 * An edit policy that can show feedback for selection on a
 * {@link org.eclipse.sirius.diagram.internal.edit.parts.DEdgeNameEditPart}.
 * 
 * @author mPorhel
 */
public class DEdgeNameSelectionFeedbackEditPolicy extends AbstractEdgeSelectionFeedbackEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeEditPart()
     */
    @Override
    protected AbstractDiagramEdgeEditPart getEdgeEditPart() {
        if (getHost() instanceof AbstractDEdgeNameEditPart && getHost().getParent() instanceof AbstractDiagramEdgeEditPart) {
            return (AbstractDiagramEdgeEditPart) getHost().getParent();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeNameEditPart()
     */
    @Override
    protected List<AbstractDEdgeNameEditPart> getEdgeNameEditPart() {
        List<AbstractDEdgeNameEditPart> names = new ArrayList<AbstractDEdgeNameEditPart>();
        if (getHost() instanceof AbstractDEdgeNameEditPart) {
            for (final Object editPart : getEdgeEditPart().getChildren()) {
                if (editPart instanceof AbstractDEdgeNameEditPart) {
                    names.add((AbstractDEdgeNameEditPart) editPart);
                }
            }
        }
        return names;
    }

}
