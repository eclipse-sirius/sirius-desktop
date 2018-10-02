/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.api.permission;

import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionAuthorityRegistryImpl;

/**
 * Registry for all the permission authorities.
 * 
 * @author cbrun
 */
public final class PermissionAuthorityRegistry {

    private static IPermissionAuthorityRegistry instance;

    private PermissionAuthorityRegistry() {

    }

    /**
     * Return the singleton default registry.
     * 
     * @return the singleton default registry.
     */
    public static IPermissionAuthorityRegistry getDefault() {
        if (instance == null) {
            instance = new PermissionAuthorityRegistryImpl();
        }
        return instance;
    }
}
