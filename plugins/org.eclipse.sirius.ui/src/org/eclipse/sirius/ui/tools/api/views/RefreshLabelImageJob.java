/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
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
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.UIJob;

/**
 * A {@link UIJob} to refresh the image of the label in a
 * {@link CommonNavigator}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshLabelImageJob extends UIJob {

    /** The family of this job. */
    public static final String FAMILY = RefreshLabelImageJob.class.getName();

    /**
     * The delay before really launching the job. Avoid to launch several job
     * consecutively instead of one.
     */
    public static final long REFRESH_JOB_DELAY = 200;

    /**
     * The properties of the refresh performed on the Model Explorer View: we
     * only update image.
     */
    private final String[] refreshProperties = new String[] { IBasicPropertyConstants.P_IMAGE };

    private Collection<Object> elementsToRefresh = Collections.emptySet();

    private CommonNavigator view;

    /**
     * Default constructor.
     *
     * @param view
     *            the view to refresh
     * @param elementsToRefresh
     *            the element for which refresh TreeItem in the Model explorer
     */
    public RefreshLabelImageJob(CommonNavigator view, Collection<Object> elementsToRefresh) {
        super(Messages.RefreshLabelImageJob_name);
        this.view = view;
        this.elementsToRefresh = elementsToRefresh;
    }

    /**
     * Refreshes the lock decorations of the model explorer view.
     *
     * {@inheritDoc}
     */
    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
        if (view.getCommonViewer() != null) {
            if (elementsToRefresh == null || elementsToRefresh.isEmpty()) {
                view.getCommonViewer().refresh();
            } else {
                CommonViewer commonViewer = view.getCommonViewer();
                commonViewer.update(elementsToRefresh.toArray(), refreshProperties);
            }
        }

        if (elementsToRefresh != null) {
            elementsToRefresh.clear();
        }
        return Status.OK_STATUS;
    }

    /**
     * Returns the elements that this job is in charge of refreshing.
     *
     * @return the elements that this job is in charge of refreshing
     */
    public Collection<?> getElementsToRefresh() {
        return elementsToRefresh;
    }

    @Override
    public boolean belongsTo(Object family) {
        return RefreshLabelImageJob.FAMILY.equals(family);
    }
}
