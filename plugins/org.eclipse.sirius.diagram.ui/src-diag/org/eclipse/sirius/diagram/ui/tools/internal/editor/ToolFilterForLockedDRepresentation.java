/*******************************************************************************
 * Copyright (c) 2011, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
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
    @Override
    public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
        return true;
    }

}
