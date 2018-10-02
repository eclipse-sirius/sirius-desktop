/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.refresh;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.sirius.business.internal.helper.refresh.AbstractProviderDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.helper.refresh.RefreshExtensionProviderDescriptor;

/**
 * This class provides services to enable the extension of the refresh
 * mechanism.
 * 
 * @author ymortier
 */
public final class RefreshExtensionService implements IRefreshExtension {

    /** Name of the extension point to parse for refresh extension providers. */
    private static final String REFRESH_EXTENSION_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.refreshExtensionProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "refreshExtensionProvider"; //$NON-NLS-1$

    /** The instance of the service. */
    private static RefreshExtensionService instance = new RefreshExtensionService();

    /**
     * All providers (instance of {@link RefreshExtensionProviderDescriptor}.
     */
    private List<RefreshExtensionProviderDescriptor> providers;

    /** <code>true</code> if providers are loaded. */
    private boolean providersLoaded;

    /**
     * Avoid instantiation from external.
     */
    private RefreshExtensionService() {
        this.providers = new LinkedList<RefreshExtensionProviderDescriptor>();
    }

    /**
     * Return the service instance.
     * 
     * @return the service instance.
     */
    public static RefreshExtensionService getInstance() {
        return RefreshExtensionService.instance;
    }

    /**
     * Loads all providers.
     */
    protected void loadProviders() {
        this.providers.clear();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(REFRESH_EXTENSION_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final AbstractProviderDescriptor desc = this.parseEngine(configElement);
                    if (desc != null) {
                        this.providers.add((RefreshExtensionProviderDescriptor) desc);
                    }
                }
            }
            Collections.sort(this.providers);
        }
        this.providersLoaded = true;
    }

    /**
     * Parse the configuration element.
     * 
     * @param configurationElement
     *            the configuration element.
     * @return the provider descriptor.
     */
    private AbstractProviderDescriptor parseEngine(final IConfigurationElement configurationElement) {
        if (!configurationElement.getName().equals(TAG_ENGINE)) {
            return null;
        }
        final AbstractProviderDescriptor desc = new RefreshExtensionProviderDescriptor(configurationElement);
        return desc;
    }

    /**
     * Dispose all providers.
     */
    protected void dispose() {
        this.providers.clear();
        this.providersLoaded = false;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void beforeRefresh(final DDiagram viewPoint) {
        final ListIterator<RefreshExtensionProviderDescriptor> iterProviders = this.getProviders().listIterator();

        while (iterProviders.hasNext()) {
            final RefreshExtensionProviderDescriptor currentProviderDecsriptor = iterProviders.next();
            final IRefreshExtensionProvider refreshExtensionProvider = currentProviderDecsriptor.getProviderInstance();

            if (RefreshExtensionService.safeProvides(refreshExtensionProvider, viewPoint)) {
                final IRefreshExtension refreshExtension = RefreshExtensionService.safeGetRefreshExtension(refreshExtensionProvider, viewPoint);
                RefreshExtensionService.safeBeforeRefresh(refreshExtension, viewPoint);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void postRefresh(final DDiagram viewPoint) {
        final ListIterator<RefreshExtensionProviderDescriptor> iterProviders = this.getProviders().listIterator();

        while (iterProviders.hasNext()) {
            final RefreshExtensionProviderDescriptor currentProviderDecsriptor = iterProviders.next();
            final IRefreshExtensionProvider refreshExtensionProvider = currentProviderDecsriptor.getProviderInstance();

            if (RefreshExtensionService.safeProvides(refreshExtensionProvider, viewPoint)) {
                final IRefreshExtension refreshExtension = RefreshExtensionService.safeGetRefreshExtension(refreshExtensionProvider, viewPoint);
                RefreshExtensionService.safePostRefresh(refreshExtension, viewPoint);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean aroundRefresh(DDiagram dDiagram) {
        final ListIterator<RefreshExtensionProviderDescriptor> iterProviders = this.getProviders().listIterator();

        boolean oneOverride = false;

        while (iterProviders.hasNext()) {
            final RefreshExtensionProviderDescriptor currentProviderDecsriptor = iterProviders.next();
            final IRefreshExtensionProvider refreshExtensionProvider = currentProviderDecsriptor.getProviderInstance();

            if (RefreshExtensionService.safeProvides(refreshExtensionProvider, dDiagram)) {
                final IRefreshExtension refreshExtension = RefreshExtensionService.safeGetRefreshExtension(refreshExtensionProvider, dDiagram);
                oneOverride = refreshExtension.aroundRefresh(dDiagram) || oneOverride;
            }
        }
        return oneOverride;
    }

    /**
     * Get the providers.
     * 
     * @return the providers
     */
    public List<RefreshExtensionProviderDescriptor> getProviders() {
        if (!this.providersLoaded) {
            this.loadProviders();
        }
        return providers;
    }

    /**
     * Does an "exception safe" provides invocation. Returns <code>true</code>
     * if the specified provider provides a Refresh extension for the specified
     * {@link DDiagram}. Returns <code>false</code> if the provider doesn't
     * provide a refresh extension or if the provides method throws an
     * {@link Exception}.
     * 
     * @param refreshExtensionProvider
     *            the provider.
     * @param viewPoint
     *            the viewpoint.
     * @return <code>true</code> if the specified provider provides a Refresh
     *         extension for the specified {@link DDiagram}
     */
    protected static boolean safeProvides(final IRefreshExtensionProvider refreshExtensionProvider, final DDiagram viewPoint) {
        boolean result = false;
        if (refreshExtensionProvider != null) {
            result = refreshExtensionProvider.provides(viewPoint);
        }
        return result;
    }

    /**
     * Does an "exception safe" getRefreshExtension invocation. Returns a
     * refresh extension instance if the invocation doesn't throw an exception.
     * 
     * 
     * @param refreshExtensionProvider
     *            the provider.
     * @param viewPoint
     *            the viewpoint.
     * @return a refresh extension instance if the invocation doesn't throw an
     *         exception.
     */
    protected static IRefreshExtension safeGetRefreshExtension(final IRefreshExtensionProvider refreshExtensionProvider, final DDiagram viewPoint) {
        IRefreshExtension refreshExtension = null;
        if (refreshExtensionProvider != null) {
            refreshExtension = refreshExtensionProvider.getRefreshExtension(viewPoint);
        }
        return refreshExtension;
    }

    /**
     * Does an "exception safe" beforeRefresh invocation.
     * 
     * @param refreshExtension
     *            the extension element.
     * @param viewPoint
     *            the viewpoint to refresh.
     */
    private static void safeBeforeRefresh(final IRefreshExtension refreshExtension, final DDiagram viewPoint) {
        if (refreshExtension != null) {
            refreshExtension.beforeRefresh(viewPoint);
        }
    }

    /**
     * Does an "exception safe" afterRefresh invocation.
     * 
     * @param refreshExtension
     *            the extension element.
     * @param viewPoint
     *            the viewpoint to refresh.
     */
    private static void safePostRefresh(final IRefreshExtension refreshExtension, final DDiagram viewPoint) {
        if (refreshExtension != null) {
            refreshExtension.postRefresh(viewPoint);
        }
    }

}
