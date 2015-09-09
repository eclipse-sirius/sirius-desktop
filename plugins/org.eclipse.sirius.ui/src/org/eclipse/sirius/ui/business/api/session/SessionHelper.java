/*******************************************************************************
 * Copyright (c) 2008, 2009, 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.dialogs.RepresentationsSelectionDialog;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Maps;

/**
 * Utility class for clients wanting to start their own sessions.
 *
 * @author cbrun
 *
 */
public final class SessionHelper {

    private SessionHelper() {
    }

    /**
     * Open existing representations whose description has the
     * <code>showOnStartup</code> flag.
     * <p>
     * If there is only one such representation, it is opened automatically. If
     * there are several, a dialog box opens allowing the user to select which
     * ones to open (including none).
     * <p>
     * No new representations are created. Only the existing ones are
     * candidates.
     *
     * @param session
     *            the session for which to open the startup representations.
     * @param monitor
     *            the monitor that displays the status.
     * @since 0.9.0
     */
    public static void openStartupRepresentations(final Session session, final IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.SessionHelper_startupRepresentationOpeningTask, 1);

            Collection<DRepresentation> startupCandidates = SessionHelper.findAllStartupCandidates(session);
            String origin = null;
            if (session.getSessionResource().getURI().segmentCount() > 1) {
                String projectName = session.getSessionResource().getURI().segment(1);
                if (projectName != null) {
                    origin = MessageFormat.format(Messages.SessionHelper_origin, projectName);
                }
            }
            final Collection<DRepresentation> selection = SessionHelper.selectRepresentationsToOpen(origin, startupCandidates);
            if (!selection.isEmpty()) {
                monitor.subTask(Messages.SessionHelper_openStartupRepresentationsTask);
            }
            for (final DRepresentation repr : selection) {
                DialectUIManager.INSTANCE.openEditor(session, repr, new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Finds all the existing representations in the session which are marked as
     * <code>showOnStartup</code> and hence candidates for being automatically
     * opened.
     *
     * @param session
     *            the session in which to look for representations.
     * @return all the existing representations in the session which are marked
     *         as <code>showOnStartup</code>, in no particular order. May be
     *         empty, but not <code>null</code>.
     */
    public static Collection<DRepresentation> findAllStartupCandidates(final Session session) {
        Collection<DRepresentation> candidates = new ArrayList<DRepresentation>();
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

        if (!selectedViewpoints.isEmpty()) {
            Map<RepresentationDescription, Boolean> alreadyCheckedDescriptions = Maps.newHashMap();
            Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
            for (DRepresentation repr : allRepresentations) {
                RepresentationDescription description = DialectManager.INSTANCE.getDescription(repr);
                if (description != null && SessionHelper.checkStartupDescInSelectedVps(description, alreadyCheckedDescriptions, selectedViewpoints)) {
                    candidates.add(repr);
                }
            }
        }
        return candidates;
    }

    private static boolean checkStartupDescInSelectedVps(RepresentationDescription description, Map<RepresentationDescription, Boolean> checkedDescriptions, Collection<Viewpoint> selectedVps) {
        if (!checkedDescriptions.containsKey(description)) {
            boolean candidate = false;
            if (description.isShowOnStartup()) {
                Viewpoint parentViewpoint = new RepresentationDescriptionQuery(description).getParentViewpoint();
                candidate = parentViewpoint == null ? false : selectedVps.contains(parentViewpoint);
            }
            checkedDescriptions.put(description, candidate);
        }
        return checkedDescriptions.get(description);
    }

    /**
     * Select which of the specified representations should really be
     * automatically opened. If there is only one candidate, it is automatically
     * selected. If there are more than one, the user is asked to select the
     * ones he wants (including none) through a modal dialog box.
     *
     * @param representationsOrigin
     *            A String representing the origin of the candidates (for
     *            example "from projectName", or
     *            "from representations file fileName"), or null if no precision
     *            is needed. This information is displayed in the message of the
     *            dialog, at the end of the message
     *            "Select the startup representations to open"
     * @param candidates
     *            the candidate representation to select from.
     * @return the subset of candidates which are selected for opening.
     */
    public static Collection<DRepresentation> selectRepresentationsToOpen(final String representationsOrigin, final Collection<DRepresentation> candidates) {
        final Collection<DRepresentation> selection = new ArrayList<DRepresentation>();
        if (candidates.size() == 1) {
            selection.addAll(candidates);
        } else if (candidates.size() > 1) {
            EclipseUIUtil.displaySyncExec(new Runnable() {
                @Override
                public void run() {
                    final Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                    final RepresentationsSelectionDialog dlg = new RepresentationsSelectionDialog(shell, candidates);
                    dlg.create();
                    dlg.setTitle(Messages.SessionHelper_selectionDialogTitle);
                    String message = Messages.SessionHelper_selectionDialogMessage;
                    if (representationsOrigin != null) {
                        message = MessageFormat.format(Messages.SessionHelper_selectionDialogMessageWithOrigin, representationsOrigin);
                    }
                    dlg.setMessage(message);
                    if (dlg.open() == Window.OK) {
                        selection.addAll(dlg.getSelectedRepresentations());
                    }
                }
            });
        }
        return selection;
    }
}
