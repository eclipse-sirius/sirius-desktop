/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart.RegionContainerLayoutManager;

/**
 * A specific {@link PropertyChangeListener} associated with {@link ContainerBorderedNodeFigure} and
 * {@link RegionContainerLayoutManager} to resize width of last region when the size of "container" is changed. The
 * container size change can be caused by another container (a region brother in case of multiple level of regions
 * containers).<BR/>
 * This listener, if necessary, changes the constraint of the region figure, updates its bounds and invalidate its
 * parent to trigger a redraw.
 * 
 * @author lredor
 */
public class ParentPropertyChangeListener implements PropertyChangeListener {

    /**
     * The last region to resize when this listener is called, by the container notifying with a
     * {@link ContainerBorderedNodeFigure#CONTAINER_WIDTH_CHANGE}.
     */
    IFigure lastRegionFigureToAdapt;

    /**
     * The layout manager in charge of the above figure to also change the constraint (and not only the figure bounds).
     */
    RegionContainerLayoutManager regionContainerLayoutManager;

    /**
     * Default constructor.
     * 
     * @param regionContainerLayoutManager
     *            The layout manager in charge of this region container.
     */
    public ParentPropertyChangeListener(RegionContainerLayoutManager regionContainerLayoutManager) {
        this.regionContainerLayoutManager = regionContainerLayoutManager;
    }

    /**
     * Set the region to resize when the container notify a {@link ContainerBorderedNodeFigure#CONTAINER_WIDTH_CHANGE}.
     * It can be changed in case of addition of a new region at the end of the regions container.
     * 
     * @param lastRegionFigureToAdapt
     *            the region figure to resize.
     */
    public void setLastRegionFigureToResize(IFigure lastRegionFigureToAdapt) {
        this.lastRegionFigureToAdapt = lastRegionFigureToAdapt;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (lastRegionFigureToAdapt != null && ContainerBorderedNodeFigure.CONTAINER_WIDTH_CHANGE.equals(evt.getPropertyName())) {
            int newParentWidthToConsider = ((Integer) evt.getNewValue()).intValue();
            Rectangle currentBounds = lastRegionFigureToAdapt.getBounds().getCopy();
            int delta = newParentWidthToConsider - (currentBounds.x() + currentBounds.width());
            // If the parent width is larger than their regions, resize the last region.
            if (delta > 0) {
                regionContainerLayoutManager.setConstraint(lastRegionFigureToAdapt, currentBounds.getResized(delta, 0));
                lastRegionFigureToAdapt.setBounds(currentBounds.getResized(delta, 0));
                lastRegionFigureToAdapt.getParent().invalidateTree();
            }
        }
    }
}
