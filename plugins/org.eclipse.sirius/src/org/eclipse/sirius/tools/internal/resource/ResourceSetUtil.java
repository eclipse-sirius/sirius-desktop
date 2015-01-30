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
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

/**
 * A Helper to override the
 * {@link org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl} and the
 * {@link org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl} of
 * a {@link ResourceSet} to have progress monitor on resource loading.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class ResourceSetUtil {

    private ResourceSetUtil() {
        // Not instantiable
    }

    /**
     * Create two new {@link URIHandlerImpl} to override the
     * {@link org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl} and the
     * {@link org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl}
     * to have progress monitor on resource loading. If these two
     * {@link URIHandlerImpl} already exists, we only set the new monitor.
     * 
     * Warning : If you use this method, you must call
     * {@link #resetProgressMonitor(ResourceSet)} when the monitor is done.
     * 
     * @param resourceSet
     *            the {@link ResourceSet} to update
     * @param monitor
     *            the {@link IProgressMonitor} to set
     */
    public static void setProgressMonitor(ResourceSet resourceSet, IProgressMonitor monitor) {
        // Reuse the existing handlers
        List<URIHandler> handlers = new ArrayList<URIHandler>(resourceSet.getURIConverter().getURIHandlers());
        FileURIHandlerWithProgressMonitorImpl fileURIHandlerWithProgressMonitorImpl = getFileURIHandlerWithProgressMonitorImpl(resourceSet);
        if (fileURIHandlerWithProgressMonitorImpl == null) {
            // Create a new FileURIHandlerWithProgressMonitorImpl and add it at
            // the first location.
            fileURIHandlerWithProgressMonitorImpl = new FileURIHandlerWithProgressMonitorImpl();
            handlers.add(0, fileURIHandlerWithProgressMonitorImpl);
        }
        PlatformResourceURIHandlerWithProgressMonitorImpl platformResourceURIHandlerWithProgressMonitorImpl = getPlatformResourceURIHandlerWithProgressMonitorImpl(resourceSet);
        if (platformResourceURIHandlerWithProgressMonitorImpl == null) {
            // Create a new PlatformResourceURIHandlerWithProgressMonitorImpl
            // and add it at the first location.
            platformResourceURIHandlerWithProgressMonitorImpl = new PlatformResourceURIHandlerWithProgressMonitorImpl();
            handlers.add(0, platformResourceURIHandlerWithProgressMonitorImpl);
        }
        // Set the new progress monitor
        platformResourceURIHandlerWithProgressMonitorImpl.setProgressMonitor(monitor);
        // Set a new ExtensibleURIConverterImpl with the 2 handlers (with new
        // progress monitor)
        Map<URI, URI> existingURIMap = resourceSet.getURIConverter().getURIMap();
        resourceSet.setURIConverter(new ExtensibleURIConverterImpl(handlers, ContentHandler.Registry.INSTANCE.contentHandlers()));
        resourceSet.getURIConverter().getURIMap().putAll(existingURIMap);
    }

    /**
     * Remove the specific handlers with progress monitor. This method must be
     * called after using
     * {@link #setProgressMonitor(ResourceSet, IProgressMonitor)} when the
     * monitor is done.
     * 
     * @param resourceSet
     *            The {@link ResourceSet} to clean.
     */
    public static void resetProgressMonitor(ResourceSet resourceSet) {
        resourceSet.getURIConverter().getURIHandlers().remove(getFileURIHandlerWithProgressMonitorImpl(resourceSet));
        resourceSet.getURIConverter().getURIHandlers().remove(getPlatformResourceURIHandlerWithProgressMonitorImpl(resourceSet));

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
