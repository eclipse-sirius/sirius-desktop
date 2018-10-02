/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.Request;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * Returns a command to reveal all diagram elements. (set the visible property
 * to <code>true</code>).
 * 
 * @author ymortier
 */
public class RevealElementsEditPolicy extends AbstractRevealElementEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getCurrentType() {
        return RequestConstants.REQ_REVEAL_ALLS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command buildCommand(final Request request, final DDiagram diagram, final IDiagramCommandFactory emfCommandFactory) {
        return emfCommandFactory.buildRevealElementsCommand(diagram);
    }
}
