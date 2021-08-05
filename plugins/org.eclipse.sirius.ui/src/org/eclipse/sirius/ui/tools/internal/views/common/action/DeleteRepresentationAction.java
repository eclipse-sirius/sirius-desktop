/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.provider.Messages;
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

    private Collection<DRepresentationDescriptor> selectedRepDescriptors;

    /**
     * Create a new instance.
     * 
     * @param repDescriptors
     *            holds the representation to delete
     */
    public DeleteRepresentationAction(Collection<DRepresentationDescriptor> repDescriptors) {
        super(Messages.DeleteRepresentationAction_name, AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/delete.gif")); //$NON-NLS-1$
        this.selectedRepDescriptors = repDescriptors;

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {
        Map<DRepresentationDescriptor, Session> representation2Session = getRepresentations();
        final Map<Session, Set<DRepresentationDescriptor>> session2DRepresentations = getSession2Representations(representation2Session);
        try {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            boolean deletionConfirmation = SiriusPlugin.getDefault().getUiCallback().shouldDeleteRepresentation(representation2Session.keySet());
            if (deletionConfirmation) {
                IRunnableContext context = new ProgressMonitorDialog(shell);
                IRunnableWithProgress editorClosingRunnable = new IRunnableWithProgress() {
                    @Override
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.DeleteRepresentationAction_closeEditorsTask, 1);
                            for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2DRepresentations.entrySet()) {
                                Session session = entry.getKey();
                                Set<DRepresentationDescriptor> dRepdescriptors = entry.getValue();

                                IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
                                if (editingSession != null) {
                                    for (DRepresentationDescriptor dRepDescriptor : dRepdescriptors) {
                                        Optional.of(dRepDescriptor).filter(DRepresentationDescriptor::isLoadedRepresentation).map(DRepresentationDescriptor::getRepresentation).ifPresent(rep -> {
                                            DialectEditor editor = editingSession.getEditor(rep);
                                            if (editor != null) {
                                                DialectUIManager.INSTANCE.closeEditor(editor, false);
                                                editingSession.detachEditor(editor);
                                            }
                                        });
                                    }
                                }
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
                            String taskName = session2DRepresentations.size() > 1 ? Messages.DeleteRepresentationAction_deleteRepresentationTask_plural
                                    : Messages.DeleteRepresentationAction_deleteRepresentationTask;
                            monitor.beginTask(taskName, session2DRepresentations.size());
                            for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2DRepresentations.entrySet()) {
                                Session session = entry.getKey();
                                Set<DRepresentationDescriptor> dRepDescriptors = entry.getValue();

                                Command deleteDRepresentationsCmd = new DeleteRepresentationCommand(session, dRepDescriptors);
                                session.getTransactionalEditingDomain().getCommandStack().execute(deleteDRepresentationsCmd);
                            }
                        } finally {
                            monitor.done();
                        }
                    }
                };
                PlatformUI.getWorkbench().getProgressService().run(true, false, representationsDeletionRunnable);
            }
        } catch (final InvocationTargetException | InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
        }
    }

    private Map<DRepresentationDescriptor, Session> getRepresentations() {
        final Map<DRepresentationDescriptor, Session> representations = new HashMap<DRepresentationDescriptor, Session>();

        for (final DRepresentationDescriptor dRepDescription : selectedRepDescriptors) {
            EObjectQuery eObjectQuery = new EObjectQuery(dRepDescription);
            Session currentSession = eObjectQuery.getSession();
            if (currentSession != null) {
                representations.put(dRepDescription, currentSession);
            }
        }
        return representations;
    }

    private Map<Session, Set<DRepresentationDescriptor>> getSession2Representations(Map<DRepresentationDescriptor, Session> dRepresentation2Session) {
        Map<Session, Set<DRepresentationDescriptor>> session2DRepresentations = new HashMap<Session, Set<DRepresentationDescriptor>>();
        for (Entry<DRepresentationDescriptor, Session> entry : dRepresentation2Session.entrySet()) {
            DRepresentationDescriptor dRepresentation = entry.getKey();
            Session session = entry.getValue();
            Set<DRepresentationDescriptor> repDescriptors = session2DRepresentations.get(session);
            if (repDescriptors == null) {
                repDescriptors = new HashSet<DRepresentationDescriptor>();
                session2DRepresentations.put(session, repDescriptors);
            }
            repDescriptors.add(dRepresentation);
        }
        return session2DRepresentations;
    }

    /**
     * Overridden to ask to the {@link IPermissionAuthority} if we can delete all selected {@link DRepresentation}s, if
     * we can't delete one then the action is disabled.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean isEnabled = super.isEnabled();
        if (isEnabled) {
            for (DRepresentationDescriptor dRepDescriptor : selectedRepDescriptors) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dRepDescriptor);
                if (!permissionAuthority.canDeleteInstance(dRepDescriptor)) {
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

        boolean anyInvalidDelete = Iterables.any(selectedRepDescriptors, new Predicate<DRepresentationDescriptor>() {

            @Override
            public boolean apply(DRepresentationDescriptor input) {
                EObject container = input.eContainer();
                if (container instanceof DView) {
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
