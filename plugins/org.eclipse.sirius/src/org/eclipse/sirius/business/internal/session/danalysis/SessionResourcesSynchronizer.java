/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.ReloadingPolicy.Action;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.SyncStatus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * A {@link ResourceSyncClient} that updates a session according to the resource
 * changes it is notified of.
 * 
 * @author pcdavid
 */
public class SessionResourcesSynchronizer implements ResourceSyncClient {
    private final DAnalysisSessionImpl session;

    /**
     * Creates a new synchronizer.
     * 
     * @param session
     *            the session to synchronize.
     */
    public SessionResourcesSynchronizer(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
    }

    @Override
    public void statusChanged(final Resource resource, final ResourceStatus oldStatus, final ResourceStatus newStatus) {
        // This method is called for each change in turn,
        // while the ResourceSetSync is still gathering/analyzing notifications.
        // Do nothing in this case, and defer all the processing to the
        // statusesChanged method, which is called only once will all the
        // changes already known and aggregated.
    }

    @Override
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        if (session.isOpen()) {
            Multimap<ResourceStatus, Resource> newStatuses = getImpactingNewStatuses(changes);
            boolean allResourcesSync = allResourcesAreInSync();
            for (ResourceStatus newStatus : newStatuses.keySet()) {
                switch (newStatus) {
                case SYNC:
                    if (allResourcesSync) {
                        session.notifyListeners(SessionListener.SYNC);
                    }
                    break;
                case CHANGED:
                    session.notifyListeners(SessionListener.DIRTY);
                    break;
                case EXTERNAL_CHANGED:
                case CONFLICTING_CHANGED:
                case CONFLICTING_DELETED:
                case DELETED:
                case CHANGES_CANCELED:
                    IProgressMonitor pm = new NullProgressMonitor();
                    for (Resource resource : newStatuses.get(newStatus)) {
                        try {
                            /*
                             * if the project was renamed, deleted or closed we
                             * should not try to reload any thing, this does not
                             * make sense
                             */
                            if (isInProjectDeletedRenamedOrClosed(resource)) {
                                processAction(Action.CLOSE_SESSION, resource, pm);
                                return;
                            }
                            processActions(session.getReloadingPolicy().getActions(session, resource, newStatus), resource, pm);

                            // CHECKSTYLE:OFF
                        } catch (final Exception e) {
                            // CHECKSTYLE:ON
                            SiriusPlugin.getDefault().error("Can't handle resource change : " + resource.getURI(), e);

                        }
                    }
                    // Reload were launched, get global status.
                    allResourcesSync = allResourcesAreInSync();
                    if (allResourcesSync) {
                        session.notifyListeners(SessionListener.SYNC);
                    } else {
                        session.notifyListeners(SessionListener.DIRTY);
                    }
                    break;
                default:
                    break;
                }
            }

            if (allResourcesSync) {
                session.setSynchronizationStatus(SyncStatus.SYNC);
            } else {
                session.setSynchronizationStatus(SyncStatus.DIRTY);
            }
        }
    }

    /**
     * Check if this resource is in an non-existent project or a closed project.
     * 
     * @param resource
     *            the resource to check
     * @return true if this resource is in an non-existent project or a closed
     *         project, false otherwise
     */
    private boolean isInProjectDeletedRenamedOrClosed(Resource resource) {
        IFile file = WorkspaceSynchronizer.getFile(resource);
        if (file != null) {
            IProject project = file.getProject();
            if (project != null) {
                return !project.exists() || !project.isOpen();
            }
        }
        return false;
    }

    private void processActions(List<Action> actions, Resource resource, IProgressMonitor pm) throws Exception {
        for (Action action : actions) {
            processAction(action, resource, pm);
        }
    }

    private void processAction(Action action, final Resource resource, IProgressMonitor pm) throws Exception {
        switch (action) {
        case CLOSE_SESSION:
            session.close(pm);
            break;
        case RELOAD:
            if (session.isOpen()) {
                reloadResource(resource);
            }
            break;
        case REMOVE:
            removeResourceFromSession(resource, pm);
            break;
        default:
            break;
        }
    }

    private void reloadResource(final Resource resource) throws IOException {
        session.notifyListeners(SessionListener.ABOUT_TO_BE_REPLACED);
        TransactionalEditingDomain ted = session.getTransactionalEditingDomain();

        Command reloadingAnalysisCmd = null;
        ResourceQuery resourceQuery = new ResourceQuery(resource);
        boolean representationsResource = resourceQuery.isRepresentationsResource();
        if (representationsResource) {
            reloadingAnalysisCmd = new AnalysisResourceReloadedCommand(session, ted, resource);
        }
        List<Resource> resourcesBeforeReload = Lists.newArrayList(ted.getResourceSet().getResources());
        /* execute the reload operation as a read-only transaction */
        RunnableWithResult<?> reload = new RunnableWithResult.Impl<Object>() {
            @Override
            public void run() {
                session.disableCrossReferencerResolve(resource);
                resource.unload();
                session.enableCrossReferencerResolve(resource);
                try {
                    resource.load(Collections.EMPTY_MAP);
                    EcoreUtil.resolveAll(resource);
                    session.getSemanticCrossReferencer().resolveProxyCrossReferences(resource);
                } catch (IOException e) {
                    setResult(e);
                }
            }
        };
        try {
            ted.runExclusive(reload);
            if (reload.getResult() != null) {
                throw (IOException) reload.getResult();
            } else if (!reload.getStatus().isOK()) {
                SiriusPlugin.getDefault().error("a reload operation failed for unknown reason", null);
            } else {
                if (representationsResource) {
                    ted.getCommandStack().execute(reloadingAnalysisCmd);
                    if (resource.getURI().equals(session.getSessionResource().getURI())) {
                        session.sessionResourceReloaded(resource);
                    }
                }
                // Analyze the unknown resources to detect new semantic or
                // session resources.
                session.discoverAutomaticallyLoadedResources(resourcesBeforeReload);
                session.notifyListeners(SessionListener.REPLACED);
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    /**
     * Remove a resource from the current session and close the current session
     * if it contains no more analysis resource.
     * 
     * @param resource
     *            The resource to remove
     */
    private void removeResourceFromSession(Resource resource, IProgressMonitor pm) {
        if (session.getAllSemanticResources().contains(resource)) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RemoveSemanticResourceCommand(session, resource, new NullProgressMonitor(), false));
        } else if (session.getAllSessionResources().contains(resource)) {
            session.removeAnalysis(resource);
        }
        if (session.getResources().contains(resource)) {
            for (final EObject root : Lists.newArrayList(resource.getContents())) {
                EcoreUtil.remove(root);
            }
            session.getResources().remove(resource);
        }
        // delete session only if no more aird file is open
        if (session.getAllSessionResources().isEmpty()) {
            session.close(pm);
        }
    }

    /**
     * Indicates whether all resources (semantic and Danalysises) of this
     * Session are whether {@link ResourceStatus#SYNC} or
     * {@link ResourceStatus#READONLY}.
     * 
     * @return true if all resources (semantic and Danalysises) of this Session
     *         are whether {@link ResourceStatus#SYNC} or
     *         {@link ResourceStatus#READONLY}, false otherwise
     */
    protected boolean allResourcesAreInSync() {
        return checkResourcesAreInSync(getAllSessionResources());
    }

    /**
     * Indicates whether considered resources are whether
     * {@link ResourceStatus#SYNC} or {@link ResourceStatus#READONLY}.
     * 
     * @param resourcesToConsider
     *            the resources to inspect.
     * @return true if all considered are whether {@link ResourceStatus#SYNC} or
     *         {@link ResourceStatus#READONLY}, false otherwise
     */
    protected boolean checkResourcesAreInSync(Iterable<? extends Resource> resourcesToConsider) {
        boolean allResourceAreInSync = true;
        for (Resource resource : resourcesToConsider) {
            ResourceStatus status = ResourceSetSync.getStatus(resource);
            // Test also resource.modified field in case ResourceStatus ==
            // UNKNOWN
            allResourceAreInSync = status == ResourceStatus.SYNC || !resource.isModified();
            if (!allResourceAreInSync) {
                break;
            }
        }
        return allResourceAreInSync;
    }

    private Multimap<ResourceStatus, Resource> getImpactingNewStatuses(Collection<ResourceStatusChange> changes) {
        Multimap<ResourceStatus, Resource> impactingNewStatuses = LinkedHashMultimap.create();
        Multimap<ResourceStatus, Resource> representationResourcesNewStatuses = LinkedHashMultimap.create();
        Iterable<Resource> semanticOrControlledresources = getAllSemanticResources();
        Set<Resource> representationResources = session.getAllSessionResources();
        for (ResourceStatusChange change : changes) {
            if (session.isResourceOfSession(change.getResource(), semanticOrControlledresources)) {
                impactingNewStatuses.put(change.getNewStatus(), change.getResource());
            } else if (session.isResourceOfSession(change.getResource(), representationResources)) {
                representationResourcesNewStatuses.put(change.getNewStatus(), change.getResource());
            }
        }
        // Add session resource impacting status after semantic ones.
        impactingNewStatuses.putAll(representationResourcesNewStatuses);
        return impactingNewStatuses;
    }

    private Iterable<Resource> getAllSessionResources() {
        return Iterables.concat(session.getSemanticResources(), session.getAllSessionResources(), session.getControlledResources());
    }

    private Iterable<Resource> getAllSemanticResources() {
        return Iterables.concat(session.getSemanticResources(), session.getControlledResources());
    }

}
