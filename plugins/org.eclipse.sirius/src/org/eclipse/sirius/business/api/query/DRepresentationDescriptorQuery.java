/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DRepresentationDescriptor} as a starting point.
 * 
 * @author lfasani
 * 
 */
public class DRepresentationDescriptorQuery {

    private DRepresentationDescriptor repDescriptor;

    /**
     * Create a new query.
     * 
     * @param repDescriptor
     *            the element to query.
     */
    public DRepresentationDescriptorQuery(DRepresentationDescriptor repDescriptor) {
        this.repDescriptor = repDescriptor;
    }

    /**
     * Check if the current representationDescriptor is a dangling one, ie if
     * its target element is null or if it does not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDangling() {
        return repDescriptor.getTarget() == null || SessionManager.INSTANCE.getSession(repDescriptor.getTarget()) == null;
    }

}
