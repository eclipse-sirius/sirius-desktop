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
package org.eclipse.sirius.common.acceleo.mtl.business.api;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * Implements the {@link IResourceVisitor} interface in order to try and find a
 * resource corresponding to a given path within a container.
 * <p>
 * This visitor returns all paths that ends in the given <em>input</em> path.
 * For example, if the given <em>path</em> is <code>a/package/test.emtl</code>,
 * this visitor will return the paths <code>a/package/test.emtl</code> and
 * <code>my/a/package/test.emtl</code> but not <code>package/test.emtl</code>.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResourceFinder implements IResourceVisitor {
    /** This will be used whenever we need to split a path around its separator. */
    private static final String FILE_SEPARATOR_REGEX = "/|\\\\"; //$NON-NLS-1$

    /**
     * Path we need resources for. Correspond to the <em>input</em> path split
     * around its <em>'/'</em> separators.
     */
    protected final String[] path;

    /** The list of resources matching {@link #path} we found. */
    private final List<IResource> matches = new ArrayList<>();

    /**
     * Creates our resource finder given the path of the sought emtl.
     * 
     * @param path
     *            The path for which we need an emtl.
     */
    public ResourceFinder(String path) {
        this.path = path.split(FILE_SEPARATOR_REGEX);
    }

    /**
     * Returns the list of matches. Should only be called after
     * {@link IResource#accept(IResourceVisitor)} with this visitor.
     * 
     * @return The list of resources matching {@link #path} we found.
     */
    public List<IResource> getMatches() {
        return matches;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
     */
    public boolean visit(IResource resource) throws CoreException {
        if (path.length == 0) {
            return false;
        }

        if (isCandidate(resource)) {
            matches.add(resource);
        }

        // Candidate or not, visit the resource's children
        return true;
    }

    /**
     * Checks whether the path of this resource matches the path we seek.
     * 
     * @param resource
     *            The resource which path we need to check.
     * @return <code>true</code> if the given resource is a candidate,
     *         <code>false</code> otherwise.
     */
    protected boolean isCandidate(IResource resource) {
        if (path.length == 0) {
            return false;
        }

        boolean isCandidate = false;
        if (path[path.length - 1].equals(resource.getName())) {
            isCandidate = true;
            IContainer container = resource.getParent();
            for (int i = path.length - 2; i >= 0 && container != null && isCandidate; i--) {
                if (!path[i].equals(container.getName())) {
                    isCandidate = false;
                }
                container = container.getParent();
            }
        }

        return isCandidate;
    }
}
