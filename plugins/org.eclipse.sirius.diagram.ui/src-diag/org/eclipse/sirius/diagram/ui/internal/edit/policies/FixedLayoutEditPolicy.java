/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.sirius.diagram.ui.tools.api.policies.LayoutEditPolicy;

/**
 * A layout policy which forbids the creation, moving or resizing of children
 * elements.
 */
public class FixedLayoutEditPolicy extends LayoutEditPolicy {
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (result == null) {
            result = new NonResizableEditPolicy();
        }
        return result;
    }

    @Override
    protected Command getMoveChildrenCommand(Request request) {
        return null;
    }

    @Override
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }
}
