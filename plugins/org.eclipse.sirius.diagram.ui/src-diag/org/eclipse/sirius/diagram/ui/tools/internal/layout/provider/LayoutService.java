/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;

/**
 * This class provides layout facilities based on the declared providers.
 * 
 * @author ymortier
 */
public final class LayoutService {

    /** All providers. */
    private static List<LayoutProviderDescriptor> layoutProviders = new ArrayList<LayoutProviderDescriptor>();

    static {
        LayoutService.parseExtensionMetadata();
    }

    /** Name of the extension point to parse for extender providers. */
    private static final String LAYOUT_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.diagram.ui.layoutProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "layoutProvider"; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private LayoutService() {

    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private static void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(LAYOUT_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final AbstractProviderDescriptor desc = LayoutService.parseEngine(configElement);
                    if (desc instanceof LayoutProviderDescriptor) {
                        layoutProviders.add((LayoutProviderDescriptor) desc);
                    }
                }
            }
            Collections.sort(layoutProviders);
        }
    }

    private static AbstractProviderDescriptor parseEngine(final IConfigurationElement configElement) {
        if (!configElement.getName().equals(TAG_ENGINE)) {
            return null;
        }
        final AbstractProviderDescriptor desc = new LayoutProviderDescriptor(configElement);
        return desc;
    }

    /**
     * Return the {@link LayoutProvider} to use with the specified
     * {@link org.eclipse.gef.EditPart}.
     * 
     * @param editPart
     *            the edit part.
     * @return the {@link LayoutProvider} to use with the specified
     *         {@link org.eclipse.gef.EditPart}.
     */
    public static LayoutProvider getProvider(final IGraphicalEditPart editPart) {
        final Iterator<LayoutProviderDescriptor> iterDescriptors = LayoutService.layoutProviders.listIterator();
        while (iterDescriptors.hasNext()) {
            final LayoutProviderDescriptor currentDescriptor = iterDescriptors.next();
            final LayoutProvider provider = currentDescriptor.getProviderInstance();
            if (provider != null) {
                if (provider.provides(editPart)) {
                    return provider;
                }
            } else {
                iterDescriptors.remove();
            }
        }
        return null;
    }

}
