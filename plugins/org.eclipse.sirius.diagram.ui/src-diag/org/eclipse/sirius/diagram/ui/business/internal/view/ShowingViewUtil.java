/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FigureQuery;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabelWithAttachment;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * This utility class contains methods to handle {@link View} and to take in consideration the activation status of the
 * showing mode.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class ShowingViewUtil {

    private ShowingViewUtil() {
    }

    /**
     * Returns the {@link View} of the given model. The views to be returned must be visible ({@link View#isVisible()}
     * to true ) or the showing mode must be activated.
     * 
     * @param model
     *            the model from which we want to retrieve views.
     * @return the {@link View} of the given model if it is a view. The views to be returned must be visible
     *         ({@link View#isVisible() to true} ) or the showing mode must be activated.
     */
    public static List<View> getModelChildren(Object model) {
        if (model instanceof View) {
            List<View> modelChildren = null;
            ViewQuery viewQuery = new ViewQuery((View) model);
            if (viewQuery.isInShowingMode()) {
                modelChildren = new ArrayList<EObject>(((View) model).getChildren()).stream().filter(View.class::isInstance).map(View.class::cast).collect(Collectors.toList());
            } else {
                modelChildren = new ArrayList<EObject>(((View) model).getVisibleChildren()).stream().filter(View.class::isInstance).map(View.class::cast).collect(Collectors.toList());
                DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
            }
            return modelChildren;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Returns all the {@link Edge}'s whose source is the given view if edges are visible ({@link View#isVisible()} to
     * true ) or the showing mode is activated.
     * 
     * @param view
     *            the view to use
     * @return List all the {@link Edge}'s whose source is the given view if edges are either visible
     *         ({@link View#isVisible()} to true ) or the showing mode is activated.
     */
    public static List<View> getSourceConnectionsConnectingVisibleViews(View view) {
        if (!view.eIsSet(NotationPackage.Literals.VIEW__SOURCE_EDGES))
            return Collections.EMPTY_LIST;
        List<View> sourceConnections = new ArrayList<View>();
        ViewQuery viewQuery = new ViewQuery(view);
        boolean isIsInShowingMode = viewQuery.isInShowingMode();
        Iterator<View> iter = view.getSourceEdges().iterator();
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            View target = edge.getTarget();
            if (isIsInShowingMode || (edge.isVisible() && isVisible(target))) {
                sourceConnections.add(edge);
            }
        }
        if (!isIsInShowingMode) {
            DiagramElementEditPartOperation.removeInvisibleElements(sourceConnections);
        }
        return sourceConnections;
    }

    /**
     * Set given visibility to the given part regarding the current part selection status and auto connectionvisibility.
     * 
     * @param editPart
     *            the part target of the visibility change.
     * @param visibility
     *            the visibility to set.
     * @param noSelectionStatus
     *            the Integer representing the no selection status for an edit part.
     * @param autoConnectionsVisibility
     *            true if automatic updates of source/target connections visibility should be triggered by the change of
     *            this editpart's visibility. False otherwise.
     */
    public static void setVisibility(AbstractGraphicalEditPart editPart, boolean visibility, int noSelectionStatus, boolean autoConnectionsVisibility) {
        if (!visibility && editPart.getSelected() != noSelectionStatus)
            editPart.getViewer().deselect(editPart);
        View view = (View) editPart.getModel();
        ViewQuery viewQuery = new ViewQuery(view);
        IFigure figure = editPart.getFigure();
        if (figure.isVisible() == visibility && !viewQuery.isInShowingMode()) {
            return;
        }

        // if we are going to hide the node then connections coming to the
        // node or outside it should be hidden as well
        if (autoConnectionsVisibility) {
            ShowingViewUtil.setConnectionsVisibility(editPart, view, noSelectionStatus, visibility);
        }
        if (viewQuery.isInShowingMode()) {
            figure.setVisible(true);
        } else {
            figure.setVisible(visibility);
        }
        figure.revalidate();
        figure.repaint();
    }

    /**
     * Returns true if the given {@link View} is visible as well as all its parents. False otherwise.
     * 
     * @param view
     *            the view to test.
     * @return true if the given {@link View} is visible as well as all its parents. False otherwise.
     */
    private static boolean isVisible(View view) {
        boolean result = false;
        if (view != null && view.isVisible()) {
            EObject parent = view.eContainer();
            if (parent instanceof View) {
                result = isVisible((View) parent);
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns all {@link Edge}'s whose target is the given view if edges are visible ({@link View#isVisible()} to true
     * ) or the showing mode is activated..
     * 
     * @param view
     *            the view to use.
     * @return List all {@link Edge}'s whose target is the given view if edges are visible ({@link View#isVisible()} to
     *         true ) or the showing mode is activated..
     */
    public static List<View> getTargetConnectionsConnectingVisibleViews(View view) {
        if (!view.eIsSet(NotationPackage.Literals.VIEW__TARGET_EDGES))
            return Collections.EMPTY_LIST;
        List<View> targteConnections = new ArrayList<View>();
        Iterator<View> iter = view.getTargetEdges().iterator();
        ViewQuery viewQuery = new ViewQuery(view);
        boolean isIsInShowingMode = viewQuery.isInShowingMode();
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            View source = edge.getSource();
            if (isIsInShowingMode || (edge.isVisible() && isVisible(source))) {
                targteConnections.add(edge);
            }
            if (!isIsInShowingMode) {
                DiagramElementEditPartOperation.removeInvisibleElements(targteConnections);
            }
        }
        return targteConnections;
    }

    /**
     * Sets the visibility of the edit part's source and target connections to the given one by taking in consideration
     * the selection status.
     * 
     * @param editPart
     *            the part from which connections visibility will be updated.
     * @param view
     *            the view used to retrieve the {@link DDiagram} with showing mode activation status information.
     * @param selectionStatus
     *            the selection status of the part.
     * @param isVisible
     *            the new visibility to set.
     */
    public static void setConnectionsVisibility(AbstractGraphicalEditPart editPart, View view, int selectionStatus, boolean isVisible) {
        List<Object> srcConnections = editPart.getSourceConnections();
        ViewQuery viewQuery = new ViewQuery(view);
        boolean isInShowingMode = viewQuery.isInShowingMode();
        updateVisibility(editPart, selectionStatus, isVisible, srcConnections, isInShowingMode);
        List<Object> targetConnections = editPart.getTargetConnections();
        updateVisibility(editPart, selectionStatus, isVisible, targetConnections, isInShowingMode);
    }

    /**
     * Update visibility of edit part's connections regarding showing mode activation status and diagram selection
     * status.
     * 
     * @param editPart
     *            the part from which connections visibility will be updated.
     * @param selectionStatus
     *            the current selection status.
     * @param isVisible
     *            the new visibility to set.
     * @param connections
     *            the edit part's connections.
     * @param isInShowingMode
     *            true if the diagram is in showing mode. False otherwise.
     */
    private static void updateVisibility(AbstractGraphicalEditPart editPart, int selectionStatus, boolean isVisible, List<Object> connections, boolean isInShowingMode) {
        Iterator<Object> connexionsIte = connections.iterator();
        while (connexionsIte.hasNext()) {
            ConnectionEditPart connection = (ConnectionEditPart) connexionsIte.next();
            if (connection.getFigure().isVisible() != isVisible || isInShowingMode) {
                if (!isVisible && connection.getSelected() != selectionStatus)
                    connection.getViewer().deselect(editPart);
                if (isInShowingMode) {
                    connection.getFigure().setVisible(true);
                } else {
                    connection.getFigure().setVisible(isVisible);
                }
                connection.getFigure().revalidate();
            }
        }
    }

    /**
     * Initialize the graphics to draw the invisible element with transparency. After this method is called, the
     * graphics must be restored. Example of use: <code>
     * public void paint(Graphics graphics) {
     *     initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) getModel());
     *     try {
     *         super.paint(graphics);
     *         graphics.restoreState();
     *     } finally {
     *         graphics.popState();
     *     }
     *}
     * </code>
     * 
     * @param figure
     *            Figure to be drawn
     * @param graphics
     *            Graphics to use to draw the figure
     * @param correspondingView
     *            The GMF view associated with this figure
     */
    public static void initGraphicsForVisibleAndInvisibleElements(IFigure figure, Graphics graphics, View correspondingView) {
        try {
            graphics.pushState();
            ViewQuery viewQuery = new ViewQuery(correspondingView);
            if (figure instanceof SiriusWrapLabel) {
                // labels do not have any view associated so we have to check the filters on the node view containing
                // the
                // label.
                EObject element = correspondingView.getElement();
                if (element instanceof DDiagramElement) {
                    EList<GraphicalFilter> graphicalFilters = ((DDiagramElement) element).getGraphicalFilters();
                    boolean isLabelFiltered = false;
                    if (correspondingView instanceof Edge && figure instanceof SiriusWrapLabelWithAttachment) {
                        SiriusWrapLabelWithAttachment siriusWrapLabelWithAttachment = (SiriusWrapLabelWithAttachment) figure;
                        int edgeLabelViewConstantToVisualID = new FigureQuery(figure).edgeLabelViewConstantToVisualID(siriusWrapLabelWithAttachment.getLocationField());
                        isLabelFiltered = new DDiagramElementQuery((DDiagramElement) element).isLabelHidden(edgeLabelViewConstantToVisualID);
                    } else {
                        isLabelFiltered = graphicalFilters.stream().anyMatch(HideLabelFilter.class::isInstance) || graphicalFilters.stream().anyMatch(HideFilter.class::isInstance)
                            || (correspondingView instanceof Edge && !((DDiagramElement) correspondingView.getElement()).isVisible());
                    }
                    if (isLabelFiltered && viewQuery.isInShowingMode()) {
                        graphics.setAlpha(50);
                    }
                }
            } else if (figure.isVisible() != correspondingView.isVisible() && viewQuery.isInShowingMode()) {
                graphics.setAlpha(50);
            }
        } catch (IllegalStateException e) {
            // Silent catch: this can happen when trying to access model on distant resource when the session is already
            // closed
        }
    }
}
