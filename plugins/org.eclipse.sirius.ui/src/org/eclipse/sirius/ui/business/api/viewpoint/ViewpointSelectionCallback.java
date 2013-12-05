/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.viewpoint;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;

/**
 * The callback for selection.
 * <p>
 * All methods must be executed in transactional mode.
 * </p>
 * 
 * @author mchauvin
 */
public class ViewpointSelectionCallback implements ViewpointSelection.Callback {

    /**
     * {@inheritDoc}
     */
    public void selectViewpoint(Viewpoint viewpoint, Session session, IProgressMonitor monitor) {
        selectViewpoint(viewpoint, session, true, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Viewpoint selection : " + viewpoint.getName(), 3);
            // Get the corresponding viewpoint in the resourceSet of the
            // editingDomain
            final Viewpoint editingDomainViewpoint = SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint);
            boolean newView = false;
            if (editingDomainViewpoint != null && !SiriusResourceHelper.isViewExistForSirius(session, editingDomainViewpoint)) {
                if (!session.getSemanticResources().isEmpty()) {
                    /* get all the roots */
                    final List<EObject> roots = Lists.newArrayList();
                    for (final Resource resource : session.getSemanticResources()) {
                        if (resource != null) {
                            roots.addAll(resource.getContents());
                        }
                    }
                    /*
                     * create view which try to init representations for each
                     * root
                     */
                    session.createView(editingDomainViewpoint, roots, createNewRepresentations, new SubProgressMonitor(monitor, 1));
                    DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, true);
                    newView = true;
                }
            }
            // Check if there is wrong view in the session
            for (final DView view : session.getOwnedViews()) {
                if (view.getViewpoint() == null || view.getViewpoint().eResource() == null) {
                    // There is a problem with this viewpoint (case of odesign
                    // being
                    // renamed) so we remove it from
                    // the session
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
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deselectViewpoint(Viewpoint deselectedViewpoint, Session session, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Viewpoint deselection : " + deselectedViewpoint.getName(), 1);
            for (final DView view : session.getSelectedViews()) {
                if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), deselectedViewpoint)) {
                    session.removeSelectedView(view, new SubProgressMonitor(monitor, 1));
                    final Viewpoint editingDomainViewpoint = SiriusResourceHelper.getCorrespondingViewpoint(session, deselectedViewpoint);
                    DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainViewpoint, false);
                }
            }
        } finally {
            monitor.done();
        }
    }
}
