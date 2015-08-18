/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A class aggregating all the queries (read-only!) having a URI as a starting
 * point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class URIQuery {

    /** The {@link URI} scheme used for Sirius environment xmi resources. */
    public static final String ENVIRONMENT_URI_SCHEME = "environment"; //$NON-NLS-1$

    /** The {@link URI} of Sirius core environment xmi resource. */
    public static final URI VIEWPOINT_ENVIRONMENT_QUERY = URI.createURI(ENVIRONMENT_URI_SCHEME + ":/viewpoint"); //$NON-NLS-1$

    /** The {@link URI} scheme used for InMemory URIs. */
    public static final String INMEMORY_URI_SCHEME = "memory"; //$NON-NLS-1$

    /** The {@link URI} scheme used for CDO URIs. */
    public static final String CDO_URI_SCHEME = "cdo"; //$NON-NLS-1$

    private URI uri;

    /**
     * Create a new query.
     * 
     * @param uri
     *            the starting point.
     */
    public URIQuery(URI uri) {
        this.uri = uri;
    }

    /**
     * Utility method that returns the IResource located at the given URI (if
     * any).
     * 
     * @return the IResource located at the given URI, null if no resource found
     */
    public Option<IResource> getCorrespondingResource() {
        IResource res = null;
        if (uri.isPlatformPlugin()) {
            String pluginResourceString = uri.toString();
            if (pluginResourceString != null) {
                res = ResourcesPlugin.getWorkspace().getRoot();
            }
        } else {
            String platformResourceString = uri.toPlatformString(true);
            if (platformResourceString != null) {
                res = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString));
            } else {
                if (uri.toFileString() != null) {
                    res = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(uri.toFileString()));
                }
            }
        }
        if (res == null) {
            return Options.newNone();
        } else {
            return Options.newSome(res);
        }
    }

    /**
     * Check if this URI is the Sirius core environment URI.
     * 
     * @return true if this URI is the URI of the Sirius core environment, false
     *         otherwise.
     */
    public boolean isSiriusCoreEnvironmentURI() {
        return VIEWPOINT_ENVIRONMENT_QUERY.equals(uri);
    }

    /**
     * Check if this URI is a Sirius environment URI.
     * 
     * @return true if this URI is the URI of a Sirius environment resource,
     *         false otherwise.
     */
    public boolean isEnvironmentURI() {
        return ENVIRONMENT_URI_SCHEME.equals(uri.scheme());
    }

    /**
     * Checks whether the current {@link URI} is a InMemory URI, i.e. a URI for
     * InMemoryResource.
     * 
     * @return <code>true</code> if it is a InMemory<code>uri</code> is a valid
     *         InMemory URI.
     */
    public boolean isInMemoryURI() {
        return INMEMORY_URI_SCHEME.equals(uri.scheme());
    }

    /**
     * Checks whether the current {@link URI} is a CDO URI, i.e. a URI for a
     * resource stored in a CDO Repository.
     * 
     * @return <code>true</code> if it is a CDO<code>uri</code> is a valid CDO
     *         URI.
     */
    public boolean isCDOURI() {
        return CDO_URI_SCHEME.equals(uri.scheme());
    }

    /**
     * Returns the workspace-relative path of the resource, manage especially
     * InMemory URI which have a segment corresponding to a existing project,
     * but not CDO URI.
     * 
     * @return the workspace-relative path of the resource, or null if not
     *         corresponding workspace path exists.
     */
    public String toPlatformString() {
        String platformString = null;
        if (isInMemoryURI()) {
            StringBuffer result = new StringBuffer();
            result.append('/').append(uri.opaquePart());
            platformString = result.toString();
        } else if (uri.isPlatform()) {
            platformString = uri.toPlatformString(true);
        }
        return platformString;
    }
}
