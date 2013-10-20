/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.internal.extension;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.common.acceleo.mtl.AcceleoMTLInterpreterPlugin;

/**
 * This listener will allow us to be aware of contribution changes against the
 * interpreter's extension points.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class AcceleoRegistryListener implements IRegistryEventListener {
    /** ID of the extension point to parse for import handlers. */
    public static final String IMPORT_HANDLER_EXTENSION_POINT = AcceleoMTLInterpreterPlugin.PLUGIN_ID + ".importhandler"; //$NON-NLS-1$

    /** Name of the extension point's "importHandler" tag. */
    private static final String IMPORT_HANDLER_TAG = "importHandler"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
     */
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
     */
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could
     * have been contributions before it's been registered. This will parse
     * these initial extensions.
     */
    public void parseInitialContributions() {
        for (IExtension extension : Platform.getExtensionRegistry().getExtensionPoint(IMPORT_HANDLER_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
     */
    public synchronized void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (IMPORT_HANDLER_TAG.equals(elem.getName())) {
                    final String className = elem.getAttribute(ImportHandlerDescriptor.IMPORT_HANDLER_ATTRIBUTE_CLASS);
                    ImportHandlerRegistry.removeHandler(className);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtensionPoint[])
     */
    public void removed(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
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
            if (IMPORT_HANDLER_TAG.equals(elem.getName())) {
                ImportHandlerRegistry.addHandler(new ImportHandlerDescriptor(elem));
            }
        }
    }
}
