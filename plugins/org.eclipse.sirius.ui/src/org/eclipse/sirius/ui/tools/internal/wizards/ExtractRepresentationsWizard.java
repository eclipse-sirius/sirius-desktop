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
package org.eclipse.sirius.ui.tools.internal.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.internal.command.PrepareNewAnalysisCommand;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SessionResourceCreationWizardPage;

/**
 * This wizard ask the user for which viewpoints he wants to externalize and
 * create the new .aird files.
 * 
 * @author cbrun
 * 
 */
public class ExtractRepresentationsWizard extends Wizard {

    private final TransactionalEditingDomain domain;

    private Session session;

    private Collection<DRepresentation> representations;

    private SessionResourceCreationWizardPage sessionResourceFilePage;

    private Resource pickedResource;

    /**
     * Constructor.
     * 
     * @param session
     *            origin session.
     * @param domain
     *            the editing domain
     * @param movableRepresentations
     *            the DView instance
     */
    public ExtractRepresentationsWizard(final Session session, final TransactionalEditingDomain domain, final Collection<DRepresentation> movableRepresentations) {
        this.domain = domain;
        this.session = session;
        this.representations = movableRepresentations;
    }

    /**
     * Initialize the wizard.
     * 
     * @param workbench
     *            the workbench
     * @param selection
     *            the selection
     */
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle("Extract views");
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        closeRepresentations(representations);

        final IRunnableWithProgress op = new DiagramFileCreationOperation();
        boolean errorCatch = false;
        errorCatch = createAIRDFile(op, errorCatch);
        if (errorCatch) {
            return false;
        }

        final DAnalysis slaveAnalysis = prepareNewAnalysis();

        moveRepresentations(slaveAnalysis);

        return true;
    }

    /**
     * @param slaveAnalysis
     */
    private void moveRepresentations(final DAnalysis slaveAnalysis) {
        final IRunnableWithProgress moveReps = new IRunnableWithProgress() {
            public void run(final IProgressMonitor mon) {
                domain.getCommandStack().execute(new MoveRepresentationCommand(session, slaveAnalysis, representations));
            }
        };
        try {
            getContainer().run(false, true, moveReps);
        } catch (final InterruptedException e) {
            SiriusPlugin.getDefault().warning("the move representations action was interrupted", e);
        } catch (final InvocationTargetException e) {
            SiriusPlugin.getDefault().error("the move representations encountered an exception", e);
        }
    }

    /**
     * @param op
     * @param errorCatch
     * @return
     */
    private boolean createAIRDFile(final IRunnableWithProgress op, final boolean error) {
        boolean errorCatch = error;
        try {
            getContainer().run(false, true, op);
        } catch (final InterruptedException e) {
            errorCatch = true;
            // return false;
        } catch (final InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(), "Error creating resource", null, ((CoreException) e.getTargetException()).getStatus());
            } else {
                SiriusTransPlugin.getPlugin().error("Error creating aird session data", e.getTargetException()); //$NON-NLS-1$
            }
            // return false;
            errorCatch = true;
        }
        return errorCatch;
    }

    /**
     * @return
     */
    private DAnalysis prepareNewAnalysis() {
        final DAnalysis slaveAnalysis = SiriusFactory.eINSTANCE.createDAnalysis();
        domain.getCommandStack().execute(new PrepareNewAnalysisCommand(domain, pickedResource, slaveAnalysis, session));
        return slaveAnalysis;
    }

    private void closeRepresentations(final Collection<DRepresentation> diagrams) {
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
            for (DRepresentation representation : diagrams) {
                closeOpenedEditor(uiSession, representation);
            }
        }
    }

    /**
     * @param uiSession
     * @param representation
     */
    private void closeOpenedEditor(final IEditingSession uiSession, final DRepresentation representation) {
        final IEditorPart editor = uiSession.getEditor(representation);
        if (editor != null) {
            editor.getEditorSite().getPage().closeEditor(editor, false);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        final Collection<String> extensions = new ArrayList<String>();
        extensions.add(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
        sessionResourceFilePage = new SessionResourceCreationWizardPage("Representations file", new StructuredSelection(), SiriusUtil.SESSION_RESOURCE_EXTENSION); //$NON-NLS-1$
        addPage(sessionResourceFilePage);
    }

    /**
     * return the newly created resource.
     * 
     * @return the newly created resource.
     */
    public Resource getCreatedResource() {
        return pickedResource;
    }

    private class DiagramFileCreationOperation extends WorkspaceModifyOperation {

        public DiagramFileCreationOperation() {
            super(null);
        }

        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {

            final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
            pickedResource = set.createResource(sessionResourceFilePage.getURI());
        }
    }

}
