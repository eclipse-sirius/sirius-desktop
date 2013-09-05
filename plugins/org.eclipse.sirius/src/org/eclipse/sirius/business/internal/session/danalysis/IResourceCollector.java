/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Simple interface to allow clients to handle by themselves the detection of
 * resources dependencies (i.e. given a resource, which other resources does it
 * refer to).
 * 
 * @see DAnalysisSessionImpl#setResourceCollector(IResourceCollector)
 * 
 * @author pcdavid
 */
public interface IResourceCollector {

    /**
     * Returns all the resources referenced directly or indirectly to the
     * specified one.
     * 
     * @param resource
     *            a resource.
     * @return all the resources associated to res, including res itself.
     */
    Collection<Resource> getAllReferencedResources(Resource resource);

    /**
     * Get from the current {@link org.eclipse.emf.ecore.resource.ResourceSet}
     * all {@link Resource} whose content references directly or indirectly
     * content of the specified {@link Resource}.
     * 
     * @param resource
     *            the {@link Resource} for which get all referencing
     *            {@link Resource}s.
     * @return all referencing {@link Resource}s
     */
    Collection<Resource> getAllReferencingResources(Resource resource);

    /**
     * Dispose this {@link IResourceCollector}.
     */
    void dispose();
}
