/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.selection.page.EObjectSelectionWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SessionResourceCreationWizardPage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * Wizard to create a new DView from an Existing model.
 *
 * @author ymortier
 */
public class CreateSessionResourceWizard extends Wizard implements INewWizard {

    /**
     * The selection.
     */
    protected IStructuredSelection selection;

    /**
     * The wizard page to create a new diagram file.
     */
    protected SessionResourceCreationWizardPage diagramModelFilePage;

    /**
     * The wizard page to select the first viewpoint.
     */
    protected EObjectSelectionWizardPage viewpointSelectionPage;

    private IWorkbench workbench;

    private Session createdSession;

    /**
     * Constructor.
     */
    public CreateSessionResourceWizard() {
    }

    /**
     * Constructor.
     *
     * @param initialSelection
     *            The initial selection
     */
    public CreateSessionResourceWizard(final IStructuredSelection initialSelection) {
        selection = initialSelection;
    }

    public IWorkbench getWorkbench() {
        return workbench;
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

    @Override
    public void init(final IWorkbench w, final IStructuredSelection s) {
        this.workbench = w;
        this.selection = s;
        setWindowTitle(Messages.CreateSessionResourceWizard_title);
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        final Collection<String> extensions = new ArrayList<String>();
        extensions.add(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
        diagramModelFilePage = new SessionResourceCreationWizardPage("DiagramModelFile", getSelection(), SiriusUtil.SESSION_RESOURCE_EXTENSION); //$NON-NLS-1$
        addPage(diagramModelFilePage);
    }

    @Override
    public boolean performFinish() {
        final IRunnableWithProgress op = new DiagramFileCreationOperation();
        boolean errorCatch = false;
        try {
            getContainer().run(false, true, op);
        } catch (final InterruptedException e) {
            errorCatch = true;
            // return false;
        } catch (final InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(), Messages.CreateSessionResourceWizard_resourceCreationError, null, ((CoreException) e.getTargetException()).getStatus());
            } else {
                SiriusTransPlugin.getPlugin().error(Messages.CreateSessionResourceWizard_sessionDataCreationError, e.getTargetException());
            }
            // return false;
            errorCatch = true;
        }
        if (errorCatch) {
            return false;
        } else {
            return createdSession != null;
        }
    }

    /**
     * return the newly created {@link Session}.
     *
     * @return the newly created {@link Session}.
     */
    public Session getCreatedSession() {
        return createdSession;
    }

    private class DiagramFileCreationOperation extends WorkspaceModifyOperation {

        DiagramFileCreationOperation() {
            super(null);
        }

        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
            SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(diagramModelFilePage.getURI(), monitor);
            sessionCreationOperation.execute();
            createdSession = sessionCreationOperation.getCreatedSession();
        }
    }

}
