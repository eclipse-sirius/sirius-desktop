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
package org.eclipse.sirius.diagram.sequence.ui;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author mporhel
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SequenceDiagramUIPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String AbstractInstanceRoleValidator_nullInstanceRoleList;

    @TranslatableMessage
    public static String AbstractNodeEventResizeSelectionValidator_nullExecution;

    @TranslatableMessage
    public static String CombinedFragmentResizableEditPolicy_moveCompositeCommand;

    @TranslatableMessage
    public static String CombinedFragmentResizableEditPolicy_resizeCompositeCommand;
    
    @TranslatableMessage
    public static String CombinedFragmentResizableEditPolicy_resizeSubCommand;

    @TranslatableMessage
    public static String EndOfLifeSelectionPolicy_lifelineMoveCommand;

    @TranslatableMessage
    public static String ExecutionOperations_invalidMessageSource;

    @TranslatableMessage
    public static String ExecutionSelectionEditPolicy_moveCompositeCommand;

    @TranslatableMessage
    public static String ExecutionSelectionEditPolicy_resizeCompositeCommand;

    @TranslatableMessage
    public static String InstanceRoleResizableEditPolicy_moveCommand;

    @TranslatableMessage
    public static String InstanceRoleResizableEditPolicy_resizeCommand;

    @TranslatableMessage
    public static String InstanceRoleSiriusGraphicalNodeEditPolicy_createParticipantMessageAndMoveDownLifelineCompositeCommand;

    @TranslatableMessage
    public static String InteractionUseResizableEditPolicy_moveCompositeCommand;

    @TranslatableMessage
    public static String OperandResizableEditPolicy_resizeCompositeCommand;
    
    @TranslatableMessage
    public static String OperandResizableEditPolicy_resizeSubCommand;

    @TranslatableMessage
    public static String ResizeViewOperation_invalidNullSizeDelta;

    @TranslatableMessage
    public static String ResizeViewOperation_invalidNullView;

    @TranslatableMessage
    public static String SequenceContainerCreationPolicy_combinedFragmentCreationCommand;

    @TranslatableMessage
    public static String SequenceContainerCreationPolicy_interactionUseCreationCommand;

    @TranslatableMessage
    public static String SequenceDiagramTypeProvider_endBeforeVariableDescription;

    @TranslatableMessage
    public static String SequenceDiagramTypeProvider_sequenceAdditionalVariablesTooltip;

    @TranslatableMessage
    public static String SequenceEMFCommandFactory_notSupportedZorderCommandsMessage;

    @TranslatableMessage
    public static String SequenceLayoutProvider_arrangeAllCommand;

    @TranslatableMessage
    public static String SequenceMessageEditPolicy_moveCreateMessageCommand;

    @TranslatableMessage
    public static String SequenceMessageEditPolicy_synchronizeOrderingCompositeCommand;

    @TranslatableMessage
    public static String ShifDescendantMessagesOperation_operationName;

    @TranslatableMessage
    public static String ShiftMessagesOperation_operationName;

    @TranslatableMessage
    public static String VisibilityEventHandler_nonSupportedHideRevealInSequenceDiagram;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
