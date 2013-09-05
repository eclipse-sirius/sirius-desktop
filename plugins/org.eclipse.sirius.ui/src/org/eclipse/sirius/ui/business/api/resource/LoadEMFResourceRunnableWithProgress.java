/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.resource;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.eclipse.sirius.business.api.resource.LoadEMFResource;

/**
 * Runnable to load an EMF resource.
 * 
 * @author mchauvin
 * @since 2.6
 */
public class LoadEMFResourceRunnableWithProgress implements IRunnableWithProgress {

    private LoadEMFResource wrappedRunnable;

    /**
     * Constructor.
     * 
     * @param set
     *            the resource set in which to load the file
     * @param file
     *            the file containing the resource to load.
     */
    public LoadEMFResourceRunnableWithProgress(final ResourceSet set, final IFile file) {
        wrappedRunnable = new LoadEMFResource(set, file);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask("Loading resource", 1);
        wrappedRunnable.run();
        monitor.done();
    }

    /**
     * Returns the loaded resource.
     * 
     * @return the loaded resource, or <code>null</code> if it could be loaded
     */
    public Resource getLoadedResource() {
        return wrappedRunnable.getLoadedResource();
    }

}
