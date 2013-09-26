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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;

/**
 * A specifc {@link FileURIHandlerImpl} for file reading/writing with progress
 * monitor.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FileURIHandlerWithProgressMonitorImpl extends FileURIHandlerImpl {

    private IProgressMonitor monitor = new NullProgressMonitor();

    /**
     * Default constructor.
     */
    public FileURIHandlerWithProgressMonitorImpl() {
        super();
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        InputStream inputStream = super.createInputStream(uri, options);
        File file = new File(uri.toFileString());
        inputStream = new ProgressMonitorInputStream(inputStream, file.length(), 1, monitor);
        return inputStream;
    }

    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        OutputStream outputStream = super.createOutputStream(uri, options);
        File file = new File(uri.toFileString());
        outputStream = new ProgressMonitorOutputStream(outputStream, file.length(), 1, monitor);
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
