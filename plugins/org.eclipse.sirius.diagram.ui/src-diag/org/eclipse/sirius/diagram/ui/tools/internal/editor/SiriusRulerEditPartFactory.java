/*******************************************************************************
 * Copyright (c) 2018, 2024 THALES GLOBAL SERVICES and others.
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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.internal.ui.rulers.GuideEditPart;
import org.eclipse.gef.internal.ui.rulers.RulerEditPartFactory;

/**
 * A specific {@link RulerEditPartFactory} to instantiate a specific Sirius {@link GuideEditPart}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusRulerEditPartFactory extends RulerEditPartFactory {

    /**
     * Default constructor.
     * 
     * @param primaryViewer
     *            The associated graphical viewer.
     */
    public SiriusRulerEditPartFactory(GraphicalViewer primaryViewer) {
        super(primaryViewer);
    }

    @Override
    public EditPart createEditPart(EditPart parentEditPart, Object model) {
        // the model can be null when the contents of the root edit part are set
        // to null
        EditPart part = null;
        if (isRuler(model)) {
            part = new SiriusRulerEditPart(model);
        } else if (model != null) {
            part = new GuideEditPart(model);
        }
        return part;
    }
}
