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
package org.eclipse.sirius.business.api.resource.strategy;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This legacy implementation can be used by client who don't want the new
 * optimized behavior. They just have to contributed to {@link ResourceStrategy}
 * extension point with this class to keep the old and non optimized
 * behavior.</br>
 * 
 * @deprecated This class corresponds to the previous code. It will be removed
 *             in a future version of Sirius (6.0.0).
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
@Deprecated
public class LegacyReleaseResourceStrategyImpl extends AbstractResourceStrategyImpl {

    @Override
    public IStatus releaseResourceAtResourceSetDispose(Resource resource, IProgressMonitor monitor) {
        try {
            // Don't try to unload metamodel resources.
            if (!isFromPackageRegistry(resource)) {
                resource.unload();
            }
        } catch (final IllegalStateException e) {
            // we might have an exception unloading a resource already
            // unaccessible
            return new Status(IStatus.WARNING, SiriusPlugin.ID, MessageFormat.format(Messages.DAnalysisSessionImpl_unloadingErrorMsg, e.getMessage()), e);
        }
        return new Status(IStatus.OK, SiriusPlugin.ID, ""); //$NON-NLS-1$
    }

    private boolean isFromPackageRegistry(Resource resource) {
        URI uri = resource.getURI();
        return uri != null && resource.getResourceSet().getPackageRegistry().getEPackage(uri.toString()) != null;
    }

    @Override
    public boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType) {
        return ResourceStrategyType.RELEASE_RESOURCE_AT_RESOURCESET_DISPOSE.equals(resourceStrategyType);
    }

    @Override
    public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
        return ResourceStrategyType.RELEASE_RESOURCE_AT_RESOURCESET_DISPOSE.equals(resourceStrategyType);
    }

}
