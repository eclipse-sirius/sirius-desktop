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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;

/**
 * A specifc {@link PlatformResourceURIHandlerImpl} for platform resource
 * reading/writing with progress monitor.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class PlatformResourceURIHandlerWithProgressMonitorImpl extends PlatformResourceURIHandlerImpl {

    private IProgressMonitor monitor;

    /**
     * Default constructor.
     */
    public PlatformResourceURIHandlerWithProgressMonitorImpl() {
        super();
    }

    @SuppressWarnings("resource")
    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        InputStream inputStream = null;
        String filePath = null;
        if (uri.isFile()) {
            filePath = uri.toFileString();
        } else if (uri.isPlatformResource()) {
            URI fileURI = CommonPlugin.asLocalURI(uri);
            if (fileURI.isFile()) {
                filePath = fileURI.toFileString();
            }
        }
        if (filePath != null) {
            File file = new File(filePath);
            inputStream = new ProgressMonitorInputStream(new FileInputStream(file), file.length(), 1, monitor);
            Map<Object, Object> response = getResponse(options);
            if (response != null) {
                response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, file.lastModified());
            }
        } else {
            if (uri.isFile()) {
                inputStream = super.createInputStream(uri, options);
            } else if (uri.isPlatformResource()) {
                inputStream = new PlatformResourceURIHandlerImpl().createInputStream(uri, options);
            }
        }
        return inputStream;
    }

    @SuppressWarnings("resource")
    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        OutputStream outputStream = null;
        String filePath = null;
        if (uri.isFile()) {
            filePath = uri.toFileString();
        } else if (uri.isPlatformResource()) {
            URI fileURI = CommonPlugin.asLocalURI(uri);
            if (fileURI.isFile()) {
                filePath = fileURI.toFileString();
            }
        }
        if (filePath != null) {
            File file = new File(filePath);
            outputStream = new ProgressMonitorOutputStream(new FileOutputStream(file), file.length(), 1, monitor);
            Map<Object, Object> response = getResponse(options);
            if (response != null) {
                response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, file.lastModified());
            }
        } else {
            if (uri.isFile()) {
                outputStream = super.createOutputStream(uri, options);
            } else if (uri.isPlatformResource()) {
                outputStream = new PlatformResourceURIHandlerImpl().createOutputStream(uri, options);
            }
        }
        return outputStream;
    }

    /**
     * Set the {@link IProgressMonitor} to use to show progression of file
     * resource reading.
     * 
     * @param progressMonitor
     *            a {@link IProgressMonitor} to show progression of reading of
     *            file resource
     */
    public void setProgressMonitor(IProgressMonitor progressMonitor) {
        this.monitor = progressMonitor;
    }

}
