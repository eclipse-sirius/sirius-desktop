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

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;

/**
 * Special DNodeContainerViewNodeContainerCompartmentEditPart contained by an interaction container. This class was
 * created in order to forward command request to the parent SequenceDiagramEditPart.
 * 
 * @author smonnier
 */
public class InteractionContainerViewNodeContainerCompartmentEditPart extends DNodeContainerViewNodeContainerCompartmentEditPart {

    /**
     * Default constructor.
     * 
     * @param view
     *            {@link View} element that will be represented by this edit part.
     */
    public InteractionContainerViewNodeContainerCompartmentEditPart(View view) {
        super(view);
    }

    @Override
    public Command getCommand(Request request) {
        if (getParent() instanceof InteractionContainerEditPart && getParent().getParent() instanceof SequenceDiagramEditPart sequenceDiagramEditPart) {
            // Forward the request to the SequenceDiagramEditPart
            return sequenceDiagramEditPart.getCommand(request);
        }
        return super.getCommand(request);
    }

}
