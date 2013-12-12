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
package org.eclipse.sirius.business.internal.movida;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * Operations to manipulate Viewpoints Resources (VSMs).
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointResourceOperations {
    /**
     * The Sirius resource to manipulate.
     */
    private final Resource resource;

    /**
     * Constructor.
     * 
     * @param resource
     *            the viewpoint resource to manipulate.
     */
    public ViewpointResourceOperations(Resource resource) {
        this.resource = Preconditions.checkNotNull(resource);
    }

    /**
     * Unloads the resource, and reset all the proxy URIs for viewpoints elements
     * to the corresponding logical (instead of physical) URI, so that when
     * references to these elements are re-resolved later, they are resolved
     * taking the logical/physical mapping at that time.
     */
    public void unloadAndResetProxyURIs() {
        Map<EObject, URI> logicalURIs = computeLogicalURIs();
        resource.unload();
        for (InternalEObject obj : Iterables.filter(logicalURIs.keySet(), InternalEObject.class)) {
            if (obj.eIsProxy()) {
                obj.eSetProxyURI(logicalURIs.get(obj));
            }
        }
    }

    /**
     * For each element of the resource which appears inside a Sirius
     * definition (including the Sirius definitions themselves), returns the
     * logical Sirius URI designating the element.
     */
    private Map<EObject, URI> computeLogicalURIs() {
        Preconditions.checkState(resource.isLoaded());

        Map<EObject, URI> logicalURIs = Maps.newHashMap();
        URI currentSiriusURI = null;
        Iterator<EObject> allContents = EcoreUtil.getAllProperContents(resource, false);
        while (allContents.hasNext()) {
            EObject internal = allContents.next();
            if (internal instanceof Viewpoint) {
                currentSiriusURI = new ViewpointQuery((Viewpoint) internal).getViewpointURI().get();
            }
            if (currentSiriusURI != null) {
                String fragment = internal.eResource().getURIFragment(internal);
                logicalURIs.put(internal, currentSiriusURI.appendFragment(fragment));
            }
        }
        return logicalURIs;
    }
}
