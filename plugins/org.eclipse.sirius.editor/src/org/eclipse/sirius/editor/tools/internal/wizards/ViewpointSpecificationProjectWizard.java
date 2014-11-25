/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ui.tools.api.project.ViewpointSpecificationProject;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.NewModelingProjectCreationWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * This Class is the wizard that create a viewpoint specification project. This
 * also creates a .odesign file and open it at the end of the wizard.
 * 
 * @author smonnier, pcdavid
 */
public class ViewpointSpecificationProjectWizard extends Wizard implements INewWizard {

    /**
     * Wizard id.
     */
    public static final String ID = "org.eclipse.sirius.ui.specificationproject.wizard"; //$NON-NLS-1$

    /**
     * Default prefix of the new module.
     */
    private static final String MODULE_NAME_PREFIX = "my.project.design"; //$NON-NLS-1$

    private IProject project;

    /**
     * This is a new project wizard page.
     */
    private WizardNewProjectCreationPage newProjectPage;

    /**
     * This is the file creation page.
     */
    private WizardNewODesignFilePage newOdesignPage;

    /**
     * Remember the workbench during initialization.
     */
    private IWorkbench workbench;

    /**
     * Creates the project, all the directories and files and open the .odesign.
     * 
     * @return true if successful
     */
    @Override
    public boolean performFinish() {
        try {
            // if user do not reach page 2, the VSM name is defined according to
            // the project name
            if (!newOdesignPage.isVsmNameChanged) {
                newOdesignPage.modelName.setText(newOdesignPage.extractModelName(newOdesignPage.firstPage.getProjectName()));
            }
            ViewpointSpecificationProject.createNewViewpointSpecificationProject(workbench, newProjectPage.getProjectName(), newProjectPage.getLocationPath(), newOdesignPage.getModelName().getText(),
                    newOdesignPage.getInitialObjectName(), newOdesignPage.getEncoding(), getContainer());
            return true;
        } catch (final CoreException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID, IStatus.OK, e.getMessage(), e);
            SiriusEditorPlugin.getPlugin().getLog().log(status);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(final IWorkbench wkbch, final IStructuredSelection sel) {
        this.workbench = wkbch;
        setWindowTitle("New Viewpoint Specification Project");
        setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(SiriusEditorPlugin.INSTANCE.getImage("full/wizban/banner_viewpoint_specification_project.gif")));
    }

