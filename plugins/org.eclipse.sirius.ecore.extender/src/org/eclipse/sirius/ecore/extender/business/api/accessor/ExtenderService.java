/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.sirius.ecore.extender.business.internal.common.AbstractProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.common.ExtenderProviderDescriptor;

/**
 * This class provides activated model extensions based on the declared
 * providers.
 * 
 * @author cbrun
 * 
 */
public final class ExtenderService {

    private static List<AbstractProviderDescriptor> extenderProviders = new ArrayList<AbstractProviderDescriptor>();

    /** Name of the extension point to parse for extender providers. */
    private static final String EXTENDER_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.ecore.extender.ExtenderProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "extenderprovider"; //$NON-NLS-1$

    private ExtenderService() {

    }

    static {
        ExtenderService.parseExtensionMetadata();
    }

    /**
     * Create a new model accessor from the {@link ResourceSet}.
     * 
     * @param set
     *            the model.
     * @return the corresponding Model accessor.
     */
    public static ModelAccessor createModelAccessor(final ResourceSet set) {
        final ModelAccessor result = new ModelAccessor();

        final Iterator<AbstractProviderDescriptor> it = ExtenderService.extenderProviders.iterator();
        while (it.hasNext()) {
            final ExtenderProviderDescriptor desc = (ExtenderProviderDescriptor) it.next();
            if (desc.getProviderInstance() != null && desc.getProviderInstance().provides(set)) {
                result.addExtender(desc.getProviderInstance().getExtender(set), desc.getPriority());
            }
        }
        return result;
    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private static void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(ExtenderService.EXTENDER_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final AbstractProviderDescriptor desc = ExtenderService.parseEngine(configElement);
                    if (desc != null) {
                        ExtenderService.extenderProviders.add(desc);
                    }
                }
            }
            Collections.sort(ExtenderService.extenderProviders);
        }
    }

    private static AbstractProviderDescriptor parseEngine(final IConfigurationElement configElement) {
        if (!configElement.getName().equals(ExtenderService.TAG_ENGINE)) {
            return null;
        }
        final AbstractProviderDescriptor desc = new ExtenderProviderDescriptor(configElement);
        return desc;
    }

}
