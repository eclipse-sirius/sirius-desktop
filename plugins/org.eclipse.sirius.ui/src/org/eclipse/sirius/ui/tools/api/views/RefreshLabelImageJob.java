/*******************************************************************************
 * Copyright (c) 2015, 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.api.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.UIJob;

import com.google.common.collect.Iterables;
/**
 * A {@link UIJob} to refresh the image of the label in a {@link CommonViewer}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshLabelImageJob extends UIJob {

    /** The family of this job. */
    public static final String FAMILY = RefreshLabelImageJob.class.getName();

    /**
     * The delay before really launching the job. Avoid to launch several job consecutively instead of one.
     */
    public static final long REFRESH_JOB_DELAY = 200;

    /**
     * The properties of the refresh performed on the tree viewer: we only update image.
     */
    private final String[] refreshProperties = new String[] { IBasicPropertyConstants.P_IMAGE };

    private Collection<Object> elementsToRefresh = Collections.emptySet();

    private TreeViewer commonViewer;

    /**
     * Default constructor.
     *
     * @param commonViewer
     *            the viewer to refresh
     * @param elementsToRefresh
     *            the element for which refresh TreeItem in the Model explorer
     */
    public RefreshLabelImageJob(TreeViewer commonViewer, Collection<Object> elementsToRefresh) {
        super(Messages.RefreshLabelImageJob_name);
        this.commonViewer = commonViewer;
        this.elementsToRefresh = elementsToRefresh;
    }

    /**
     * Refreshes the lock decorations of the model explorer view.
     *
     * {@inheritDoc}
     */
    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
        if (commonViewer != null && commonViewer.getTree() != null && !commonViewer.getTree().isDisposed()) {
            if (elementsToRefresh == null || elementsToRefresh.isEmpty()) {
                commonViewer.refresh();
            } else {
                List<Object> repDescs = elementsToRefresh.stream().filter(DRepresentationDescriptor.class::isInstance).collect(Collectors.toList());
                Object[] expandedElements = commonViewer.getExpandedElements();
                // RepresentationItemImpl encapsulate the DRepresentationDescriptor. It's label and image are based on
                // its descriptor status. So any change to the descriptor triggers the refresh of any
                // RepresentationItemImpl encapsulating it.
                if (!repDescs.isEmpty() && expandedElements != null) {
                    List<Object> expandedElementsList = new ArrayList<Object>(Arrays.asList(expandedElements));
                    for (RepresentationDescriptionItem item : expandedElementsList.stream().filter(RepresentationDescriptionItem.class::isInstance).map(RepresentationDescriptionItem.class::cast)
                            .collect(Collectors.toList())) {
                        for (RepresentationItemImpl repItem : Iterables.filter(item.getChildren(), RepresentationItemImpl.class)) {
                            if (repDescs.contains(repItem.getWrappedObject())) {
                                elementsToRefresh.add(repItem);
                            }
                        }
                    }
                }
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
