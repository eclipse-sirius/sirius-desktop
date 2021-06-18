/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command.view;

import java.util.List;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.tools.internal.Messages;

/**
 * Command that is able to send elements to back.
 * 
 * @author lredor
 */
public class SendElementsToBack extends AbstractZOrderRecordingCommand {
    /**
     * Create a new {@link SendElementsToBack}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToSendToBack
     *            the elements (nodes or edges) to send to back. These elements must be of the same type and have the
     *            same parent.
     */
    public SendElementsToBack(final TransactionalEditingDomain domain, final List<? extends View> elementsToSendToBack) {
        super(domain, elementsToSendToBack);
        // Reverse the order according to z-order
        this.elementsToMove = reverseSortSelection(this.elementsToMove);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        for (View view : elementsToMove) {
            // Reposition the child
            if (view instanceof Edge && containerView instanceof Diagram) {
                ((Diagram) containerView).removeEdge((Edge) view);
                ((Diagram) containerView).insertEdgeAt((Edge) view, 0);
            } else {
                // Same code than
                // org.eclipse.gmf.runtime.diagram.core.internal.commands.SendToBackCommand.doExecuteWithResult(IProgressMonitor,
                // IAdaptable)
                ViewUtil.repositionChildAt(containerView, view, 0);
            }
        }
    }

    @Override
    protected String getCommandLabelForOneElement() {
        return Messages.SendElementsToBack_oneElementLabel;
    }

    @Override
    protected String getCommandLabelForSeveralElements() {
        return Messages.SendElementsToBack_severalElementsLabel;
    }
}
