/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler;

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
            SiriusCanonicalLayoutHandler.launchArrangeCommand(editor.getDiagramEditPart());
        }
    }

}
