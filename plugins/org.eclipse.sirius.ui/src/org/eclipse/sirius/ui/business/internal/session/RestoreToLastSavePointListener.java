/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import java.util.Collection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Marker able to restore the command stack to the last save point.
 * 
 * @author mporhel
 */
public class RestoreToLastSavePointListener implements ResourceSyncClient, IOperationHistoryListener {

    private IUndoableOperation marker;

    private Session session;

    /**
     * Create a new instance.
     * 
     * @param session
     *            the session to listen
     */
    public RestoreToLastSavePointListener(Session session) {
        this.session = session;
    }

    /**
     * Register this listener.
     */
    public void register() {
        OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
        ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).registerClient(this);
    }

    /**
     * Unregister this listener.
     */
    public void unregister() {
        OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(this);
        // Do not call ResourceSetSync.getOrInstallResourceSetSync as the
        // ResourceSetSync could already have been removed.
        Option<ResourceSetSync> resourceSetSync = ResourceSetSync.getResourceSetSync(session.getTransactionalEditingDomain());
        if (resourceSetSync.some()) {
            resourceSetSync.get().unregisterClient(this);
        }
    }

    /**
     * Return to the last save point.
     */
    public void returnToSyncState() {
        if (isAllowedToReturnToSyncState()) {
            IProgressMonitor pm = new NullProgressMonitor();
            try {
                IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
                IUndoContext undoCtx;
                if (session.getTransactionalEditingDomain() != null) {
                    if (session.getTransactionalEditingDomain().getCommandStack() instanceof IWorkspaceCommandStack) {
                        undoCtx = ((IWorkspaceCommandStack) session.getTransactionalEditingDomain().getCommandStack()).getDefaultUndoContext();
                    } else {
                        undoCtx = new EditingDomainUndoContext(session.getTransactionalEditingDomain());
                    }
                } else {
                    undoCtx = new ObjectUndoContext(this);
                }
                while (operationHistory != null && operationHistory.canUndo(undoCtx) && session.getStatus() != SessionStatus.SYNC) {
                    IUndoableOperation toUndo = operationHistory.getUndoOperation(undoCtx);

                    operationHistory.undoOperation(toUndo, new NullProgressMonitor(), null);

                    if (marker == toUndo) {
                        break;
                    }
                }
                session.save(pm);
            } catch (ExecutionException e) {
                // Session could not be reloaded, close it.
                session.close(pm);
                String sessionLabel = SiriusEditPlugin.getPlugin().getUiCallback().getSessionNameToDisplayWhileSaving(session);
                SiriusPlugin.getDefault().warning(sessionLabel + " were closed. An error occurs while attempting to cancel all modifications. No modification were saved.", e);
            }
        }
    }

    /**
     * Check the DesignerUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE
     * preference state.
     * 
     * @return the preference value.
     */
    public static boolean isAllowedToReturnToSyncState() {
        IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        return preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name());
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        session = null;
        marker = null;
    }

    /**
     * {@inheritDoc}
     */
    public void statusChanged(Resource resource, ResourceStatus oldStatus, ResourceStatus newStatus) {
        // Do nothing while processing,
        // see statusesChanged(Collection<ReResourceStatusChange>)
    }

    /**
     * {@inheritDoc}
     */
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        /* this may be call in a async thread */
        if (session != null) {
            if (SessionStatus.SYNC.equals(session.getStatus())) {
                marker = null;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void historyNotification(OperationHistoryEvent event) {
        if (session != null && SessionStatus.DIRTY.equals(session.getStatus())) {
            if (marker == null) {
                marker = event.getOperation();
            }
        }
    }
}
