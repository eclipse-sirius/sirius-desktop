/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * Useful operations for the type {@link IResource}.
 * 
 * @author ymortier
 */
public final class ResourceUtil {

    private static final String FILE_SEPARATOR = "/"; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private ResourceUtil() {
        // empty
    }

    /**
     * Creates a backup file.
     * 
     * @param file
     *            the source file.
     * @param monitor
     *            a progress monitor.
     * @return The backup file.
     * @throws CoreException
     *             in case of while saving file error.
     */
    public static IFile createBackupFile(final IFile file, final IProgressMonitor monitor) throws CoreException {
        monitor.beginTask("Backup and refresh workspace", 2);
        // computes a timestamp.
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()); //$NON-NLS-1$

        // Computes a candidate name.
        final int lastDotIndex = file.getName().lastIndexOf('.');
        final String name;
        if (lastDotIndex > 0) {
            name = file.getName().substring(0, lastDotIndex) + "-" + timestamp + "." + file.getName().substring(lastDotIndex + ".".length()) + ".old"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        } else {
            name = file.getName() + "-" + timestamp + ".old"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // find the backup file.
        IFile backup;
        if (file.getParent().findMember(name, true) == null) {
            backup = file.getParent().getFile(new Path(FILE_SEPARATOR + name));
        } else {
            int i = 2;
            while (file.getParent().findMember(name + i, true) != null) {
                i++;
            }
            backup = file.getParent().getFile(new Path(FILE_SEPARATOR + name + i));
        }
        assert !backup.exists() : "the file already exists";

        // creates the backup
        file.copy(backup.getFullPath(), IResource.FORCE, new SubProgressMonitor(monitor, 1));
        // refresh local container.
        file.getParent().refreshLocal(IResource.DEPTH_ONE, new SubProgressMonitor(monitor, 1));
        monitor.done();
        return backup;
    }

}
