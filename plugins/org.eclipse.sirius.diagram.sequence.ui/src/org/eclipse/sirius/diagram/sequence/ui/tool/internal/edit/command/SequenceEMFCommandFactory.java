/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.FixGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetVerticalRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeISequenceEventsSemanticOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.business.internal.util.DecreasingRange;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.tools.internal.command.UndoRedoCapableEMFCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;

/**
 * A custom EMF Command Factory to specialize the delete behavior for sequence diagram elements.
 * 
 * @author pcdavid
 */
public final class SequenceEMFCommandFactory extends UndoRedoCapableEMFCommandFactory {

    private final SequenceDiagramEditPart sdep;

    /**
     * Constructor.
     * 
     * The factory will be initialized with the TransactionalEditingDomain of the current SequenceDiagramEditPart.
     * 
     * @param sdep
     *            the sequence diagram.
     */
    public SequenceEMFCommandFactory(SequenceDiagramEditPart sdep) {
        super(sdep.getEditingDomain());
        this.sdep = sdep;
    }

    /**
     * Overridden to reconnect the children of an execution about to be deleted.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Command buildDeleteDiagramElement(DDiagramElement element) {
        Command result = super.buildDeleteDiagramElement(element);
        if (AbstractNodeEvent.viewpointElementPredicate().apply(element)) {
            Object part = sdep.getViewer().getEditPartRegistry().get(element);
            if (part instanceof ExecutionEditPart) {
                ExecutionEditPart executionPart = (ExecutionEditPart) part;
                result = getDeleteExecutionCommand(executionPart, result);
            }
        } else if (Operand.viewpointElementPredicate().apply(element)) {
            Object part = sdep.getViewer().getEditPartRegistry().get(element);
            if (part instanceof OperandEditPart) {
                OperandEditPart operandPart = (OperandEditPart) part;
                result = getDeleteOperandCommand(operandPart, result);
            }
        }
        return result;
    }

    @Override
    public Command buildInsertOrRemoveVerticalBlankSpaceCommand(DDiagram diagram, int startY, int spaceToInsertOrRemove) {
        if (spaceToInsertOrRemove < 0) {
            return CommandFactory.createRecordingCommand(sdep.getEditingDomain(),
                    new VerticalSpaceExpansionOrReduction(sdep.getSequenceDiagram(), new DecreasingRange(startY, startY + spaceToInsertOrRemove), 0, Collections.<ISequenceEvent> emptyList()));

        } else {
            return CommandFactory.createRecordingCommand(sdep.getEditingDomain(),
                    new VerticalSpaceExpansionOrReduction(sdep.getSequenceDiagram(), new Range(startY, startY + spaceToInsertOrRemove), 0, Collections.<ISequenceEvent> emptyList()));

        }

    }

    private Command getDeleteExecutionCommand(ExecutionEditPart executionPart, Command basicDelete) {
        TransactionalEditingDomain ted = sdep.getEditingDomain();

        CompoundCommand cc = new CompoundCommand();
        cc.append(ExecutionOperations.getReconnectSubExecutionsToParentCommand(executionPart));
        cc.append(ExecutionOperations.getReconnectEdgesToParentCommand(executionPart));
        cc.append(basicDelete);
        cc.append(CommandFactory.createRecordingCommand(ted, new RefreshSemanticOrderingsOperation((SequenceDDiagram) sdep.resolveSemanticElement())));
        cc.append(CommandFactory.createRecordingCommand(ted, new FixGraphicalOrderingOperation((SequenceDDiagram) sdep.resolveSemanticElement())));
        return cc;
    }

    private Command getDeleteOperandCommand(OperandEditPart operandPart, Command basicDelete) {
        if (operandPart.getViewer().getSelectedEditParts().contains(operandPart.getParentCombinedFragmentEditPart())) {
            // The operand container is also beeing deleted. Therefore we do not
            // need to add delete operation on operand.
            return IdentityCommand.INSTANCE;
        } else {
            return buildDeleteOperandCommand(operandPart, basicDelete);
        }
    }

    private Command buildDeleteOperandCommand(OperandEditPart operandPart, Command basicDelete) {
        TransactionalEditingDomain ted = sdep.getEditingDomain();
        CompoundCommand cc = new CompoundCommand();
        cc.append(basicDelete);

        Operand deletedOperand = (Operand) operandPart.getISequenceEvent();
        if (deletedOperand.getCombinedFragment().getOperands().size() == 1 || operandPart.getViewer().getSelectedEditParts().size() != 1) {
            /*
             * The last remaining operand can not be deleted. It is also not possible to delete 2 operand to avoid NPE
             * in SetVerticalRangeOperation.
             */
            return UnexecutableCommand.INSTANCE;
        }
        Option<Operand> absorbingOperand = getAbsorbingOperand(deletedOperand);
        assert absorbingOperand.some();
        if (absorbingOperand.some()) {
            Range expandedRange = absorbingOperand.get().getVerticalRange().union(deletedOperand.getVerticalRange());
            cc.append(CommandFactory.createRecordingCommand(ted, new SetVerticalRangeOperation(absorbingOperand.get(), expandedRange)));
            cc.append(CommandFactory.createRecordingCommand(ted, new RefreshSemanticOrderingsOperation((SequenceDDiagram) sdep.resolveSemanticElement())));
            cc.append(CommandFactory.createRecordingCommand(ted, new RefreshGraphicalOrderingOperation(sdep.getSequenceDiagram())));
            cc.append(CommandFactory.createRecordingCommand(ted, new SynchronizeISequenceEventsSemanticOrderingOperation(absorbingOperand.get())));
        }

        return cc;
    }

    /**
     * Returns the sibling operand which should absorb the space previously occupied by the operand about to be deleted.
     */
    private Option<Operand> getAbsorbingOperand(Operand deletedOperand) {
        assert deletedOperand != null;
        if (deletedOperand.isFirstOperand()) {
            return deletedOperand.getFollowingOperand();
        } else {
            return deletedOperand.getPreviousOperand();
        }
    }
}
