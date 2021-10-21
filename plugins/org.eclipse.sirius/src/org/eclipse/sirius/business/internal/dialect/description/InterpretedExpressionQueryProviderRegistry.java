/*******************************************************************************
 * Copyright (c) 2016, 2021 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.dialect.description;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQueryProvider;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionDescriptor;

/**
 * Maintains the set of {@link IInterpretedExpressionQueryProvider} currently registered.
 *
 * @author pcdavid
 */
public class InterpretedExpressionQueryProviderRegistry {
    /** Name of the extension point to parse. */
    public static final String EXTENSION_POINT = "org.eclipse.sirius.interpretedExpressionQueryProvider"; //$NON-NLS-1$

    /** The attributed used to designate the provider implementation class. */
    public static final String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    private final IExtensionRegistry registry;

    private final Plugin context;

    private final Collection<IInterpretedExpressionQueryProvider> entries;

    private final IRegistryEventListener listener = new IRegistryEventListener() {

        @Override
        public void added(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                for (IConfigurationElement elem : extension.getConfigurationElements()) {
                    register(elem);
                }
            }
        }

        @Override
        public void removed(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                for (IConfigurationElement elem : extension.getConfigurationElements()) {
                    unregister(elem);
                }
            }
        }

        @Override
        public void added(IExtensionPoint[] extensionPoints) {
            // no need to listen to this event
        }

        @Override
        public void removed(IExtensionPoint[] extensionPoints) {
            // no need to listen to this event
        }
    };

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to look for registered providers.
     * @param context
     *            the plug-in in the context of which we're running; used for logging.
     */
    public InterpretedExpressionQueryProviderRegistry(IExtensionRegistry registry, Plugin context) {
        this.registry = Objects.requireNonNull(registry);
        this.context = context;
        this.entries = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        registry.addListener(listener, InterpretedExpressionQueryProviderRegistry.EXTENSION_POINT);
        IExtension[] initialExtensions = registry.getExtensionPoint(InterpretedExpressionQueryProviderRegistry.EXTENSION_POINT).getExtensions();
        listener.added(initialExtensions);
    }

    /**
     * Stop listening for further registry changes and forget about all currently known entries.
     */
    public void dispose() {
        registry.removeListener(listener);
        entries.clear();
    }

    /**
     * Returns all the currently registered {@link IInterpretedExpressionQueryProvider}.
     * 
     * @return the currently registered {@link IInterpretedExpressionQueryProvider}.
     */
    public Collection<IInterpretedExpressionQueryProvider> getEntries() {
        return Collections.unmodifiableList(new ArrayList<IInterpretedExpressionQueryProvider>(entries));
    }

    private void register(IConfigurationElement elem) {
        Object executableExtension;
        try {
            executableExtension = elem.createExecutableExtension(CLASS_ATTRIBUTE);
            if (executableExtension instanceof IInterpretedExpressionQueryProvider) {
                entries.add((IInterpretedExpressionQueryProvider) executableExtension);
            }
        } catch (CoreException e) {
            String message = MessageFormat.format(Messages.InterpretedExpressionQueryProviderRegistry_instanciationError, elem.getAttribute(CLASS_ATTRIBUTE));
            context.getLog().log(new Status(IStatus.ERROR, context.getBundle().getSymbolicName(), message, e));
        }
    }

    private void unregister(IConfigurationElement elem) {
        String className = elem.getAttribute(ExternalJavaActionDescriptor.CLASS_ATTRIBUTE);
        Iterator<IInterpretedExpressionQueryProvider> iter = entries.iterator();
        while (iter.hasNext()) {
            IInterpretedExpressionQueryProvider provider = iter.next();
            if (Objects.equals(className, provider.getClass().getName())) {
                iter.remove();
            }
        }
    }
}
