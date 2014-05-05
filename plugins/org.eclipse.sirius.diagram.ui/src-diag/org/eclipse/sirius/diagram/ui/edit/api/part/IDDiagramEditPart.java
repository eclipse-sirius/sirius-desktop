/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ext.base.Option;

/**
 * The top level interface of diagram edit part.
 * 
 * @author ymortier
 */
public interface IDDiagramEditPart extends IGraphicalEditPart, ISiriusEditPart {

    /**
     * Returns the diagram that is represented by this edit part.
     * 
     * @return the diagram that is represented by this edit part.
     */
    Option<DDiagram> resolveDDiagram();
}
