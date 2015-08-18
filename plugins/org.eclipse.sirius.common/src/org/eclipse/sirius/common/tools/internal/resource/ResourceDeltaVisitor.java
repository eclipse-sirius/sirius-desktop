/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * A {@link IResourceDeltaVisitor}.
 * 
 * @author cbr
 */
public class ResourceDeltaVisitor implements IResourceDeltaVisitor {

    private Collection<Resource> addedResources = new ArrayList<Resource>();

    private Collection<Resource> changedResources = new ArrayList<Resource>();

    private Collection<Resource> removedResources = new ArrayList<Resource>();

    private Collection<Resource> initialresources;

    private WorkspaceBackend workspaceBackend;

    /**
     * Default constructor.
     * 
     * @param workspaceBackend
     *            {@link WorkspaceBackend}
     */
    public ResourceDeltaVisitor(WorkspaceBackend workspaceBackend) {
        this.workspaceBackend = workspaceBackend;
        initialresources = new ArrayList<Resource>(this.workspaceBackend.getObservedSet().getResources());
    }

    /**
     * {@inheritDoc}
     */
    public boolean visit(final IResourceDelta delta) {
        if (!isMarkerNotification(delta) && !isTeamSyncNotification(delta) && isAboutFileChange(delta)) {
            processDelta(delta);
        }
        return true;
    }

    private boolean isAboutFileChange(final IResourceDelta delta) {
        return delta.getResource().getType() == IResource.FILE;
    }

    private boolean isMarkerNotification(final IResourceDelta delta) {
        return delta.getFlags() == IResourceDelta.MARKERS;
    }

    private boolean isTeamSyncNotification(final IResourceDelta delta) {
        return delta.getFlags() == IResourceDelta.SYNC;
    }

    /**
     * @param delta
     */
    private void processDelta(final IResourceDelta delta) {
        // CHECKSTYLE:OFF
        if ((delta.getKind() & (IResourceDelta.ADDED | IResourceDelta.CHANGED | IResourceDelta.REMOVED)) != 0 && delta.getFullPath() != null) {
            // CHECKSTYLE:ON
            final URI uri = URI.createURI(delta.getFullPath().toString(), true);
            final Resource resource = alreadyContainsResource(uri, false);
            if (resource != null) {
                if ((delta.getKind() & IResourceDelta.REMOVED) != 0) {
                    removedResources.add(resource);
                } else if ((delta.getKind() & IResourceDelta.CHANGED) != 0) {
                    changedResources.add(resource);
                } else {
                    addedResources.add(resource);
                }
            }
        }
    }

    /**
     * Get a copy of the initial set of resources to avoid
     * ConcurrentModificationExceptions. alreadyContainsResource() does the
     * equivalent to ResourceSetImpl.getResource() on the copy, and uses code
     * copied from ResourceSetImpl to guarantee the same semantics.
     */
    private Resource alreadyContainsResource(final URI uri, boolean loadOnDemand) {
        Resource alreadyContainsResource = null;
        Map<URI, Resource> map = ((ResourceSetImpl) workspaceBackend.getObservedSet()).getURIResourceMap();
        if (map != null) {
            Resource resource = map.get(uri);
            if (resource != null) {
                alreadyContainsResource = resource;
            }
        }
        if (alreadyContainsResource == null) {
            URIConverter theURIConverter = workspaceBackend.getObservedSet().getURIConverter();
            URI normalizedURI = theURIConverter.normalize(uri);
            for (Resource resource : initialresources) {
                URI resourceNormalizedURI = URI.createURI(""); //$NON-NLS-1$
                try {
                    resourceNormalizedURI = workspaceBackend.getObservedSet().getURIConverter().normalize(resource.getURI());
                } catch (IllegalStateException e) {
                    // Silent catch : normalizing URI has failed, it cannot
                    // match the changed resource's URI
                }
                if (resourceNormalizedURI.equals(normalizedURI)) {
                    if (map != null) {
                        map.put(uri, resource);
                    }
                    alreadyContainsResource = resource;
                    break;
                }
            }
            if (alreadyContainsResource == null) {
                Resource delegatedResource = delegatedGetResource(uri, loadOnDemand);
                if (delegatedResource != null) {
                    if (map != null) {
                        map.put(uri, delegatedResource);
                    }
                    alreadyContainsResource = delegatedResource;
                }
            }
        }
        return alreadyContainsResource;
    }

    /**
     * Returns a resolved resource available outside of the resource set. It is
     * called by {@link #getResource(URI, boolean) getResource(URI, boolean)}
     * after it has determined that the URI cannot be resolved based on the
     * existing contents of the resource set. This implementation looks up the
     * URI in the {#getPackageRegistry() local} package registry. Clients may
     * extend this as appropriate.
     * 
     * @param uri
     *            the URI
     * @param loadOnDemand
     *            whether demand loading is required.
     * @return the {@link Resource} corresponding to the {@link URI}
     */
    protected Resource delegatedGetResource(URI uri, boolean loadOnDemand) {
        EPackage ePackage = workspaceBackend.getObservedSet().getPackageRegistry().getEPackage(uri.toString());
        return ePackage == null ? null : ePackage.eResource();
    }

    public Collection<Resource> getAddedResources() {
        return addedResources;
    }

    public Collection<Resource> getChangedResources() {
        return changedResources;
    }

    public Collection<Resource> getRemovedResources() {
        return removedResources;
    }
}
