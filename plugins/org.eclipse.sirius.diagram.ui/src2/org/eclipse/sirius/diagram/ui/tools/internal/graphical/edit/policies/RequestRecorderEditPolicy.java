/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

/**
 * 
 * An edit policy recording every ChangeBoundRequest sent to the editpart and
 * transmitting it to a recorder.
 * 
 * @author cedric brun <cedric.brun@obeo.fr>
 * 
 */
public class RequestRecorderEditPolicy extends AbstractEditPolicy {

    /** The recorder that should process all requests. */
    private ChangeBoundRequestRecorder allRequests;

    /**
     * Creates a new Request Recorder.
     * 
     * @param requestRecorder
     *            the main registry that contains all requests.
     */
    public RequestRecorderEditPolicy(final ChangeBoundRequestRecorder requestRecorder) {
        this.allRequests = requestRecorder;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest
     * (org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart
     * (org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse
     * .gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        this.allRequests.processRequest(request, getHost());
        return null;
    }
}
