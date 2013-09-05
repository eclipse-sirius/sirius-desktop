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

import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;

/**
 * Absract {@link PermissionProviderDescriptor} to be common between eclipse &
 * standalone descriptors.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractPermissionProviderDescriptor implements PermissionProviderDescriptor {

    /** id of this descriptor. */
    protected String id;

    /**
     * the priority of this {@link IPermissionProvider}.
     */
    protected int priority;

    /**
     * We only need to create the instance once, this will keep reference to it.
     */
    protected IPermissionProvider permissionProvider;

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Overridden to return the current {@link IPermissionProvider}.
     * 
     * {@inheritDoc}
     */
    public IPermissionProvider getPermissionProvider() {
        return permissionProvider;
    }

    /**
     * Overridden to sort {@link PermissionProviderDescriptor} according to
     * their priority. PermissionProviderDescriptor most priority are in front
     * 
     * {@inheritDoc}
     */
    public int compareTo(PermissionProviderDescriptor permissionProviderDescriptor) {
        return priority - permissionProviderDescriptor.getPriority();
    }
}
