/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.gef.Request;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * Edit policy to reveal only user selected elements.
 * 
 * @author dlecan
 */
public class RevealSelectedElementsEditPolicy extends AbstractRevealElementEditPolicy {

    /**
     * Key where to find selected elements to reveal.
     */
    public static final String SELECTED_ELEMENTS_KEY = "selectedElements"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Command buildCommand(final Request request, final DDiagram diagram, final IDiagramCommandFactory emfCommandFactory) {
        final Set<DDiagramElement> elements = (Set<DDiagramElement>) request.getExtendedData().get(SELECTED_ELEMENTS_KEY);

        Command result = UnexecutableCommand.INSTANCE;
        if (elements != null) {
            result = emfCommandFactory.buildRevealElementsCommand(elements);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getCurrentType() {
        return RequestConstants.REQ_REVEAL_SELECTED;
    }

}
