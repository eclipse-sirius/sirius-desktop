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
import org.eclipse.gmf.runtime.common.core.util.IAdaptableSelection;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;

/**
 * This interface is for Lists.
 * 
 * @author ymortier
 */
public interface IDiagramListEditPart extends IAbstractDiagramNodeEditPart, INodeEditPart, IPrimaryEditPart, NotificationListener, IBorderedShapeEditPart, IAdaptableSelection {

    /**
     * Returns the figure of the list.
     * 
     * @return the figure of the list.
     */
    ViewNodeContainerFigureDesc getPrimaryShape();

    /**
     * Returns the background figure of the list.
     * 
     * @return the background figure of the list.
     */
    IFigure getBackgroundFigure();

    /**
     * Creates the background figure of the list.
     */
    void createBackgroundFigure();
}
