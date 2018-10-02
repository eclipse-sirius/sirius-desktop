/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.bracket;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;

/**
 * A {@link DragEditPartsTrackerEx} for DimensionEdgeEditPart to doesn't
 * considers other target EditPart than the DiagramEditPart.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DiagramTargetingTool extends DragEditPartsTrackerEx {

    /**
     * Default constructor.
     * 
     * @param sourceEditPart
     *            the attached {@link EditPart}
     */
    public DiagramTargetingTool(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Overridden to keep the diagramEditPart as target. {@inheritDoc}
     */
    @Override
    protected void setTargetEditPart(EditPart editpart) {
        final EditPart diagramEditPart = getSourceEditPart().getRoot().getContents();
        super.setTargetEditPart(diagramEditPart);
    }
}
