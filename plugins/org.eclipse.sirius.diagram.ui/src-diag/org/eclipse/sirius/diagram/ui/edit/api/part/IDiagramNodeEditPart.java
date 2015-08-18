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

/**
 * The edit part for all <code>DiagramNode</code>s.
 * 
 * @author ymortier
 */
public interface IDiagramNodeEditPart extends IAbstractDiagramNodeEditPart, INodeEditPart, IPrimaryEditPart, NotificationListener, IBorderedShapeEditPart, IAdaptableSelection {

    /**
     * Returns the primary figure.
     * 
     * @return the primary figure.
     */
    IFigure getPrimaryFigure();

    /**
     * Refreshes the figure.
     */
    void refreshFigure();

}
