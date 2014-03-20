/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.util.IAdaptableSelection;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;

/**
 * Top Level type of Diagram Containers.
 * 
 * @author ymortier
 */
public interface IDiagramContainerEditPart extends IAbstractDiagramNodeEditPart, INodeEditPart, IPrimaryEditPart, NotificationListener, IBorderedShapeEditPart, IAdaptableSelection {

    /**
     * Returns the background image.
     * 
     * @return the background image.
     */
    IFigure getBackgroundFigure();

    /**
     * Creates the background image.
     */
    void createBackgroundFigure();

    /**
     * Returns the figure of the container.
     * 
     * @return the figure of the container.
     */
    ViewNodeContainerFigureDesc getPrimaryShape();

    /**
     * Make the figure consider it's in autosize even if it's not already in the
     * notation model.
     */
    void forceFigureAutosize();

    /**
     * return the dimension the container should have when autosized, this call
     * will only return the good result if forceFigureAutosize() is called
     * before and container figure has been forced to validate itself.
     * 
     * @return the dimension the container should have when autosized
     */
    Rectangle getAutosizedDimensions();
}
