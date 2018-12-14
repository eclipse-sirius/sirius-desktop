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

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.EditingSessionEvent;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * A listener to resource set change which save session if there is no dialect editor opens.
 * 
 * @author mchauvin
 */
public class SaveSessionWhenNoDialectEditorsListener implements ResourceSyncClient {

    /**
     * The session from which Sirius resources modifications are listened to know if a save must be execute when no
     * editors are opened.
     */
    protected final Session session;

    private Job saveSessionJob;

    private boolean activation = true;

    /**
     * Create a new instance.
     * 
     * @param session
     *            the session.
     */
    public SaveSessionWhenNoDialectEditorsListener(Session session) {
        this.session = session;
    }

    /**
     * Register this listener.
     */
    public void register() {
        ResourceSetSync.getOrInstallResourceSetSync(session.getTransactionalEditingDomain()).registerClient(this);
    }

    /**
     * Unregister this listener.
     */
    public void unregister() {
        // Do not call ResourceSetSync.getOrInstallResourceSetSync as the
        // ResourceSetSync could already have been removed.
        Optional<ResourceSetSync> resourceSetSync = ResourceSetSync.getResourceSetSync(session.getTransactionalEditingDomain());
        if (resourceSetSync.isPresent()) {
            resourceSetSync.get().unregisterClient(this);
        }
        saveSessionJob = null;
    }

    /**
     * Notify this listener.
     * 
     * @param event
     *            the event
     */
    public void notify(final EditingSessionEvent event) {
        switch (event) {
        case REPRESENTATION_ABOUT_TO_BE_CREATED_BEFORE_OPENING:
            activation = false;
            break;
        case REPRESENTATION_CREATED_BEFORE_OPENING:
            activation = true;
            break;
        default:
            break;
        }
    }

    private boolean newMode() {
        final IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        return preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient#statusChanged(org.eclipse.emf.ecore.resource.Resource,
     *      org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus,
     *      org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus)
     */
    @Override
    public void statusChanged(Resource resource, ResourceStatus oldStatus, ResourceStatus newStatus) {
        // Do nothing while processing,
        // see statusesChanged(Collection<ReResourceStatusChange>)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        if (activation && newMode()) {
            statusChangedInternal(changes);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient#statusChanged(org.eclipse.emf.ecore.resource.Resource,
     *      org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus,
     *      org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus)
     */
    private void statusChangedInternal(Collection<ResourceStatusChange> changes) {
        final IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);

        if (session.isOpen() && editingSession != null && editingSession.getSiriusEditors().isEmpty() && resourceNoMoreInSync(changes)) {
            if (wasProjectDeletedOrRenamed(changes))
                return;

            if (SessionStatus.DIRTY.equals(session.getStatus())) {
                if (saveSessionJob == null || saveSessionJob.getState() == Job.NONE) {
                    preSave();
                    saveSessionJob = createSaveSessionJob(session);
                    saveSessionJob.schedule();
                }
            }
        }
    }

    /**
     * Create the job that will save the session.
     * 
     * @param aSession
     *            the session to save
     * @return a save session job to schedule
     */
    protected SaveSessionJob createSaveSessionJob(Session aSession) {
        return new SaveSessionJob(aSession);
    }

    /**
     * This method allows to do some treatments before saving.
     * 
     * WARNING : Be careful not to break default Sirius saving behavior when overriding this method.
     * 
     */
    protected void preSave() {
        // Do nothing
    }

    private boolean wasProjectDeletedOrRenamed(Collection<ResourceStatusChange> changes) {
        Collection<IProject> projects = new HashSet<>();
        for (ResourceStatusChange change : changes) {
            IFile file = WorkspaceSynchronizer.getFile(change.getResource());
            if (file != null) {
                IProject project = file.getProject();
                if (project != null) {
                    projects.add(project);
                }
            }
        }

        for (IProject project : projects) {
            if (!project.exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean resourceNoMoreInSync(Collection<ResourceStatusChange> changes) {
        boolean resourcesNoMoreInSync = false;
        for (ResourceStatusChange change : changes) {
            ResourceStatus newStatus = change.getNewStatus();
            resourcesNoMoreInSync = resourcesNoMoreInSync || !newStatus.equals(ResourceStatus.SYNC);
        }
        return resourcesNoMoreInSync;
    }

}
