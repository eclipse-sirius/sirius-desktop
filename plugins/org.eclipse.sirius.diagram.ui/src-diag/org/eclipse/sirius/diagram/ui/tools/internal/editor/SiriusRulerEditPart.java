/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.internal.ui.rulers.RulerDragTracker;
import org.eclipse.gef.internal.ui.rulers.RulerEditPart;

/**
 * A specific {@link RulerEditPart} to use a specific {@link RulerDragTracker}, to insert blank space instead of create
 * guide, if Ctrl key is pressed.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusRulerEditPart extends RulerEditPart {

    /**
     * Default constructor.
     * 
     * @param model
     *            The primary model object that this EditPart represents
     */
    public SiriusRulerEditPart(Object model) {
        super(model);
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        DragTracker result = SiriusBlankSpacesDragTracker.getDragTracker(this, diagramViewer, request, true, false);
        if (result == null) {
            return super.getDragTracker(request);
        } else {
            return result;
        }
    }
}
