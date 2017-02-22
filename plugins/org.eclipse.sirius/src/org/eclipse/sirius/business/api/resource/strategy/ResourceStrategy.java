/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.resource.strategy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;

/**
 * Represents an extension contribution to the resource strategy.</br>
 * Implementations of this API allow memory or CPU optimizations linked to the
 * resource.<BR>
 * A resource strategy can be contribute only for one type of (
 * {@link ResourceStrategyType} or for several.
 * {@link #canHandle(Resource, ResourceStrategyType)} and
 * {@link #canHandle(URI, ResourceStrategyType)} should be as accurate as
 * possible to avoid being too intrusive.<BR>
 * An abstract implementation exists, {@link AbstractResourceStrategyImpl},
 * especially for the extension point "org.eclipse.sirius.resourceStrategy".
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public interface ResourceStrategy {

    /**
     * All the type of ResourceStrategy. Each type is associated to one or more
     * specific methods. The result of
     * {@link ResourceStrategy#canHandle(Resource, ResourceStrategyType)} or
     * {@link ResourceStrategy#canHandle(URI, ResourceStrategyType)} can be
     * conditioned according to this type.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    enum ResourceStrategyType {
        /**
         * To contribute a resource strategy that handles this type, you need to
         * implement method
         * {@link ResourceStrategy#releaseResourceAtResourceSetDispose}.<BR>
         * This method is called before removing the resource from the
         * resourceSet. It is used by
         * {@link ResourceStrategyRegistry#unloadAtResourceSetDispose(Resource, IProgressMonitor)}
         * .
         */
        RELEASE_RESOURCE_AT_RESOURCESET_DISPOSE,

        /**
         * To contribute a resource strategy that handles this type, you need to
         * implement methods
         * {@link ResourceStrategy#isPotentialSemanticResource(URI)} and
         * {@link ResourceStrategy#isLoadableModel(URI, Session)}.<BR>
         * These methods are used to identify if a resource must be considered
         * as a semantic resource for a Modeling Project. It is used by
         * {@link ResourceStrategyRegistry#isPotentialSemanticResource(URI)} and
         * {@link ResourceStrategyRegistry#isLoadableModel(URI, Session)}.
         */
        SEMANTIC_RESOURCE
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
     * Check if the URI corresponds to a potential semantic model.
     * 
     * @param uri
     *            the uri to test
     *
     * @return <code>false</code> if the model with this <code>uri</code> should
     *         be ignored, <code>true</code> otherwise.
     */
    boolean isPotentialSemanticResource(URI uri);

    /**
     * Check if the URI corresponds to a loadable model. The method
     * {@link #isPotentialSemanticResource(URI)} must be checked before.
     * 
     * @param uri
     *            the uri to test
     * @param session
     *            the session to use for trying to load the model
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    boolean isLoadableModel(URI uri, Session session);

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
