/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * A specific DescriptionCompartmentEditPart to continue the selection with its
 * parents. This allows to handle the direct edit (slow double click) directly
 * on Note and Text..
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusDescriptionCompartmentEditPart extends DescriptionCompartmentEditPart {

    /**
     * Constructor
     * 
     * @param view
     *            the view controlled by this edit part
     */
    public SiriusDescriptionCompartmentEditPart(View view) {
        super(view);
    }

    /* Send the request to the parent (Note or Text). */
    @Override
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }
}
