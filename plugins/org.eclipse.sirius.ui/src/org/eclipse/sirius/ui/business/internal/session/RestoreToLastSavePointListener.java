/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.internal.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;

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
        OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
        ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).registerClient(this);
    }

    /**
     * Return to the last save point.
     */
    public void returnToSyncState() {
        // If the user is closing the workbench, we do not need to reset the
        // changes.
        if (PlatformUI.getWorkbench().isClosing()) {
            return;
        }
        if (isAllowedToReturnToSyncState()) {
            if (canPerformTheRevertFromOperationHistory()) {
                returnToSyncStateFromOperationHistory();
            } else {
                reloadTheResources();
            }
        }
    }

    private void reloadTheResources() {
        List<ResourceStatusChange> changes = new ArrayList<ResourceStatusChange>();
        for (Resource currentResource : getModifiedResources()) {
            changes.add(new ResourceStatusChange(currentResource, ResourceStatus.CHANGES_CANCELED, ResourceStatus.CHANGED));
        }
        if (session instanceof ResourceSyncClient) {
            ((ResourceSyncClient) session).statusesChanged(changes);
        }
    }

    private Collection<Resource> getModifiedResources() {
        List<Resource> changed = new ArrayList<Resource>();
        for (Resource currentResource : session.getAllSessionResources()) {
            if (currentResource.isModified()) {
                changed.add(currentResource);
            }
        }
        for (Resource currentResource : session.getSrmResources()) {
            if (currentResource.isModified()) {
                changed.add(currentResource);
            }
        }
        for (Resource currentResource : session.getSemanticResources()) {
            if (currentResource.isModified()) {
                changed.add(currentResource);
            }
        }
        if (session instanceof DAnalysisSessionEObject) {
            for (Resource currentResource : ((DAnalysisSessionEObject) session).getControlledResources()) {
                if (currentResource.isModified()) {
                    changed.add(currentResource);
                }
            }
        }
        return changed;
    }

    private void returnToSyncStateFromOperationHistory() {
        try {
            IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
            IUndoContext undoCtx = getUndoContext();
            while (operationHistory != null && operationHistory.canUndo(undoCtx) && session.getStatus() != SessionStatus.SYNC) {
                IUndoableOperation toUndo = operationHistory.getUndoOperation(undoCtx);

                operationHistory.undoOperation(toUndo, new NullProgressMonitor(), null);

                if (marker == toUndo) {
                    break;
                }
            }
            setResourcesBackToSync();
        } catch (ExecutionException e) {
            // If something went wrong, we reload the changed resources:
            reloadTheResources();
            String sessionLabel = SiriusEditPlugin.getPlugin().getUiCallback().getSessionNameToDisplayWhileSaving(session);
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.RestoreToLastSavePointListener_errorModifiedResourcesReloaded, sessionLabel), e);
        }
    }

    private void setResourcesBackToSync() {
        if (session instanceof ResourceSyncClient) {
            for (Resource currentResource : getModifiedResources()) {
                currentResource.setModified(false);
            }
        }
    }

    private IUndoContext getUndoContext() {
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
        return undoCtx;
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(this);
        // Do not call ResourceSetSync.getOrInstallResourceSetSync as the
        // ResourceSetSync could already have been removed.
        Optional<ResourceSetSync> resourceSetSync = ResourceSetSync.getResourceSetSync(session.getTransactionalEditingDomain());
        if (resourceSetSync.isPresent()) {
            resourceSetSync.get().unregisterClient(this);
        }
        session = null;
        marker = null;
    }

    @Override
    public void statusChanged(Resource resource, ResourceStatus oldStatus, ResourceStatus newStatus) {
        // Do nothing while processing,
        // see statusesChanged(Collection<ReResourceStatusChange>)
    }

    @Override
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        /* this may be call in a async thread */
        if (session != null) {
            if (SessionStatus.SYNC == session.getStatus()) {
                marker = null;
            }
        }
    }

    @Override
    public void historyNotification(OperationHistoryEvent event) {
        if (session != null && SessionStatus.DIRTY == session.getStatus()) {
            if (marker == null) {
                marker = event.getOperation();
            }
        }
    }

    /**
     * Returns whether we can perform the entire revert by undoing all operations. In other words, if the undo history
     * is strictly lower to the max limit.
     *
     * @return true if we can perform the undo, false otherwise.
     */
    private boolean canPerformTheRevertFromOperationHistory() {
        IUndoContext undoContext = getUndoContext();
        IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
        IUndoableOperation[] iUndoableOperations = operationHistory.getUndoHistory(undoContext);
        return iUndoableOperations.length < operationHistory.getLimit(undoContext);
    }

    /**
     * Check the DesignerUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE preference state.
     *
     * @return the preference value.
     */
    public boolean isAllowedToReturnToSyncState() {
        IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        return preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name());
    }
}
