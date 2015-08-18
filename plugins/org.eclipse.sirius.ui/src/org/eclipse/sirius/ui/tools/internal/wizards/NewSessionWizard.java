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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.common.ui.tools.api.wizard.SelectFilesWizardPage;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SessionFileCreationWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SessionKindSelectionWizardPage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

/**
 * Wizard to create a new Session.
 * 
 * Can be used in project without the modeling nature only.
 * 
 * @author mchauvin
 */
public class NewSessionWizard extends Wizard implements INewWizard {

    /**
     * Wizard id.
     */
    public static final String ID = "org.eclipse.sirius.ui.session.creation"; //$NON-NLS-1$

    private static final String SESSION_CREATION_ERROR_MSG = "Error creating Representations File";

    /**
     * The selection.
     */
    protected IStructuredSelection selection;

    /**
     * Remember the workbench during initialization.
     */
    protected IWorkbench workbench;

    /**
     * The wizard page to select the session kind.
     */
    protected SessionKindSelectionWizardPage sessionKindWizardPage;

    /**
     * The wizard page to create a new session file.
     */
    protected SessionFileCreationWizardPage sessionFileCreationPage;

    /**
     * The wizard page to select the model file.
     */
    protected SelectFilesWizardPage modelSelectionPage;

    /**
     * Constructor.
     */
    public NewSessionWizard() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param initialSelection
     *            The initial selection
     */
    public NewSessionWizard(final TreeSelection initialSelection) {
        selection = initialSelection;
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
        this.selection = s;
        this.workbench = w;
        setWindowTitle("New Representations File");
        setNeedsProgressMonitor(true);
        setDefaultPageImageDescriptor(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/wizban/banner_aird.gif")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if (sessionKindWizardPage.emptySession()) {
            return sessionKindWizardPage.isPageComplete() && sessionFileCreationPage.isPageComplete();
        } else {
            return super.canFinish();
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        final boolean singleSemanticResourceSelected = selection.size() == 1 && selection.getFirstElement() instanceof IFile;
        final boolean defaultToEmptyModel = !singleSemanticResourceSelected;
        sessionKindWizardPage = new SessionKindSelectionWizardPage("SessionKind", defaultToEmptyModel);
        addPage(sessionKindWizardPage);
        modelSelectionPage = new SelectFilesWizardPage("Select model to analyse", 1, 1, new String[] { StringUtil.JOKER_STRING });
        if (selection != null) {
            modelSelectionPage.setInitialSelection(selection);
        }
        addPage(modelSelectionPage);
        sessionFileCreationPage = new SessionFileCreationWizardPage("DiagramModelFile", getSelection(), SiriusUtil.SESSION_RESOURCE_EXTENSION); //$NON-NLS-1$
        addPage(sessionFileCreationPage);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        boolean finished = true;
        URI sessionModelURI = sessionFileCreationPage.getURI();
        final Collection<URI> semanticResourceURIs = getSemanticResourceURIs();
        SessionCreationOperation sessionCreationOperation = new SessionCreationOperation(sessionModelURI, semanticResourceURIs);
        try {
            getContainer().run(true, false, sessionCreationOperation);
            final Session session = sessionCreationOperation.getSession();
            getContainer().run(false, false, new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    if (session != null && !semanticResourceURIs.isEmpty()) {
                        ViewpointSelection.openViewpointsSelectionDialog(session);
                        SessionHelper.openStartupRepresentations(session, monitor);
                    }
                    IFile newReprepresentationsFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sessionFileCreationPage.getURI().toPlatformString(true)));
                    if (newReprepresentationsFile != null && newReprepresentationsFile.exists()) {
                        // Selects and reveals the newly added resource in all
                        // parts of
                        // the active workbench window's active page.
                        BasicNewResourceWizard.selectAndReveal(newReprepresentationsFile, workbench.getActiveWorkbenchWindow());
                        EclipseUIUtil.expand(newReprepresentationsFile, workbench.getActiveWorkbenchWindow());
                    }
                }
            });

        } catch (final InterruptedException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, SESSION_CREATION_ERROR_MSG, e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
            finished = false;
        } catch (final InvocationTargetException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, SESSION_CREATION_ERROR_MSG, e.getTargetException());
            SiriusEditPlugin.getPlugin().getLog().log(status);
            finished = false;
        }
        return finished;
    }

    private Collection<URI> getSemanticResourceURIs() {
        Collection<URI> semanticResources = new ArrayList<URI>();
        if (!sessionKindWizardPage.emptySession()) {
            final IPath[] selectedSemanticFilePaths = modelSelectionPage.getSelection();
            if (selectedSemanticFilePaths.length != 0) {
                final Collection<IFile> files = getSemanticFiles(selectedSemanticFilePaths);
                if (!files.isEmpty()) {
                    for (IFile semanticModelFile : files) {
                        semanticResources.add(URI.createPlatformResourceURI(semanticModelFile.getFullPath().toOSString(), true));
                    }
                }
            }
        }
        return semanticResources;
    }

    private Collection<IFile> getSemanticFiles(final IPath[] selectedSemanticFilePaths) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final Collection<IFile> files = new HashSet<IFile>();

        for (final IPath path : selectedSemanticFilePaths) {
            final IFile file = workspace.getRoot().getFile(path);
            files.add(file);
        }
        return files;
    }

    /**
     * A local class to create a session.
     * 
     * @author mchauvin
     */
    private static final class SessionCreationOperation extends WorkspaceModifyOperation {

        private URI sessionModelURI;

        private Collection<URI> semanticResourceURIs;

        private Session session;

        public SessionCreationOperation(URI sessionModelURI, Collection<URI> semanticResourceURIs) {
            super();
            this.sessionModelURI = sessionModelURI;
            this.semanticResourceURIs = semanticResourceURIs;
        }

        public Session getSession() {
            return session;
        }

        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
            try {
                monitor.beginTask("Representations file creation", 1);

                // Create a Session from the session model URI
                org.eclipse.sirius.business.api.session.SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionModelURI, new SubProgressMonitor(
                        monitor, 1));
                sessionCreationOperation.execute();
                session = sessionCreationOperation.getCreatedSession();

                for (URI semanticResourceURI : semanticResourceURIs) {
                    Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new SubProgressMonitor(monitor, 1));
                    session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
                }
            } finally {
                monitor.done();
            }
        }

    }
}
