/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Override GMF LayoutHelper to use the center of the visible part of the container (and not just the center of the part
 * of the container) for reference position.
 * 
 * @author lredor
 */
public class SiriusLayoutHelper extends org.eclipse.gmf.runtime.diagram.ui.figures.LayoutHelper {

    /**
     * The container edit part.
     */
    private IGraphicalEditPart containerEditPart;

    /**
     * Creates a new instance of {@link SiriusLayoutHelper}.
     * 
     * @param editPart
     *            the container edit part.
     */
    public SiriusLayoutHelper(IGraphicalEditPart editPart) {
        this.containerEditPart = editPart;
    }

    /**
     * Override to use the center of the visible part of the container and not just the center of the part of the
     * container.
     * 
     * @param parent
     *            the containing figure (typically <tt>layout()</tt>'s input parameter)
     * @param viewport
     *            The {@link Viewport} of the current diagram
     * @param part
     *            a part from the diagram.
     * @return the nearest free point of the center of the visible part of the container (in logical coordinates, not in
     *         screen coordinates).
     */
    public Point getReferencePosition(IFigure parent, Viewport viewport, IGraphicalEditPart part) {
        Point result;
        // Get the visible area relative to the logical origin (always in 100%)
        Rectangle visibleAreainLogicalRef = viewport.getBounds().getCopy();
        GraphicalHelper.screen2logical(visibleAreainLogicalRef, part);

        // Get the parent bounds relative to the logical origin (always in 100%)
        Rectangle parentBoundsInLogicalRef = parent.getBounds().getCopy();
        Point topLeft = parentBoundsInLogicalRef.getTopLeft();
        FigureUtilities.translateToAbsoluteByIgnoringScrollbar(parent, topLeft);
        parentBoundsInLogicalRef.setLocation(topLeft);

        // Check if the parent is visible
        boolean parentIsVisible = visibleAreainLogicalRef.intersects(parentBoundsInLogicalRef);
        Rectangle parentVisibleArea = visibleAreainLogicalRef.getIntersection(parentBoundsInLogicalRef);
        if (!parentIsVisible) {
            // If the parent is not currently visible use the center of it.
            result = parent.getBounds().getCenter().getCopy();
        } else if (parentVisibleArea.equals(visibleAreainLogicalRef)) {
            // The parent take all the place of the visible viewPort (or is the diagram), so we take the center of this.
            result = parentVisibleArea.getCenter();
        } else {
            // Take the center of the visible edit part and translate again in relative to its container coordinates
            result = parentVisibleArea.getCenter();
            FigureUtilities.translateToRelativeByIgnoringScrollbar(parent, result);
        }
        GraphicalHelper.logical2screen(result, this.containerEditPart);
        EditPartQuery editPartQuery = new EditPartQuery(this.containerEditPart);
        Point snapLocation = editPartQuery.getSnapLocation(new CreateRequest(), result);
        GraphicalHelper.screen2logical(snapLocation, this.containerEditPart);
        return snapLocation;
    }

    /**
     * Overridden to snap the position of the figure to the grid and translate the figure depending
     * {@link SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT} preference.
     */
    @Override
    public Point updateClobberedPosition(IFigure clobbered, IFigure newlyAddedChild) {
        Point clobberedPosition = computeTranslatedPoint(determineReferencePoint(clobbered), clobbered, true);
        // Snap the first created element to the grid
        GraphicalHelper.logical2screen(clobberedPosition, this.containerEditPart);
        EditPartQuery editPartQuery = new EditPartQuery(this.containerEditPart);
        Point snapLocation = editPartQuery.getSnapLocation(new CreateRequest(), clobberedPosition);
        GraphicalHelper.screen2logical(snapLocation, this.containerEditPart);
        return snapLocation;
    }

    /**
     * Determines the figure reference point based on user preferences. This point can then be used to determine the
     * arrangement of adjacent figures.
     * 
     * @param figure
     *            the figure for which the reference point is determined
     * @return the figure reference point
     */
    public Point determineReferencePoint(IFigure figure) {
        int pref = DiagramUIPlugin.getPlugin().getPreferenceStore().getInt(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name());
        Dimension size = figure.getSize().getCopy().union(figure.getPreferredSize());
        Rectangle bounds = figure.getBounds().getCopy().setSize(size);
        Point referencePoint = switch (pref) {
        case SiriusLayoutDataManager.VERTICAL_ARRANGEMENT -> bounds.getBottomLeft().getCopy();
        case SiriusLayoutDataManager.HORIZONTAL_ARRANGEMENT -> bounds.getTopRight().getCopy();
        default -> bounds.getRight().getCopy();
        };
        return referencePoint;
    }

    /**
     * Computes the translated point based on user preferences and a reference point. The translation is determined by
     * user preferences and applied to the specified reference point. Note that only the first created element should
     * snap to grid (if enabled), if many elements are created simultaneously, the first element will snap and following
     * elements will only be shifted by 30px.
     * 
     * @param referencePoint
     *            the reference point to translate
     * @param figure
     *            the figure for which to compute the translated point
     * @param isFirstCreatedElement
     *            indicates whether the first newly created element is being processed, if {@code true}, then the
     *            translated point may be snap to grid; otherwise the point will be shifted by 30px
     * @return the translated point based on user preferences
     */
    public Point computeTranslatedPoint(Point referencePoint, IFigure figure, boolean isFirstCreatedElement) {
        int shiftingValue = 30;
        if (isFirstCreatedElement && new EditPartQuery(this.containerEditPart).isSnapToGridEnabled()) {
            Dimension spacing = (Dimension) containerEditPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_SPACING);
            shiftingValue = spacing.width;
        }

        int pref = DiagramUIPlugin.getPlugin().getPreferenceStore().getInt(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name());
        int translationUnit = figure != null ? MapModeUtil.getMapMode(figure).DPtoLP(shiftingValue) : shiftingValue;
        Point clobberedPosition = switch (pref) {
        case SiriusLayoutDataManager.VERTICAL_ARRANGEMENT -> referencePoint.getCopy().translate(0, translationUnit);
        case SiriusLayoutDataManager.HORIZONTAL_ARRANGEMENT -> referencePoint.getCopy().translate(translationUnit, 0);
        default -> referencePoint.getCopy().translate(translationUnit, 0);
        };
        return clobberedPosition;
    }
}
