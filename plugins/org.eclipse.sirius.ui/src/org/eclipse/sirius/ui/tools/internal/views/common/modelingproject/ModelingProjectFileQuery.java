/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

import com.google.common.base.Preconditions;

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
        this.file = Preconditions.checkNotNull(file);
    }

    /**
     * Indicates wether the given file contains a DAnalysis model.
     * 
     * @param aFile
     *            the file to test
     * @return true if the given file contains a DAnalysis model, false
     *         otherwise
     */
    protected boolean isRepresentationsModel(IFile aFile) {
        return new FileQuery(aFile).isSessionResourceFile();
    }

    /**
     * Indicates wether the given file contains a VSM model.
     * 
     * @param aFile
     *            the file to test
     * @return true if the given file contains a VSM model, false otherwise
     */
    protected boolean isVsmModel(IFile aFile) {
        return new FileQuery(aFile).isVSMFile();
    }

    /**
     * Check if the file is a potential semantic model. The following types will be ignored : VSM file, representation file,
     * derived file, repair action backup file, derived file, svn file, ...
     * 
     * @return <code>false</code> if the file should be ignored.
     */
    public boolean isPotentialSemanticResource() {
        boolean fileToIgnore = isRepresentationsModel(file) || isVsmModel(file);
        if (!fileToIgnore) {
            fileToIgnore = file.isDerived(IResource.CHECK_ANCESTORS) || file.getFullPath().toString().contains("/.svn/") || isRepairBackupFile(); //$NON-NLS-1$
        }
        return !fileToIgnore;
    }

    /*
     * Sirius's Repair action creates a '.old' backup file, it is working on
     * vsm and representations resources.
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
