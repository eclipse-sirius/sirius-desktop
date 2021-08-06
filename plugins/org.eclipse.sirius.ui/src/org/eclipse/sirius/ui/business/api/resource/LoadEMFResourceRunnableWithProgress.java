/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.business.api.resource.support.LoadEMFResource;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * Runnable to load an EMF resource.
 *
 * @author mchauvin
 * @since 0.9.0
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

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.LoadEMFResourceRunnableWithProgress_loadResourceTask, 1);
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
