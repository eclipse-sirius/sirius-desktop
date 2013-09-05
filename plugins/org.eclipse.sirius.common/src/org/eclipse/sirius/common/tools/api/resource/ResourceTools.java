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

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Tools for EMF resources.
 * 
 * @author cbrun
 * 
 */
public final class ResourceTools {

    /**
     * Avoid instantiation.
     */
    private ResourceTools() {

    }

    /**
     * Attach a listener to a given resource set.
     * 
     * @param set
     *            the resource set
     * @return the created adapter
     */
    public static ResourceLoaderListener attachResourceLoaderListener(final ResourceSet set) {
        final ResourceLoaderListener adapter = new ResourceLoaderListener();
        set.eAdapters().add(adapter);
        return adapter;
    }

    /**
     * Detach the adapter from a resource set.
     * 
     * @param set
     *            the resource set
     * @param adapter
     *            the adapter to detach
     */
    public static void detachResourceLoaderListener(final ResourceSet set, final ResourceLoaderListener adapter) {
        if (set.eAdapters().contains(adapter)) {
            set.eAdapters().remove(adapter);
        }
    }
}
