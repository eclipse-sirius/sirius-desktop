/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.common.tools.api.util.WorkspaceUtil;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * Wizard page to create a new Session file.
 *
 * @author mchauvin
 */
public class SessionFileCreationWizardPage extends SessionResourceCreationWizardPage {

    /**
     * Constructor.
     *
     * @param pageName
     *            the page name.
     * @param selection
     *            the selection
     * @param fileExtension
     *            the file extension
     */
    public SessionFileCreationWizardPage(final String pageName, final IStructuredSelection selection, final String fileExtension) {
        super(pageName, selection, fileExtension);
        setTitle(Messages.SessionFileCreationWizardPage_title);
        setDescription(Messages.SessionFileCreationWizardPage_description);
    }

    @Override
    protected String getNoselectionFileName() {
        return Messages.SessionFileCreationWizardPage_noSelectionFileName;
    }

    @Override
    protected boolean validatePage() {
        boolean result = true;

        // 1. ModelingProject should have only one main aird
        boolean isValidForModelingProject = noSecondaryAirdCreationForModelingProject(getFilePath());
        if (!isValidForModelingProject) {
            result = false;
            setErrorMessage(Messages.SessionFileCreationWizardPage_errorMessage);
        } else {
            // 2. Normal file validation
            result = super.validatePage();
        }
        return result;
    }

    private boolean noSecondaryAirdCreationForModelingProject(IPath filePath) {
        String[] segments = filePath.segments();
        if (segments != null && segments.length > 1 && SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(getExtension())) {
            String projName = segments[0];
            for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
                if (p.getName().equals(projName) && ModelingProject.hasModelingProjectNature(p)) {
                    List<IFile> airdInProject = WorkspaceUtil.getFilesFromWorkspace(Collections.singleton(p), SiriusUtil.SESSION_RESOURCE_EXTENSION);
                    if (!airdInProject.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
