/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.ordering;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingProvider;

/**
 * This class registers all {@link ViewOrderingProvider}s.
 * 
 * @author ymortier
 */
public final class ViewOrderingProviderRegistry {

    /** The shared instance. */
    private static ViewOrderingProviderRegistry instance = new ViewOrderingProviderRegistry();

    /**
     * Name of the extension point to parse for style configuration providers.
     */
    private static final String VIEW_ORDERING_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.diagram.ui.viewOrderingProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "viewOrderingProvider"; //$NON-NLS-1$

    /** The providers. */
    private Collection<ViewOrderingProvider> viewOrderingProviders;

    /**
     * Avoid instantiation from outside.
     */
    private ViewOrderingProviderRegistry() {
        this.parseExtensionMetadata();
    }

    /**
     * This will parse the currently running platform for extensions and store all the match engines that can be found.
     */
    private void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            this.viewOrderingProviders = new LinkedList<ViewOrderingProvider>();
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(VIEW_ORDERING_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    if (configElement.getName().equals(TAG_ENGINE)) {
                        try {
                            final ViewOrderingProvider viewOrderingProvider = (ViewOrderingProvider) configElement.createExecutableExtension("providerClass"); //$NON-NLS-1$
                            this.viewOrderingProviders.add(viewOrderingProvider);
                        } catch (final CoreException e) {
                            DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ViewOrderingProviderRegistry_viewOrderingProvider_loadingProblem, configElement.getName()), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Return the shared instance.
     * 
     * @return the shared instance.
     */
    public static ViewOrderingProviderRegistry getInstance() {
        return instance;
    }

    /**
     * Return all providers.
     * 
     * @return all providers.
     */
    public Collection<ViewOrderingProvider> getAllProviders() {
        return this.viewOrderingProviders;
    }

    /**
     * Return the {@link ViewOrderingProvider} to use for the specified mapping. Return <code>null</code> if no
     * {@link ViewOrderingProvider} is available for the specified mapping.
     * 
     * @param mapping
     *            the mapping.
     * @return the {@link ViewOrderingProvider} to use for the specified mapping.
     */
    public ViewOrderingProvider getProvider(final DiagramElementMapping mapping) {
        final Iterator<ViewOrderingProvider> iterProviders = this.getAllProviders().iterator();
        while (iterProviders.hasNext()) {
            final ViewOrderingProvider currentProvider = iterProviders.next();
            // try {
            if (currentProvider.provides(mapping)) {
                return currentProvider;
            }
            // } catch (final RuntimeException e) {
            // DiagramPlugin.getDefault().logWarning(
            // "The view ordering provider " +
            // currentProvider.getClass().getName()
            // + " has been removed from the ViewOrderingProviderRegistry since
            // it threw an Exception in its provides method.", e);
            // }
        }
        return null;
    }
}
