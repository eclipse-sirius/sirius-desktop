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
package org.eclipse.sirius.common.tools.internal.editing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryRegistry;

/**
 * This listener will allow us to be aware of contribution changes against the
 * editingDomainFactory extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
//NOTE : IRegistryEventListener doesn't exists with Eclipse 3.3
public class EditingDomainFactoryRegistryListener implements IRegistryChangeListener /* IRegistryEventListener */ {

    /** Name of the extension point to parse for extensions. */
    public static final String EDITING_DOMAIN_FACTORY_EXTENSION_POINT = DslCommonPlugin.PLUGIN_ID + ".editingDomainFactory"; //$NON-NLS-1$

    /** Name of the extension point's "editingDomainFactory" tag. */
    private static final String EDITING_DOMAIN_FACTORY_TAG_EXTENSION = "editingDomainFactory"; //$NON-NLS-1$

    /** Override attribute of the extension point's "editingDomainFactory" tag. */
    private static final String EDITING_DOMAIN_FACTORY_TAG_OVERRIDE = "override"; //$NON-NLS-1$

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
     * these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(EDITING_DOMAIN_FACTORY_EXTENSION_POINT).getExtensions()) {
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
                if (EDITING_DOMAIN_FACTORY_TAG_EXTENSION.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(EclipseEditingDomainFactoryDescriptor.EDITING_DOMAIN_FACTORY_CLASS_ATTRIBUTE);
                    EditingDomainFactoryRegistry.removeExtension(extensionClassName);
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
        final String id = extension.getUniqueIdentifier();
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            if (EDITING_DOMAIN_FACTORY_TAG_EXTENSION.equals(elem.getName())) {
                String override = elem.getAttribute(EDITING_DOMAIN_FACTORY_TAG_OVERRIDE);
                EditingDomainFactoryRegistry.addExtension(new EclipseEditingDomainFactoryDescriptor(id, override, elem));
            }
        }
    }

    /**
     * Overridden to support TP 3.3, because IRegistryEventListener exists only
     * from 3.4.
     * 
     * {@inheritDoc}
     */
    public void registryChanged(IRegistryChangeEvent event) {
        IExtensionDelta[] deltas = event.getExtensionDeltas();
        List<IExtension> addedExtensions = new ArrayList<IExtension>();
        List<IExtension> removedExtensions = new ArrayList<IExtension>();
        for (int i = 0; i < deltas.length; i++) {
            if (!EDITING_DOMAIN_FACTORY_EXTENSION_POINT.equals(deltas[i].getExtensionPoint().getUniqueIdentifier()))
                continue;
            if (deltas[i].getKind() == IExtensionDelta.ADDED) {
                IExtension extension = deltas[i].getExtension();
                addedExtensions.add(extension);
            } else if (deltas[i].getKind() == IExtensionDelta.REMOVED) {
                IExtension extension = deltas[i].getExtension();
                removedExtensions.add(extension);
            }
        }
        added(addedExtensions.toArray(new IExtension[0]));
        removed(addedExtensions.toArray(new IExtension[0]));
    }
}
