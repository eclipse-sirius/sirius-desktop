/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
