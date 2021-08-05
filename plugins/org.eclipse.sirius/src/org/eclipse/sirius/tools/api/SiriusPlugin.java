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
package org.eclipse.sirius.tools.api;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQueryProvider;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.business.internal.dialect.description.InterpretedExpressionQueryProviderRegistry;
import org.eclipse.sirius.business.internal.helper.delete.DeleteHookDescriptorRegistryListener;
import org.eclipse.sirius.business.internal.helper.task.ModelOperationManagerRegistryListener;
import org.eclipse.sirius.business.internal.migration.IMigrationHandler;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationRuleRegistryListener;
import org.eclipse.sirius.business.internal.resource.strategy.ResourceStrategyRegistryListener;
import org.eclipse.sirius.business.internal.session.factory.SessionFactoryRegistryListener;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessorsRegistry;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionRegistryListener;
import org.eclipse.sirius.tools.internal.validation.EValidatorAdapter;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.osgi.framework.BundleContext;

/**
 * Sirius plug-in.
 * 
 * @author ymortier
 */
public final class SiriusPlugin extends EMFPlugin {
    /**
     * Tell whether Eclipse is running or not.
     */
    public static final boolean IS_ECLIPSE_RUNNING;

    static {
        boolean result = false;
        // CHECKSTYLE:OFF
        try {
            result = Platform.isRunning();
        } catch (final Throwable exception) {
            // Assume that we aren't running.
        }
        // CHECKSTYLE:ON
        IS_ECLIPSE_RUNNING = result;
    }

    /** The id. */
    public static final String ID = "org.eclipse.sirius"; //$NON-NLS-1$

    /** Keep track of the singleton.. */
    public static final SiriusPlugin INSTANCE = new SiriusPlugin();

    private static Implementation plugin;

    /**
     * create at the initialization to avoid synchronization cost in ExtendedPackageRegistry
     */
    private static final ModelAccessorsRegistry REGISTRY = new ModelAccessorsRegistry();

