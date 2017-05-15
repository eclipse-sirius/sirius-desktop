/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others,
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.representation;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.danalysis.DRepresentationLocationRule;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * This manager is responsible to retrieve or create the {@link Resource} of a {@link DRepresentation}.
 * 
 * @author fbarbin
 *
 */
public class DRepresentationLocationManager {

    /**
     * Get or creates (if it does not exist) the corresponding {@link Resource} for the given representation.
     * 
     * @param representation
     *            the current representation.
     * @param airdResource
     *            the aird resource from which the representation will be referenced.
     * @return the representation resource.
     */
    public Resource getOrCreateRepresentationResource(DRepresentation representation, Resource airdResource) {
        Resource resource = null;
        ResourceSet resourceSet = airdResource != null ? airdResource.getResourceSet() : null;
        if (resourceSet != null) {
            // Get the fragment of the URI
            resource = getURIFromExtensionPoint(representation, airdResource).map(repResourceURI -> {
                // get or create resource
                Resource res = resourceSet.getResource(repResourceURI, false);
                if (res == null) {
                    if (resourceSet.getURIConverter().exists(repResourceURI, null)) {
                        // load the resource in case the representation have to be added in a non loaded resource
                        res = resourceSet.getResource(repResourceURI, true);
                    } else {
                        res = resourceSet.createResource(repResourceURI);
                    }
                }
                return res;
            }).orElse(null);
        }
        return resource;
    }

    private Optional<URI> getURIFromExtensionPoint(DRepresentation representation, Resource airdResource) {
        Optional<DRepresentationLocationRule> representationLocationRule = DRepresentationLocationRuleRegistry.getInstance().getRepresentationLocationRule(representation, airdResource);

        return representationLocationRule.map(r -> r.getResourceURI(representation, airdResource));
    }
}
