/******************************************************************************
 * Copyright (c) 2002, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 ****************************************************************************/
package org.eclipse.sirius.diagram.elk.debug.gmf.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.common.core.internal.CommonCoreDebugOptions;
import org.eclipse.gmf.runtime.common.core.internal.CommonCorePlugin;
import org.eclipse.gmf.runtime.common.core.internal.CommonCoreStatusCodes;
import org.eclipse.gmf.runtime.common.core.internal.l10n.CommonCoreMessages;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProvider;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.core.service.IProviderPolicy;
import org.eclipse.gmf.runtime.common.core.service.ProviderChangeEvent;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * This class provides layout facilities based on the declared providers. It is inspired from
 * {@link org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutService},
 * {@link org.eclipse.gmf.runtime.common.core.service.AbstractProvider} and super-classes, to have access to the "best"
 * provider to get the ELK Layout Configuration associated to it (if there is one).
 *
 * @author lredor
 */
public final class LayoutService {
    /**
     * A descriptor for providers defined by a configuration element.
     *
     * @author khussey
     */
    public static class ProviderDescriptor extends AbstractProvider implements IProvider, IProviderChangeListener {

        protected boolean policyInitialized = false;

        private String providerClassName;

        /**
         * The name of the 'class' XML attribute.
         */
        protected static final String A_CLASS = "class"; //$NON-NLS-1$

        /**
         * The name of the 'plugin' XML attribute.
         *
         */
        protected static final String A_PLUGIN = "plugin"; //$NON-NLS-1$

        /**
         * The name of the 'Policy' XML element.
         */
        protected static final String E_POLICY = "Policy"; //$NON-NLS-1$

        /**
         * The configuration element describing this descriptor's provider.
         */
        private final IConfigurationElement element;

        /**
         * The provider for which this object is a descriptor.
         */
        protected IProvider provider;

        /**
         * The policy associated with this descriptor's provider (if specified).
         */
        protected IProviderPolicy policy;

        /**
         * Tracks the failure of the provider class intantiation, so that a failure to create the class is logged only
         * once.
         */
        private boolean providerClassInstantiationFailed = false;

        /**
         * Constructs a new provider descriptor for the specified configuration element.
         *
         * @param element
         *            The configuration element describing the provider.
         */
        protected ProviderDescriptor(IConfigurationElement element) {
            super();
            this.element = element;
        }

        /**
         * Retrieves the configuration element describing this descriptor's provider.
         *
         * @return The configuration element describing this descriptor's provider.
         */
        protected final IConfigurationElement getElement() {
            return element;
        }

        /**
         * Retrieves the provider for which this object is a descriptor. Lazy-initializes the value by instantiating the
         * class described by this provider descriptor's configuration element.
         *
         * @return The provider for which this object is a descriptor.
         */
        public IProvider getProvider() {
            if (null == provider && !providerClassInstantiationFailed) {
                CommonCorePlugin corePlugin = CommonCorePlugin.getDefault();

                try {
                    Log.info(corePlugin, CommonCoreStatusCodes.OK, "Activating provider '" + element.getAttribute(ProviderDescriptor.A_CLASS) + "'..."); //$NON-NLS-1$ //$NON-NLS-2$
                    provider = (IProvider) element.createExecutableExtension(ProviderDescriptor.A_CLASS);
                    provider.addProviderChangeListener(this);
                    Trace.trace(corePlugin, CommonCoreDebugOptions.SERVICES_ACTIVATE, "Provider '" + provider + "' activated."); //$NON-NLS-1$ //$NON-NLS-2$

                } catch (CoreException ce) {

                    if (provider == null) {
                        // remember that the provider class could not be instantiated
                        providerClassInstantiationFailed = true;
                    }

                    Trace.catching(corePlugin, CommonCoreDebugOptions.EXCEPTIONS_CATCHING, getClass(), "getProvider", ce); //$NON-NLS-1$
                    IStatus status = ce.getStatus();
                    Log.log(corePlugin, status.getSeverity(), CommonCoreStatusCodes.SERVICE_FAILURE,
                            NLS.bind(CommonCoreMessages.serviceProviderNotActivated, element.getAttribute(ProviderDescriptor.A_CLASS)), status.getException());
                }
            }
            return provider;
        }

