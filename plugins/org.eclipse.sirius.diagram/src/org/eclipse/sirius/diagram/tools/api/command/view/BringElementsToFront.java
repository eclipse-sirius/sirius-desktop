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
 * Command that is able to bring elements to front.
 * 
 * @author lredor
 */
public class BringElementsToFront extends AbstractZOrderRecordingCommand {
    /**
     * Create a new {@link BringElementsToFront}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToBringToFront
     *            the elements (nodes or edges) to bring to front. These elements must be of the same type and have the
     *            same parent.
     */
    public BringElementsToFront(final TransactionalEditingDomain domain, final List<? extends View> elementsToBringToFront) {
        super(domain, elementsToBringToFront);
        // Sort selection according to z-order
        this.elementsToMove = sortSelection(this.elementsToMove);
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
                ((Diagram) containerView).insertEdge((Edge) view);
            } else {
                // Same code than
                // org.eclipse.gmf.runtime.diagram.core.internal.commands.BringToFrontCommand.doExecuteWithResult(IProgressMonitor,
                // IAdaptable)
                ViewUtil.repositionChildAt(containerView, view, containerView.getChildren().size() - 1);
            }
        }
    }

    @Override
    protected String getCommandLabelForOneElement() {
        return Messages.BringElementsToFront_oneElementLabel;
    }

    @Override
    protected String getCommandLabelForSeveralElements() {
        return Messages.BringElementsToFront_severalElementsLabel;
    }
}
