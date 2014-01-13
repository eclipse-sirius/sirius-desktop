/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.graphical.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy;

/**
 * The specific Edit Policy.
 * 
 * @author ymortier
 */
public class AirNoteAttachmentEditPolicy extends ConnectionEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy#createDeleteViewCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command createDeleteViewCommand(final GroupRequest deleteRequest) {
        return super.createDeleteViewCommand(deleteRequest);
    }
}
