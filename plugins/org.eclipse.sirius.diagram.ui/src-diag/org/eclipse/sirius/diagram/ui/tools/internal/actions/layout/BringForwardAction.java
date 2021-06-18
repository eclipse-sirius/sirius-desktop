/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;

/**
 * Bring forward one or several {@link Edge} on a {@link Diagram}.
 * 
 * @author lredor
 * 
 */
public class BringForwardAction extends AbstractEdgesZOrderAction {

    /**
     * Constructor.
     */
    public BringForwardAction() {
        super();
    }

    @Override
    protected Command getCommandToExecute(IDiagramCommandFactory commandFactory, List<IDiagramEdgeEditPart> selectedEdges) {
        // Search the previous crossing edge
        int newIndex = computeNextCrossingEdgeIndex(selectedEdges, true);
        return commandFactory.buildBringForwardCommand(partsToEdge(selectedEdges), newIndex);
    }
}
