/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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
package org.eclipse.sirius.business.api.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Class to select or deselect Viewpoints for a given session.
 * 
 * @author cedric
 */
public class ViewpointSelector {

    private final Session session;

    /**
     * Create a new ViewpointSelector for a session.
     * 
     * @param session
     *            the session to update when activating or deactivating
     *            viewpoints.
     */
    public ViewpointSelector(Session session) {
        this.session = Objects.requireNonNull(session);
    }

    /**
     * Select a {@link Viewpoint} in the session. The Session internal state
     * will be updated so that DRepresentation and RepresentationDescription
     * matching this Viewpoint will be available.
     * 
     * @param viewpoint
     *            the {@link Viewpoint} to activate
     * @param createNewRepresentations
     *            true to create representations which are specified as being
     *            automatically initialized with a viewpoint initialization.
     *            False otherwise.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression
     */
    public void selectViewpoint(Viewpoint viewpoint, boolean createNewRepresentations, IProgressMonitor monitor) {
        // Get the corresponding viewpoint in the resourceSet of the
        // editingDomain
        final Viewpoint editingDomainViewpoint = SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint);
        boolean newView = false;
        if (editingDomainViewpoint != null && !SiriusResourceHelper.isViewExistForSirius(session, editingDomainViewpoint)) {
            if (!session.getSemanticResources().isEmpty()) {
                /* get all the roots */
                final List<EObject> roots = new ArrayList<>();
                for (final Resource resource : session.getSemanticResources()) {
                    if (resource != null) {
                        roots.addAll(resource.getContents());
                    }
                }
                /*
                 * Create view which try to init representations for each root
                 */
                session.createView(editingDomainViewpoint, roots, createNewRepresentations, new SubProgressMonitor(monitor, 1));
                DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, true);
                newView = true;
            }
        }
        // Check if there is wrong view in the session
        for (final DView view : session.getOwnedViews()) {
            if (view.getViewpoint() == null || view.getViewpoint().eResource() == null) {
                /*
                 * There is a problem with this viewpoint (case of odesign being
                 * renamed) so we remove it from the session
                 */
                session.removeSelectedView(view, new SubProgressMonitor(monitor, 1));
                DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, false);
            }
        }
        if (!newView) {
            // Add the view to the selected list
            for (final DView view : session.getOwnedViews()) {
                if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), viewpoint)) {
                    if (!session.getSelectedViewpoints(false).contains(view.getViewpoint())) {
                        session.addSelectedView(view, new SubProgressMonitor(monitor, 1));
                        DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, true);
                    }
                }
            }
        }
    }

    /**
     * Deselect a {@link Viewpoint} in the session. The Session internal state
     * will be updated so that DRepresentation and RepresentationDescription
     * matching this Viewpoint will not be available anymore.
     * 
     * @param deselectedViewpoint
     *            the {@link Viewpoint} to deactivate
     * @param monitor
     *            a {@link IProgressMonitor} to show progression
     */
    public void deselectViewpoint(Viewpoint deselectedViewpoint, IProgressMonitor monitor) {
        for (final DView view : session.getSelectedViews()) {
            if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), deselectedViewpoint)) {
                session.removeSelectedView(view, new SubProgressMonitor(monitor, 1));
                final Viewpoint editingDomainViewpoint = SiriusResourceHelper.getCorrespondingViewpoint(session, deselectedViewpoint);
                DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, false);
            }
        }
    }

}
