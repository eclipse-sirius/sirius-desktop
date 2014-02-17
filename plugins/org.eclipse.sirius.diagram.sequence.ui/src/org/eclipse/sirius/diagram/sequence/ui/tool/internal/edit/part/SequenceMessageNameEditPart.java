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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.InverseRelativeNodePositionOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SequenceMessageLabelLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * The edit part for sequence message labels.
 * 
 * @author mporhel, smonnier
 */
public class SequenceMessageNameEditPart extends DEdgeNameEditPart {
    /**
     * The visual ID.
     */
    public static final int VISUAL_ID = DEdgeNameEditPart.VISUAL_ID;

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public SequenceMessageNameEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        if (resolveSemanticElement() instanceof DEdge) {
            super.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(Request request) {
        Command result = super.getCommand(request);
        if (result != null && result.canExecute() && new RequestQuery(request).isMove() && messageIsRightToLeft()) {
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(getEditingDomain(), result.getLabel());
            ctc.compose(new CommandProxy(result));
            ICommand fixPositionCommand = CommandFactory.createICommand(getEditingDomain(), new InverseRelativeNodePositionOperation((Node) getNotationView()));
            ctc.compose(fixPositionCommand);
            result = new ICommandProxy(ctc);
        }
        return result;
    }

    private boolean messageIsRightToLeft() {
        IFigure figure = getFigure();
        ViewEdgeFigure parentFigure = null;
        if (figure != null && figure.getParent() instanceof ViewEdgeFigure) {
            parentFigure = (ViewEdgeFigure) figure.getParent();
        }
        return SequenceMessageLabelLocator.isRightToLeft(parentFigure);
    }
}
