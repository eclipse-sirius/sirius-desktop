/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry.monitoring;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ISiriusComponent;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A monitor which provides compatibility with the legacy APIs used for Sirius
 * registration.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class LegacyPluginMonitor extends AbstractViewpointResourceMonitor {
    private final ViewpointRegistry registry;

    private final Set<URI> knownURIs = Sets.newHashSet();

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to which the VSMs are registered.
     */
    public LegacyPluginMonitor(ViewpointRegistry registry) {
        this.registry = registry;
    }

    /**
     * {@inheritDoc}
     */
    public void start() {
        this.running = true;
        EclipseUtil.getExtensionPlugins(ISiriusComponent.CLASS_TO_EXTEND, ISiriusComponent.ID, ISiriusComponent.CLASS_ATTRIBUTE);
    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
        this.running = false;
    }

    /**
     * Register all components in the resource. This method should be called on
     * org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     * 
     * @param modelerModelResourcePath
     *            the platform file path ("pluginname/rep1/rep2/file.odesign)
     * @return the added Viewpoints;
     */
    public Set<Viewpoint> registerFromPlugin(String modelerModelResourcePath) {
        try {
            final URI uri = URI.createPlatformPluginURI(modelerModelResourcePath, true);
            // if (SiriusURIQuery.isValidSiriusURI(uri)) {
            return registerFromPlugin(uri);
            // }
        } catch (IllegalArgumentException e) {
            // Do nothing if the URI is invalid.
        }
        return Collections.emptySet();
    }

    private Set<Viewpoint> registerFromPlugin(final URI uri) {
        if (knownURIs.contains(uri)) {
            notifyResourcesEvents(Collections.<URI> emptySet(), Collections.<URI> emptySet(), Collections.singleton(uri));
        } else {
            notifyResourcesEvents(Collections.<URI> emptySet(), Collections.singleton(uri), Collections.<URI> emptySet());
        }
        return ImmutableSet.copyOf(Iterables.filter(registry.getViewpoints(), new Predicate<Viewpoint>() {
            public boolean apply(Viewpoint input) {
                Resource r = input.eResource();
                return r != null && r.getURI().equals(uri);
            }
        }));
    }

    /**
     * Dispose a {@link Viewpoint}. This method should be called on
     * {@link org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)}
     * 
     * @param viewpoint
     *            the viewpoint to dispose
     */
    public void disposeFromPlugin(Viewpoint viewpoint) {
        if (viewpoint != null) {
            Option<URI> uri = new ViewpointQuery(viewpoint).getViewpointURI();
            if (uri.some()) {
                notifyResourcesEvents(Collections.singleton(uri.get()), Collections.<URI> emptySet(), Collections.<URI> emptySet());
                knownURIs.remove(uri.get());
            }
        }
    }
}
