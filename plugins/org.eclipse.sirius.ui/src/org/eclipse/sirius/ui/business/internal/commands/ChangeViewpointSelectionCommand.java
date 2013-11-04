/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.metamodel.helper.ComponentizationHelper;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection.Callback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Specific command to change viewpoints selection.
 * 
 * @author mporhel
 */
public class ChangeViewpointSelectionCommand extends RecordingCommand {

    private Session session;

    private final Callback callback;

    private final Set<Viewpoint> newSelectedViewpoints;

    private final Set<Viewpoint> newDeselectedViewpoints;

    private boolean createNewRepresentations;

    private IProgressMonitor monitor;

    /**
     * Specific command to change viewpoints selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current ViewpointSelection.Callback
     * @param newSelectedViewpoints
     *            the new selected viewpoints.
     * @param newDeselectedViewpoints
     *            the new deselected viewpoints.
     * @deprecated use the constructor with a {@link IProgressMonitor}
     */
    public ChangeViewpointSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedViewpoints, Set<Viewpoint> newDeselectedViewpoints) {
        this(session, callback, newSelectedViewpoints, newDeselectedViewpoints, new NullProgressMonitor());
    }

    /**
     * Specific command to change viewpoint selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current SiriusSelection.Callback
     * @param newSelectedViewpoints
     *            the new selected viewpoints.
     * @param newDeselectedViewpoints
     *            the new deselected viewpoints.
     * @param monitor
     *            a {@link IProgressMonitor}
     */
    public ChangeViewpointSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedViewpoints, Set<Viewpoint> newDeselectedViewpoints, IProgressMonitor monitor) {
        super(session.getTransactionalEditingDomain(), "Change viewpoints selection");
        this.callback = callback;
        this.newSelectedViewpoints = newSelectedViewpoints;
        this.newDeselectedViewpoints = newDeselectedViewpoints;
        this.session = session;
        this.monitor = monitor;
        this.createNewRepresentations = true;
    }

    /**
     * Specific command to change viewpoints selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current SiriusSelection.Callback
     * @param newSelectedViewpoints
     *            the new selected viewpoints.
     * @param newDeselectedViewpoints
     *            the new deselected viewpoints.
     * @param createNewRepresentations
     *            true to create new DRepresentation for
     *            RepresentationDescription having their initialization
     *            attribute at true for selected {@link Viewpoint}s.
     * @param monitor
     *            a {@link IProgressMonitor}
     */
    public ChangeViewpointSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedViewpoints, Set<Viewpoint> newDeselectedViewpoints, boolean createNewRepresentations,
            IProgressMonitor monitor) {
        this(session, callback, newSelectedViewpoints, newDeselectedViewpoints, monitor);
        this.createNewRepresentations = createNewRepresentations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (callback == null || session == null) {
            return;
        }
        try {
            int nbSiriussInChange = (newSelectedViewpoints != null ? newSelectedViewpoints.size() + 1 : 0) + (newDeselectedViewpoints != null ? newDeselectedViewpoints.size() : 0);
            monitor.beginTask("Apply new viewpoints selection...", nbSiriussInChange);
            if (newSelectedViewpoints != null) {
                List<Viewpoint> sorted = sortByDependencies(newSelectedViewpoints);
                monitor.worked(1);
                for (final Viewpoint viewpoint : sorted) {
                    monitor.subTask("Select viewpoint : " + new IdentifiedElementQuery(viewpoint).getLabel());
                    callback.selectViewpoint(viewpoint, session, createNewRepresentations, new SubProgressMonitor(monitor, 1));
                }
            }
            if (newDeselectedViewpoints != null) {
                for (final Viewpoint viewpoint : newDeselectedViewpoints) {
                    monitor.subTask("Deselect viewpoint : " + new IdentifiedElementQuery(viewpoint).getLabel());
                    callback.deselectViewpoint(viewpoint, session, new SubProgressMonitor(monitor, 1));
                }
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Sort the viewpoints in such a way that if Ext depends on Base, Base
     * appears before Ext in the resulting list.
     */
    private List<Viewpoint> sortByDependencies(Collection<Viewpoint> viewpoints) {
        // Code taken from
        // DAnalysisSessionImpl#getSelectedSiriussSpecificToGeneric, except
        // that we want the opposite order
        List<Viewpoint> orderedViewpoints = new ArrayList<Viewpoint>(viewpoints.size());
        for (Viewpoint viewpoint : viewpoints) {
            int insertPosition = orderedViewpoints.size();
            for (Viewpoint viewpoint2 : orderedViewpoints) {
                if (ComponentizationHelper.isExtendedBy(viewpoint, viewpoint2)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2);
                } else if (ComponentizationHelper.isExtendedBy(viewpoint2, viewpoint)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2) + 1;
                }
            }
            orderedViewpoints.add(insertPosition, viewpoint);
        }
        Collections.reverse(orderedViewpoints);
        return Collections.unmodifiableList(orderedViewpoints);
    }

}
