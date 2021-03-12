/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.resource.MigrationUtil;
import org.eclipse.sirius.business.internal.session.danalysis.SessionLazyCrossReferencer;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.MarkerUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Default implementation for a session manager.
 *
 * @author cbrun
 *
 */
public class SessionManagerImpl extends SessionManagerEObjectImpl implements SessionManager {

    /**
     * Listeners provide by the extension point org.eclipse.sirius.SessionManagerListener.
     */
    private Set<SessionManagerListener> extensionPointListeners;

    /**
     * Listeners added programmatically using SessionManager.addSessionsListener.
     */
    private Set<SessionManagerListener> programmaticListeners = new LinkedHashSet<>();

    private Set<Viewpoint> selectedViewpoints = new HashSet<Viewpoint>();

    private final Map<Session, SessionListener> sessionsToListeners = new HashMap<>();

    private SiriusOperationHistoryListener siriusOperationHistoryListener = new SiriusOperationHistoryListener();

    /**
     * Default initialization of a {@link SessionManagerImpl}.
     *
     * @return a new instance of {@link SessionManager}.
     */
    public static SessionManagerImpl init() {
        return new SessionManagerImpl();
    }

    @Override
    public void addSessionsListener(final SessionManagerListener listener) {
        programmaticListeners.add(listener);
    }

    @Override
    public Collection<Session> getSessions() {
        return Collections.unmodifiableCollection(doGetSessions());
    }

    private Collection<? extends Session> doGetSessions() {
        return sessionsToListeners.keySet();
    }

    @Override
    public void removeSessionsListener(final SessionManagerListener listener) {
        programmaticListeners.remove(listener);
    }

    @Override
    public void add(final Session newSession) {
        Assert.isNotNull(newSession, Messages.SessionManagerImpl_cantAddNullSessionErrorMsg);
        if (!doGetSessions().contains(newSession)) {
            if (newSession instanceof DAnalysisSessionEObject) {
                getOwnedSessions().add((DAnalysisSessionEObject) newSession);
            }
            final SessionListener sessListener = new SessionListener() {

                @Override
                public void notify(final int changeKind) {
                    if (changeKind == SessionListener.SELECTED_VIEWS_CHANGE_KIND) {
                        fireVPSelectionDeselectionEvents();
                    }
                    notifyUpdatedSession(newSession, changeKind);
                }

            };
            newSession.addListener(sessListener);
            sessionsToListeners.put(newSession, sessListener);

            if (sessionsToListeners.size() == 1) {
                // Add the Sirius operation history listener (because this is the first session added)
                OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(siriusOperationHistoryListener);
            }

            /*
             * Concurrent modification safe iterator => useful if a listener want to remove from listeners list
             */
            final Set<SessionManagerListener> listenersToIterate = Sets.newLinkedHashSet(getAllListeners());
            for (final SessionManagerListener listener : listenersToIterate) {
                listener.notifyAddSession(newSession);
            }
            this.fireVPSelectionDeselectionEvents();
        }
    }

    private void notifyUpdatedSession(final Session newSession, final int changeKind) {
        for (SessionManagerListener listener : Iterables.filter(Lists.newArrayList(getAllListeners()), SessionManagerListener.class)) {
            listener.notify(newSession, changeKind);
        }

    }

    @Override
    public void remove(final Session removedSession) {
        if (doGetSessions().contains(removedSession)) {
            getOwnedSessions().remove(removedSession);
            final SessionListener sessListener = sessionsToListeners.get(removedSession);
            if (sessListener != null) {
                removedSession.removeListener(sessListener);
            }
            sessionsToListeners.remove(removedSession);

            // Use a copy to avoid ConcurrentModificationException if some
            // listeners remove it from this list during the call of
            // notifyRemoveSession.
            final Set<SessionManagerListener> listenersToIterate = Sets.newLinkedHashSet(getAllListeners());
            for (final SessionManagerListener listener : listenersToIterate) {
                listener.notifyRemoveSession(removedSession);
            }

            this.fireVPSelectionDeselectionEvents();

            /*
             * no more session, we should dispose all the model accessor registered
             */
            if (sessionsToListeners.isEmpty()) {
                SiriusPlugin.getDefault().getModelAccessorRegistry().dispose();
                // Remove the Sirius operation history listener (because the last session has been removed)
                OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(siriusOperationHistoryListener);
            }
        }

    }

