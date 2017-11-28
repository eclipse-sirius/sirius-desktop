/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.resource;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Implements file modification validator. The default process of Eclipse is
 * overriden for specific needs to the ClearCase SCM. With Clearcase, there
 * isn't interaction with the user (no popup) if we use
 * IWorkspace#VALIDATE_PROMPT.
 * 
 * @author smonnier
 * 
 */
public class FileModificationValidator implements IFileModificationValidator {

    /**
     * Empty constructor of the implementation of validation.
     */
    public FileModificationValidator() {
    }

    /**
     * Validates a collection of file.
     * 
     * @param files2Validate
     *            a collection of file
     * @return the status.
     */
    public IStatus validateEdit(final Collection<IFile> files2Validate) {
        final Display display = PlatformUI.getWorkbench().getDisplay();
        final IStatus[] statusArray = { null };
        final Runnable validateEditRunnable = new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
                statusArray[0] = ResourcesPlugin.getWorkspace().validateEdit(files2Validate.toArray(new IFile[files2Validate.size()]), display.getActiveShell());
            }
        };
        if (null == Display.getCurrent()) {
            // Waiting for the end-user answer
            display.syncExec(validateEditRunnable);
        } else {
            // Already in the UI thread.
            validateEditRunnable.run();
        }
        return statusArray[0];
    }

}
