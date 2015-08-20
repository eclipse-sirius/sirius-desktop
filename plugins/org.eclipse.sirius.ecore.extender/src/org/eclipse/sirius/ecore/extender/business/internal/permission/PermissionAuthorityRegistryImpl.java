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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;

/**
 * Registry for all the permission authorities.
 * 
 * @author cbrun
 */
public class PermissionAuthorityRegistryImpl implements IPermissionAuthorityRegistry {

    /**
     * A Map assiocating Resource Set with their corresponding
     * {@link IPermissionAuthority}.
     */
    private Map<ResourceSet, IPermissionAuthority> resourceSetToAuthority = new WeakHashMap<ResourceSet, IPermissionAuthority>();

    /**
     * Creates a new registry.
     * 
     */
    public PermissionAuthorityRegistryImpl() {
    }

    @Override
    public IPermissionAuthority getPermissionAuthority(final EObject modelElement) {
        IPermissionAuthority authority = null;
        // If element is a Resource (can happen when the given parameter is a
        // CDOResource)
        if (modelElement instanceof Resource && ((Resource) modelElement).getResourceSet() != null) {
            authority = getPermissionAuthority(((Resource) modelElement).getResourceSet());
        } else {
            Resource modelElementResource = null;
            if (modelElement != null) {
                modelElementResource = modelElement.eResource();
            }
            if (modelElement == null || modelElementResource == null || modelElementResource.getResourceSet() == null) {
                if (resourceSetToAuthority.size() > 0) {
                    authority = resourceSetToAuthority.values().iterator().next();
                } else {
                    // here we really can't manage something
                    throw new RuntimeException(Messages.PermissionAuthorityRegistryImpl_noResourceMessage);
                }
            } else {
                authority = getPermissionAuthority(modelElementResource.getResourceSet());
            }
        }
        return authority;
    }

    @Override
    public IPermissionAuthority getPermissionAuthority(final ResourceSet resourceSet) {
        if (!resourceSetToAuthority.containsKey(resourceSet)) {
            final IPermissionAuthority newAuth = PermissionService.createPermissionAuthority(resourceSet);
            newAuth.init(resourceSet);
            resourceSetToAuthority.put(resourceSet, newAuth);
        }
        return resourceSetToAuthority.get(resourceSet);
    }

    @Override
    public IPermissionAuthority getPermissionAuthority(final Resource res) {
        IPermissionAuthority result = null;
        if (res.getResourceSet() != null) {
            result = getPermissionAuthority(res.getResourceSet());
        }
        if (result == null && res.getContents().size() > 0) {
            result = getPermissionAuthority(res.getContents().get(0));
        }
        return result;
    }

    @Override
    public void dispose() {
        resourceSetToAuthority.clear();
    }

}