    @Override
    public Session getSession(final EObject any) {
        Session found = null;
        if (any instanceof Resource) {
            found = getSession((Resource) any);
        } else if (any != null) {
            // Fast path: try to leverage the SessionLazyCrossReferencer first if present
            Optional<Adapter> slcr = any.eAdapters().stream().filter(SessionLazyCrossReferencer.class::isInstance).findFirst();
            if (slcr.isPresent()) {
                found = ((SessionLazyCrossReferencer) slcr.get()).getSession();
            }
            // Handle explicit SessionTransientAttachment
            if (found == null) {
                Optional<SessionTransientAttachment> attachment = SessionTransientAttachment.of(any);
                if (attachment.isPresent()) {
                    found = attachment.get().getSession();
                }
            }
            // Slow path: walk up to the Resource and call getSession(Resource) which will search in all currently
            // opened sessions for that resource.
            if (found == null) {
                try {
                    EObject root = EcoreUtil.getRootContainer(any);
                    Resource res = root != null ? root.eResource() : null;
                    if (res != null) {
                        found = getSession(res);
                    }
                    if (found == null) {
                        Resource resource = any.eResource();
                        if (resource != null) {
                            found = getSession(resource);
                        }
                    }
                } catch (IllegalStateException e) {
                    // An issue has been encountered while connecting to remote CDO server
                    if (SiriusPlugin.getDefault().isDebugging()) {
                        SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, Messages.SessionManagerImpl_remoteServerConnectionErrorMsg));
                    }
                }
            }
        }
        return found;
    }

    @Override
    public Session getSession(final Resource semanticResource) {
        Session found = null;
        Optional<SessionTransientAttachment> attachment = SessionTransientAttachment.of(semanticResource);

        if (attachment.isPresent()) {
            found = attachment.get().getSession();
        }
        if (found == null) {
            for (Iterator<? extends Session> sessionsIter = doGetSessions().iterator(); sessionsIter.hasNext() && found == null; /* */) {
                Session sess = sessionsIter.next();
                Collection<Resource> semanticResources = new ArrayList<Resource>(sess.getSemanticResources());
                if (semanticResources.contains(semanticResource)) {
                    found = sess;
                } else if (sess instanceof DAnalysisSessionEObject && ((DAnalysisSessionEObject) sess).getControlledResources().contains(semanticResource)) {
                    found = sess;
                }
            }
        }
        return found;
    }

    @Override
    public Session getSession(URI sessionModelURI, IProgressMonitor monitor) {
        Option<IResource> optionalResource = new URIQuery(sessionModelURI).getCorrespondingResource();
        if (optionalResource.some()) {
            MarkerUtil.removeMarkerFor(optionalResource.get(), MarkerRuntimeLogger.MARKER_TYPE);
        }
        Session session = lookForAlreadyLoadedSession(sessionModelURI);
        if (session == null) {
            try {
                session = SessionFactory.INSTANCE.createSession(sessionModelURI, monitor);
            } catch (CoreException e) {
                if (optionalResource.some()) {
                    MarkerUtil.addMarkerFor(optionalResource.get(),
                            MessageFormat.format(Messages.SessionManagerImpl_representationsFileLoadingSeeErrorLogMsg, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()),
                            IMarker.SEVERITY_ERROR, MarkerRuntimeLogger.MARKER_TYPE);
                }
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        }
        return session;
    }

    @Override
    public Session getExistingSession(URI sessionResourceURI) {
        return lookForAlreadyLoadedSession(sessionResourceURI);
    }

    private Session lookForAlreadyLoadedSession(URI sessionModelURI) {
        Session alreadyLoadedSession = null;
        for (Session loadedSession : getSessions()) {
            if (loadedSession.getSessionResource().getURI().equals(sessionModelURI)) {
                alreadyLoadedSession = loadedSession;
            }
        }
        return alreadyLoadedSession;
    }

    @Override
    public void notifyRepresentationCreated(final Session session) {
        if (doGetSessions().contains(session)) {
            notifyUpdatedSession(session, SessionListener.REPRESENTATION_CHANGE);
        }
    }

    @Override
    public void notifyRepresentationDeleted(final Session session) {
        if (doGetSessions().contains(session)) {
            notifyUpdatedSession(session, SessionListener.REPRESENTATION_CHANGE);
        }
    }

    @Override
    public void notifyRepresentationRenamed(Session session) {
        if (doGetSessions().contains(session)) {
            notifyUpdatedSession(session, SessionListener.REPRESENTATION_CHANGE);
        }
    }

    /**
     * Fires viewpoints selection deselection events.
     *
     */
    private void fireVPSelectionDeselectionEvents() {
        final Set<Viewpoint> newSelectedViewpoints = this.collectSelectedViewpoints();

        //
        // Selecting.
        final Set<Viewpoint> selectingViewpoints = new HashSet<Viewpoint>(newSelectedViewpoints);
        selectingViewpoints.removeAll(this.selectedViewpoints);
        for (final Viewpoint viewpoint : selectingViewpoints) {
            fireSelectedViewpointEvent(viewpoint);
        }

        //
        // Deselecting.
        final Set<Viewpoint> deselectingViewpoints = new HashSet<Viewpoint>(this.selectedViewpoints);
        deselectingViewpoints.removeAll(newSelectedViewpoints);
        for (final Viewpoint viewpoint : deselectingViewpoints) {
            fireDeselectedViewpointEvent(viewpoint);
        }

        this.selectedViewpoints = newSelectedViewpoints;

    }

    /**
     * Collects all selected viewpoints.
     *
     * @return all selected viewpoints.
     */
    private Set<Viewpoint> collectSelectedViewpoints() {
        final Set<Viewpoint> result = new HashSet<Viewpoint>();
        for (final Session session : this.doGetSessions()) {
            result.addAll(session.getSelectedViewpoints(false));
        }
        return result;
    }

    private void fireSelectedViewpointEvent(final Viewpoint viewpoint) {
        for (final SessionManagerListener listener : getAllListeners()) {
            listener.viewpointSelected(viewpoint);
        }
    }

    private void fireDeselectedViewpointEvent(final Viewpoint viewpoint) {
        for (final SessionManagerListener listener : getAllListeners()) {
            listener.viewpointDeselected(viewpoint);
        }
    }

    /**
     * @return an iterable over the listeners provide by the extension point org.eclipse.sirius.SessionManagerListener
     *         and the listeners added programmatically.
     */
    private synchronized Iterable<SessionManagerListener> getAllListeners() {
        return Iterables.concat(getExtensionPointListeners(), programmaticListeners);
    }

    /**
     * Get the list of listeners provide by the extension point org.eclipse.sirius.SessionManagerListener2.
     *
     * @return the listeners provide by the extension point org.eclipse.sirius.SessionManagerListener2
     */
    private synchronized Set<SessionManagerListener> getExtensionPointListeners() {
        if (extensionPointListeners == null) {
            extensionPointListeners = new LinkedHashSet<>();
            extensionPointListeners.addAll(EclipseUtil.getExtensionPlugins(SessionManagerListener.class, SessionManagerListener.ID, SessionManagerListener.CLASS_ATTRIBUTE));
        }
        return extensionPointListeners;
    }

    @Override
    public Session openSession(URI sessionResourceURI, IProgressMonitor monitor, UICallBack uiCallback) {
        return openSession(sessionResourceURI, monitor, uiCallback, false);
    }

    @Override
    public Session openSession(URI sessionResourceURI, IProgressMonitor monitor, UICallBack uiCallback, boolean isDirectUserActionLoading) {
        SubMonitor subMonitor = SubMonitor.convert(monitor, 100);

        Session session = this.getSession(sessionResourceURI, subMonitor.newChild(30));
        if (session != null && !session.isOpen()) {
            try {
                session.open(subMonitor.newChild(70));
            } catch (AirdResourceVersionMismatchException e) {
                session = null;
                if (uiCallback != null && uiCallback.askSessionReopeningWithResourceVersionMismatch(e)) {
                    try {
                        MigrationUtil.ignoreVersionMismatch = true;
                        session = this.getSession(sessionResourceURI, new NullProgressMonitor());
                        session.open(new NullProgressMonitor());
                    } finally {
                        MigrationUtil.ignoreVersionMismatch = false;
                    }
                } else {
                    throw e;
                }
            }
        }
        for (Resource resource : session.getAllSessionResources()) {
            if (resource instanceof XMLResource) {
                ((XMLResource) resource).getDefaultLoadOptions().put(AbstractSiriusMigrationService.OPTION_RESOURCE_NON_BATCH_MIGRATION, isDirectUserActionLoading);
            }
        }
        if (uiCallback != null) {
            uiCallback.askUserAndSaveMigratedSession(session);
        }
        subMonitor.done();

        return session;
    }
}
