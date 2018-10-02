/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.api.policies;

import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;

/**
 * An edit policy to erase correctly target feedback.
 * 
 * @author mchauvin
 */
// Copied from org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy.
// CHECKSTYLE:OFF
public abstract class FlowLayoutEditPolicy extends org.eclipse.gef.editpolicies.FlowLayoutEditPolicy {

    /**
     * Override to erase in case of GMF drop request, see
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=276033. (non-Javadoc)
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseTargetFeedback(final Request request) {
        super.eraseTargetFeedback(request);
        if (RequestConstants.REQ_DROP.equals(request.getType())) {
            eraseLayoutTargetFeedback(request);
        }
    }

}
// CHECKSTYLE:ON
