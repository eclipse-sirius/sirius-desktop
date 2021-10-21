/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Queries on EMF Resources.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ResourceQuery {
    /**
     * The resource to query.
     */
    protected final Resource resource;

    /**
     * Constructor.
     * 
     * @param resource
     *            the resource to query.
     */
    public ResourceQuery(Resource resource) {
        this.resource = Objects.requireNonNull(resource);
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this resource depend and which are
     * loaded/resolved. Calling this method does not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources through containment references.
     * 
     * @return the URIs of all the Resources this one depends on and which are loaded/resolved.
     */
    public Collection<URI> getResolvedDependencies() {
        Collection<URI> dependencies = new HashSet<>();
        for (EObject root : resource.getContents()) {
            dependencies.addAll(new EObjectQuery(root).getResolvedDependencies());
        }
        return dependencies;
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this resource depend but which are not yet
     * loaded/resolved. Calling this method does not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources through containment references.
     * 
     * @return the URIs of all the Resources this one depends on but which is not yet loaded/resolved.
     */
    public Collection<URI> getUnresolvedDependencies() {
        Collection<URI> dependencies = new HashSet<>();
        for (EObject root : resource.getContents()) {
            dependencies.addAll(new EObjectQuery(root).getUnresolvedDependencies());
        }
        return dependencies;
    }

    /**
     * Check if this resource is a representations resource :
     * <UL>
     * <LI>resource with aird extension, or</LI>
     * <LI>resource of kind {@link AirdResource}, or</LI>
     * <LI>resource with {@link org.eclipse.sirius.viewpoint.DAnalysis} as content.</LI>
     * </UL>
     * 
     * @return true if this resource is a representations resource, false otherwise.
     */
    public boolean isRepresentationsResource() {
        boolean isRepresentationsResource = false;
        URI uri = getUri();
        isRepresentationsResource = uri != null && new FileQuery(uri.fileExtension()).isSessionResourceFile();
        isRepresentationsResource = isRepresentationsResource || resource instanceof AirdResource;
        if (!isRepresentationsResource && !resource.getContents().isEmpty()) {
            // Bug #490908: only check the 1st root since Sirius always puts
            // DAnalysis as 1st root of the representation resources.
            isRepresentationsResource = resource.getContents().get(0) instanceof DAnalysis;
        }
        return isRepresentationsResource;
    }

    /**
     * Returns whether the resource is a srm resource ({@link isSrmResource}) or an aird resource
     * ({@link isRepresentationsResource}).
     * 
     * @return true if the resource is a srm or aird resource.
     */
    public boolean isAirdOrSrmResource() {
        return isSrmResource() || isRepresentationsResource();
    }

    /**
     * Returns whether the resource is a representation resource or not that is contains a {@link DRepresentation} as
     * root element.
     * 
     * @return true if the resource is a representation resource, otherwise false.
     */
    public boolean isSrmResource() {
        EList<EObject> contents = resource.getContents();
        return Optional.ofNullable(contents.isEmpty() ? null : contents.get(0)).filter(DRepresentation.class::isInstance).isPresent();
    }

    /**
     * Tells if the current {@link Resource} corresponds to a modeler resource (*.odesign).
     * 
     * @return true if it is a modeler resource
     */
    public boolean isModelerResource() {
        URI uri = getUri();
        if (uri != null) {
            return new FileQuery(uri.fileExtension()).isVSMFile();
        }
        return false;
    }

    private URI getUri() {
        URI uri = null;
        try {
            uri = resource.getURI();
        } catch (IllegalStateException e) {
            // Silent catch: if an issue occurred while getting this Resource's
            // URI, then it will not be considered as a representation resource
        }
        return uri;
    }
}
