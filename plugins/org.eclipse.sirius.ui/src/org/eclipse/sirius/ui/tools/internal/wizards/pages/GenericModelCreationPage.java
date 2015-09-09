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

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * A wizard page to create any model file in the workspace.
 *
 * @author mchauvin
 */
public class GenericModelCreationPage extends WizardNewFileCreationPage {

    private String fileExtension;

    /**
     * Construct a new wizard page instance.
     *
     * @param pageName
     *            the page name
     * @param selection
     *            the selection
     */
    public GenericModelCreationPage(final String pageName, final IStructuredSelection selection) {
        super(pageName, selection);
    }

    /**
     * Get the created model file.
     *
     * @return the created model file
     */
    public IFile getModelFile() {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
    }

    /**
     * Set the model file extension.
     *
     * @param modelFileExtension
     *            the model file extension.
     */
    public void setModelFileExtension(final String modelFileExtension) {
        this.fileExtension = modelFileExtension;
    }

    @Override
    protected boolean validatePage() {
        if (super.validatePage()) {
            final String enteredExt = new Path(getFileName()).getFileExtension();
            if (fileExtension == null || (enteredExt != null && enteredExt.equals(fileExtension))) {
                return true;
            } else {
                setErrorMessage(MessageFormat.format(Messages.GenericModelCreationPage_fileExtensionError, fileExtension));
            }
        }
        return false;
    }

}
