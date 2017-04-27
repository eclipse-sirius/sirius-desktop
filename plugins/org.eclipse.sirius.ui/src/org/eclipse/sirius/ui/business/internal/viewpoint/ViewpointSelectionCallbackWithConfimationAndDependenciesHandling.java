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
package org.eclipse.sirius.ui.business.internal.viewpoint;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallbackWithConfimation;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * This callback asks user confirmations in different situation:
 * 
 * In case of viewpoint activation, if additional viewpoints activation is needed by activated viewpoint missing
 * dependencies the user is asked to confirm the activations. If a viewpoint is missing a dependency that is not
 * registered and thus cannot be activated, an error message is shown to user.
 * 
 * In case of viewpoint deactivation, if additional viewpoints deactivation is needed by the deactivated viewpoint
 * because they depend on it, then the user is asked to confirm the deactivations.
 *
 * <p>
 * All methods must be executed in transactional mode.
 * </p>
 *
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ViewpointSelectionCallbackWithConfimationAndDependenciesHandling extends ViewpointSelectionCallbackWithConfimation {
    @Override
    public void deselectViewpoint(Viewpoint deactivatedViewpoint, Session session, Set<Viewpoint> allViewpointToDeactivate, IProgressMonitor monitor) {
        Set<Viewpoint> additionalViewpointToDeactivate = ViewpointHelper.getAdditionalViewpointsToDeactivate(deactivatedViewpoint, session).stream().filter(vp -> {
            return viewpointWillNotBeDeactivatedInSession(session, allViewpointToDeactivate, vp);
        }).collect(Collectors.toSet());
        boolean confirmDeactivation = additionalViewpointToDeactivate.isEmpty()
                || (!additionalViewpointToDeactivate.isEmpty() && userConfirmsDependenciesActivationStatusChange(deactivatedViewpoint, additionalViewpointToDeactivate, false));
        if (confirmDeactivation) {
            for (Viewpoint viewpointToDeactivate : additionalViewpointToDeactivate) {
                // Deactivate dependencies of the given viewpoint that are not already deactivated or will be.
                deselectViewpoint(viewpointToDeactivate, session, monitor);
            }
            super.deselectViewpoint(deactivatedViewpoint, session, monitor);
        }

    }

    /**
     * Return true if it the given viewpoint is currently not deactivated in session and if it does not belong to the
     * viewpoints that will be deactivated. False otherwise.
     * 
     * @param session
     *            the session used for testing the activation status of viewpoints.
     * @param allViewpointToDeactivate
     *            all the viewpoints that will be currently deactivated.
     * @return true if it the given viewpoint is currently not deactivated in session and if it does not belong to the
     *         viewpoints that will be deactivated. False otherwise.
     */
    private boolean viewpointWillNotBeDeactivatedInSession(Session session, Set<Viewpoint> allViewpointToDeactivate, Viewpoint vp) {
        boolean alreadyDeactivated = false;
        for (Viewpoint desactivatedViewpoint : allViewpointToDeactivate) {
            if (EqualityHelper.areEquals(vp, desactivatedViewpoint)) {
                alreadyDeactivated = true;
            }
        }
        boolean isActivatedInSession = false;
        for (Viewpoint activatedViewpoint : session.getSelectedViewpoints(false)) {
            if (EqualityHelper.areEquals(vp, activatedViewpoint)) {
                isActivatedInSession = true;
            }
        }
        alreadyDeactivated = alreadyDeactivated || !isActivatedInSession;
        return !alreadyDeactivated;
    }

    @Override
    public void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, Set<Viewpoint> allSelectedViewpoint, IProgressMonitor monitor) {
        Map<String, Viewpoint> viewpointDependencies = ViewpointHelper.getViewpointDependencies(ViewpointHelper.getAvailableViewpoints(session), allSelectedViewpoint, viewpoint);
        boolean allDependenciesAvailable = viewpointDependencies.isEmpty() || viewpointDependencies.values().stream().allMatch(vp -> vp != null);
        if (!allDependenciesAvailable) {
            informUserViewpointActivationFailed(viewpoint, viewpointDependencies);
        } else {
            Map<String, Viewpoint> viewpointToActivateMap = viewpointDependencies.entrySet().stream().filter(entry -> {
                return viewpointWillNotBeActivatedInSession(session, allSelectedViewpoint, entry.getValue());
            }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
            boolean confirmActivation = viewpointToActivateMap.isEmpty()
                    || (allDependenciesAvailable && userConfirmsDependenciesActivationStatusChange(viewpoint, viewpointToActivateMap.values(), true));
            if (confirmActivation) {
                for (Viewpoint viewpointToActivate : viewpointToActivateMap.values()) {
                    selectViewpoint(viewpointToActivate, session, createNewRepresentations, monitor);
                }
                if (allDependenciesAvailable) {
                    super.selectViewpoint(viewpoint, session, createNewRepresentations, monitor);
                }
            }
        }
    }

    /**
     * Return true if the given viewpoint is currently not activated in the session and does not belongs to the
     * viewpoints that will be.
     * 
     * @param session
     *            the session used for testing the activation status of viewpoints.
     * @param allSelectedViewpoint
     *            all viewpoints that will be activated in the session.
     * @param vp
     *            the viewpoint to test.
     * @return true if the given viewpoint is currently not activated in the session and does not belongs to the
     *         viewpoints that will be.
     */
    private boolean viewpointWillNotBeActivatedInSession(Session session, Set<Viewpoint> allSelectedViewpoint, Viewpoint vp) {
        boolean alreadyActivated = false;
        for (Viewpoint desactivatedViewpoint : allSelectedViewpoint) {
            if (EqualityHelper.areEquals(vp, desactivatedViewpoint)) {
                alreadyActivated = true;
            }
        }
        boolean isActivatedInSession = false;
        for (Viewpoint activatedViewpoint : session.getSelectedViewpoints(false)) {
            if (EqualityHelper.areEquals(vp, activatedViewpoint)) {
                isActivatedInSession = true;
            }
        }
        alreadyActivated = alreadyActivated || isActivatedInSession;
        return !alreadyActivated;
    }

    /**
     * Inform the user that viewpoint activation has failed because some dependencies could not be found.
     * 
     * @param viewpoint
     *            the viewpoint that cannot be activated.
     * @param missingDependenciesMap
     *            all dependencies that can be missing.
     */
    private void informUserViewpointActivationFailed(Viewpoint viewpoint, Map<String, Viewpoint> missingDependenciesMap) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                String missingDependenciesList = missingDependenciesMap.entrySet().stream().filter(entry -> entry.getValue() == null).map(entry -> entry.getKey()).collect(Collectors.joining(", ")); //$NON-NLS-1$
                String message = MessageFormat.format(Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_missingDependency_message, viewpoint.getName(),
                        missingDependenciesList);
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_missingDependency_title, message);
            }
        });
    }

    /**
     * Asks the user to confirm the activation or deactivation of dependencies of the given viewpoint from the session
     * and regarding the "activate" boolean value.
     * 
     * @param viewpoint
     *            the viewpoint that have dependencies to activate or deactivate.
     * @param dependencies
     *            the dependencies of the given viewpoint.
     * @param activate
     *            true, if the dependencies should be activated in the session. False otherwise.
     * @return true if the users accepts additional activation/deactivation. False if this viewpoint activation must be
     *         cancelled.
     */
    private boolean userConfirmsDependenciesActivationStatusChange(Viewpoint viewpoint, Collection<Viewpoint> dependencies, boolean activate) {
        String viewpointList = dependencies.stream().map(vp -> vp.getName()).collect(Collectors.joining(new StringBuilder(", "))); //$NON-NLS-1$
        String activationStatusLabel = activate ? Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_confirmDependencyActivation_activateLabel
                : Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_confirmDependencyActivation_deactivateLabel;
        final String message = MessageFormat.format(Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_confirmDependencyActivation_message, viewpoint.getName(),
                activationStatusLabel, viewpointList);
        final AtomicBoolean confirmation = new AtomicBoolean(false);
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                confirmation.set(MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        Messages.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling_confirmDependencyActivation_title, message));
            }
        });
        return confirmation.get();
    }
}
