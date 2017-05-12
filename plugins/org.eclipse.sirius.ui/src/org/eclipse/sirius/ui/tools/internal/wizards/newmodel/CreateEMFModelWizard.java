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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * A wizard allowing to create a new EMF model. The model will be an instance of a metamodel available through the given
 * {@link Registry}.
 * 
 * @see #CreateEMFModelWizard(Registry, IStructuredSelection)
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class CreateEMFModelWizard extends Wizard {

    /** The page to select the metamodel. */
    private SelectEMFMetamodelWizardPage selectEMFMetamodelWizardPage;

    /** The page to select the root element. */
    private SelectRootElementWizardPage selectRootElementWizardPage;

    /** The page to select the name and the location of the model. */
    private NameAndLocationWizardPage nameAndLocationWizardPage;

    /** The data model used by the wizard and its pages. */
    private CreateEMFModelWizardDataModel dataModel;

    private IFile result;

    /**
     * Default constructor for this wizard.
     * 
     * @param packageRegistry
     *            an {@link Registry}, allowing to select a metamodel.
     * @param selection
     *            an {@link IStructuredSelection}, allowing to initialize the location of the model to create.
     */
    public CreateEMFModelWizard(Registry packageRegistry, IStructuredSelection selection) {
        this.dataModel = new CreateEMFModelWizardDataModel();
        this.selectEMFMetamodelWizardPage = new SelectEMFMetamodelWizardPage(packageRegistry, this.dataModel);
        this.selectRootElementWizardPage = new SelectRootElementWizardPage(this.dataModel);
        this.nameAndLocationWizardPage = new NameAndLocationWizardPage(selection, this.dataModel);
        setWindowTitle(Messages.CreateEMFModelWizard_windowTitle);
        this.dataModel.addPropertyChangeListener(this.selectRootElementWizardPage);
        this.dataModel.addPropertyChangeListener(this.nameAndLocationWizardPage);
    }

    @Override
    public void addPages() {
        addPage(this.selectEMFMetamodelWizardPage);
        addPage(this.selectRootElementWizardPage);
        addPage(this.nameAndLocationWizardPage);
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        IWizardPage nextPage = null;
        if (page == this.selectEMFMetamodelWizardPage) {
            nextPage = this.selectRootElementWizardPage;
        } else if (page == this.selectRootElementWizardPage) {
            nextPage = this.nameAndLocationWizardPage;
        } else if (page == this.nameAndLocationWizardPage) {
            nextPage = null;
        } else {
            nextPage = super.getNextPage(page);
        }
        return nextPage;
    }

    @Override
    public boolean canFinish() {
        return this.dataModel.getSelectedPackage() != null && this.dataModel.getSelectedRootElement() != null && this.nameAndLocationWizardPage.validatePage();
    }

    @Override
    public boolean performFinish() {
        final IFile modelFile = this.nameAndLocationWizardPage.getModelFile();

        // this code is inspired from the generation of the wizard class of an
        // EMF metamodel from an EMF genmodel
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            protected void execute(IProgressMonitor progressMonitor) {
                try {
                    ResourceSet resourceSet = new ResourceSetImpl();
                    URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
                    Resource resource = resourceSet.createResource(fileURI);
                    EObject rootObject = instantiateRootElement();
                    if (rootObject != null) {
                        resource.getContents().add(rootObject);
                    }
                    resource.save(Collections.EMPTY_MAP);
                } catch (IllegalArgumentException | IOException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getMessage()));
                } finally {
                    progressMonitor.done();
                }
            }
        };

        try {
            getContainer().run(false, false, operation);
            this.result = modelFile;
        } catch (InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getMessage()));
        } catch (InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getTargetException().getMessage()));
        }

        selectNewlyCreatedFile(modelFile);

        return true;
    }

    /**
     * Returns the newly created model file if/when the wizard finished successfully.
     * 
     * @return the newly created model file.
     */
    public IFile getResult() {
        return result;
    }

    private void selectNewlyCreatedFile(final IFile modelFile) {
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (workbenchWindow != null) {
            IWorkbenchPage page = workbenchWindow.getActivePage();
            if (page != null) {
                final IWorkbenchPart activePart = page.getActivePart();
                if (activePart instanceof ISetSelectionTarget) {
                    final ISelection targetSelection = new StructuredSelection(modelFile);
                    getShell().getDisplay().asyncExec(new Runnable() {
                        public void run() {
                            ((ISetSelectionTarget) activePart).selectReveal(targetSelection);
                        }
                    });
                }
            }
        }
    }

    /**
     * Instantiates a root element based on the selection in the {@link SelectRootElementWizardPage}.
     * 
     * @return a root element based on the selection in the {@link SelectRootElementWizardPage}.
     */
    private EObject instantiateRootElement() {
        EClass rootElement = this.dataModel.getSelectedRootElement();
        if (rootElement != null) {
            return EcoreUtil.create(rootElement);
        }
        return null;
    }
}
