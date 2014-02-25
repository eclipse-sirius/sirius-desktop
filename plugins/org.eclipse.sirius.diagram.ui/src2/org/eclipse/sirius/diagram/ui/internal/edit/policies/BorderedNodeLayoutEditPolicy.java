/*******************************************************************************
 * Copyright (c) 2007, 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy;

/**
 * The common LayoutEditPolicy for all bordered nodes.
 */
public class BorderedNodeLayoutEditPolicy extends org.eclipse.sirius.diagram.ui.tools.api.policies.LayoutEditPolicy {
    /**
     * {@inheritDoc}
     */
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        if (child instanceof AbstractDiagramNameEditPart) {
            return new SpecificBorderItemSelectionEditPolicy();
        } else if (child instanceof AbstractBorderItemEditPart) {
            return ((AbstractBorderItemEditPart) child).getPrimaryDragEditPolicy();
        }
        EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (result == null) {
            result = new NonResizableAndNonDuplicableEditPolicy();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    protected Command getMoveChildrenCommand(final Request request) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    protected Command getCreateCommand(final CreateRequest request) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Command getCommand(final Request request) {
        if (REQ_RESIZE_CHILDREN.equals(request.getType()) && getHost() instanceof AbstractDiagramBorderNodeEditPart && request instanceof ChangeBoundsRequest) {
            AbstractDiagramBorderNodeEditPart borderedNode = (AbstractDiagramBorderNodeEditPart) getHost();
            Command command = borderedNode.getResizeBorderItemCommand((ChangeBoundsRequest) request);
            if (command != null) {
                return command;
            }
        }
        return super.getCommand(request);
    }
}
