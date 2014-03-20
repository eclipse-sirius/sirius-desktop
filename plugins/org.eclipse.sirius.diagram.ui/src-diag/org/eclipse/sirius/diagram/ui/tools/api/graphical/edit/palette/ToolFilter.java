/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * A filter allows one to hide tools in the palette.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface ToolFilter {

    /**
     * Check if a tool should be hidden in the palette for the given diagram.
     * 
     * @param diagram
     *            the diagram
     * @param tool
     *            the tool
     * @return <code>true</code> if the tool should be hidden,
     *         <code>false</code> otherwise
     */
    boolean filter(DDiagram diagram, AbstractToolDescription tool);

}
