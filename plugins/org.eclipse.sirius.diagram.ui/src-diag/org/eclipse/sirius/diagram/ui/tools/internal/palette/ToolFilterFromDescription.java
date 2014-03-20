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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

/**
 * Create tool filter from a tool filter description.
 * 
 * @author mchauvin
 */
public class ToolFilterFromDescription implements ToolFilter {

    private ToolFilterDescription filterDescription;

    private AbstractToolDescription toolDescription;

    private IInterpreter interpreter;

    /**
     * Constructs a new instance.
     * 
     * @param interpreter
     *            interpreter
     * @param filterDescription
     *            filter description
     */
    public ToolFilterFromDescription(final IInterpreter interpreter, final ToolFilterDescription filterDescription) {
        this.interpreter = interpreter;
        this.filterDescription = filterDescription;
        this.toolDescription = (AbstractToolDescription) filterDescription.eContainer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.ToolFilter#filter(DDiagram,
     *      AbstractToolDescription)
     */
    public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
        if (tool == toolDescription) {
            try {
                return interpreter.evaluateBoolean(diagram, filterDescription.getPrecondition());
            } catch (final EvaluationException e) {
                // do nothing
            }
        }
        return false;
    }

}
