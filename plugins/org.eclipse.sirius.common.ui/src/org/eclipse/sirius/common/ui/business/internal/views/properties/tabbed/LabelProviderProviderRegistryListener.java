/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;

/**
 * This listener will allow us to be aware of contribution changes against the
 * propertyContributorLabelProviderDelegate extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelProviderProviderRegistryListener implements IRegistryChangeListener {

    /** Name of the extension point to parse for extensions. */
    public static final String PROPERTY_CONTRIBUTOR_LABEL_PROVIDER_DELEGATE_EXTENSION_POINT = SiriusTransPlugin.PLUGIN_ID + ".propertyContributorLabelProviderDelegate"; //$NON-NLS-1$

    /** Name of the extension point's "sessionFactory" tag. */
    private static final String LABEL_PROVIDER_PROVIDER = "labelProviderProvider"; //$NON-NLS-1$

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addRegistryChangeListener(this, PROPERTY_CONTRIBUTOR_LABEL_PROVIDER_DELEGATE_EXTENSION_POINT);
        parseInitialContributions();
    }

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
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
     */
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (LABEL_PROVIDER_PROVIDER.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(LabelProviderProviderDescriptor.LABEL_PROVIDER_PROVIDER_CLASS_ATTRIBUTE);
                    LabelProviderProviderRegistry.removeExtension(extensionClassName);
                }
            }
        }
    }

    /**
     * Though this listener reacts to the extension point changes, there could
     * have been contributions before it's been registered. This will parse
     * these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(PROPERTY_CONTRIBUTOR_LABEL_PROVIDER_DELEGATE_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
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
            if (LABEL_PROVIDER_PROVIDER.equals(elem.getName())) {
                LabelProviderProviderRegistry.addExtension(new EclipseLabelProviderProviderDescriptor(elem));
            }
        }
    }

    /**
     * Overridden to support 3.3, because IRegistryEventListener exists only
     * from 3.4.
     * 
     * {@inheritDoc}
     */
    public void registryChanged(IRegistryChangeEvent event) {
        IExtensionDelta[] deltas = event.getExtensionDeltas();
        List<IExtension> addedExtensions = new ArrayList<IExtension>();
        List<IExtension> removedExtensions = new ArrayList<IExtension>();
        for (int i = 0; i < deltas.length; i++) {
            if (!PROPERTY_CONTRIBUTOR_LABEL_PROVIDER_DELEGATE_EXTENSION_POINT.equals(deltas[i].getExtensionPoint().getUniqueIdentifier())) {
                continue;
            }
            if (deltas[i].getKind() == IExtensionDelta.ADDED) {
                IExtension extension = deltas[i].getExtension();
                addedExtensions.add(extension);
            } else if (deltas[i].getKind() == IExtensionDelta.REMOVED) {
                IExtension extension = deltas[i].getExtension();
                removedExtensions.add(extension);
            }
        }
        added(addedExtensions.toArray(new IExtension[addedExtensions.size()]));
        removed(removedExtensions.toArray(new IExtension[removedExtensions.size()]));
    }

    /**
     * Remove this listener and flush the associated registry.
     */
    public void dispose() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeRegistryChangeListener(this);
        LabelProviderProviderRegistry.clearRegistry();
    }

}
