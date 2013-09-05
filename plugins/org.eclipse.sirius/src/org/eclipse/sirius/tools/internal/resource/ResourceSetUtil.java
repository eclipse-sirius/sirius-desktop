/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

/**
 * A Helper to set a new {@link IProgressMonitor} to a {@link ResourceSet}'s
 * {@link FileURIHandlerWithProgressMonitorImpl} to have progress monitor on
 * resource loading.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class ResourceSetUtil {

    private ResourceSetUtil() {
        // Not instanciate
    }

    /**
     * Set a new {@link IProgressMonitor} to a {@link ResourceSet}'s
     * {@link FileURIHandlerWithProgressMonitorImpl} to have progress monitor on
     * resource loading.
     * 
     * @param resourceSet
     *            the {@link ResourceSet} to update
     * @param monitor
     *            the {@link IProgressMonitor} to set
     */
    public static void setProgressMonitor(ResourceSet resourceSet, IProgressMonitor monitor) {
        List<URIHandler> handlers = new ArrayList<URIHandler>(resourceSet.getURIConverter().getURIHandlers());
        FileURIHandlerWithProgressMonitorImpl fileURIHandlerWithProgressMonitorImpl = getFileURIHandlerWithProgressMonitorImpl(resourceSet);
        if (fileURIHandlerWithProgressMonitorImpl == null) {
            fileURIHandlerWithProgressMonitorImpl = new FileURIHandlerWithProgressMonitorImpl();
            handlers.add(0, fileURIHandlerWithProgressMonitorImpl);
        }
        PlatformResourceURIHandlerWithProgressMonitorImpl platformResourceURIHandlerWithProgressMonitorImpl = getPlatformResourceURIHandlerWithProgressMonitorImpl(resourceSet);
        if (platformResourceURIHandlerWithProgressMonitorImpl == null) {
            platformResourceURIHandlerWithProgressMonitorImpl = new PlatformResourceURIHandlerWithProgressMonitorImpl();
            handlers.add(0, platformResourceURIHandlerWithProgressMonitorImpl);
        }
        resourceSet.setURIConverter(new ExtensibleURIConverterImpl(handlers, ContentHandler.Registry.INSTANCE.contentHandlers()));
        fileURIHandlerWithProgressMonitorImpl.setProgressMonitor(monitor);
        platformResourceURIHandlerWithProgressMonitorImpl.setProgressMonitor(monitor);
    }

    private static FileURIHandlerWithProgressMonitorImpl getFileURIHandlerWithProgressMonitorImpl(ResourceSet resourceSet) {
        FileURIHandlerWithProgressMonitorImpl fileURIHandlerWithProgressMonitorImpl = null;
        URIConverter uriConverter = resourceSet.getURIConverter();
        if (uriConverter instanceof ExtensibleURIConverterImpl) {
            ExtensibleURIConverterImpl extensibleURIConverterImpl = (ExtensibleURIConverterImpl) uriConverter;
            List<URIHandler> uriHandlers = extensibleURIConverterImpl.getURIHandlers();
            for (URIHandler uriHandler : uriHandlers) {
                if (uriHandler instanceof FileURIHandlerWithProgressMonitorImpl) {
                    fileURIHandlerWithProgressMonitorImpl = (FileURIHandlerWithProgressMonitorImpl) uriHandler;
                    break;
                }
            }
        }
        return fileURIHandlerWithProgressMonitorImpl;
    }

    private static PlatformResourceURIHandlerWithProgressMonitorImpl getPlatformResourceURIHandlerWithProgressMonitorImpl(ResourceSet resourceSet) {
        PlatformResourceURIHandlerWithProgressMonitorImpl platformResourceURIHandlerWithProgressMonitorImpl = null;
        URIConverter uriConverter = resourceSet.getURIConverter();
        if (uriConverter instanceof ExtensibleURIConverterImpl) {
            ExtensibleURIConverterImpl extensibleURIConverterImpl = (ExtensibleURIConverterImpl) uriConverter;
            List<URIHandler> uriHandlers = extensibleURIConverterImpl.getURIHandlers();
            for (URIHandler uriHandler : uriHandlers) {
                if (uriHandler instanceof PlatformResourceURIHandlerWithProgressMonitorImpl) {
                    platformResourceURIHandlerWithProgressMonitorImpl = (PlatformResourceURIHandlerWithProgressMonitorImpl) uriHandler;
                    break;
                }
            }
        }
        return platformResourceURIHandlerWithProgressMonitorImpl;
    }
}
