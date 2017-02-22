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
package org.eclipse.sirius.ecore.extender.business.api.permission;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Registry for all the permission authorities.
 * 
 * @author mchauvin
 */
public interface IPermissionAuthorityRegistry {

    /**
     * return the permission authority associated with the given element.
     * 
     * @param modelElement
     *            any instance.
     * @return the permission authority associated with the given element.
     */
    IPermissionAuthority getPermissionAuthority(EObject modelElement);

    /**
     * return the permission authority associated with the given element.
     * 
     * @param resourceSet
     *            any model.
     * @return the permission authority associated with the given element.
     */
    IPermissionAuthority getPermissionAuthority(ResourceSet resourceSet);

    /**
     * return the permission authority associated with the given element.
     * 
     * @param res
     *            any resource.
     * @return the permission authority associated with the given element.
     */
    IPermissionAuthority getPermissionAuthority(Resource res);

    /**
     * clear all the known permission authorities.
     */
    void dispose();

}
