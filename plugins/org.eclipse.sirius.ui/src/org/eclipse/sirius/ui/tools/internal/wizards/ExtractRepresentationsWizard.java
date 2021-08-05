/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.internal.command.PrepareNewAnalysisCommand;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SessionResourceCreationWizardPage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * This wizard ask the user for which viewpoints he wants to externalize and create the new .aird files.
 *
 * @author cbrun
 *
 */
public class ExtractRepresentationsWizard extends Wizard {

    private final TransactionalEditingDomain domain;

    private Session session;

    private Collection<DRepresentationDescriptor> repDescriptors;

    private SessionResourceCreationWizardPage sessionResourceFilePage;

    private Resource pickedResource;

    /**
     * Constructor.
     *
     * @param session
     *            origin session.
     * @param domain
     *            the editing domain
     * @param movableRepDescriptors
     *            the DView instance
     */
    public ExtractRepresentationsWizard(final Session session, final TransactionalEditingDomain domain, final Collection<DRepresentationDescriptor> movableRepDescriptors) {
        this.domain = domain;
        this.session = session;
        this.repDescriptors = movableRepDescriptors;
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
        setWindowTitle(Messages.ExtractRepresentationsWizard_title);
        setNeedsProgressMonitor(true);
    }

    @Override
    public boolean performFinish() {

        closeRepresentations();

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

    private void moveRepresentations(final DAnalysis slaveAnalysis) {
        final IRunnableWithProgress moveReps = new IRunnableWithProgress() {
            @Override
            public void run(final IProgressMonitor mon) {
                domain.getCommandStack().execute(new MoveRepresentationCommand(session, slaveAnalysis, repDescriptors));
            }
        };
        try {
            getContainer().run(false, true, moveReps);
        } catch (final InterruptedException e) {
            SiriusPlugin.getDefault().warning(Messages.ExtractRepresentationsWizard_moveInterrupted, e);
        } catch (final InvocationTargetException e) {
            SiriusPlugin.getDefault().error(Messages.ExtractRepresentationsWizard_moveFailed, e);
        }
    }

    private boolean createAIRDFile(final IRunnableWithProgress op, final boolean error) {
        boolean errorCatch = error;
        try {
            getContainer().run(false, true, op);
        } catch (final InterruptedException e) {
            errorCatch = true;
        } catch (final InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(), Messages.ExtractRepresentationsWizard_resourceCreationError, null, ((CoreException) e.getTargetException()).getStatus());
            } else {
                SiriusTransPlugin.getPlugin().error(Messages.ExtractRepresentationsWizard_airdCreationError, e.getTargetException());
            }
            errorCatch = true;
        }
        return errorCatch;
    }

    private DAnalysis prepareNewAnalysis() {
        final DAnalysis slaveAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        domain.getCommandStack().execute(new PrepareNewAnalysisCommand(domain, pickedResource, slaveAnalysis, session));
        return slaveAnalysis;
    }

    private void closeRepresentations() {
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
            for (DRepresentationDescriptor repDescriptor : repDescriptors) {
                closeOpenedEditor(uiSession, repDescriptor.getRepresentation());
            }
        }
    }

    private void closeOpenedEditor(final IEditingSession uiSession, final DRepresentation representation) {
        final IEditorPart editor = uiSession.getEditor(representation);
        if (editor != null) {
            editor.getEditorSite().getPage().closeEditor(editor, false);
        }
    }

    @Override
    public void addPages() {
        final Collection<String> extensions = new ArrayList<String>();
        extensions.add(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
        sessionResourceFilePage = new SessionResourceCreationWizardPage(Messages.ExtractRepresentationsWizard_pageName, new StructuredSelection(), SiriusUtil.SESSION_RESOURCE_EXTENSION);
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

        DiagramFileCreationOperation() {
            super(null);
        }

        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
            final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
            pickedResource = set.createResource(sessionResourceFilePage.getURI());
        }
    }

}
