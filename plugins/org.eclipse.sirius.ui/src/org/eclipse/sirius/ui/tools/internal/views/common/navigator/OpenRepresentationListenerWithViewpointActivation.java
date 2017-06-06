/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationsAction;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A double click listener opening the representation from {@link RepresentationItemImpl} selection in a Sirius Modeler.
 * If corresponding viewpoint is not activated, then it will be before opening the modeler.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class OpenRepresentationListenerWithViewpointActivation extends OpenRepresentationListener {

    private Session session;

    /**
     * Constructor.
     * 
     * @param theSession
     *            the session used for viewpoint activation.
     */
    public OpenRepresentationListenerWithViewpointActivation(Session theSession) {
        this.session = theSession;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        if (event != null && event.getSelection() instanceof IStructuredSelection) {
            List<?> selection = ((IStructuredSelection) event.getSelection()).toList();
            Iterable<DRepresentationDescriptor> repDescriptorToOpen = getRepresentationDescriptorsToOpen(selection);
            if (!Iterables.isEmpty(repDescriptorToOpen)) {
                activateViewpoints(selection);
                new OpenRepresentationsAction(Sets.newLinkedHashSet(repDescriptorToOpen)).run();
            }
        }
    }

    /**
     * Activate all viewpoints associated to the given selection if such elements exists and only if they are not
     * already activated.
     * 
     * @param selection
     *            the selection from which viewpoints to activate must be identified and activated.
     */
    private void activateViewpoints(List<?> selection) {
        List<RepresentationItemImpl> representationItemList = getRepresentationItems(selection);
        Set<Viewpoint> viewpointsToActivate = new HashSet<>();
        for (RepresentationItemImpl representationItemImpl : representationItemList) {
            Viewpoint parentViewpoint = ViewpointHelper.getViewpointInVSM(session,
                    new RepresentationDescriptionQuery(representationItemImpl.getDRepresentationDescriptor().getDescription()).getParentViewpoint());
            if (parentViewpoint != null) {
                boolean activateViewpoint = !ViewpointHelper.isViewpointEnabledInSession(session, parentViewpoint);
                if (activateViewpoint) {
                    viewpointsToActivate.add(parentViewpoint);
                }
            }
        }
        if (!viewpointsToActivate.isEmpty()) {
            ViewpointHelper.handleViewpointActivation(session, viewpointsToActivate, true, false);
        }
    }

    private List<RepresentationItemImpl> getRepresentationItems(List<?> selection) {
        List<RepresentationItemImpl> representationItemList = new ArrayList<>();
        for (final Object obj : selection) {
            if (obj instanceof RepresentationItemImpl) {
                representationItemList.add((RepresentationItemImpl) obj);
            }
        }
        return representationItemList;
    }
}
