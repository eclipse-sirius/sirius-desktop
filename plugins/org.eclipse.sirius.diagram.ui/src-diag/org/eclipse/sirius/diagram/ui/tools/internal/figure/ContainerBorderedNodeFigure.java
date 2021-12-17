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

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart.RegionContainerLayoutManager;

/**
 * Sirius-specific extension of GMF's BorderedNodeFigure for container to allow "regions notification". With a hierarchy
 * of several "regions containers", the size of an area could have impact on the size of another area "not directly
 * linked". To support this kind a impact, this class has been created to fire a specific property change.<BR/>
 * This class is used for all {@link AbstractDiagramElementContainerEditPart} but the fire notification is only used for
 * regions container.
 * 
 * @author lredor
 *
 */
public class ContainerBorderedNodeFigure extends BorderedNodeFigure {
    /**
     * Property name used with PropertyChangeListener when the size of the container of regions is modified to
     * potentially resize the last region.
     */
    public static final String CONTAINER_WIDTH_CHANGE = "regionsContainerWidthChange"; //$NON-NLS-1$

    public ParentPropertyChangeListener parentPropertyChangeListener;

    /**
     * Default constructor.
     * 
     * @param mainFigure
     *            the figure to use with this figure
     */
    public ContainerBorderedNodeFigure(IFigure mainFigure) {
        super(mainFigure);
    }

    /**
     * Notify the {@link ParentPropertyChangeListener} of the new parent width.
     * 
     * @param newParentWidth
     *            The new parent width.
     */
    public void fireContainerWidthChange(int newParentWidth) {
        // Old value is not important. It is not handled by ParentPropertyChangeListener.
        super.firePropertyChange(CONTAINER_WIDTH_CHANGE, 0, newParentWidth);
    }

    /**
     * Create and add the listener if not already done and set the last region figure to resize.
     * 
     * @param lastRegionFigureToResize
     *            The last region to resize when the container notify a
     *            {@link ContainerBorderedNodeFigure#CONTAINER_WIDTH_CHANGE}.
     * @param regionContainerLayoutManager
     *            The layout manager in charge of this region to also change the constraint (and not only the figure
     *            bounds).
     */
    public void updateParentPropertyChangeListener(IFigure lastRegionFigureToResize, RegionContainerLayoutManager regionContainerLayoutManager) {
        if (parentPropertyChangeListener == null) {
            parentPropertyChangeListener = new ParentPropertyChangeListener(regionContainerLayoutManager);
            this.addPropertyChangeListener(ContainerBorderedNodeFigure.CONTAINER_WIDTH_CHANGE, parentPropertyChangeListener);
        }
        parentPropertyChangeListener.setLastRegionFigureToResize(lastRegionFigureToResize);
    }
}
