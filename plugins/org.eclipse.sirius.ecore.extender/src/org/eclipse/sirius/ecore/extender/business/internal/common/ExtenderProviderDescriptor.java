/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.common;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.ecore.extender.business.api.accessor.IExtenderProvider;
import org.eclipse.sirius.ecore.extender.business.internal.ExtenderPlugin;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;

/**
 * This Descriptor is used to keep track of the ExtenderProvider engines.
 * 
 * @author cbrun
 * 
 */
public class ExtenderProviderDescriptor extends AbstractProviderDescriptor {
    IExtenderProvider provider;

    /**
     * Create a new descriptor from an Extension Point element.
     * 
     * @param element
     *            an {@link IConfigurationElement} from OSGI.
     */
    public ExtenderProviderDescriptor(final IConfigurationElement element) {
        super(element);
    }

    /**
     * Return the provider instance, this is a singleton.
     * 
     * @return the provider instance.
     */
    public IExtenderProvider getProviderInstance() {
        if (provider == null) {
            try {
                provider = (IExtenderProvider) element.createExecutableExtension("providerClass"); //$NON-NLS-1$
            } catch (final CoreException e) {
                /* log an error */
                ExtenderPlugin.getPlugin().logError(Messages.ExtenderProviderDescriptor_errorLoadingExtenderProvider, e);
            }
        }
        return provider;
    }

}
