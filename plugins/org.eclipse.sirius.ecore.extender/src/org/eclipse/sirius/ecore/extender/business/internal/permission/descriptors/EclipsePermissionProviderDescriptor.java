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
package org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;

/**
 * This descriptor is used to keep track of the Permission providers.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EclipsePermissionProviderDescriptor extends AbstractPermissionProviderDescriptor implements PermissionProviderDescriptor {

    private IConfigurationElement configurationElement;

    /**
     * Create a new descriptor from an Extension Point element.
     * 
     * @param id
     *            unique identifier of this extension
     * @param priority
     *            priority of this extension
     * @param configurationElement
     *            an {@link IConfigurationElement} coming from OSGI.
     */
    public EclipsePermissionProviderDescriptor(String id, int priority, final IConfigurationElement configurationElement) {
        super();
        this.id = id;
        this.priority = priority;
        this.configurationElement = configurationElement;
    }

    /**
     * Overridden to get a instance of the contributed class.
     * 
     * {@inheritDoc}
     */
    public IPermissionProvider getPermissionProvider() {
        if (permissionProvider == null) {
            try {
                permissionProvider = (IPermissionProvider) configurationElement.createExecutableExtension("providerClass"); //$NON-NLS-1$
            } catch (final CoreException e) {
                // silent catch.
            }
        }
        return permissionProvider;
    }

}