    public IFile getModelFile() {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(project.getFullPath().append("description/" + newOdesignPage.getModelName().getText()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        newProjectPage = new NewModelingProjectCreationWizardPage(SiriusEditorPlugin.getPlugin().getString("_UI_ViewpointSpecificationProjectWizard_label")); //$NON-NLS-1$
        newProjectPage.setInitialProjectName(MODULE_NAME_PREFIX);
        newProjectPage.setTitle(SiriusEditorPlugin.getPlugin().getString("_UI_ViewpointSpecificationProjectWizard_label")); //$NON-NLS-1$
        newProjectPage.setDescription(SiriusEditorPlugin.getPlugin().getString("_UI_ViewpointSpecificationProjectWizard_description")); //$NON-NLS-1$        
        project = ResourcesPlugin.getWorkspace().getRoot().getProject(newProjectPage.getProjectName());
        addPage(newProjectPage);

        newOdesignPage = new WizardNewODesignFilePage("ODesign Model", newProjectPage); //$NON-NLS-1$
        newOdesignPage.setTitle(SiriusEditorPlugin.getPlugin().getString("_UI_SiriusModelWizard_label")); //$NON-NLS-1$
        newOdesignPage.setDescription(SiriusEditorPlugin.getPlugin().getString("_UI_SiriusModelWizard_description")); //$NON-NLS-1$
        addPage(newOdesignPage);

        super.addPages();
    }

    /**
     * A page to configure the new model to create.
     * 
     * @author Obeo
     */
    private static class WizardNewODesignFilePage extends WizardPage {
        private static final String DOT = ".";

        private List<String> encodings;

        private Combo encodingField;

        private ModifyListener validator = new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                setPageComplete(validatePage());
                isVsmNameChanged = true;
            }
        };

        private Text modelName;

        // Check if VSM name has been modified
        private Boolean isVsmNameChanged = false;

        private WizardNewProjectCreationPage firstPage;

        protected WizardNewODesignFilePage(final String pageName, WizardNewProjectCreationPage firstPage) {
            super(pageName);
            this.firstPage = firstPage;
        }

        public Text getModelName() {
            return modelName;
        }

        public String getEncoding() {
            return encodingField.getText();
        }

        public String getInitialObjectName() {
            return ViewpointSpecificationProject.INITIAL_OBJECT_NAME;
        }

        public void createControl(final Composite parent) {
            final Composite composite = new Composite(parent, SWT.NONE);
            final GridLayout layout = new GridLayout();
            layout.numColumns = 2;
            layout.verticalSpacing = 12;
            composite.setLayout(layout);

            GridData data = new GridData();
            data.verticalAlignment = GridData.FILL;
            data.grabExcessVerticalSpace = true;
            data.horizontalAlignment = GridData.FILL;
            composite.setLayoutData(data);

            final Label modelNameLabel = new Label(composite, SWT.LEFT);
            modelNameLabel.setText(SiriusEditorPlugin.getPlugin().getString("_UI_SiriusModelWizardName_label"));

            data = new GridData();
            data.horizontalAlignment = GridData.FILL;
            modelNameLabel.setLayoutData(data);

            modelName = new Text(composite, SWT.LEFT | SWT.BORDER);
            modelName.setText(extractModelName(firstPage.getProjectName()));

            data = new GridData();
            data.horizontalAlignment = GridData.FILL;
            data.grabExcessHorizontalSpace = true;
            modelName.setLayoutData(data);
            modelName.addModifyListener(validator);

            final Label encodingLabel = new Label(composite, SWT.LEFT);
            encodingLabel.setText(SiriusEditorPlugin.getPlugin().getString("_UI_XMLEncoding"));

            data = new GridData();
            data.horizontalAlignment = GridData.FILL;
            encodingLabel.setLayoutData(data);

            encodingField = new Combo(composite, SWT.BORDER);
            data = new GridData();
            data.horizontalAlignment = GridData.FILL;
            data.grabExcessHorizontalSpace = true;
            encodingField.setLayoutData(data);

            for (final String string : getEncodings()) {
                encodingField.add(string);
            }

            encodingField.select(0);
            encodingField.addModifyListener(validator);

            setPageComplete(validatePage());
            setControl(composite);
        }

        protected boolean validatePage() {
            return /* getInitialObjectName() != null && */getEncodings().contains(encodingField.getText())
                    && getModelName().getText().endsWith(DOT + ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION)
                    && (getModelName().getText().length() > (ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION.length() + 1));
        }

        private Collection<String> getEncodings() {
            if (encodings == null) {
                encodings = new ArrayList<String>();
                final StringTokenizer stringTokenizer = new StringTokenizer(SiriusEditorPlugin.getPlugin().getString("_UI_XMLEncodingChoices"));
                while (stringTokenizer.hasMoreTokens()) {
                    encodings.add(stringTokenizer.nextToken());
                }
            }
            return encodings;
        }

        public void setVisible(boolean visible) {
            if (visible) {
                if (!isVsmNameChanged) {
                    this.modelName.setText(extractModelName(firstPage.getProjectName()));
                }
            }
            super.setVisible(visible);
        }

        private String extractModelName(String projectName) {
            String modelPrefixName = "";
            if (projectName != null && projectName.contains(".")) {
                String[] projectNames = projectName.split("[.]");
                if ("design".equals(projectNames[projectNames.length - 1])) {
                    modelPrefixName = projectNames[projectNames.length - 2];
                } else {
                    modelPrefixName = projectNames[projectNames.length - 1];
                }
            } else {
                modelPrefixName = projectName;
            }
            return modelPrefixName + DOT + ViewpointSpecificationProject.VIEWPOINT_MODEL_EXTENSION;
        }
    }
}
