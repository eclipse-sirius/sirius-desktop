/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
