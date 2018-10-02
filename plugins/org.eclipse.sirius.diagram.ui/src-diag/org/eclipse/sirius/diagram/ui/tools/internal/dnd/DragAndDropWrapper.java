/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
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
