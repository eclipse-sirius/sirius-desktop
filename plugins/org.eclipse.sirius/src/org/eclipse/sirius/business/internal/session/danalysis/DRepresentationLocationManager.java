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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Lists;

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
     * @param aird
     *            the aird resource from which the representation will be referenced.
     * @return the representation resource.
     */
    public Resource getOrCreateRepresentationResource(DRepresentation representation, Resource aird) {
        Resource resource = null;
        ResourceSet resourceSet = aird != null ? aird.getResourceSet() : null;
        if (resourceSet != null) {
            // Get the fragment of the URI
            URI repResourceURI = getURIFromExtensionPoint(representation, aird);
            if (repResourceURI == null) {
                repResourceURI = getRepURI(representation, aird, resourceSet);
            }

            // get or create resource
            resource = resourceSet.getResource(repResourceURI, false);
            if (resource == null) {
                resource = resourceSet.createResource(repResourceURI);
            }
        }
        return resource;
    }

    private URI getRepURI(DRepresentation representation, Resource aird, ResourceSet resourceSet) {
        int count = 0;
        URI repUri = createRepURI(aird, representation, count++);
        while (resourceSet.getResource(repUri, false) != null) {
            repUri = createRepURI(aird, representation, count++);
        }
        return repUri;

    }

    private URI getURIFromExtensionPoint(DRepresentation representation, Resource dViewResource) {
        List<DRepresentationLocationRule> extensionPointRules = Lists.newArrayList();
        extensionPointRules.addAll(EclipseUtil.getExtensionPlugins(DRepresentationLocationRule.class, DRepresentationLocationRule.ID, DRepresentationLocationRule.CLASS_ATTRIBUTE));

        Optional<DRepresentationLocationRule> locationRule = extensionPointRules.stream().filter(rule -> rule.provides(representation, dViewResource)).findFirst();
        return locationRule.isPresent() ? locationRule.get().getResourceURI(representation, dViewResource) : null;
    }

    /**
     * Create the representation URI based on the aird resource URI. Only the last part of the segment is changed.
     * 
     * @param aird
     * @param representation
     * @param count
     * @return
     */
    private URI createRepURI(Resource aird, DRepresentation representation, int count) {
        // get the representation URI fragment
        String repName = representation.getName().replace(' ', '_');
        if (count > 0) {
            repName += String.valueOf(count);
        }
        URI airdURI = aird.getURI();

        List<String> srmFileSegments = new ArrayList<>(airdURI.segmentsList());
        srmFileSegments.remove(srmFileSegments.size() - 1);
        srmFileSegments.add(airdURI.lastSegment().replace("." + SiriusUtil.SESSION_RESOURCE_EXTENSION, "_aird")); //$NON-NLS-1$ //$NON-NLS-2$
        srmFileSegments.add(repName + "." + SiriusUtil.REPRESENTATION_FILE_EXTENSION); //$NON-NLS-1$

        // return the URI
        return URI.createHierarchicalURI(airdURI.scheme(), airdURI.authority(), airdURI.device(), srmFileSegments.toArray(new String[srmFileSegments.size()]), airdURI.query(), airdURI.fragment());
    }
}
