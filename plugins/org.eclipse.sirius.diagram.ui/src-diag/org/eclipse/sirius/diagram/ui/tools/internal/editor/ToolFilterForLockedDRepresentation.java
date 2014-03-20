/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * a {@link ToolFilter} to hide creation tool when a {@link DDiagram} is locked.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ToolFilterForLockedDRepresentation implements ToolFilter {

    /**
     * Overridden to filter creation tool when a DDiagram is locked.
     * 
     * {@inheritDoc}
     */
    public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
        return true;
    }

}
