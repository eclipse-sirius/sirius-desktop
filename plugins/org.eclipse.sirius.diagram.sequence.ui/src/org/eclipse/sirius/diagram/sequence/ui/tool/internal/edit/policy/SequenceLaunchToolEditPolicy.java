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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command.SequenceDelegatingCommandFactory;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.CreationUtil;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

/**
 * Edit policy for launching tools. Adding the support of $endBefore variable.
 * 
 * @author mporhel
 */
public class SequenceLaunchToolEditPolicy extends LaunchToolEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    protected CreationUtil getCreationUtil(CreateRequest request, Point location, EditPart editPart, IDiagramCommandFactory baseEmfCommandFactory) {
        IDiagramCommandFactory launchToolCommandFactory = baseEmfCommandFactory;

        Object tool = request.getNewObject();
        if (tool instanceof ToolDescription || tool instanceof PaneBasedSelectionWizardDescription || tool instanceof SelectionWizardDescription) {
            launchToolCommandFactory = getLaunchToolCommandFactory(editPart, request.getLocation().getCopy(), baseEmfCommandFactory);
        }

        return super.getCreationUtil(request, location, editPart, launchToolCommandFactory);
    }

    private IDiagramCommandFactory getLaunchToolCommandFactory(EditPart editPart, Point requestLocation, IDiagramCommandFactory baseEmfCommandFactory) {
        IDiagramCommandFactory result = baseEmfCommandFactory;

        Point location = requestLocation.getCopy();
        if (editPart instanceof IGraphicalEditPart) {
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) editPart);
        }

        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(editPart);
        if (sequenceDiagram != null) {
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(sequenceDiagram.getNotationDiagram());
            SequenceDDiagram diagram = sequenceDiagram.getSequenceDDiagram();
            EventEnd endBefore = SequenceGraphicalHelper.getEndBefore(diagram, location.y);
            result = new SequenceDelegatingCommandFactory(baseEmfCommandFactory, domain, sequenceDiagram, endBefore, location.getCopy());
        }
        return result;
    }
}
