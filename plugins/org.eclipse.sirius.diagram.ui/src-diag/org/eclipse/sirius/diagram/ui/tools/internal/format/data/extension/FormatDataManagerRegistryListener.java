/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension;

import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.Sets;

/**
 * This listener will allow us to be aware of contribution changes against the
 * {@link FormatDataManagerRegistryListener#FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public class FormatDataManagerRegistryListener implements IRegistryChangeListener {

    /** Name of the extension point to parse for extensions. */
    public static final String FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.diagram.ui.formatDataManager"; //$NON-NLS-1$

    /** Name of the extension point's "Format Data Manager Extension" tag. */
    private static final String FORMAT_DATA_MANAGER_PROVIDER_EXTENSION = "formatDataManagerProvider"; //$NON-NLS-1$

    /**
     * initialize this listener.
     */
    public void init() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addRegistryChangeListener(this, FormatDataManagerRegistryListener.FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT);
        this.parseInitialContributions();
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeRegistryChangeListener(this);
        FormatDataManagerRegistry.clearRegistry();
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the
     *            registry.
     */
    private void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            if (FORMAT_DATA_MANAGER_PROVIDER_EXTENSION.equals(elem.getName())) {

                try {
                    FormatDataManagerRegistry.addExtension(new FormatDataManagerDescriptor(elem));
                } catch (IllegalArgumentException e) {
                    // Do nothing
                }
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
     */
    public void added(IExtensionPoint[] extensionPoints) {
        for (IExtensionPoint extensionPoint : extensionPoints) {
            for (IExtension extension : extensionPoint.getExtensions()) {
                parseExtension(extension);
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryChangeListener#registryChanged(org.eclipse.core.runtime.IRegistryChangeEvent)
     */
    @Override
    public void registryChanged(IRegistryChangeEvent event) {
        Set<IExtension> addedExtensions = Sets.newLinkedHashSet();
        for (IExtensionDelta extensionDelta : event.getExtensionDeltas()) {
            addedExtensions.add(extensionDelta.getExtension());
        }
        added(addedExtensions.toArray(new IExtension[addedExtensions.size()]));
    }

    /**
     * Behavior when the given extensions are added.
     * 
     * @param extensions
     *            the added extensions
     */
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    /**
     * Though this listener reacts to the extension point changes, there could
     * have been contributions before it's been registered. This will parse
     * these initial contributions.
     */
    private void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
     */
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(FormatDataManagerDescriptor.FORMAT_DATA_MANAGER_PROVIDER_CLASS_NAME);
                    FormatDataManagerRegistry.removeExtension(extensionClassName);
                }
            }
        }
    }

}
