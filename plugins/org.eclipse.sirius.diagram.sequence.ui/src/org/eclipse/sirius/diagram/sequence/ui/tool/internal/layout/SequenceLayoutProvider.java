/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.tools.api.command.DoNothingCommand;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.util.EditPartTools;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * The layout provider for sequence diagrams.
 * 
 * @author ymortier, mporhel
 */
public class SequenceLayoutProvider extends AbstractLayoutProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public Command layoutEditParts(@SuppressWarnings("rawtypes") List selectedObjects, IAdaptable layoutHint) {
        SequenceDiagramEditPart sdep = getParentSequenceDiagramEditPart(selectedObjects);
        boolean isArrangeAll = isArrangeAll(sdep, selectedObjects);
        if (sdep != null && isArrangeAll) {
            return createArrangeAllCommand(sdep);
        } else {
            return DoNothingCommand.INSTANCE;
        }
    }

    private boolean isArrangeAll(SequenceDiagramEditPart sdep, List selectedObjects) {
        boolean result = false;
        if (sdep != null && selectedObjects != null) {
            result = sdep.getChildren().size() == selectedObjects.size() && sdep.getChildren().containsAll(selectedObjects);
        }
        return result;
    }

    private Command createArrangeAllCommand(SequenceDiagramEditPart sdep) {
        TransactionalEditingDomain transactionalEditingDomain = sdep.getEditingDomain();
        SequenceDiagram sequenceDiagram = sdep.getSequenceDiagram();
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sdep.resolveSemanticElement();
        Collection<AbstractModelChangeOperation<Boolean>> operations = new ArrayList<AbstractModelChangeOperation<Boolean>>();

        operations.add(new RefreshGraphicalOrderingOperation(sequenceDiagram));
        operations.add(new RefreshSemanticOrderingsOperation(sequenceDDiagram));
        operations.add(new SynchronizeGraphicalOrderingOperation(sdep.getDiagramView(), true));

        ICommand cmd = CommandFactory.createICommand(transactionalEditingDomain, operations);
        cmd.setLabel(Messages.SequenceLayoutProvider_arrangeAllCommand);

        return new ICommandProxy(cmd);
    }

    private SequenceDiagramEditPart getParentSequenceDiagramEditPart(List<?> selectedObjects) {
        if (!selectedObjects.isEmpty()) {
            return EditPartTools.getParentOfType((EditPart) selectedObjects.iterator().next(), SequenceDiagramEditPart.class);
        } else {
            return null;
        }
    }
}
