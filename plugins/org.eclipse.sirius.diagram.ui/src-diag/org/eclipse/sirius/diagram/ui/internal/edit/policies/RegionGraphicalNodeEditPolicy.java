/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;

/**
 * Specific graphical node policy for Regions: if an edge creation tool
 * 
 * @author mporhel
 */
@SuppressWarnings("restriction")
public class RegionGraphicalNodeEditPolicy extends SiriusGraphicalNodeEditPolicy {

    @Override
    public EditPart getTargetEditPart(Request request) {
        EditPart targetEp = super.getTargetEditPart(request);
        org.eclipse.gef.GraphicalEditPart host = (org.eclipse.gef.GraphicalEditPart) this.getHost();
        if (targetEp == host && host.getModel() instanceof View) {
            View view = (View) host.getModel();
            if (view.getElement() instanceof DDiagramElementContainer && new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) view.getElement()).isRegion()) {
                if (REQ_CONNECTION_START.equals(request.getType()) || REQ_CONNECTION_END.equals(request.getType())) {
                    // Delegate this request to the RegionContainer if there is
                    // no valid edge creation tool for this request.
                    Command command = getCommand(request);
                    if (command == null || !command.canExecute()) {
                        targetEp = null;
                    }
                } else {
                    // Delegate all reconnect requests to the RegionContainer
                    // until ReconnectionEdgeDescription handles extra
                    // source/target mappings.
                    targetEp = null;
                }
            }
        }
        return targetEp;
    }
}
