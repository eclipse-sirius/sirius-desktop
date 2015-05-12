/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.views;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.PermissionAuthoritySessionManagerListener;
import org.eclipse.ui.navigator.CommonNavigator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This class is an helper to refresh lock decoration on a
 * {@link CommonNavigator} according to the notifications send to
 * {@link IAuthorityListener}.<BR>
 * It adds an {@link IAuthorityListener} on each opened
 * {@link org.eclipse.sirius.business.api.session.Session} when calling
 * {@link #register(CommonNavigator)}. A
 * {@link org.eclipse.sirius.business.api.session.SessionManagerListener} is
 * also added for future Session. This {@link IAuthorityListener} refreshes the
 * {@link CommonNavigator} when notifications are received. This refresh is
 * called in a job to avoid deadlock.<BR>
 * The {@link IAuthorityListener} and the SessionManagerListener are removed
 * when calling {@link #unregister()}.<BR>
 * 
 * To use this class on your own {@link CommonNavigator}, create a new instance
 * of it. The call {@link #register(CommonNavigator)} during the
 * {@link CommonNavigator#init(org.eclipse.ui.IViewSite)} method and call
 * {@link #unregister()} during the {@link CommonNavigator#dispose()} method.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LockDecorationUpdater implements IAuthorityListener {

    /**
     * The refresh job used to refresh the CommonNavigator.
     */
    private RefreshLabelImageJob refreshJob;

    /**
     * The CommonNavigator to refresh.
     */
    private CommonNavigator commonNavigator;

    /**
     * The SessionManagerListener in charge of adding authorityListener on new
     * sessions and remove it for removed sessions. It is added to the listeners
     * list only during {@link #register(CommonNavigator)} method.
     */
    private PermissionAuthoritySessionManagerListener permissionAuthoritySessionManagerListener = new PermissionAuthoritySessionManagerListener();

    /**
     * Default constructor.
     */
    public LockDecorationUpdater() {
    }

    /**
     * Register the <code>commonNavigator</code> to be refreshed when
     * notifications are send to {@link IAuthorityListener}.
     * 
     * @param commonNavigatorToRefresh
     *            The {@link CommonNavigator} to refresh
     */
    public void register(CommonNavigator commonNavigatorToRefresh) {
        this.commonNavigator = commonNavigatorToRefresh;
        permissionAuthoritySessionManagerListener.register(this);
    }

    /**
     * Unregistrer the <code>commonNavigator</code>. It is no longer refreshed
     * when notifications are send to {@link IAuthorityListener}.
     */
    public void unregister() {
        permissionAuthoritySessionManagerListener.unregister();
        this.commonNavigator = null;
        this.refreshJob = null;
    }

    @Override
    public void notifyIsLocked(EObject instance) {
        launchRefreshModelExplorerViewJob(Lists.newArrayList(instance));
    }

    @Override
    public void notifyIsReleased(EObject instance) {
        launchRefreshModelExplorerViewJob(Lists.newArrayList(instance));
    }

    @Override
    public void notifyIsLocked(Collection<EObject> instances) {
        launchRefreshModelExplorerViewJob(instances);
    }

    @Override
    public void notifyIsReleased(Collection<EObject> instances) {
        launchRefreshModelExplorerViewJob(instances);
    }

    /**
     * Updates the decorations inside the Model Explorer View if exists for the
     * given lock/unlocked elements.
     * 
     * @param elements
     *            the lock/unlocked element to refresh
     */
    protected void launchRefreshModelExplorerViewJob(Collection<EObject> elements) {
        // Step 1 : collect new elements to refresh
        final Collection<Object> toRefresh = Sets.newLinkedHashSet();
        toRefresh.addAll(elements);

        // Step 2 : refresh the Model Explorer View inside a job (to avoid
        // potential deadlock)
        if (refreshJob != null) {
            toRefresh.addAll(refreshJob.getElementsToRefresh());
            refreshJob.cancel();
        }
        refreshJob = new RefreshLabelImageJob(commonNavigator, toRefresh);
        refreshJob.schedule(RefreshLabelImageJob.REFRESH_JOB_DELAY);
    }
}
