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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The callback for selection.
 * <p>
 * All methods must be executed in transactional mode.
 * </p>
 * 
 * @author mchauvin
 */
public class SiriusSelectionCallback implements SiriusSelection.Callback {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.viewpoint.SiriusSelection.Callback#deselectSirius(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    public void deselectSirius(final Viewpoint deselectedSirius, final Session session) {
        deselectSirius(deselectedSirius, session, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.viewpoint.SiriusSelection.Callback#selectSirius(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    public void selectSirius(final Viewpoint viewpoint, final Session session) {
        selectSirius(viewpoint, session, true, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void selectSirius(Viewpoint viewpoint, Session session, boolean createNewRepresentations) {
        selectSirius(viewpoint, session, createNewRepresentations, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void selectSirius(Viewpoint viewpoint, Session session, IProgressMonitor monitor) {
        selectSirius(viewpoint, session, true, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public void selectSirius(Viewpoint viewpoint, Session session, boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Sirius selection : " + viewpoint.getName(), 3);
            // Get the corresponding viewpoint in the resourceSet of the
            // editingDomain
            final Viewpoint editingDomainSirius = SiriusResourceHelper.getCorrespondingSirius(session, viewpoint);
            boolean newView = false;
            if (editingDomainSirius != null && !SiriusResourceHelper.isViewExistForSirius(session, editingDomainSirius)) {
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
                    session.createView(editingDomainSirius, roots, createNewRepresentations, new SubProgressMonitor(monitor, 1));
                    DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainSirius, true);
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
                    DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainSirius, false);
                }
            }
            if (!newView) {
                // Add the view to the selected list
                for (final DView view : session.getOwnedViews()) {
                    if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), viewpoint)) {
                        if (!session.getSelectedSiriuss(false).contains(view.getViewpoint())) {
                            session.addSelectedView(view, new SubProgressMonitor(monitor, 1));
                            DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainSirius, true);
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
    public void deselectSirius(Viewpoint deselectedSirius, Session session, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Sirius deselection : " + deselectedSirius.getName(), 1);
            for (final DView view : session.getSelectedViews()) {
                if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), deselectedSirius)) {
                    session.removeSelectedView(view, new SubProgressMonitor(monitor, 1));
                    final Viewpoint editingDomainSirius = SiriusResourceHelper.getCorrespondingSirius(session, deselectedSirius);
                    DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, editingDomainSirius, false);
                }
            }
        } finally {
            monitor.done();
        }
    }
}
