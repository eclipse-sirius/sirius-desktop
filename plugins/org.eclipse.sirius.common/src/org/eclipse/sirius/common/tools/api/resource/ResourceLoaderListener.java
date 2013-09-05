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
package org.eclipse.sirius.common.tools.api.resource;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * This adapter listen a resource set in order to determine whether a resource
 * has been loaded or not.
 * 
 * @author cbrun
 * 
 */
public class ResourceLoaderListener extends EContentAdapter {

    private Set<Resource> resources = new HashSet<Resource>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification notification) {
        if (notification.getNotifier() instanceof Resource) {
            switch (notification.getFeatureID(Resource.class)) {
            case Resource.RESOURCE__WARNINGS:
            case Resource.RESOURCE__IS_LOADED:
                final Resource resource = (Resource) notification.getNotifier();
                resources.add(resource);
                break;
            default:
                break;
            }
        } else {
            super.notifyChanged(notification);
        }
    }

    /**
     * Get the loaded resources.
     * 
     * @return the resources loaded while the eAdapter was listening.
     */
    public Set<Resource> getLoadedResources() {
        return resources;
    }
}
