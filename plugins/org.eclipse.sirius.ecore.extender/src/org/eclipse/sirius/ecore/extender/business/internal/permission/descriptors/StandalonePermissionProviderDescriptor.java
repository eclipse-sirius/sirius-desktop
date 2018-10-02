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
package org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors;

import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;

/**
 * A {@link PermissionProviderDescriptor} to be contributed programmatically.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandalonePermissionProviderDescriptor extends AbstractPermissionProviderDescriptor implements PermissionProviderDescriptor {

    /**
     * Default constructor.
     * 
     * @param id
     *            Id of this descriptor
     * @param priority
     *            the priority of this {@link IPermissionProvider}
     * 
     * @param permissionProvider
     *            the {@link IPermissionProvider} to contribute
     */
    public StandalonePermissionProviderDescriptor(String id, int priority, IPermissionProvider permissionProvider) {
        this.id = id;
        this.priority = priority;
        this.permissionProvider = permissionProvider;
    }

}
