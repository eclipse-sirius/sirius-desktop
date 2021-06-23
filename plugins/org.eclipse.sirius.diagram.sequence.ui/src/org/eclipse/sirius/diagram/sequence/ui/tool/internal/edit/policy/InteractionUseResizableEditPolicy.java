/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractInteractionFrameValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.InteractionUseMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * A specific AirResizableEditPolicy to manage interaction use roles move & resize requests.
 * 
 * @author mporhel
 */
public class InteractionUseResizableEditPolicy extends AbstractFrameResizableEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        cancelHorizontalDelta(request);
        Command result = super.getMoveCommand(request);

        InteractionUseEditPart iuep = (InteractionUseEditPart) getHost();
        InteractionUseMoveValidator validator = new InteractionUseMoveValidator((InteractionUse) iuep.getISequenceEvent(), new RequestQuery(request));

        if (validator.isValid()) {
            // TODO Implement MoveInteractionUseCommand
        } else {
            result = UnexecutableCommand.INSTANCE;
        }

        result = postProcessDefaultCommand(iuep, request, result, validator);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command result = super.getResizeCommand(request);

        InteractionUseEditPart iuep = (InteractionUseEditPart) getHost();
        AbstractInteractionFrameValidator validator = AbstractInteractionFrameValidator.getOrCreateResizeValidator(request, (InteractionUse) iuep.getISequenceEvent());

        if (validator.isValid()) {
            // TODO Implement ResizeInteractionUseCommand
        } else {
            result = UnexecutableCommand.INSTANCE;
        }

        result = postProcessDefaultCommand(iuep, request, result, validator);
        return result;
    }

    private Command postProcessDefaultCommand(InteractionUseEditPart self, ChangeBoundsRequest request, Command defaultCommand, AbstractInteractionFrameValidator validator) {
        Command result = defaultCommand;

        if (result != null && result.canExecute()) {
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(self.getEditingDomain(), Messages.InteractionUseResizableEditPolicy_moveCompositeCommand);
            ctc.setLabel(String.valueOf(defaultCommand.getLabel()));
            ctc.compose(new CommandProxy(defaultCommand));

            Range expansionZone = validator.getExpansionZone();
            if (expansionZone != null && !expansionZone.isEmpty()) {
                ISequenceEvent iSequenceEvent = self.getISequenceEvent();
                SequenceDiagram diagram = iSequenceEvent.getDiagram();
                Collection<ISequenceEvent> eventToIgnore = Collections.singletonList(iSequenceEvent);
                ICommand autoExpand = CommandFactory.createICommand(self.getEditingDomain(), new VerticalSpaceExpansionOrReduction(diagram, expansionZone, 0, eventToIgnore));
                ctc.compose(autoExpand);
            }

            SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, self);
            SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(ctc, self);
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, self.getISequenceEvent());
            result = new ICommandProxy(ctc);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceEventEditPart> getChildrenToFeedBack(ChangeBoundsRequest request) {
        return new ArrayList<>();
    }
}
