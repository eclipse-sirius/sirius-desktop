/*******************************************************************************
 * Copyright (c) 2025 CEA.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * 
 * Specific DBorderItemLocator to handle border item offset and side computation for Gates.
 * 
 * @author SMonnier
 */
public class GateItemLocator extends DBorderItemLocator {
    private final IGraphicalEditPart owner;

    /**
     * Create an {@link ExecutionItemLocator} with the specified parentFigure.
     * 
     * @param owner
     *            the owner edit part.
     * @param parentFigure
     *            the parent figure.
     */
    public GateItemLocator(IGraphicalEditPart owner, IFigure parentFigure) {
        super(parentFigure);
        this.owner = owner;
    }

    @Override
    public void setBorderItemOffset(Dimension borderItemOffset) {
        super.setBorderItemOffset(borderItemOffset);
    }

}