        /**
         * Retrieves the policy associated with this descriptor's provider (if specified). Lazy-initializes the value by
         * instantiating the class described by this provider descriptor's configuration element, if specified.
         *
         * @return The policy associated with this descriptor's provider (if specified).
         */
        protected IProviderPolicy getPolicy() {
            if (!policyInitialized) {
                policyInitialized = true;
                IConfigurationElement[] elements = element.getChildren(ProviderDescriptor.E_POLICY);
                working: {
                    if (elements.length == 0) {
                        break working; // no child elements
                    }

                    CommonCorePlugin corePlugin = CommonCorePlugin.getDefault();

                    try {
                        Log.info(corePlugin, CommonCoreStatusCodes.OK, "Activating provider policy '" + elements[0].getAttribute(ProviderDescriptor.A_CLASS) + "'..."); //$NON-NLS-1$ //$NON-NLS-2$

                        // the following results in a core dump on Solaris if
                        // the policy plug-in cannot be found

                        policy = (IProviderPolicy) element.createExecutableExtension(ProviderDescriptor.E_POLICY);

                        Trace.trace(corePlugin, CommonCoreDebugOptions.SERVICES_ACTIVATE, "Provider policy '" + policy + "' activated."); //$NON-NLS-1$ //$NON-NLS-2$
                    } catch (CoreException ce) {
                        Trace.catching(corePlugin, CommonCoreDebugOptions.EXCEPTIONS_CATCHING, getClass(), "getPolicy", ce); //$NON-NLS-1$
                        IStatus status = ce.getStatus();
                        Log.log(corePlugin, status.getSeverity(), CommonCoreStatusCodes.SERVICE_FAILURE, status.getMessage(), status.getException());
                    }
                }
            }
            return policy;
        }

        /**
         * Indicates whether this provider descriptor can provide the functionality described by the specified
         * <code>operation</code>.
         *
         * @param operation
         *            The operation in question.
         * @return <code>true</code> if this descriptor's policy or provider provides the operation; <code>false</code>
         *         otherwise.
         */
        @Override
        public boolean provides(IOperation operation) {
            if (!policyInitialized) {
                policy = getPolicy();
                policyInitialized = true;
            }

            if (null != policy) {
                try {
                    return policy.provides(operation);
                } catch (Throwable e) {
                    Log.log(CommonCorePlugin.getDefault(), IStatus.ERROR, CommonCoreStatusCodes.SERVICE_FAILURE,
                            "Ignoring provider since policy " + policy + " threw an exception or error in the provides() method", //$NON-NLS-1$ //$NON-NLS-2$
                            e);

                    // re-throw fatal errors
                    if (e instanceof ThreadDeath) {
                        throw (ThreadDeath) e;
                    }

                    if (e instanceof VirtualMachineError) {
                        throw (VirtualMachineError) e;
                    }

                    return false;
                }
            }

            IProvider theProvider = getProvider();

            return (theProvider != null) ? LayoutService.safeProvides(theProvider, operation) : false;
        }

        /**
         * Handles an event indicating that a provider has changed.
         *
         * @param event
         *            The provider change event to be handled.
         */
        @Override
        public void providerChanged(ProviderChangeEvent event) {
            fireProviderChange(event);
        }

        /**
         * Returns the provider's class name, if it can be found.
         */
        @Override
        public String toString() {

            if (providerClassName == null) {
                if (getElement() != null && getElement().isValid()) {
                    // get the provider class name
                    providerClassName = getElement().getAttribute(ProviderDescriptor.A_CLASS);
                }
                if (providerClassName == null) {
                    // use the object ID if no provider class name can be found
                    providerClassName = super.toString();
                }
            }

            return providerClassName;
        }

    }

