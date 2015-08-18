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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * Wizard to create a new Session resource file.
 * 
 * @author mchauvin
 */
public class SessionResourceCreationWizardPage extends WizardNewFileCreationPage {

    private final String fileExtension;

    private IFile selectedFile;

    /**
     * Default Constructor to the wizard for session resource creation.
     * 
     * @param pageName
     *            the page name.
     * @param selection
     *            the selection
     * @param fileExtension
     *            the file extension
     */
    public SessionResourceCreationWizardPage(final String pageName, final IStructuredSelection selection, final String fileExtension) {
        super(pageName, selection);
        this.fileExtension = fileExtension;
        if (selection.getFirstElement() instanceof IFile) {
            selectedFile = (IFile) selection.getFirstElement();
        }
    }

    /**
     * Get the file extension. Override to create files with this extension.
     * 
     * @return the file extension
     */
    protected String getExtension() {
        return fileExtension;
    }

    /**
     * Get the URI.
     * 
     * @return the URI
     */
    public URI getURI() {
        return URI.createPlatformResourceURI(getFilePath().toString(), true);
    }

    /**
     * Get the file path.
     * 
     * @return the file path
     */
    protected IPath getFilePath() {
        IPath path = getContainerFullPath();
        if (path == null) {
            path = new Path(""); //$NON-NLS-1$
        }
        final String fileName = getFileName();
        if (fileName != null) {
            path = path.append(fileName);
        }
        return path;
    }

    /**
     * Get the default file name.
     * 
     * @return the default file name
     */
    public String getDefaultFileName() {
        if (selectedFile != null && selectedFile.getFullPath().removeFileExtension().lastSegment() != null) {
            final String name = selectedFile.getFullPath().removeFileExtension().lastSegment();
            return name;
        }
        return getNoselectionFileName();
    }

    /**
     * Get the default file name to use when there is no selected file.
     * 
     * @return the default file name to use when there is no selected file.
     */
    protected String getNoselectionFileName() {
        return "newDiagrams";
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
        setFileName(getDefaultFileName() + "." + getExtension()); //$NON-NLS-1$
        setPageComplete(validatePage());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validatePage()
     */
    @Override
    protected boolean validatePage() {
        boolean result = true;
        if (!super.validatePage()) {
            return false;
        }
        // Validate that entered extension is the expected one.
        final String extension = getExtension();
        if (extension != null && !getFilePath().toString().endsWith("." + extension)) { //$NON-NLS-1$
            setErrorMessage("The file extension is wrong. It should be " + extension);
            result = false;
        }
        return result;
    }
}
