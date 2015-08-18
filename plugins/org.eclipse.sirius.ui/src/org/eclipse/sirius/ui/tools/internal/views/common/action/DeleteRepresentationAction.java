/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Action to delete on or more Sirius representations.
 * 
 * @author mchauvin
 */
public class DeleteRepresentationAction extends Action {

    private Collection<DRepresentation> selectedRepresentations;

    /**
     * Create a new instance.
     * 
     * @param representations
     *            the representations to delete
     */
    public DeleteRepresentationAction(Collection<DRepresentation> representations) {
        super("Delete", AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/delete.gif")); //$NON-NLS-2$
        this.selectedRepresentations = representations;

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {
        Map<DRepresentation, Session> dRepresentation2Session = getRepresentations();
        final Map<Session, Set<DRepresentation>> session2DRepresentations = getSession2DRepresentations(dRepresentation2Session);
        String deleteRepresenationDialogTitle = "Delete representation";
        String deletionMessage = "Are you sure you want to delete the selected representation?";
        if (dRepresentation2Session.size() >= 2) {
            deleteRepresenationDialogTitle = "Delete representations";
            deletionMessage = "Are you sure you want to delete the selected representations?";
        }
        try {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            boolean deletionConfirmation = MessageDialog.openConfirm(shell, deleteRepresenationDialogTitle, deletionMessage);
            if (deletionConfirmation) {
                IRunnableContext context = new ProgressMonitorDialog(shell);
                IRunnableWithProgress editorClosingRunnable = new IRunnableWithProgress() {
                    @Override
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask("Associated editor closing", 1);
                            for (Entry<Session, Set<DRepresentation>> entry : session2DRepresentations.entrySet()) {
                                Session session = entry.getKey();
                                Set<DRepresentation> dRepresentations = entry.getValue();

                                IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
                                if (editingSession != null) {
                                    for (DRepresentation dRepresentation : dRepresentations) {
                                        DialectEditor editor = editingSession.getEditor(dRepresentation);
                                        if (editor != null) {
                                            DialectUIManager.INSTANCE.closeEditor(editor, false);
                                            editingSession.detachEditor(editor);
                                        }
                                    }
                                }

                                Command deleteDRepresentationsCmd = new DeleteRepresentationCommand(session, dRepresentations);
                                session.getTransactionalEditingDomain().getCommandStack().execute(deleteDRepresentationsCmd);
                            }
                        } finally {
                            monitor.done();
                        }
                    }

                };
                PlatformUI.getWorkbench().getProgressService().runInUI(context, editorClosingRunnable, null);

                IRunnableWithProgress representationsDeletionRunnable = new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        try {
                            monitor.beginTask("Representation" + (session2DRepresentations.size() > 1 ? "s" : "") + " deletion", session2DRepresentations.size());
                            for (Entry<Session, Set<DRepresentation>> entry : session2DRepresentations.entrySet()) {
                                Session session = entry.getKey();
                                Set<DRepresentation> dRepresentations = entry.getValue();

                                Command deleteDRepresentationsCmd = new DeleteRepresentationCommand(session, dRepresentations);
                                session.getTransactionalEditingDomain().getCommandStack().execute(deleteDRepresentationsCmd);
                            }
                        } finally {
                            monitor.done();
                        }
                    }
                };
                PlatformUI.getWorkbench().getProgressService().run(true, false, representationsDeletionRunnable);
            }
        } catch (final InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        } catch (final InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        }
    }

    private Map<DRepresentation, Session> getRepresentations() {
        final Map<DRepresentation, Session> representations = new HashMap<DRepresentation, Session>();

        for (final DRepresentation dRepresentation : selectedRepresentations) {
            EObjectQuery eObjectQuery = new EObjectQuery(dRepresentation);
            Session currentSession = eObjectQuery.getSession();
            if (currentSession != null) {
                representations.put(dRepresentation, currentSession);
            }
        }
        return representations;
    }

    private Map<Session, Set<DRepresentation>> getSession2DRepresentations(Map<DRepresentation, Session> dRepresentation2Session) {
        Map<Session, Set<DRepresentation>> session2DRepresentations = new HashMap<Session, Set<DRepresentation>>();
        for (Entry<DRepresentation, Session> entry : dRepresentation2Session.entrySet()) {
            DRepresentation dRepresentation = entry.getKey();
            Session session = entry.getValue();
            Set<DRepresentation> dRepresentations = session2DRepresentations.get(session);
            if (dRepresentations == null) {
                dRepresentations = new HashSet<DRepresentation>();
                session2DRepresentations.put(session, dRepresentations);
            }
            dRepresentations.add(dRepresentation);
        }
        return session2DRepresentations;
    }

    /**
     * Overridden to ask to the {@link IPermissionAuthority} if we can delete
     * all selected {@link DRepresentation}s, if we can't delete one then the
     * action is disabled.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean isEnabled = super.isEnabled();
        if (isEnabled) {
            for (DRepresentation dRepresentation : selectedRepresentations) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dRepresentation);
                if (!permissionAuthority.canDeleteInstance(dRepresentation)) {
                    isEnabled = false;
                    break;
                }
            }
        }
        return isEnabled;
    }

    /**
     * Test if the selection is valid.
     * 
     * @return true if the selection is valid
     */
    private boolean isValidSelection() {

        boolean anyInvalidDelete = Iterables.any(selectedRepresentations, new Predicate<DRepresentation>() {

            @Override
            public boolean apply(DRepresentation input) {
                EObject container = input.eContainer();
                if (container instanceof DRepresentationContainer) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority != null && !permissionAuthority.canDeleteInstance(input)) {
                        return true;
                    }
                }
                return false;
            }
        });

        return !anyInvalidDelete;
    }

}