    /**
     * The name of the 'name' XML attribute.
     */
    private static final String A_NAME = "name"; //$NON-NLS-1$

    /**
     * The name of the 'Priority' XML element.
     */
    private static final String E_PRIORITY = "Priority"; //$NON-NLS-1$

    /**
     * The size of a cache which is indexed by {@link ProviderPriority} ordinals.
     */
    private static final int priorityCount;

    // Initialize priorityCount.
    static {
        // any priority will do to get the list of values
        List priorities = ProviderPriority.HIGHEST.getValues();
        int maxOrdinal = 0;

        for (Iterator i = priorities.iterator(); i.hasNext();) {
            int ordinal = ((ProviderPriority) i.next()).getOrdinal();

            if (maxOrdinal < ordinal) {
                maxOrdinal = ordinal;
            }
        }

        priorityCount = maxOrdinal + 1;
    }

    private static final LayoutService instance = new LayoutService();

    public static LayoutService getInstance() {
        return LayoutService.instance;
    }

    /**
     * List of providers class names that have thrown exceptions in the provides() method. Used to prevent logging
     * repeatedly for the same failed provider.
     */
    private static final List<String> ignoredProviders = new ArrayList<>();

    /**
     * The lists of registered providers.
     */
    private final ArrayList<ProviderDescriptor>[] providerDescriptors;

    /**
     * The list of pre-defined provider priorities.
     */
    public static final ProviderPriority[] PRIORITIES = { ProviderPriority.HIGHEST, ProviderPriority.HIGH, ProviderPriority.MEDIUM, ProviderPriority.LOW, ProviderPriority.LOWEST };

    /**
     * Registers the service providers described by the extensions of the specified namespace and extension point name
     * with this service.
     *
     * @param namespace
     *            the namespace for the given extension point (e.g. <code>"org.eclipse.gmf.runtime.common.core"</code>)
     * @param extensionPointName
     *            the simple identifier of the extension point (e.g. <code>"parserProviders"</code>)
     */
    protected final void configureProviders(String namespace, String extensionPointName) {
        configureProviders(Platform.getExtensionRegistry().getExtensionPoint(namespace, extensionPointName).getConfigurationElements());
    }

