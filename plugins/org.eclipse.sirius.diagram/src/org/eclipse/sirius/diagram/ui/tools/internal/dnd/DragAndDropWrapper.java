/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dnd;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * This class is a wrapper of an Object useful to manage drag and drop.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class DragAndDropWrapper extends AbstractGraphicalEditPart {

    private static final IFigure FIGURE = new Figure();

    private Object wrappedObject;

    /**
     * Constructor.
     * 
     * @param eObj
     *            the wrapped object
     */
    public DragAndDropWrapper(final Object eObj) {
        wrappedObject = eObj;
    }

    /**
     * Get the wrapped Object.
     * 
     * @return the wrapped object
     */
    public Object getObject() {
        return this.wrappedObject;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        return FIGURE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        // do nothing
    }
}
