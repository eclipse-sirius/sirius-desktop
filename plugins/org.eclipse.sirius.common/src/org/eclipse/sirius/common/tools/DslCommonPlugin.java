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

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryRegistry;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistryListener;
import org.eclipse.sirius.common.tools.internal.ecore.DynamicPackageRegistryReader;
import org.eclipse.sirius.common.tools.internal.editing.EditingDomainFactoryRegistryListener;
import org.osgi.framework.BundleContext;

/**
 * The activator.
 * 
 * @author ymortier
 */
public class DslCommonPlugin extends EMFPlugin {

    /** The plugin id. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.common"; //$NON-NLS-1$
    
    /** Keep track of the singleton.. */
    public static final DslCommonPlugin INSTANCE = new DslCommonPlugin();

    /** The profiler. */
    public static final TimeProfiler PROFILER = new TimeProfiler2();

    private static Implementation plugin;

    /**
     * The constructor.
     */
    public DslCommonPlugin() {
        super(new ResourceLocator[0]);
    }

    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getDefault() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipsePlugin {

        /**
         * The registry listener that will be used to listen to extension
         * changes.
         */
        private EditingDomainFactoryRegistryListener editingDomainFactoryRegistryListener = new EditingDomainFactoryRegistryListener();

        /**
         * The registry listener that will be used to react to changes against
         * the proposal providers extension point.
         */
        private final ProposalProviderRegistryListener proposalProviderRegistryListener = new ProposalProviderRegistryListener();

        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            initExtensionRegistries();
            new DynamicPackageRegistryReader().readRegistry();
        }

        /**
         * Initializes the extension registries and their listeners for this
         * plugin.
         */
        private void initExtensionRegistries() {
            IExtensionRegistry registry = Platform.getExtensionRegistry();

            registry.addRegistryChangeListener(editingDomainFactoryRegistryListener, EditingDomainFactoryRegistryListener.EDITING_DOMAIN_FACTORY_EXTENSION_POINT);
            editingDomainFactoryRegistryListener.parseInitialContributions();

            registry.addListener(proposalProviderRegistryListener, ProposalProviderRegistryListener.PROPOSAL_PROVIDER_EXTENSION_POINT);
            proposalProviderRegistryListener.parseInitialContributions();
        }

        @Override
        public void stop(final BundleContext context) throws Exception {
            super.stop(context);
            clearExtensionRegistries();
        }

        /**
         * Clears the extension registries for this plugin and remove their
         * corresponding listeners.
         */
        private void clearExtensionRegistries() {
            IExtensionRegistry registry = Platform.getExtensionRegistry();

            registry.removeRegistryChangeListener(editingDomainFactoryRegistryListener);
            EditingDomainFactoryRegistry.clearRegistry();

            registry.removeListener(proposalProviderRegistryListener);
            ProposalProviderRegistry.clearRegistry();
        }

        /**
         * Logs the given message and throwable as an error.
         * 
         * @param message
         *            the message.
         * @param t
         *            the exception.
         */
        public void error(String message, Throwable t) {
            IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
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
        public void warning(String message, Throwable t) {
            IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message, t);
            this.getLog().log(status);
        }

        /**
         * Logs the given message as an information.
         * 
         * @param message
         *            the message.
         */
        public void info(String message) {
            IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message);
            this.getLog().log(status);
        }
    }
}
