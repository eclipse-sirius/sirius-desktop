/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.ext.gmf.runtime.editpolicies.SiriusSnapFeedbackPolicy;

/**
 * Sirius edit policy which support the {@link ActionIds#EDGE_SNAP_BACK} request
 * type on AbstractDiagramEdgeEditPart.
 * 
 * Returns a composed command with one {@link SetBoundsCommand} by existing
 * labels of the edge with offset to its default location at creation.
 * 
 * @author pguilet
 */
public class SiriusEdgeLabelSnapBackEditPolicy extends SiriusSnapFeedbackPolicy {

    /**
     * Returns a <code>Command</code> which sets the label's offset to its
     * original position.
     * 
     * @param request
     *            the request
     * @return the command
     */
    @Override
    public Command getCommand(Request request) {
        if (ActionIds.EDGE_SNAP_BACK.equals(request.getType())) {
            View view = (View) getHost().getModel();
            if (view.getElement() instanceof DDiagramElement) {
                List<?> childrenPart = getHost().getChildren();
                TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                // Since 4.0 we can have a maximum of three labels.
                List<SetBoundsCommand> list = new ArrayList<SetBoundsCommand>(3);
                for (Object childPart : childrenPart) {
                    if (childPart instanceof AbstractDEdgeNameEditPart && !((AbstractDEdgeNameEditPart) childPart).getEditText().isEmpty()) {
                        View model = (View) ((AbstractDEdgeNameEditPart) childPart).getModel();
                        String hint = model.getType();
                        Point offset = LabelEditPart.getSnapBackPosition(hint);
                        if (offset != null) {
                            SetBoundsCommand moveCommand = new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, new EObjectAdapter(model), offset);
                            list.add(moveCommand);
                        }
                    }
                }
                if (list.size() > 0) {
                    CompositeCommand compositeCommand = new CompositeCommand(Messages.SiriusEdgeSnapBackAction_EdgeSnapBackCommandLabel, list);
                    return new ICommandProxy(compositeCommand);
                }

            }

        }
        return null;
    }

    @Override
    public EditPart getTargetEditPart(Request request) {
        if (understandsRequest(request))
            return getHost();
        return super.getTargetEditPart(request);
    }

    /**
     * Understands RActionIds.EDGE_SNAP_BACK request types.
     * 
     * @param request
     * @return boolean
     */
    @Override
    public boolean understandsRequest(Request request) {
        return ActionIds.EDGE_SNAP_BACK.equals(request.getType());
    }
}
