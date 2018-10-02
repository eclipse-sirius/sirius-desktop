/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.resource.strategy;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategy.ResourceStrategyType;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.resource.strategy.DefaultResourceStrategyImpl;

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

    private ResourceStrategy defaultResourceStrategy = new DefaultResourceStrategyImpl();

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
     * provided. </BR>
     * If several is found, the first is returned.
     * 
     * @param resStrategyType
     *            the type of resource Strategy
     * @param resource
     *            the resource
     * @return the corresponding {@link ResourceStrategy}
     */
    private ResourceStrategy getResourceStrategy(ResourceStrategy.ResourceStrategyType resStrategyType, Resource resource) {
        for (ResourceStrategy iResourceStrategy : providedResourceStrategies) {
            if (iResourceStrategy.canHandle(resource, resStrategyType)) {
                return iResourceStrategy;
            }
        }
        return defaultResourceStrategy;
    }

    /**
     * Get all the compatible {@link ResourceStrategy}. </br>
     * If not found among the registered {@link ResourceStrategy} a default is
     * provided.
     * 
     * @param resStrategyType
     *            the type of resource Strategy
     * @param resourceURI
     *            the URI of the resource
     * @return the corresponding {@link ResourceStrategy}
     */
    private Collection<ResourceStrategy> getResourceStrategies(ResourceStrategy.ResourceStrategyType resStrategyType, URI resourceURI) {
        Set<ResourceStrategy> compatibleResourceStrategies = new LinkedHashSet<ResourceStrategy>();
        for (ResourceStrategy iResourceStrategy : providedResourceStrategies) {
            if (iResourceStrategy.canHandle(resourceURI, resStrategyType)) {
                compatibleResourceStrategies.add(iResourceStrategy);
            }
        }
        if (compatibleResourceStrategies.isEmpty()) {
            compatibleResourceStrategies.add(defaultResourceStrategy);
        }
        return compatibleResourceStrategies;
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

    /**
     * Check if the URI corresponds to a potential semantic model. For that, all
     * the compatible resource strategies must consider the model with this
     * <code>uri</code> as a potential semantic resource.
     * 
     * @param uri
     *            the uri to test
     *
     * @return <code>false</code> if the model with this <code>uri</code> should
     *         be ignored, <code>true</code> otherwise.
     */
    public boolean isPotentialSemanticResource(URI uri) {
        for (ResourceStrategy resourceStrategy : getResourceStrategies(ResourceStrategyType.SEMANTIC_RESOURCE, uri)) {
            if (!resourceStrategy.isPotentialSemanticResource(uri)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the URI corresponds to a loadable model. For that, all the
     * compatible resource strategies must consider the model with this
     * <code>uri</code> as loadable.<BR>
     * The method {@link #isPotentialSemanticResource(URI)} must be checked
     * before.
     * 
     * @param uri
     *            the uri to test
     * @param session
     *            the session to use for trying to load the model
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public boolean isLoadableModel(URI uri, Session session) {
        for (ResourceStrategy resourceStrategy : getResourceStrategies(ResourceStrategyType.SEMANTIC_RESOURCE, uri)) {
            if (!resourceStrategy.isLoadableModel(uri, session)) {
                return false;
            }
        }
        return true;
    }
}
