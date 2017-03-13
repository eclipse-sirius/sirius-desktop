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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.IViewpointStateListener;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.ViewpointStateChangeEvent;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Maps;

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
     * Contains all viewpoint available in the current runtime registry.
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
            SortedMap<Viewpoint, Boolean> newViewpointSelectedMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
            newViewpointSelectedMap.putAll(originalViewpointsMap);
            newViewpointSelectedMap.put(viewpointStateChangeEvent.getViewpoint(), viewpointStateChangeEvent.shouldBeActivated());
            // We init the listener that will check back the viewpoint if we do a deselection that is canceled by user.
            // The following calls are synchronous.
            ViewpointHelper.applyNewViewpointSelection(originalViewpointsMap, newViewpointSelectedMap, session, true);

            boolean viewpointSelected = session.getSelectedViewpoints(true).stream().anyMatch(viewpoint -> EqualityHelper.areEquals(viewpointStateChangeEvent.getViewpoint(), viewpoint));
            newViewpointSelectedMap.put(viewpointStateChangeEvent.getViewpoint(), viewpointSelected);
            lastSelectedViewpoints = new HashSet<Viewpoint>(newViewpointSelectedMap.keySet().stream().filter(viewpoint -> newViewpointSelectedMap.get(viewpoint)).collect(Collectors.toSet()));
            viewpointsSelectionGraphicalHandler.getViewer().setCheckedElements(lastSelectedViewpoints.toArray(new Object[0]));
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

    /**
     * Reinitialize the viewer containing all the checkbox for available viewpoints for the session with their check
     * status.
     * 
     * @param theAvailableViewpoints
     *            all the viewpoints available to the session that need to be made visible for activation/deactivation
     *            to the user in the graphic component.
     */
    private void setViewpoints(Collection<Viewpoint> theAvailableViewpoints) {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
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
                viewpointsSelectionGraphicalHandler.getRootComposite().layout();
            }
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
        viewpointsSelectionGraphicalHandler.setHeight(404);
        checkStateListener = new ICheckStateListener() {
            @Override
            public void checkStateChanged(final CheckStateChangedEvent event) {
                if (event.getChecked() && event.getElement() instanceof Viewpoint) {
                    viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent((Viewpoint) event.getElement(), true));
                } else {
                    viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent((Viewpoint) event.getElement(), false));
                }
            }
        };
        viewpointsSelectionGraphicalHandler.getViewer().addCheckStateListener(checkStateListener);

        setViewpoints(availableViewpoints);
        sessionChangelistener = new SessionChangeListener();
        session.addListener(sessionChangelistener);
    }

    /**
     * Dispose any listeners added by this instance.
     */
    public void dispose() {
        session.removeListener(sessionChangelistener);
        viewpointsSelectionGraphicalHandler.getViewer().removeCheckStateListener(checkStateListener);
        viewpointStateListener = null;
    }
}
