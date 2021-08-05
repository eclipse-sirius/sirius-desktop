/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.helper.refresh;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.business.internal.helper.refresh.AbstractProviderDescriptor;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Descriptor of a {@link IRefreshExtensionProvider}.
 * 
 * @author ymortier
 */
public class RefreshExtensionProviderDescriptor extends AbstractProviderDescriptor {

    /** The provider. */
    private IRefreshExtensionProvider extensionProvider;

    /**
     * Create a new descriptor.
     * 
     * @param element
     *            the configuration element.
     */
    public RefreshExtensionProviderDescriptor(final IConfigurationElement element) {
        super(element);
    }

    /**
     * Return the instance of {@link IRefreshExtensionProvider}.
     * 
     * @return the instance of {@link IRefreshExtensionProvider}.
     */
    public IRefreshExtensionProvider getProviderInstance() {
        if (this.extensionProvider == null) {
            try {
                this.extensionProvider = (IRefreshExtensionProvider) this.element.createExecutableExtension("providerClass"); //$NON-NLS-1$
            } catch (final CoreException e) {
                SiriusPlugin.getDefault().error(Messages.RefreshExtensionProviderDescriptor_instantiatingErrorMsg, e);
            }
        }
        return this.extensionProvider;
    }

}
