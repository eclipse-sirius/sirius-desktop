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
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Makes sure modification tracking is enabled for all resources added to the
 * target resource set.
 * 
 * @author pierre-charles.david@obeo.fr
 */
final class ModificationTrackingEnabler extends AdapterImpl {
    private final ResourceSet resourceSet;

    /**
     * Constructor.
     * 
     * @param resourceSet
     *            the resource set to watch.
     */
    ModificationTrackingEnabler(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(Notification msg) {
        if (isNewResourceAddition(msg)) {
            Resource newResource = (Resource) msg.getNewValue();
            if (newResource.getURI().isPlatformResource()) {
                newResource.setTrackingModification(true);
            }
        }
    }

    private boolean isNewResourceAddition(Notification msg) {
        boolean ifFromResourceSet = msg.getNotifier() == resourceSet;
        boolean isInResourceFeature = msg.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES;
        boolean isAddition = msg.getEventType() == Notification.ADD;
        return ifFromResourceSet && isInResourceFeature && isAddition;
    }
}
