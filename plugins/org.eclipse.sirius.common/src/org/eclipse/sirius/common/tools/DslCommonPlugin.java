/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.osgi.framework.BundleContext;

import org.eclipse.sirius.common.tools.api.ecore.WorkspaceEPackageRegistry;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryRegistry;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistryListener;
import org.eclipse.sirius.common.tools.internal.ecore.DynamicPackageRegistryReader;
import org.eclipse.sirius.common.tools.internal.editing.EditingDomainFactoryRegistryListener;

/**
 * The activator.
 * 
 * @author ymortier
 */
public class DslCommonPlugin extends Plugin {

    /** The plugin id. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.common";

    /** The profiler. */
    public static final TimeProfiler PROFILER = new TimeProfiler2();

    // The shared instance
    private static DslCommonPlugin plugin;

    /** The registry listener that will be used to listen to extension changes. */
    private EditingDomainFactoryRegistryListener editingDomainFactoryRegistryListener = new EditingDomainFactoryRegistryListener();

    /**
     * The registry listener that will be used to react to changes against the
     * proposal providers extension point.
     */
    private final ProposalProviderRegistryListener proposalProviderRegistryListener = new ProposalProviderRegistryListener();

    private WorkspaceEPackageRegistry workspaceEPackageRegistry;

    /**
     * The constructor.
     */
    public DslCommonPlugin() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        initExtensionRegistries();

        new DynamicPackageRegistryReader().readRegistry();

        workspaceEPackageRegistry = new WorkspaceEPackageRegistry(true);
        workspaceEPackageRegistry.init(ResourcesPlugin.getWorkspace());
    }

    /**
     * Initializes the extension registries and their listeners for this plugin.
     */
    private void initExtensionRegistries() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        registry.addRegistryChangeListener(editingDomainFactoryRegistryListener, EditingDomainFactoryRegistryListener.EDITING_DOMAIN_FACTORY_EXTENSION_POINT);
        editingDomainFactoryRegistryListener.parseInitialContributions();

        registry.addListener(proposalProviderRegistryListener, ProposalProviderRegistryListener.PROPOSAL_PROVIDER_EXTENSION_POINT);
        proposalProviderRegistryListener.parseInitialContributions();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);

        clearExtensionRegistries();

        workspaceEPackageRegistry.dispose(ResourcesPlugin.getWorkspace());
        workspaceEPackageRegistry = null;
    }

    /**
     * Clears the extension registries for this plugin and remove their
     * corresponding listeners.
     */
    private void clearExtensionRegistries() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        registry.removeRegistryChangeListener(editingDomainFactoryRegistryListener);
        EditingDomainFactoryRegistry.clearRegistry();

        registry.removeListener(proposalProviderRegistryListener);
        ProposalProviderRegistry.clearRegistry();
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static DslCommonPlugin getDefault() {
        return plugin;
    }

    /**
     * Logs the given message and throwable as an error.
     * 
     * @param message
     *            the message.
     * @param t
     *            the exception.
     */
    public void error(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
        this.getLog().log(status);
    }

    /**
     * Logs the given message and throwable as a warning.
     * 
     * @param message
     *            the message.
     * @param t
     *            the exception.
     */
    public void warning(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message, t);
        this.getLog().log(status);
    }

    /**
     * Logs the given message as an information.
     * 
     * @param message
     *            the message.
     */
    public void info(final String message) {
        final IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message);
        this.getLog().log(status);
    }

    /**
     * Get a {@link Registry} which aggregate EPackage from EMF registry and
     * EPackage from workspace.
     * 
     * @return a {@link Registry} which aggregate EPackage from EMF registry and
     *         EPackage from workspace
     */
    public Registry getWorkspaceEPackageRegistry() {
        return workspaceEPackageRegistry;
    }
}
