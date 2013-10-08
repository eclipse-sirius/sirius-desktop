/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.internal.helper.delete.DeleteHookDescriptorRegistryListener;
import org.eclipse.sirius.business.internal.session.factory.SessionFactoryRegistryListener;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessorsRegistry;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.preferences.DCorePreferences;
import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionRegistryListener;
import org.eclipse.sirius.tools.internal.validation.EValidatorAdapter;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.osgi.framework.BundleContext;

/**
 * @author ymortier
 * 
 */
public final class SiriusPlugin extends Plugin {
    /**
     * Tell whether Eclipse is running or not.
     */
    public static final boolean IS_ECLIPSE_RUNNING;
    static {
        boolean result = false;
        try {
            result = Platform.isRunning();
        } catch (final Throwable exception) {
            // Assume that we aren't running.
        }
        IS_ECLIPSE_RUNNING = result;
    }

    /** The id. */
    public static final String ID = "org.eclipse.sirius"; //$NON-NLS-1$

    /** The shared instance. */
    private static SiriusPlugin defaultPlugin;

    /**
     * create at the initialization to avoid synchronization cost in
     * ExtendedPackageRegistry
     */
    private static final ModelAccessorsRegistry registry = new ModelAccessorsRegistry(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);

    /**
     * create at the initialization to avoid synchronization cost in
     * ExtendedPackageRegistry
     */
    private static final InterpreterRegistry interRegistry = new InterpreterRegistry();

    /**
     * The registry listener that will be used to listen to sessionFactory
     * extension changes.
     */
    private SessionFactoryRegistryListener sessionFactoryRegistryListener;

    /** The registry listener that will be used to listen to extension changes. */
    private DeleteHookDescriptorRegistryListener deleteHookDescriptorRegistryListener;

    /**
     * The registry listener that will be used to listen to contribution changes
     * against the external java action extension point.
     */
    private ExternalJavaActionRegistryListener javaActionRegistryListener;

    /**
     * Creates a new <code>ExtensionPlugin</code>.
     */
    public SiriusPlugin() {
        defaultPlugin = this;
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static SiriusPlugin getDefault() {
        return SiriusPlugin.defaultPlugin;
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
        if (message == null && e != null) {
            message = e.getMessage();
        }
        if (e instanceof CoreException) {
            this.getLog().log(((CoreException) e).getStatus());
        } else {
            final IStatus status = new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), message, e);
            this.getLog().log(status);
        }
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
        if (message == null && e != null) {
            message = e.getMessage();
        }
        if (e instanceof CoreException) {
            this.getLog().log(((CoreException) e).getStatus());
        } else {
            final IStatus status = new Status(IStatus.WARNING, this.getBundle().getSymbolicName(), message, e);
            this.getLog().log(status);
        }
    }

    /**
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        initPreferences();

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
    }

    private void initPreferences() {
        final IPreferencesService service = Platform.getPreferencesService();
        /* init the viewpoints registry with an initial size */
        final int initialSize = service.getInt(ID, DCorePreferences.VIEWPOINT_REGISTRY_INITIAL_SIZE, DCorePreferences.VIEWPOINT_REGISTRY_INITIAL_SIZE_DEFAULT_VALUE, null);
        ViewpointRegistry.getInstance().init(initialSize);
    }

    /**
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        super.stop(context);
        registry.dispose();

        sessionFactoryRegistryListener.dispose();
        sessionFactoryRegistryListener = null;
        deleteHookDescriptorRegistryListener.dispose();
        deleteHookDescriptorRegistryListener = null;
        javaActionRegistryListener.dispose();
        javaActionRegistryListener = null;

        ViewpointRegistry.getInstance().dispose();
    }

    /**
     * return the global instance of {@link ModelAccessorsRegistry}.
     */
    public ModelAccessorsRegistry getModelAccessorRegistry() {
        return registry;
    }

    /**
     * return the global instance of {@link ExtendedPackageRegistry}.
     */
    public InterpreterRegistry getInterpreterRegistry() {
        return interRegistry;
    }

}
