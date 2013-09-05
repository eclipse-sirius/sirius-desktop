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
package org.eclipse.sirius.ecore.extender.business.api.permission;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Provider for permission authorities.
 * 
 * @author cbrun
 * 
 */
public interface IPermissionProvider {
    /**
     * Tell if the provider want's to provide or not.
     * 
     * @param set
     *            the model.
     * @return true if the provider provides an {@link IPermissionAuthority} for
     *         the given model.
     */
    boolean provides(ResourceSet set);

    /**
     * Return the provided permission authority.
     * 
     * @param set
     *            the model.
     * @return the permission authority.
     */
    IPermissionAuthority getAuthority(ResourceSet set);
}
