/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.strategy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Objects;

/**
 * Represents the default and optimized contribution to the resource strategy.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class DefaultOptimizedResourceStrategyImpl implements ResourceStrategy {

    @Override
    public IStatus releaseResourceAtResourceSetDispose(Resource resource, IProgressMonitor monitor) {
        URI uri = resource.getURI();
        if (uri != null && Objects.equal(URIQuery.INMEMORY_URI_SCHEME, uri.scheme())) {
            // InMemory resources must be unloaded explicitly for their memory
            // buffers to be released.
            resource.unload();
        } else if (!isFromPackageRegistry(resource)) {
            // optimized implementation that avoids to unload the resource
            TreeIterator<EObject> allContents = EcoreUtil.getAllProperContents(resource, false);
            while (allContents.hasNext()) {
                EObject eObject = allContents.next();
                eObject.eAdapters().clear();
            }
        }
        return new Status(IStatus.INFO, SiriusPlugin.ID, ""); //$NON-NLS-1$
    }

    private boolean isFromPackageRegistry(Resource resource) {
        URI uri = resource.getURI();
        return uri != null && resource.getResourceSet().getPackageRegistry().getEPackage(uri.toString()) != null;
    }

    @Override
    public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
        return true;
    }

    @Override
    public boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType) {
        return true;
    }
}
