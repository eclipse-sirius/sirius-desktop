/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;

/**
 * Default {@link IPermissionProvider} to provides a specified
 * {@link IPermissionAuthority}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DefaultPermissionProvider implements IPermissionProvider {

    private IPermissionAuthority permissionAuthority;

    /**
     * Default constructor.
     * 
     * @param permissionAuthority
     *            {@link IPermissionAuthority}
     */
    public DefaultPermissionProvider(IPermissionAuthority permissionAuthority) {
        this.permissionAuthority = permissionAuthority;
    }

    /**
     * Overridden to always provides the {@link IPermissionAuthority}.
     * 
     * {@inheritDoc}
     */
    public boolean provides(ResourceSet set) {
        return true;
    }

    /**
     * Overridden to provides the specified {@link IPermissionAuthority}.
     * 
     * {@inheritDoc}
     */
    public IPermissionAuthority getAuthority(ResourceSet set) {
        return permissionAuthority;
    }
}
