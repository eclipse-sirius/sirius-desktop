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
package org.eclipse.sirius.business.api.query;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.business.api.session.resource.AirdResource;

/**
 * Queries on EMF Resources.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ResourceQuery {
    /**
     * The resource to query.
     */
    private final Resource resource;

    /**
     * Constructor.
     * 
     * @param resource
     *            the resource to query.
     */
    public ResourceQuery(Resource resource) {
        this.resource = Preconditions.checkNotNull(resource);
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this
     * resource depend and which are loaded/resolved. Calling this method does
     * not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources
     * through containment references.
     * 
     * @return the URIs of all the Resources this one depends on and which are
     *         loaded/resolved.
     */
    public Collection<URI> getResolvedDependencies() {
        Collection<URI> dependencies = Sets.newHashSet();
        for (EObject root : resource.getContents()) {
            dependencies.addAll(new EObjectQuery(root).getResolvedDependencies());
        }
        return dependencies;
    }

    /**
     * Returns the URIs of all the resources on which loaded elements of this
     * resource depend but which are not yet loaded/resolved. Calling this
     * method does not load any new resource of resolve any proxy.
     * <p>
     * <em>WARNING:</em> This does not consider dependencies to sub-resources
     * through containment references.
     * 
     * @return the URIs of all the Resources this one depends on but which is
     *         not yet loaded/resolved.
     */
    public Collection<URI> getUnresolvedDependencies() {
        Collection<URI> dependencies = Sets.newHashSet();
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
     * <LI>resource with {@link org.eclipse.sirius.DAnalysis} as content.</LI>
     * </UL>
     * 
     * @return true if this resource is a representations resource, false
     *         otherwise.
     */
    public boolean isRepresentationsResource() {
        boolean isRepresentationsResource = false;
        try {
            isRepresentationsResource = resource.getURI() != null;
        } catch (IllegalStateException e) {
            // Silent catch: if an issue occurred while getting this Resource's
            // URI, then it will not be considered as a representation resource
        }
        isRepresentationsResource = isRepresentationsResource && new FileQuery(resource.getURI().fileExtension()).isSessionResourceFile();
        isRepresentationsResource = isRepresentationsResource || resource instanceof AirdResource;
        if (!isRepresentationsResource && resource.getContents() != null && resource.getContents().size() == 1) {
            isRepresentationsResource = resource.getContents().get(0).eClass().equals(SiriusPackage.eINSTANCE.getDAnalysis());
        }
        return isRepresentationsResource;
    }

    /**
     * Tells if the current {@link Resource} corresponds to a modeler resource
     * (*.odesign).
     * 
     * @return true if it is a modeler resource
     */
    public boolean isModelerResource() {
        if (resource.getURI() != null) {
            return new FileQuery(resource.getURI().fileExtension()).isVSMFile();
        }
        return false;
    }
}
