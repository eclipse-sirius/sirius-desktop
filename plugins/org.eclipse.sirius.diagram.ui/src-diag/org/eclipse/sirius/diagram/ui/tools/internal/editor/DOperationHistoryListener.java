/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.WrappingCommandIgnoringAffectedFiles;

/**
 * .
 * 
 * @author mchauvin
 */
public class DOperationHistoryListener implements IOperationHistoryListener {

    private final DDiagramEditorImpl editor;

    /**
     * .
     * 
     * @param editor
     *            .
     */
    public DOperationHistoryListener(final DDiagramEditorImpl editor) {
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
     */
    public void historyNotification(final OperationHistoryEvent event) {
        if (event.getEventType() == OperationHistoryEvent.DONE && editor.getDiagramEditPart() != null) {
            if (eventShouldTriggerArrange(event)) {
                SiriusCanonicalLayoutHandler.launchArrangeCommand(editor.getDiagramEditPart());
            }
        }
    }

    /**
     * Allow to avoid to launch an arrange command for some specific events.
     * 
     * @param event
     *            Event to deal with
     * @return true if this event should trigger an arrange command, false otherwise.
     */
    protected boolean eventShouldTriggerArrange(OperationHistoryEvent event) {
        boolean result = true;
        if (event.getOperation() instanceof WrappingCommandIgnoringAffectedFiles) {
            ICommand originalCommand = ((WrappingCommandIgnoringAffectedFiles) event.getOperation()).getOriginalCommand();
            if (Messages.InitializeHiddenElementsCommand_label.equals(originalCommand.getLabel())) {
                // InitializeHiddenElementsCommand is an operation done during AbstractDDiagramEditPart activation. The
                // layout is not necessary in this case because it will be done later by
                // SiriusCanonicalLayoutHandler.launchArrangeCommandOnOpening. So it avoids to have two successive
                // arrange and it allows to have the expected behavior for ELK layout with
                // launchArrangeCommandOnOpening()
                // instead of just launchArrangeCommand().
                result = false;
            }
        }
        return result;
    }
}
