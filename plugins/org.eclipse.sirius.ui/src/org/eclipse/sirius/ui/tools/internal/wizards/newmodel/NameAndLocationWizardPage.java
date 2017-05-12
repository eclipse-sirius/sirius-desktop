/**
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.tools.internal.wizards.newmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * A wizard page allowing to select a name and a location for the model to create.
 * 
 * @see CreateEMFModelWizard
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class NameAndLocationWizardPage extends WizardNewFileCreationPage implements PropertyChangeListener {

    /** The default extension for all emf models. */
    public static final String XMI_DEFAULT_EXTENSION = "xmi"; //$NON-NLS-1$

    /** The data model used by the wizard and its pages. */
    private CreateEMFModelWizardDataModel dataModel;

    /**
     * Create the wizard page.
     * 
     * @param selection
     *            the selection (cannot be null).
     * @param dataModel
     *            the data model used by this page.
     */
    public NameAndLocationWizardPage(IStructuredSelection selection, CreateEMFModelWizardDataModel dataModel) {
        super("NameAndLocationWizardPage", selection); //$NON-NLS-1$
        this.dataModel = dataModel;
        setTitle(Messages.NameAndLocationWizardPage_title);
        setDescription(Messages.NameAndLocationWizardPage_description);
    }

    /**
     * Get the {@link IFile} corresponding to the model to create.
     * 
     * @return the {@link IFile} corresponding to the model to create.
     */
    public IFile getModelFile() {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (CreateEMFModelWizardDataModel.SELECTED_PACKAGE_EVENT.equals(evt.getPropertyName())) {
            setFileName(genDefaultFullFileName());
        }
    }

    @Override
    protected boolean validatePage() {
        boolean isValid = false;
        if (super.validatePage()) {
            String extension = new Path(getFileName()).getFileExtension();
            // The file extension is generated, so may be it is not the appropriate file extension.
            // So we only set a message error if the file has no extension.
            if (extension == null) {
                setErrorMessage(Messages.NameAndLocationWizardPage_errorMessage);
                isValid = false;
            } else {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Generates the default file name + extension for the selected {@link EPackage}.
     * 
     * @return the default file name + extension for the selected {@link EPackage}.
     */
    private String genDefaultFullFileName() {
        return genDefaultFileName() + "." + genDefaultExtensionName(); //$NON-NLS-1$
    }

    /**
     * Generates the default file name for the selected {@link EPackage}.
     * 
     * @return the default file name for the selected {@link EPackage}.
     */
    private String genDefaultFileName() {
        String defaultFileName = Messages.CreateEMFModelWizard_modelNamePrefix;
        EPackage ePackage = this.dataModel.getSelectedPackage();
        if (ePackage != null) {
            String ePackageName = ePackage.getName();
            defaultFileName += Character.toUpperCase(ePackageName.charAt(0)) + ePackageName.substring(1);
        }
        return defaultFileName;
    }

    /**
     * Generates the default file extension for the selected {@link EPackage}, or {@value #XMI_DEFAULT_EXTENSION} if
     * default extension can't be computed.
     * 
     * @return the default file extension for the selected {@link EPackage}, or {@value #XMI_DEFAULT_EXTENSION} if
     *         default extension can't be computed.
     */
    private String genDefaultExtensionName() {
        String defaultExtensionName = null;
        EPackage ePackage = this.dataModel.getSelectedPackage();
        if (ePackage != null) {
            defaultExtensionName = ePackage.getName();
        } else {
            defaultExtensionName = XMI_DEFAULT_EXTENSION;
        }
        return defaultExtensionName;
    }
}
