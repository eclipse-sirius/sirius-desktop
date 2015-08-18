/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Monitors VSMs installed in Eclipse plug-ins and registered through the
 * appropriate extension point.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class PluginMonitor extends AbstractViewpointResourceMonitor {
    /**
     * The identifier for the extension point used to register VSMs.
     */
    public static final String VSM_REGISTRATION_EXTENSION_POINT = "org.eclipse.sirius.viewpointSpecificationModel"; //$NON-NLS-1$

    /**
     * The bundle listener which detects VSMs registered in bundles which
     * dynamically installed or un-installed.
     */
    private final class SiriusRegistrationBundleListener implements BundleListener {
        /**
         * {@inheritDoc}
         */
        public void bundleChanged(BundleEvent event) {
            Bundle bundle = event.getBundle();
            // FIXME Study OSGi's bundle life-cycle to understand which state
            // and events to listen to and how to react to them.
            switch (event.getType()) {
            case BundleEvent.INSTALLED:
                addAllRegisteredVSMs(bundle);
                break;
            case BundleEvent.UNINSTALLED:
                removeAllRegisteredVSMs(bundle);
                break;
            default:
                // Ignore.
                break;
            }
        }

        private void addAllRegisteredVSMs(Bundle bundle) {
            Set<URI> discovered = findRegisteredVSMs(bundle);
            notifyResourcesEvents(Collections.<URI> emptySet(), discovered, Collections.<URI> emptySet());
        }

        private void removeAllRegisteredVSMs(Bundle bundle) {
            Set<URI> discovered = findRegisteredVSMs(bundle);
            notifyResourcesEvents(discovered, Collections.<URI> emptySet(), Collections.<URI> emptySet());
        }
    }

    /**
     * The extension registry to use to discover the registered VSMs.
     */
    private final IExtensionRegistry extensionRegistry;

    /**
     * The handler used to check if the declared resources are of a supported
     * type.
     */
    private final ViewpointResourceHandler resourceHandler;

    /**
     * The listener used for dynamic bundle installation support.
     */
    private final BundleListener bundleListener = new SiriusRegistrationBundleListener();

    /**
     * Constructor.
     * 
     * @param extensionRegistry
     *            the extension registry to use to discover the registered VSMs.
     * @param resourceType
     *            the handler used to check if the declared resources are of a
     *            supported type.
     */
    public PluginMonitor(IExtensionRegistry extensionRegistry, ViewpointResourceHandler resourceType) {
        this.extensionRegistry = Preconditions.checkNotNull(extensionRegistry);
        this.resourceHandler = Preconditions.checkNotNull(resourceType);
    }

    /**
     * {@inheritDoc}
     */
    public void start() {
        this.running = true;
        detectRegisteredVSMs();
        registerBundleListener();
    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
        unregisterBundleListener();
        this.running = false;
    }

    private void registerBundleListener() {
        BundleContext context = SiriusPlugin.getDefault().getBundle().getBundleContext();
        context.addBundleListener(this.bundleListener);
    }

    private void unregisterBundleListener() {
        BundleContext context = SiriusPlugin.getDefault().getBundle().getBundleContext();
        context.removeBundleListener(this.bundleListener);
    }

    private void detectRegisteredVSMs() {
        IConfigurationElement[] elements = extensionRegistry.getConfigurationElementsFor(VSM_REGISTRATION_EXTENSION_POINT);
        Set<URI> discovered = detectRegisteredVSMs(elements);
        notifyResourcesEvents(Collections.<URI> emptySet(), discovered, Collections.<URI> emptySet());
    }

    private Set<URI> findRegisteredVSMs(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        IContributor contrib = ContributorFactoryOSGi.createContributor(bundle);
        IExtension[] extensions = extensionRegistry.getExtensions(contrib.getName());
        if (extensions == null) {
            return Collections.emptySet();
        } else {
            Set<URI> discovered = Sets.newHashSet();
            for (IExtension ext : extensions) {
                if (ext.getExtensionPointUniqueIdentifier().equals(VSM_REGISTRATION_EXTENSION_POINT)) {
                    Set<URI> registered = detectRegisteredVSMs(ext.getConfigurationElements());
                    discovered.addAll(registered);
                }
            }
            return discovered;
        }
    }

    private Set<URI> detectRegisteredVSMs(IConfigurationElement[] elements) {
        Preconditions.checkNotNull(elements);
        Set<URI> discovered = Sets.newLinkedHashSet();
        for (IConfigurationElement element : elements) {
            String contributingPlugin = element.getContributor().getName();
            String localPath = element.getAttribute("path"); //$NON-NLS-1$
            if (localPath == null) {
                reportWarning("Missing 'path' attribute for VSM definition in " + contributingPlugin + "; ignoring this definition.");
                continue;
            }
            try {
                URI uri = URI.createPlatformPluginURI("/" + contributingPlugin + "/" + localPath, true); //$NON-NLS-1$ //$NON-NLS-2$
                if (!pluginResourceExists(contributingPlugin, localPath)) {
                    reportWarning("Not resource found at specified location " + uri + " in " + contributingPlugin);
                    continue;
                }
                if (!resourceHandler.handles(uri)) {
                    reportWarning("Registered resource is not of a supported type: " + uri);
                    continue;
                }
                boolean newEntry = discovered.add(uri);
                if (!newEntry) {
                    reportWarning("Duplicate registration of " + uri + " in " + contributingPlugin);
                }
            } catch (IllegalArgumentException iea) {
                reportWarning("Invalid 'path' attribute for VSM definition in " + contributingPlugin + "; ignoring this definition.");
            }
        }
        return discovered;
    }

    private boolean pluginResourceExists(String pluginId, String localPath) {
        Bundle bundle = Platform.getBundle(pluginId);
        if (bundle != null) {
            return FileLocator.find(bundle, new Path(localPath), null) != null;
        } else {
            return false;
        }
    }

    private void reportWarning(String message) {
        SiriusPlugin.getDefault().warning(message, null);
    }
}
