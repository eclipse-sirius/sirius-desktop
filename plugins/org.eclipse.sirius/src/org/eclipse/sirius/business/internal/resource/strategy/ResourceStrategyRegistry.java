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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.internal.resource.strategy.ResourceStrategy.ResourceStrategyType;

/**
 * Manage the available {@link ResourceStrategy}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class ResourceStrategyRegistry {

    /**
     * The singleton instance of the ResourceStrategyRegistry.
     */
    private static final ResourceStrategyRegistry INSTANCE = new ResourceStrategyRegistry();

    private ResourceStrategy defaultResourceStrategy = new DefaultOptimizedResourceStrategyImpl();

    private Set<ResourceStrategy> providedResourceStrategies = new LinkedHashSet<ResourceStrategy>();

    private ResourceStrategyRegistry() {
    }

    public static ResourceStrategyRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * Add a ResourceStrategy to the registry.
     * 
     * @param resourceStrategy
     *            the ResourceStrategy
     */
    public void addResourceStrategy(ResourceStrategy resourceStrategy) {
        providedResourceStrategies.add(resourceStrategy);
    }

    /**
     * Add a ResourceStrategy to the registry.
     * 
     * @param resourceStrategy
     *            the ResourceStrategy
     */
    public void removeResourceStrategy(ResourceStrategy resourceStrategy) {
        providedResourceStrategies.remove(resourceStrategy);
    }

    /**
     * Add a ResourceStrategy to the registry.
     * 
     * @return the provided Resource Strategies
     */
    public Collection<ResourceStrategy> getProvidedResourceStrategies() {
        return providedResourceStrategies;
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin
     * stopping.
     */
    public void dispose() {
        providedResourceStrategies.clear();
    }

    /**
     * Get the compatible {@link ResourceStrategy}. </br>
     * If not found among the registered {@link ResourceStrategy} a default is
     * provided.
     * 
     * @param resStrategyType
     *            the type of resource Strategy
     * @param resource
     *            the resource
     * @return the corresponding {@link ResourceStrategy}
     */
    private ResourceStrategy getResourceStrategy(ResourceStrategy.ResourceStrategyType resStrategyType, Resource resource) {
        for (ResourceStrategy iResourceStrategy : providedResourceStrategies) {
            if (iResourceStrategy.canHandle(resource, resStrategyType))
                return iResourceStrategy;
        }
        return defaultResourceStrategy;
    }

    /**
     * Get the compatible {@link ResourceStrategy}. </br>
     * If not found among the registered {@link ResourceStrategy} a default is
     * provided.
     * 
     * @param resStrategyType
     *            the type of resource Strategy
     * @param resourceURI
     *            the resource URI if resource is null
     * @return the corresponding {@link ResourceStrategy}
     */
    private ResourceStrategy getResourceStrategy(ResourceStrategy.ResourceStrategyType resStrategyType, URI resourceURI) {
        for (ResourceStrategy iResourceStrategy : providedResourceStrategies) {
            if (iResourceStrategy.canHandle(resourceURI, resStrategyType))
                return iResourceStrategy;
        }
        return defaultResourceStrategy;
    }

    /**
     * Unload the resource before it is finally removed by the resourceSet and
     * not used any more by the session. A basic but non optimized
     * implementation would just unload the resource.</br>
     * <code>resource</code> and <code>resource.getResourceSet()</code> should
     * not be null.
     * 
     * @param resource
     *            the resource to unload
     * @param monitor
     *            the progress monitor
     * @return the status
     */
    public IStatus unloadAtResourceSetDispose(Resource resource, IProgressMonitor monitor) {
        ResourceStrategy resourceStrategy = getResourceStrategy(ResourceStrategyType.RELEASE_RESOURCE_AT_RESOURCESET_DISPOSE, resource);
        return resourceStrategy.releaseResourceAtResourceSetDispose(resource, monitor);
    }
}
