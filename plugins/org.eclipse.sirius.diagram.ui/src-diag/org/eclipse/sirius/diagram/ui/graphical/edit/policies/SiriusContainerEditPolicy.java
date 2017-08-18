/*******************************************************************************
 * Copyright (c) 2002, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - Adaptations.
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.CenterEdgeLayoutCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DistributeCommand;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A specific class to override the arrangeCommand to handles edge centering, snapping, ...<BR>
 * This class launches the arrange even for one element.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusContainerEditPolicy extends ContainerEditPolicy {

    private final Predicate<Object> isRegionEditPart = new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
            return input instanceof AbstractDiagramElementContainerEditPart && ((AbstractDiagramElementContainerEditPart) input).isRegion();
        }
    };

    @Override
    public Command getCommand(Request request) {
        if (org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_DISTRIBUTE.equals(request.getType())) {
            return getDistributeCommand((DistributeRequest) request);
        }

        return super.getCommand(request);
    }

    // CHECKSTYLE:OFF

    @Override
    protected Command getArrangeCommand(ArrangeRequest request) {
        if ((ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION.equals(request.getType()))) {
            if (Iterables.any(request.getPartsToArrange(), isRegionEditPart)) {
                return UnexecutableCommand.INSTANCE;
            }
        }
        Command commandToReturn = super.getArrangeCommand(request);
        // We add a Command to center edges that need to be at the end of the
        // layout.
        if (commandToReturn != null) {
            EditPart host = getHost();
            if (host instanceof GraphicalEditPart) {
                CenterEdgeLayoutCommand centerEdgeLayoutCommand = new CenterEdgeLayoutCommand((GraphicalEditPart) host);
                commandToReturn = commandToReturn.chain(new ICommandProxy(centerEdgeLayoutCommand));
            }
        }
        return commandToReturn;
    }

    // CHECKSTYLE:ON

    /**
     * Gets a distribute command.
     * 
     * @param request
     *            The distribute request
     * @return command
     */
    protected Command getDistributeCommand(DistributeRequest request) {
        List<IGraphicalEditPart> editparts = request.getEditParts();
        if (!editparts.isEmpty() && getHost() instanceof IGraphicalEditPart) {
            return new ICommandProxy(new DistributeCommand(((IGraphicalEditPart) getHost()).getEditingDomain(), editparts, request.getDistributeType()));
        }
        return null;
    }
}
