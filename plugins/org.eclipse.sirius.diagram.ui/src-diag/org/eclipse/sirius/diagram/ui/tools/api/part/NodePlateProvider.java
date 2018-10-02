/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.part;

import org.eclipse.draw2d.IFigure;

/**
 * This interface is useful to get the node plate of an EditPart.
 * 
 * @author cbrun
 * 
 */
public interface NodePlateProvider {
    /**
     * Get the node plate of the edit part.
     * 
     * @return the node plate of the {@link org.eclipse.gef.EditPart}.
     */
    IFigure getNodePlate();
}
