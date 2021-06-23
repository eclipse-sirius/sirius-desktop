/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.diagram.sequence.tool.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author mporhel
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SequenceDiagramPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String AbstractFrame_coverageProfilerTaskCategory;

    @TranslatableMessage
    public static String AbstractFrame_coverageProfilerTaskName;

    @TranslatableMessage
    public static String AbstractNodeEvent_nonAbstractNodeEventNode;

    @TranslatableMessage
    public static String AbstractSequenceElement_invalidDiagram;

    @TranslatableMessage
    public static String CombinedFragment_nonCombinedFragmentNode;

    @TranslatableMessage
    public static String EndOfLife_nonEndOfLifeNode;

    @TranslatableMessage
    public static String EndOfLifeMoveOperation_operationName;

    @TranslatableMessage
    public static String Execution_invalidExecutionContext;

    @TranslatableMessage
    public static String Execution_nonExecutionNode;

    @TranslatableMessage
    public static String FixGraphicalOrderingOperation_operationName;

    @TranslatableMessage
    public static String FlagSequenceEventsCommand_commandName;

    @TranslatableMessage
    public static String InstanceRole_nonInstanceRoleNode;

    @TranslatableMessage
    public static String InteractionUse_nonInsteractionUseNode;

    @TranslatableMessage
    public static String InverseRelativeNodePositionOperation_operationName;

    @TranslatableMessage
    public static String ISequenceNodeMoveOperation_operationName;

    @TranslatableMessage
    public static String Lifeline_nonLifelineNode;

    @TranslatableMessage
    public static String LostMessage_nonLostMessageEndNode;

    @TranslatableMessage
    public static String Message_invalidOperand;

    @TranslatableMessage
    public static String Message_nonSequenceMessageEdge;

    @TranslatableMessage
    public static String Message_unsupportedMessageKind;

    @TranslatableMessage
    public static String ObservationPoint_nonObservationPointNode;

    @TranslatableMessage
    public static String Operand_invalidOperandContext;

    @TranslatableMessage
    public static String Operand_nonOperandNode;

    @TranslatableMessage
    public static String Range_emptyRange;

    @TranslatableMessage
    public static String Range_wrongArgument;

    @TranslatableMessage
    public static String RefreshGraphicalOrderingOperation_operationName;

    @TranslatableMessage
    public static String RefreshLayoutCommand_commandName;

    @TranslatableMessage
    public static String RefreshLayoutCommand_profilerTaskCategory;

    @TranslatableMessage
    public static String RefreshLayoutCommand_profilerTaskName;

    @TranslatableMessage
    public static String RefreshSemanticOrderingsOperation_operationName;

    @TranslatableMessage
    public static String ReparentExecutionOperation_operationName;

    @TranslatableMessage
    public static String SequenceDiagram_InternalError;

    @TranslatableMessage
    public static String SequenceDiagram_nonSequenceDiagramDiagram;

    @TranslatableMessage
    public static String SequenceDiagramQuery_invalidTimePoint;

    @TranslatableMessage
    public static String SequenceDiagramQuery_nullSequenceDiagram;

    @TranslatableMessage
    public static String SequenceDiagramRepairParticipant_repairCommandName;

    @TranslatableMessage
    public static String SequenceFlagAndSyncCommand_commandName;

    @TranslatableMessage
    public static String SequenceGenericToolCommandBuilder_canonicalSyncTask;

    @TranslatableMessage
    public static String SequenceGenericToolCommandBuilder_setEndBeforeTask;

    @TranslatableMessage
    public static String SequenceGenericToolCommandBuilder_unsetEndBeforeTask;

    @TranslatableMessage
    public static String SetMessageRangeOperation_operationName;

    @TranslatableMessage
    public static String SetVerticalRangeOperation_operationName;

    @TranslatableMessage
    public static String ShiftDirectSubExecutionsOperation_operationName;

    @TranslatableMessage
    public static String State_invalidStateContext;

    @TranslatableMessage
    public static String State_nonStaveNode;

    @TranslatableMessage
    public static String SynchronizeGraphicalOrderingOperation_operationName;

    @TranslatableMessage
    public static String SynchronizeInstanceRoleSemanticOrderingOperation_invalidInstanceRoleContext;

    @TranslatableMessage
    public static String SynchronizeInstanceRoleSemanticOrderingOperation_operationName;

    @TranslatableMessage
    public static String SynchronizeISequenceEventsSemanticOrderingOperation_invalidISequenceEventContext;

    @TranslatableMessage
    public static String SynchronizeISequenceEventsSemanticOrderingOperation_operationName;

    @TranslatableMessage
    public static String VerticalSpaceExpansion_operationName;

    @TranslatableMessage
    public static String VerticalSpaceReduction_operationName;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
