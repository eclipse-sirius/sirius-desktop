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
package org.eclipse.sirius.ui.tools.internal.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;
import org.eclipse.sirius.ui.tools.internal.dialogs.SemanticResourceDialog;
import org.eclipse.sirius.ui.tools.internal.operations.SemanticResourceAdditionOperation;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.CreateOrAddResourceWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.GenericInitialObjectPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.GenericModelCreationPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SelectMetamodelWizardPage;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * Wizard to create or add a new resource to the session.
 * 
 * @author mchauvin
 */
public class CreateOrAddResourceWizard extends Wizard implements IAddModelDependencyWizard {

    private static final String WIZARD_TITLE = "Add semantic resource";

    /**
     * The selection.
     */
    protected IStructuredSelection selection;

    /**
     * The wizard page to select between file resource creation and resource
     * addition.
     */
    protected CreateOrAddResourceWizardPage selectionPage;

    /**
     * The wizard page to select a metamodel.
     */
    protected SelectMetamodelWizardPage metamodelPage;

    /**
     * The wizard page to select initial object and encodings.
     */
    protected GenericInitialObjectPage initialObjectPage;

    /**
     * The wizard page to create the model file.
     */
    protected GenericModelCreationPage modelCreationPage;

    private IWorkbench workbench;

    private List<Session> sessions;

    /**
     * Constructor.
     */
    public CreateOrAddResourceWizard() {
    }

    /**
     * Constructor.
     * 
     * @param initialSelection
     *            The initial selection
     */
    public CreateOrAddResourceWizard(final TreeSelection initialSelection) {
        selection = initialSelection;
    }

    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * Set the available sessions.
     * 
     * @param availableSessions
     *            the available sessions
     */
    public void setSessions(final List<Session> availableSessions) {
        this.sessions = availableSessions;
    }

    /**
     * return the current selection.
     * 
     * @return the current selection.
     */
    public IStructuredSelection getSelection() {
        if (selection == null) {
            selection = new StructuredSelection();
        }
        return selection;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(final IWorkbench w, final IStructuredSelection s) {
        this.workbench = w;
        this.selection = s;
        setWindowTitle(WIZARD_TITLE);
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if (!selectionPage.createResource()) {
            return true;
        } else {
            return super.canFinish();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page == selectionPage) {
            if (!selectionPage.createResource()) {
                return null;
            } else {
                createMetamodelPage(page.getControl().getParent());
            }
        } else if (page == metamodelPage) {
            initialObjectPage.setEPackage(metamodelPage.getEPackage());
        } else if (page == initialObjectPage) {
            /* we could not retrieve the file extension */
            // modelCreationPage.setFileExtension(metamodelPage.getEPackage()...);
        }
        return super.getNextPage(page);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        selectionPage = new CreateOrAddResourceWizardPage("selectionPage"); //$NON-NLS-1$
        selectionPage.setTitle(WIZARD_TITLE);
        addPage(selectionPage);
        metamodelPage = new SelectMetamodelWizardPage("metamodelPage", new String[] { "ecore" }, false); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
        metamodelPage.setTitle(WIZARD_TITLE);
        addPage(metamodelPage);
        initialObjectPage = new GenericInitialObjectPage("initialObjectPage"); //$NON-NLS-1$
        initialObjectPage.setTitle(WIZARD_TITLE);
        initialObjectPage.setDescription("Select the root instance type and the file encoding");
        addPage(initialObjectPage);
        modelCreationPage = new GenericModelCreationPage("modelCreationPage", new StructuredSelection()); //$NON-NLS-1$
        modelCreationPage.setTitle(WIZARD_TITLE);
        modelCreationPage.setDescription("Enter the file name and choose the parent container");
        addPage(modelCreationPage);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPageControls(final Composite pageContainer) {
        selectionPage.createControl(pageContainer);
        Assert.isNotNull(selectionPage.getControl());
    }

    private void createMetamodelPage(final Composite pageContainer) {
        if (metamodelPage.getControl() == null) {
            metamodelPage.createControl(pageContainer);
            Assert.isNotNull(metamodelPage.getControl());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        boolean finished = true;
        final Collection<URI> uris = new ArrayList<URI>();

        if (selectionPage.createResource()) {

            /* Remember the file */
            final IFile modelFile = modelCreationPage.getModelFile();

            /* Do the work within an operation */
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
                @Override
                protected void execute(final IProgressMonitor monitor) {
                    try {
                        monitor.beginTask("", 1); //$NON-NLS-1$
                        /* Create a resource set */
                        final ResourceSet resourceSet = new ResourceSetImpl();

                        /* Get the URI of the model file */
                        final URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

                        /* Create a resource for this file */
                        final Resource resource = resourceSet.createResource(fileURI);

                        /*
                         * Create the initial model object and add it to the
                         * contents
                         */
                        final EPackage ePackage = metamodelPage.getEPackage();
                        final EClass eClass = (EClass) ePackage.getEClassifier(initialObjectPage.getInitialObjectName());
                        final EObject rootObject = ePackage.getEFactoryInstance().create(eClass);
                        if (rootObject != null) {
                            resource.getContents().add(rootObject);
                        }

                        /* Save the contents of the resource to the file system. */
                        final Map<Object, Object> options = new HashMap<Object, Object>();
                        options.put(XMLResource.OPTION_ENCODING, initialObjectPage.getEncoding());
                        resource.save(options);
                        uris.add(resource.getURI());
                    } catch (final IOException e) {
                        SiriusPlugin.getDefault().error("the new resource was not created on disk", e);
                    } finally {
                        monitor.done();
                    }
                }
            };

            try {
                getContainer().run(true, false, operation);
            } catch (final InvocationTargetException e) {
                SiriusPlugin.getDefault().error("Error creating resource", e);
            } catch (final InterruptedException e) {
                SiriusPlugin.getDefault().error("Error creating resource", e);
            }
        } else {
            final ResourceDialog resourceDialog = new SemanticResourceDialog(Display.getCurrent().getActiveShell(), "Select resources to add", SWT.OPEN | SWT.MULTI);
            final int result = resourceDialog.open();
            if (result == Window.OK) {
                uris.addAll(resourceDialog.getURIs());
            }
        }
        if (!uris.isEmpty()) {
            SemanticResourceAdditionOperation semanticResourceAdditionOperation = new SemanticResourceAdditionOperation(sessions, uris);
            try {
                getContainer().run(true, false, semanticResourceAdditionOperation);
                Collection<Object> results = semanticResourceAdditionOperation.getResults();
                for (Object result : results) {
                    if (result instanceof Session) {
                        // We open a viewpoint selection dialog
                        ViewpointSelection.openViewpointsSelectionDialog((Session) result);
                    }
                }
            } catch (final InterruptedException e) {
                SiriusPlugin.getDefault().error("Error adding resource", e);
                finished = false;
            } catch (final InvocationTargetException e) {
                SiriusPlugin.getDefault().error("Error adding ressource", e.getTargetException());
                finished = false;
            }
        }
        return finished;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard#canApply(java.util.Collection)
     */
    public boolean canApply(Collection<Session> availableSessions) {
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard#getWizardTitle()
     */
    public String getWizardTitle() {
        return "Add Model Wizard";
    }
}
