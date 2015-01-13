/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Default {@link Predicate} to notify the SessionListener from the
 * DAnalysisSessionImpl only for {@link Resource} from
 * Session#getAllSessionResources() and Session#getSemanticResources().
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResourceSyncClientNotificationFilter implements Predicate<Resource> {

    private final Resource changedResource;

    /**
     * Default constructor.
     * 
     * @param changedResource
     *            the changed {@link Resource} notified by the ResourceSetSync
     */
    public ResourceSyncClientNotificationFilter(Resource changedResource) {
        this.changedResource = Preconditions.checkNotNull(changedResource);
    }

    @Override
    public boolean apply(final Resource input) {
        boolean same = false;
        if (changedResource == input) {
            same = true;
        } else if (changedResource.getURI() != null && input.getURI() != null) {
            same = changedResource.getURI().equals(input.getURI());
        }
        return same;
    }
}
