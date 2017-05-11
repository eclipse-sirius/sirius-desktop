/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.representation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.danalysis.DRepresentationLocationRule;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * This implementation to {@link DRepresentationLocationRule} give a behavior for representation resources in local
 * workspace.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class DRepLocationRuleForLocalResource implements DRepresentationLocationRule {

    @Override
    public boolean providesURI(DRepresentation representation, Resource dViewResource) {
        return !new URIQuery(dViewResource.getURI()).isCDOURI();
    }

    @Override
    public URI getResourceURI(DRepresentation representation, Resource airdResource) {
        URI repResourceURI = null;
        if (Boolean.getBoolean("createLocalRepresentationInSeparateResource")) { //$NON-NLS-1$
            repResourceURI = getDedicatedRepResourceURI(representation, airdResource);
        } else {
            repResourceURI = airdResource.getURI();
        }
        return repResourceURI;
    }

    /**
     * Create the representation resource URI so that the representation can be stored in a specific resource.<br/>
     * The URI is based on the aird resource URI. Only the last part of the segment is changed.
     * 
     * @param airdResource
     *            the aird resource
     * @param representation
     *            the representation
     * @return the representation URI
     */
    protected URI getDedicatedRepResourceURI(DRepresentation representation, Resource airdResource) {
        int count = 1;
        ResourceSet resourceSet = airdResource.getResourceSet();
        URI repUri = createRepURI(airdResource, representation, count++);
        while (!isUsableURI(repUri, resourceSet, representation)) {
            repUri = createRepURI(airdResource, representation, count++);
        }
        return repUri;
    }

    private boolean isUsableURI(URI repUri, ResourceSet resourceSet, DRepresentation representation) {
        boolean usableURI = true;
        Resource resource = resourceSet.getResource(repUri, false);
        // A usable URI is when the resource is not already in the resourceSet or does not exist, that is, is not
        // loadable
        usableURI = resource == null && !existsResource(repUri, resourceSet);

        // We consider the URI usable if the representation is already in the resource
        if (resource != null) {
            TreeIterator<EObject> allContents = resource.getAllContents();
            while (allContents.hasNext()) {
                EObject object = allContents.next();
                if (object instanceof DRepresentation) {
                    if (representation.equals(object)) {
                        usableURI = true;
                        break;
                    } else {
                        allContents.prune();
                    }
                }
            }
        }
        return usableURI;
    }

    /**
     * Indicates if the given uri corresponds to a resource that then could be loaded and added to the resourceSet.
     * 
     * @param repUri
     *            the resource URI
     * @param resourceSet
     *            the resourceSet
     * @return true if the resource exists.
     */
    protected boolean existsResource(URI repUri, ResourceSet resourceSet) {
        return resourceSet.getURIConverter().exists(repUri, null);
    }

    /**
     * Create the representation URI based on the aird resource URI.</br>
     * Only the last part of the segment is changed. It will include the name of the RepresentationDescription
     * 
     * @param airdResource
     *            the aird resource
     * @param representation
     *            the representation
     * @param count
     * @return the representation URI
     */
    private URI createRepURI(Resource airdResource, DRepresentation representation, int count) {
        // get the representation URI fragment
        RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
        String repName = description.getName().replace(' ', '_');
        repName += "_" + String.valueOf(count); //$NON-NLS-1$

        URI airdURI = airdResource.getURI();

        List<String> srmFileSegments = new ArrayList<>(airdURI.segmentsList());
        srmFileSegments.remove(srmFileSegments.size() - 1);
        srmFileSegments.add(SiriusUtil.REPRESENTATIONS_FOLDER_NAME);
        srmFileSegments.add(repName + "." + SiriusUtil.REPRESENTATION_FILE_EXTENSION); //$NON-NLS-1$

        // return the URI
        return URI.createHierarchicalURI(airdURI.scheme(), airdURI.authority(), airdURI.device(), srmFileSegments.toArray(new String[srmFileSegments.size()]), airdURI.query(), airdURI.fragment());
    }

    @Override
    public Boolean isARepresentationFileExtension(String fileExtension) {
        return SiriusUtil.REPRESENTATION_FILE_EXTENSION.equals(fileExtension);
    }

}
