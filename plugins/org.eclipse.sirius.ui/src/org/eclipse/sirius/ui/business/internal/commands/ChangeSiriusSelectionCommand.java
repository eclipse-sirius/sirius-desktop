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
import org.eclipse.sirius.ui.business.api.viewpoint.SiriusSelection.Callback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Specific command to change viewpoint selection.
 * 
 * @author mporhel
 */
public class ChangeSiriusSelectionCommand extends RecordingCommand {

    private Session session;

    private final Callback callback;

    private final Set<Viewpoint> newSelectedSiriuss;

    private final Set<Viewpoint> newDeselectedSiriuss;

    private boolean createNewRepresentations;

    private IProgressMonitor monitor;

    /**
     * Specific command to change viewpoint selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current SiriusSelection.Callback
     * @param newSelectedSiriuss
     *            the new selected viewpoints.
     * @param newDeselectedSiriuss
     *            the new deselected viewpoints.
     * @deprecated use the constructor with a {@link IProgressMonitor}
     */
    public ChangeSiriusSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedSiriuss, Set<Viewpoint> newDeselectedSiriuss) {
        this(session, callback, newSelectedSiriuss, newDeselectedSiriuss, new NullProgressMonitor());
    }

    /**
     * Specific command to change viewpoint selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current SiriusSelection.Callback
     * @param newSelectedSiriuss
     *            the new selected viewpoints.
     * @param newDeselectedSiriuss
     *            the new deselected viewpoints.
     * @param monitor
     *            a {@link IProgressMonitor}
     */
    public ChangeSiriusSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedSiriuss, Set<Viewpoint> newDeselectedSiriuss, IProgressMonitor monitor) {
        super(session.getTransactionalEditingDomain(), "Change viewpoint selection");
        this.callback = callback;
        this.newSelectedSiriuss = newSelectedSiriuss;
        this.newDeselectedSiriuss = newDeselectedSiriuss;
        this.session = session;
        this.monitor = monitor;
        this.createNewRepresentations = true;
    }

    /**
     * Specific command to change viewpoint selection.
     * 
     * @param session
     *            the current session.
     * @param callback
     *            the current SiriusSelection.Callback
     * @param newSelectedSiriuss
     *            the new selected viewpoints.
     * @param newDeselectedSiriuss
     *            the new deselected viewpoints.
     * @param createNewRepresentations
     *            true to create new DRepresentation for
     *            RepresentationDescription having their initialization
     *            attribute at true for selected {@link Viewpoint}s.
     * @param monitor
     *            a {@link IProgressMonitor}
     */
    public ChangeSiriusSelectionCommand(Session session, Callback callback, Set<Viewpoint> newSelectedSiriuss, Set<Viewpoint> newDeselectedSiriuss, boolean createNewRepresentations,
            IProgressMonitor monitor) {
        this(session, callback, newSelectedSiriuss, newDeselectedSiriuss, monitor);
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
            int nbSiriussInChange = (newSelectedSiriuss != null ? newSelectedSiriuss.size() + 1 : 0) + (newDeselectedSiriuss != null ? newDeselectedSiriuss.size() : 0);
            monitor.beginTask("Apply new viewpoints selection...", nbSiriussInChange);
            if (newSelectedSiriuss != null) {
                List<Viewpoint> sorted = sortByDependencies(newSelectedSiriuss);
                monitor.worked(1);
                for (final Viewpoint viewpoint : sorted) {
                    monitor.subTask("Select viewpoint : " + new IdentifiedElementQuery(viewpoint).getLabel());
                    callback.selectSirius(viewpoint, session, createNewRepresentations, new SubProgressMonitor(monitor, 1));
                }
            }
            if (newDeselectedSiriuss != null) {
                for (final Viewpoint viewpoint : newDeselectedSiriuss) {
                    monitor.subTask("Deselect viewpoint : " + new IdentifiedElementQuery(viewpoint).getLabel());
                    callback.deselectSirius(viewpoint, session, new SubProgressMonitor(monitor, 1));
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
        List<Viewpoint> orderedSiriuss = new ArrayList<Viewpoint>(viewpoints.size());
        for (Viewpoint viewpoint : viewpoints) {
            int insertPosition = orderedSiriuss.size();
            for (Viewpoint viewpoint2 : orderedSiriuss) {
                if (ComponentizationHelper.isExtendedBy(viewpoint, viewpoint2)) {
                    insertPosition = orderedSiriuss.indexOf(viewpoint2);
                } else if (ComponentizationHelper.isExtendedBy(viewpoint2, viewpoint)) {
                    insertPosition = orderedSiriuss.indexOf(viewpoint2) + 1;
                }
            }
            orderedSiriuss.add(insertPosition, viewpoint);
        }
        Collections.reverse(orderedSiriuss);
        return Collections.unmodifiableList(orderedSiriuss);
    }

}
