/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry.monitoring;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Monitors VSMs in the Eclipse workspace.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class WorkspaceMonitor extends AbstractViewpointResourceMonitor {
    /**
     * The workspace to monitor.
     */
    private final IWorkspace workspace;

    /**
     * The workspace event listener.
     */
    private final IResourceChangeListener workspaceChangeListener = new WorkspaceListener();

    /**
     * The handler used to check if the declared resources are of a supported
     * type.
     */
    private final ViewpointResourceHandler resourceHandler;

    /**
     * The visitor used for initial discovery of all the VSMs existing in the
     * workspace.
     */
    private final class VSMProxyVisitor implements IResourceProxyVisitor {
        Set<URI> detected = Sets.newLinkedHashSet();

        /**
         * {@inheritDoc}
         */
        public boolean visit(IResourceProxy proxy) throws CoreException {
            if (proxy.isAccessible()) {
                IResource res = proxy.requestResource();
                if (isVSM(res)) {
                    detected.add(URI.createPlatformResourceURI(res.getFullPath().toString(), true));
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * The listener notified of workspace changes.
     */
    private final class WorkspaceListener implements IResourceChangeListener {
        public void resourceChanged(IResourceChangeEvent event) {
            IResourceDelta delta = event.getDelta();
            if (delta != null) {
                try {
                    VSMDeltaVisitor visitor = new VSMDeltaVisitor();
                    delta.accept(visitor);
                    notifyResourcesEvents(visitor.removed, visitor.added, visitor.changed);
                } catch (CoreException e) {
                    reportCoreException(e);
                }
            }
        }
    }

    /**
     * The visitor used to detect VSM workspace changes.
     */
    private final class VSMDeltaVisitor implements IResourceDeltaVisitor {
        Set<URI> added = Sets.newLinkedHashSet();

        Set<URI> removed = Sets.newLinkedHashSet();

        Set<URI> changed = Sets.newLinkedHashSet();

        /**
         * {@inheritDoc}
         */
        public boolean visit(IResourceDelta delta) throws CoreException {
            IResource res = delta.getResource();
            if (isVSM(res)) {
                IPath fullPath = delta.getFullPath();
                URI uri = URI.createPlatformResourceURI(fullPath.toString(), true);
                switch (delta.getKind()) {
                case IResourceDelta.ADDED:
                    added.add(uri);
                    break;
                case IResourceDelta.REMOVED:
                    removed.add(uri);
                    break;
                case IResourceDelta.CHANGED:
                    // Ignore markers-only changes
                    if (delta.getMarkerDeltas().length == 0) {
                        changed.add(uri);
                    }
                    break;
                default:
                    // Ignore other delta kinds.
                    break;
                }
                return false;
            }
            return true;
        }
    }

    /**
     * Constructor.
     * 
     * @param workspace
     *            the workspace to monitor.
     * @param resourceHandler
     *            the handler used to check if the declared resources are of a
     *            supported type.
     */
    public WorkspaceMonitor(IWorkspace workspace, ViewpointResourceHandler resourceHandler) {
        this.workspace = Preconditions.checkNotNull(workspace);
        this.resourceHandler = Preconditions.checkNotNull(resourceHandler);
    }

    /**
     * {@inheritDoc}
     */
    public void start() {
        this.running = true;
        // NOTE Possible race condition here: we may lose change notifications
        // for URIs which change after they have been detected but before we
        // have registered the change listener.
        detectExistingVSMs();
        workspace.addResourceChangeListener(workspaceChangeListener, IResourceChangeEvent.POST_CHANGE);
    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
        workspace.removeResourceChangeListener(workspaceChangeListener);
        this.running = false;
    }

    private void detectExistingVSMs() {
        try {
            VSMProxyVisitor detector = new VSMProxyVisitor();
            workspace.getRoot().accept(detector, IResource.NONE);
            notifyResourcesEvents(Collections.<URI> emptySet(), detector.detected, Collections.<URI> emptySet());
        } catch (CoreException e) {
            reportCoreException(e);
        }
    }

    private void reportCoreException(CoreException e) {
        throw new RuntimeException(e);
    }

    private boolean isVSM(IResource res) {
        if (res.getType() == IResource.FILE) {
            URI uri = URI.createPlatformResourceURI(res.getFullPath().toString(), true);
            return resourceHandler.handles(uri);
        } else {
            return false;
        }
    }
}
