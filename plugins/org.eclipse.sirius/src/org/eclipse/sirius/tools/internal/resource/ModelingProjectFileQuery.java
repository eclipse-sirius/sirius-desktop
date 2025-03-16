/*******************************************************************************
 * Copyright (c) 2012, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.resource;

import java.util.Objects;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * Queries on file in the context of a modeling project.
 * 
 * @author mporhel
 */
public class ModelingProjectFileQuery {
    /**
     * The file to query.
     */
    private final IFile file;

    /**
     * Constructor.
     * 
     * @param file
     *            the resource to query.
     */
    public ModelingProjectFileQuery(IFile file) {
        this.file = Objects.requireNonNull(file);
    }

    /**
     * Indicates whether the given file contains a DAnalysis model.
     * 
     * @param aFile
     *            the file to test
     * @return true if the given file contains a DAnalysis model, false otherwise
     */
    protected boolean isRepresentationsModel(IFile aFile) {
        return new FileQuery(aFile).isSessionResourceFile();
    }

    /**
     * Indicates whether the given file contains a VSM model.
     * 
     * @param aFile
     *            the file to test
     * @return true if the given file contains a VSM model, false otherwise
     */
    protected boolean isVsmModel(IFile aFile) {
        return new FileQuery(aFile).isVSMFile();
    }

    /**
     * Check if the file is a potential semantic model. The following types will be ignored : VSM file, representation
     * file, derived file, repair action backup file, derived file, svn file, ...
     * 
     * @return <code>false</code> if the file should be ignored.
     */
    public boolean isPotentialSemanticResource() {
        boolean fileToIgnore = isRepresentationsModel(file) || isVsmModel(file) || isRepFile(file);
        if (!fileToIgnore) {
            fileToIgnore = file.isDerived(IResource.CHECK_ANCESTORS) || file.getFullPath().toString().contains("/.svn/") || isRepairBackupFile(); //$NON-NLS-1$
        }
        return !fileToIgnore;
    }

    private boolean isRepFile(IFile aFile) {
        return new FileQuery(aFile).isSrmFile();
    }

    /*
     * Sirius's Repair action creates a '.old' backup file, it is working on vsm and representations resources.
     */
    private boolean isRepairBackupFile() {
        if ("old".equals(file.getFileExtension())) { //$NON-NLS-1$
            IPath repairedPath = file.getFullPath().removeFileExtension();
            // If this is a repaired file, removing .old will make the old
            // extension to become the file extension.
            if (!StringUtil.isEmpty(repairedPath.getFileExtension())) {
                IFile repairedFile = file.getWorkspace().getRoot().getFile(repairedPath);
                return isVsmModel(repairedFile) || isRepresentationsModel(repairedFile);
            }
        }
        return false;
    }

}