    /**
     * Registers the service providers described by the specified configuration <code>elements</code> with this service.
     *
     * @param elements
     *            The configuration elements describing the providers.
     */
    protected final void configureProviders(IConfigurationElement[] elements) {
        assert null != elements : "null elements"; //$NON-NLS-1$

        for (IConfigurationElement element : elements) {
            try {
                addProviderDescriptor(ProviderPriority.parse(getPriority(element)), newProviderDescriptor(element));
            } finally {
                if (Trace.shouldTrace(CommonCorePlugin.getDefault(), CommonCoreDebugOptions.SERVICES_CONFIG)) {
                    IExtension extension = element.getDeclaringExtension();
                    String identifier = extension.getUniqueIdentifier();

                    if (identifier == null) {
                        identifier = String.valueOf(extension.getNamespaceIdentifier());
                    }

                    extension.getExtensionPointUniqueIdentifier();

                    Trace.trace(CommonCorePlugin.getDefault(), CommonCoreDebugOptions.SERVICES_CONFIG, "Provider of '" + extension.getExtensionPointUniqueIdentifier() //$NON-NLS-1$
                    + "' configured from extension '" + identifier + "'."); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }

        for (int i = LayoutService.priorityCount; --i >= 0;) {
            providerDescriptors[i].trimToSize();
        }
    }

    /**
     * Get the priority of the Provider's configuration element
     *
     * @param element
     *            The configuration elements describing the provider.
     * @return the priority of the specified configuration element
     */
    protected String getPriority(IConfigurationElement element) {
        return element.getChildren(LayoutService.E_PRIORITY)[0].getAttribute(LayoutService.A_NAME);
    }

    /**
     * Creates a new provider descriptor for the specified configuration <code>element</code>.
     *
     * @param element
     *            The configuration element from which to create the descriptor.
     * @return A new provider descriptor.
     */
    protected ProviderDescriptor newProviderDescriptor(IConfigurationElement element) {
        return new ProviderDescriptor(element);
    }

    /**
     * Registers the <code>provider</code> as a provider for this service, with the specified <code>priority</code>.
     *
     * @param priority
     *            The priority at which to add the provider.
     * @param provider
     *            The provider to be added.
     */
    protected final void addProviderDescriptor(ProviderPriority priority, ProviderDescriptor provider) {

        assert null != priority : "null ProviderPriority"; //$NON-NLS-1$
        assert null != provider : "null ProviderDescriptor"; //$NON-NLS-1$

        int ordinal = priority.getOrdinal();

        providerDescriptors[ordinal].add(provider);
    }

    /**
     * Avoid instantiation.
     */
    private LayoutService() {
        super();

        providerDescriptors = new ArrayList[LayoutService.priorityCount];
        for (int ordinal = LayoutService.priorityCount; --ordinal >= 0;) {
            providerDescriptors[ordinal] = new ArrayList(0);
        }
        configureProviders(DiagramUIPlugin.getPluginId(), "layoutProviders"); //$NON-NLS-1$
    }

    /**
     * Get the associated provider to this <code>operation</code >with the highest priority.
     * 
     * @param operation
     *            The operation to handle.
     * @return The corresponding provider
     */
    public Option<IProvider> getMainProvider(IOperation operation) {
        for (ProviderPriority element : LayoutService.PRIORITIES) {
            List<IProvider> priorityProviders = getProviders(element, operation);

            if (priorityProviders.size() != 0) {
                return Options.newSome(priorityProviders.get(0));
            }
        }
        return Options.newNone();
    }

    /**
     * Safely calls a provider's provides() method.
     *
     * The provider must not be null.
     *
     * Returns true if there were no exceptions thrown and the provides() method returns true. Returns false if an
     * exception was thrown or the provides() method returns false.
     *
     * An entry is added to the log if the provider threw an exception.
     *
     * @param provider
     *            to safely execute the provides() method
     * @param operation
     *            passed into the provider's provides() method
     * @return true if there were no exceptions thrown and the provides() method returns true. Returns false if an
     *         exception was thrown or the provides() method returns false.
     */
    static boolean safeProvides(IProvider provider, IOperation operation) {
        assert provider != null;

        try {
            return provider.provides(operation);
        } catch (Throwable e) {

            String providerClassName = provider.getClass().getName();

            if (!LayoutService.ignoredProviders.contains(providerClassName)) {
                // remember the ignored provider so that the error is only logged once per provider
                LayoutService.ignoredProviders.add(providerClassName);

                Log.log(CommonCorePlugin.getDefault(), IStatus.ERROR, CommonCoreStatusCodes.SERVICE_FAILURE,
                        "Ignoring provider " + provider + " since it threw an exception or error in the provides() method", //$NON-NLS-1$ //$NON-NLS-2$
                        e);
            }

            // re-throw fatal errors
            if (e instanceof ThreadDeath) {
                throw (ThreadDeath) e;
            }

            if (e instanceof VirtualMachineError) {
                throw (VirtualMachineError) e;
            }
            return false;
        }

    }

    protected List<IProvider> getProviders(ProviderPriority priority, IOperation operation) {
        List<ProviderDescriptor> descriptors = getProviderDescriptors(priority);
        for (ProviderDescriptor descriptor : descriptors) {
            if (LayoutService.safeProvides(descriptor, operation)) {
                return Collections.singletonList(descriptor.getProvider());
            }
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Retrieves a complete list of all the providers registered with this service that have the specified
     * <code>priority</code>.
     * <P>
     * This method does not consider the optimized state of the service.
     *
     * @param priority
     *            The priority of providers to be retrieved.
     * @return A complete list of providers of the specified priority.
     */
    final List<ProviderDescriptor> getProviderDescriptors(ProviderPriority priority) {
        return providerDescriptors[priority.getOrdinal()];
    }
}