    /**
     * Creates the instance.
     */
    public SiriusPlugin() {
        super(new ResourceLocator[] { EcorePlugin.INSTANCE, SiriusPlugin.INSTANCE, });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
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
         * Handler for migration.
         */
        private IMigrationHandler migrationHandler;

        /**
         * Registry of all supported interpreters.
         */
        private InterpreterRegistry interRegistry;

        /**
         * The registry listener that will be used to listen to sessionFactory extension changes.
         */
        private SessionFactoryRegistryListener sessionFactoryRegistryListener;

        /**
         * The registry listener that will be used to listen to extension changes.
         */
        private DeleteHookDescriptorRegistryListener deleteHookDescriptorRegistryListener;

        /**
         * The registry listener that will be used to listen to contribution changes against the external java action
         * extension point.
         */
        private ExternalJavaActionRegistryListener javaActionRegistryListener;

        /**
         * The registry listener that will be used to listen to contribution changes against the external resource
         * strategy extension point.
         */
        private ResourceStrategyRegistryListener resourceStrategyRegistryListener;

        /**
         * The registry listener that will be used to listen to contribution changes against the external
         * dRepresentationLocationRule extension point.
         */
        private DRepresentationLocationRuleRegistryListener repLocationRuleRegistryListener;

        /**
         * The registry listener that will be used to listen to contribution changes.
         */
        private ModelOperationManagerRegistryListener modelOperationManagerRegistryListener;

        /**
         * The registry for {@link IInterpretedExpressionQueryProvider}.
         */
        private InterpretedExpressionQueryProviderRegistry expressionQueryProviderRegistry;

        /**
         * The user interface callback.
         */
        private UICallBack uiCallback;

        /**
         * True if the repair of an aird is currently happening. False otherwise.
         */
        private AtomicBoolean repairInProgress;

        /**
         * Creates an instance.
         */
        public Implementation() {
            super();
            plugin = this;
            repairInProgress = new AtomicBoolean(false);
        }

        /**
         * Returns true if the repair of an aird is currently happening. False otherwise.
         * 
         * @return true if the repair of an aird is currently happening. False otherwise.
         */
        public boolean isRepairInProgress() {
            return this.repairInProgress.get();
        }

        /**
         * Set the repair in progress status.
         * 
         * @param repairInProgress
         *            true if the repair of an aird is currently happening. False otherwise.
         */
        public void setRepairInProgress(boolean repairInProgress) {
            this.repairInProgress.set(repairInProgress);
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            // initialize preferences
            // This will load the preferences from SiriusPreferencesInitializer
            DefaultScope.INSTANCE.getNode(SiriusPlugin.INSTANCE.ID);

            interRegistry = new InterpreterRegistry();

            // Sets the validator for these model.
            EValidator.Registry.INSTANCE.put(ViewpointPackage.eINSTANCE, new EValidatorAdapter());
            EValidator.Registry.INSTANCE.put(DescriptionPackage.eINSTANCE, new EValidatorAdapter());
            EValidator.Registry.INSTANCE.put(ToolPackage.eINSTANCE, new EValidatorAdapter());

            sessionFactoryRegistryListener = new SessionFactoryRegistryListener();
            sessionFactoryRegistryListener.init();
            deleteHookDescriptorRegistryListener = new DeleteHookDescriptorRegistryListener();
            deleteHookDescriptorRegistryListener.init();
            javaActionRegistryListener = new ExternalJavaActionRegistryListener();
            javaActionRegistryListener.init();
            resourceStrategyRegistryListener = new ResourceStrategyRegistryListener();
            resourceStrategyRegistryListener.init();
            repLocationRuleRegistryListener = new DRepresentationLocationRuleRegistryListener();
            repLocationRuleRegistryListener.init();
            modelOperationManagerRegistryListener = new ModelOperationManagerRegistryListener();
            modelOperationManagerRegistryListener.init();
            expressionQueryProviderRegistry = new InterpretedExpressionQueryProviderRegistry(Platform.getExtensionRegistry(), this);
            expressionQueryProviderRegistry.init();

            List<IMigrationHandler> migrationHandlers = EclipseUtil.getExtensionPlugins(IMigrationHandler.class, IMigrationHandler.ID, IMigrationHandler.CLASS_ATTRIBUTE);
            if (migrationHandlers.size() > 0) {
                migrationHandler = migrationHandlers.get(0);
            }
        }

        /**
         * Returns all the registered IInterpretedExpressionQueryProvider.
         * 
         * @return the registered IInterpretedExpressionQueryProvider.
         */
        public Collection<IInterpretedExpressionQueryProvider> getInterpretedExpressionQueryProviders() {
            return expressionQueryProviderRegistry.getEntries();
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            REGISTRY.dispose();

            sessionFactoryRegistryListener.dispose();
            sessionFactoryRegistryListener = null;
            deleteHookDescriptorRegistryListener.dispose();
            deleteHookDescriptorRegistryListener = null;
            javaActionRegistryListener.dispose();
            javaActionRegistryListener = null;
            resourceStrategyRegistryListener.dispose();
            resourceStrategyRegistryListener = null;
            repLocationRuleRegistryListener.dispose();
            repLocationRuleRegistryListener = null;
            modelOperationManagerRegistryListener.dispose();
            modelOperationManagerRegistryListener = null;
            expressionQueryProviderRegistry.dispose();
            expressionQueryProviderRegistry = null;

            ViewpointRegistry.getInstance().dispose();

            MultiLanguagesValidator.getInstance().dispose();

            // Persit preferences into the prefs file.
            InstanceScope.INSTANCE.getNode(ID).flush();

            super.stop(context);
        }

        /**
         * Return the global instance of {@link ModelAccessorsRegistry}.
         * 
         * @return the global instance of {@link ModelAccessorsRegistry}.
         */
        public ModelAccessorsRegistry getModelAccessorRegistry() {
            return REGISTRY;
        }

        /**
         * Return the global instance of {@link ExtendedPackageRegistry}.
         * 
         * @return the global instance of {@link ExtendedPackageRegistry}.
         */
        public InterpreterRegistry getInterpreterRegistry() {
            return interRegistry;
        }

        /**
         * Get the migration handler used for Description resource creation.
         *
         * @return the migration handler used for Description resource creation.
         */
        public IMigrationHandler getMigrationHandler() {
            return migrationHandler;
        }

        /**
         * Logs an error in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void error(String message, final Throwable e) {
            log(message, e, IStatus.ERROR);
        }

        /**
         * Logs a warning in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void warning(String message, final Exception e) {
            log(message, e, IStatus.WARNING);
        }

        /**
         * Logs an info in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void info(String message, final Throwable e) {
            log(message, e, IStatus.INFO);
        }

        private void log(String message, final Throwable e, int severity) {
            String messageToDisplay = message;
            if (messageToDisplay == null && e != null) {
                messageToDisplay = e.getMessage();
            }
            if (e instanceof CoreException) {
                getLog().log(((CoreException) e).getStatus());
            } else {
                IStatus status = new Status(severity, getDefault().getSymbolicName(), messageToDisplay, e);
                getLog().log(status);
            }
        }

        /**
         * Return callBack.
         *
         * @return the uiCallback
         */
        public UICallBack getUiCallback() {
            return uiCallback;
        }

        /**
         * Modify callBack.
         *
         * @param uiCallback
         *            the uiCallback to set
         */
        public void setUiCallback(UICallBack uiCallback) {
            this.uiCallback = uiCallback;
        }

    }

}
