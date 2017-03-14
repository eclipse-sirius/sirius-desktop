/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.viewpoint;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ViewpointSpec;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.IViewpointStateListener;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.ViewpointStateChangeEvent;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Component providing dynamic activation/deactivation behavior for the graphic component
 * {@link ViewpointsSelectionGraphicalHandler} and session synchronization. I.e any viewpoint check state change from
 * graphical component and triggered by user will be feed through to the session. And in the other way any change of
 * viewpoint activation/deactivation or presence change in the session will be feed through to the graphic component to
 * update viewpoints available and their check state status.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DynamicViewpointsSelectionComponent {

    /**
     * The session from which viewpoint will be available for activation/deactivation.
     */
    private Session session;

    /**
     * Contains all viewpoints available in the current runtime registry.
     */
    private Collection<Viewpoint> availableViewpoints;

    /**
     * Listen to any change regarding viewpoints or semantic models available to the session and refresh the list of
     * viewpoint available in the graphic component with their check status if needed.
     */
    private SessionChangeListener sessionChangelistener;

    /**
     * The graphic component containing checkbox for each viewpoint activable/deactivable and a browser allowing to see
     * a description for each.
     */
    private ViewpointsSelectionGraphicalHandler viewpointsSelectionGraphicalHandler;

    /**
     * Listener updating the session accordingly to changes triggered by user when checking /unchecking viewpoint from
     * graphical component {@link ViewpointsSelectionGraphicalHandler}.
     */
    private DefaultViewpointStateListener viewpointStateListener;

    /**
     * The last viewpoints activation status for the current editor session.
     */
    private Set<Viewpoint> lastSelectedViewpoints;

    /**
     * Listener delegating to the {@link DefaultViewpointStateListener} listener when a check state change occurs (I.e a
     * viewpoint has been checked or unchecked).
     */
    private ICheckStateListener checkStateListener;

    /**
     * Check if selected viewpoint is missing dependencies to be activated. If so it show an error message above the
     * viewpoint description browser.
     */
    private ISelectionChangedListener selectionListener;

    /**
     * This listener allows viewpoint activation/deactivation by a double click.
     */
    private IDoubleClickListener doubleClickListener;

    /**
     * Listener updating the session accordingly to changes triggered by user when checking /unchecking viewpoint from
     * graphical component {@link ViewpointsSelectionGraphicalHandler}.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private class DefaultViewpointStateListener implements IViewpointStateListener {

        @Override
        public void viewpointStateChange(ViewpointStateChangeEvent viewpointStateChangeEvent) {
            final SortedMap<Viewpoint, Boolean> originalViewpointsMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
            for (final Viewpoint viewpoint : availableViewpoints) {
                boolean selected = false;

                for (Viewpoint selectedViewpoint : lastSelectedViewpoints) {
                    if (EqualityHelper.areEquals(selectedViewpoint, viewpoint)) {
                        selected = true;
                        break;
                    }
                }
                originalViewpointsMap.put(viewpoint, Boolean.valueOf(selected));
            }
            SortedMap<Viewpoint, Boolean> newViewpointToSelectionStateMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
            newViewpointToSelectionStateMap.putAll(originalViewpointsMap);
            newViewpointToSelectionStateMap.put(viewpointStateChangeEvent.getViewpoint(), viewpointStateChangeEvent.shouldBeActivated());

            // we also deselect viewpoint that will be missing a dependency if such element exists.
            Set<Viewpoint> viewpointsMissingDependencies = ViewpointHelper.getViewpointsMissingDependencies(
                    newViewpointToSelectionStateMap.keySet().stream().filter(viewpoint -> newViewpointToSelectionStateMap.get(viewpoint)).collect(Collectors.toSet()));
            for (Viewpoint viewpointsMissingDependency : viewpointsMissingDependencies) {
                newViewpointToSelectionStateMap.put(viewpointsMissingDependency, false);
            }

            ViewpointHelper.applyNewViewpointSelection(originalViewpointsMap, newViewpointToSelectionStateMap, session, true);

            // If deselection has been cancelled we update the last selected viewpoints set to reflect this deselection
            // in the cache.
            boolean viewpointSelected = session.getSelectedViewpoints(true).stream().anyMatch(viewpoint -> EqualityHelper.areEquals(viewpointStateChangeEvent.getViewpoint(), viewpoint));
            newViewpointToSelectionStateMap.put(viewpointStateChangeEvent.getViewpoint(), viewpointSelected);
            for (Viewpoint viewpointsMissingDependency : viewpointsMissingDependencies) {
                viewpointSelected = session.getSelectedViewpoints(true).stream().anyMatch(viewpoint -> EqualityHelper.areEquals(viewpointsMissingDependency, viewpoint));
                newViewpointToSelectionStateMap.put(viewpointsMissingDependency, viewpointSelected);
            }

            lastSelectedViewpoints = new HashSet<Viewpoint>(
                    newViewpointToSelectionStateMap.keySet().stream().filter(viewpoint -> newViewpointToSelectionStateMap.get(viewpoint)).collect(Collectors.toSet()));
            viewpointsSelectionGraphicalHandler.getViewer().setCheckedElements(lastSelectedViewpoints.toArray(new Object[0]));
            viewpointsSelectionGraphicalHandler.getViewer().setSelection(new StructuredSelection(viewpointStateChangeEvent.getViewpoint()));

            updateGrayedOutElement();
            viewpointsSelectionGraphicalHandler.getViewer().refresh();
        }

    }

    /**
     * Create an instance of a graphic component allowing to activate/deactivate viewpoint from a session.
     *
     * @param theSession
     *            the session from which viewpoint will be available for activation/deactivation.
     */
    public DynamicViewpointsSelectionComponent(final Session theSession) {
        this.session = theSession;
        viewpointsSelectionGraphicalHandler = new ViewpointsSelectionGraphicalHandler();
        availableViewpoints = viewpointsSelectionGraphicalHandler.getAvailableViewpoints(theSession);
        viewpointStateListener = new DefaultViewpointStateListener();
        lastSelectedViewpoints = new HashSet<Viewpoint>();
    }

    private void updateGrayedOutElement() {
        for (Viewpoint viewpoint : availableViewpoints) {
            String missingDependencyErrorMessage = getMissingDependencyErrorMessage(viewpoint, lastSelectedViewpoints);
            if (missingDependencyErrorMessage != null) {
                viewpointsSelectionGraphicalHandler.getViewer().setGrayed(viewpoint, true);
            } else {
                viewpointsSelectionGraphicalHandler.getViewer().setGrayed(viewpoint, false);
            }
        }

    }

    /**
     * Listen to any change regarding viewpoints or semantic models available to the session and refresh the list of
     * viewpoint available in the graphic component with their check status if needed. Viewpoint can be made unavailable
     * for the session when closing the project containing it and model semantic models can be removed for example.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class SessionChangeListener implements SessionListener {
        @Override
        public void notify(int changeKind) {
            switch (changeKind) {
            case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
            case SessionListener.VSM_UPDATED:
            case SessionListener.SEMANTIC_CHANGE:
                availableViewpoints = viewpointsSelectionGraphicalHandler.getAvailableViewpoints(session);
                setViewpoints(getAvailableViewpoints());
                break;
            default:
                break;
            }

        }
    }

    /**
     * Reinitialize the viewer containing all the checkbox for available viewpoints for the session with their check
     * status.
     * 
     * @param theAvailableViewpoints
     *            all the viewpoints available to the session that need to be made visible for activation/deactivation
     *            to the user in the graphic component.
     */
    private void setViewpoints(Collection<Viewpoint> theAvailableViewpoints) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            lastSelectedViewpoints.clear();
            CheckboxTableViewer viewer = viewpointsSelectionGraphicalHandler.getViewer();
            viewer.setInput(theAvailableViewpoints);
            // case were activated viewpoint are the one corresponding to the session.
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
            for (int i = 0; i < viewer.getTable().getItemCount(); i++) {
                Object object = viewer.getElementAt(i);
                if (object instanceof Viewpoint) {
                    Viewpoint viewerViewpoint = (Viewpoint) object;
                    for (Viewpoint selectedViewpoint : selectedViewpoints) {
                        if (EqualityHelper.areEquals(viewerViewpoint, selectedViewpoint)) {
                            lastSelectedViewpoints.add(viewerViewpoint);
                        }
                    }
                }
            }
            if (!lastSelectedViewpoints.isEmpty()) {
                // Check all the default viewpoints
                viewer.setCheckedElements(lastSelectedViewpoints.toArray(new Object[0]));
                // Set the focus on the first one
                viewer.setSelection(new StructuredSelection(lastSelectedViewpoints.stream().findFirst().get()));
            } else {
                viewpointsSelectionGraphicalHandler.getBrowser().setText(""); //$NON-NLS-1$
            }

            // For all no selected viewpoints we check if they have missing dependencies among activated ones and if
            // they do we gray out
            // those.
            for (Viewpoint viewpoint : availableViewpoints) {
                Set<Viewpoint> viewpoints = new HashSet<Viewpoint>(lastSelectedViewpoints);
                viewpoints.add(viewpoint);
                Set<Viewpoint> viewpointsMissingDependencies = ViewpointHelper.getViewpointsMissingDependencies(viewpoints);
                if (viewpointsMissingDependencies.contains(viewpoint)) {
                    viewer.setGrayed(viewpoint, true);
                } else {
                    viewer.setGrayed(viewpoint, false);
                }
            }
            viewpointsSelectionGraphicalHandler.getRootComposite().layout();
        });

    }

    /**
     * Returns all registered viewpoints that define editors for metamodel of loaded session's semantic models.
     * 
     * @return all registered viewpoints that define editors for metamodel of loaded session's semantic models.
     */
    public Collection<Viewpoint> getAvailableViewpoints() {
        return availableViewpoints;
    }

    /**
     * Create the control allowing to select/deselect viewpoints with checkbox and to see in a browser a description of
     * each viewpoint.
     * 
     * @param parent
     *            the parent composite to attached to this graphic component.
     */
    public void createControl(final Composite parent) {
        viewpointsSelectionGraphicalHandler.createControl(parent, false);
        viewpointsSelectionGraphicalHandler.setBrowserMinWidth(200);
        viewpointsSelectionGraphicalHandler.setHeight(304);
        checkStateListener = event -> {
            String errorMessage = null;

            if (event.getElement() instanceof Viewpoint) {
                Viewpoint viewpointChecked = (Viewpoint) event.getElement();
                viewpointsSelectionGraphicalHandler.getViewer().setSelection(new StructuredSelection(viewpointChecked));
                if (!lastSelectedViewpoints.isEmpty()) {
                    errorMessage = getMissingDependencyErrorMessage(viewpointChecked, lastSelectedViewpoints);
                }
                if (errorMessage == null) {
                    viewpointsSelectionGraphicalHandler.clearBrowserErrorMessageText();
                    if (!viewpointsSelectionGraphicalHandler.getViewer().getGrayed(viewpointChecked)) {
                        if (event.getChecked()) {
                            viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent(viewpointChecked, true));
                        } else {
                            viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent(viewpointChecked, false));
                        }
                    }
                } else {
                    viewpointsSelectionGraphicalHandler.setBrowserErrorMessageText(errorMessage);
                }
            }
        };
        viewpointsSelectionGraphicalHandler.getViewer().addCheckStateListener(checkStateListener);

        selectionListener = event -> {
            if (event.getSelection() instanceof StructuredSelection) {
                StructuredSelection selection = (StructuredSelection) event.getSelection();
                if (selection.getFirstElement() instanceof Viewpoint) {
                    Viewpoint viewpoint = (Viewpoint) selection.getFirstElement();
                    String errorMessage = getMissingDependencyErrorMessage(viewpoint, lastSelectedViewpoints);
                    if (errorMessage != null) {
                        viewpointsSelectionGraphicalHandler.setBrowserErrorMessageText(errorMessage);
                    } else {
                        viewpointsSelectionGraphicalHandler.clearBrowserErrorMessageText();
                    }
                }
            }
        };
        viewpointsSelectionGraphicalHandler.getViewer().addSelectionChangedListener(selectionListener);

        doubleClickListener = (event) -> {
            event.getSelection();
            if (event.getSelection() instanceof StructuredSelection) {
                StructuredSelection selection = (StructuredSelection) event.getSelection();
                if (selection.getFirstElement() instanceof ViewpointSpec) {
                    ViewpointSpec viewpoint = (ViewpointSpec) selection.getFirstElement();
                    if (!viewpointsSelectionGraphicalHandler.getViewer().getGrayed(viewpoint)) {
                        viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent(viewpoint, !viewpointsSelectionGraphicalHandler.getViewer().getChecked(viewpoint)));
                    } else {
                        viewpointsSelectionGraphicalHandler.getViewer().setChecked(viewpoint, !viewpointsSelectionGraphicalHandler.getViewer().getChecked(viewpoint));
                    }

                }
            }
        };
        viewpointsSelectionGraphicalHandler.getViewer().addDoubleClickListener(doubleClickListener);

        setViewpoints(availableViewpoints);
        sessionChangelistener = new SessionChangeListener();
        session.addListener(sessionChangelistener);
    }

    /**
     * Return an error message referencing all missing dependencies for the given viewpoint or null if no missing
     * dependencies exists.
     * 
     * @param viewpoint
     *            the viewpoint from which we check if it has missing dependencies among activated viewpoints.
     * @param selectedViewpoints
     *            the current activated viewpoints.
     * @return an error message referencing all missing dependencies for the given viewpoint or null if no missing
     *         dependencies exists.
     */
    protected String getMissingDependencyErrorMessage(Viewpoint viewpoint, Set<Viewpoint> selectedViewpoints) {
        Set<Viewpoint> viewpoints = Sets.newHashSet(selectedViewpoints);
        viewpoints.add(viewpoint);
        Map<String, Collection<String>> missingDependencies = ViewpointSelection.getMissingDependencies(viewpoints);
        if (missingDependencies != null && missingDependencies.get(viewpoint.getName()) != null) {
            return MessageFormat.format(Messages.DynamicViewpointsSelectionComponent_missingDependencies_requirements, viewpoint.getName(),
                    missingDependencies.get(viewpoint.getName()).stream().collect(Collectors.joining(", "))); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Dispose any listeners added by this instance.
     */
    public void dispose() {
        if (session != null && sessionChangelistener != null) {
            session.removeListener(sessionChangelistener);
        }
        if (viewpointsSelectionGraphicalHandler != null) {
            if (selectionListener != null) {
                viewpointsSelectionGraphicalHandler.getViewer().removeSelectionChangedListener(selectionListener);
            }
            if (checkStateListener != null) {
                viewpointsSelectionGraphicalHandler.getViewer().removeCheckStateListener(checkStateListener);
            }
            if (doubleClickListener != null) {
                viewpointsSelectionGraphicalHandler.getViewer().removeDoubleClickListener(doubleClickListener);
            }
        }
        viewpointStateListener = null;
        selectionListener = null;
        sessionChangelistener = null;
        session = null;
    }
}
