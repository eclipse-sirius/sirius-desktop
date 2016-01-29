/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.strategy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Represents an extension contribution to the resource strategy.</br>
 * Implementations of this API allow memory or CPU optimizations linked to the
 * resource.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public interface ResourceStrategy {

    public enum ResourceStrategyType {
        /**
         * This method is called before removing the resource from the
         * resourceSet. </br>
         * It corresponds to
         * {@link ResourceStrategy.releaseResourceAtResourceSetDispose} method
         */
        RELEASE_RESOURCE_AT_RESOURCESET_DISPOSE
    }

    /**
     * Make something to replace the Resource.unload before the resource is
     * finally removed by the resourceSet and not used any more by the session.
     * A basic but non optimized implementation would just unload the
     * resource.</br>
     * <code>resource</code> and <code>resource.getResourceSet()</code> should
     * not be null.
     * 
     * @param resource
     *            the resource to unload
     * @param monitor
     *            the progress monitor
     * @return the status
     */
    IStatus releaseResourceAtResourceSetDispose(Resource resource, IProgressMonitor monitor);

    /**
     * Check if it can handle for a given resourceStrategyType. <br>
     * This method should not be called if the resource is loaded yet but,
     * instead, call {@link canHandle(Resource , ResourceStrategyType)}.
     * 
     * @param resourceURI
     *            the resource URI. Must not be null
     * @param resourceStrategyType
     *            the type of action to handle
     * @return true if it can handle.
     */
    boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType);

    /**
     * Check if it can handle for a given resourceStrategyType.
     * 
     * @param resource
     *            the resource
     * @param resourceStrategyType
     *            the type of action to handle
     * @return true if it can handle.
     */
    boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType);

}
