/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.NewModelingProjectCreationWizardPage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * This Class is the wizard that creates a viewpoint modeling project. This will
 * creates a project with the viewpoint modeling nature and add a aird file
 * automatically named.
 * 
 * @author mchauvin
 */
public class ModelingProjectWizard extends Wizard implements INewWizard {

    /**
     * Wizard id.
     */
    public static final String ID = "org.eclipse.sirius.ui.modelingproject.wizard"; //$NON-NLS-1$

    /**
     * This is a new project wizard page.
     */
    private WizardNewProjectCreationPage newProjectPage;

    /**
     * Default constructor.
     */
    public ModelingProjectWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * Creates the project, all the directories and files and open the .odesign.
     * 
     * @return true if successful
     */
    @Override
    public boolean performFinish() {
        boolean finished = true;
        try {
            final String projectName = newProjectPage.getProjectName();
            final IPath locationPath = newProjectPage.getLocationPath();
            getContainer().run(true, false, new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    try {
                        ModelingProjectManager.INSTANCE.createNewModelingProject(projectName, locationPath, true, monitor);
                    } catch (final CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                }
            });
        } catch (InvocationTargetException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.ERROR, e.getMessage(), e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
            finished = false;
        } catch (InterruptedException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.ERROR, e.getMessage(), e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
            finished = false;
        }
        return finished;

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(final IWorkbench wkbch, final IStructuredSelection sel) {
        setWindowTitle("New Modeling Project");
        setDefaultPageImageDescriptor(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/wizban/banner_modeling_project.gif")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPageControls(final Composite pageContainer) {
        super.createPageControls(pageContainer);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        newProjectPage = new NewModelingProjectCreationWizardPage(SiriusEditPlugin.getPlugin().getString("_UI_ModelingProjectWizard_label")); //$NON-NLS-1$
        newProjectPage.setInitialProjectName(""); //$NON-NLS-1$
        newProjectPage.setTitle(SiriusEditPlugin.getPlugin().getString("_UI_ModelingProjectWizard_label")); //$NON-NLS-1$
        newProjectPage.setDescription(SiriusEditPlugin.getPlugin().getString("_UI_ModelingProjectWizard_description")); //$NON-NLS-1$        
        addPage(newProjectPage);
        super.addPages();
    }
}
