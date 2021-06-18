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

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.tools.internal.Messages;

/**
 * Command that is able to send several elements backward. For edges, an index can be given (for example to send
 * backward until the next crossing edge).
 * 
 * @author lredor
 */
public class SendElementsBackward extends AbstractZOrderRecordingCommand {
    /**
     * -1 for a default behavior, ie each element is moved backward of one element, or the real index where the elements
     * must be moved. This index is used only for edges, ie nodes are always the default behavior.
     */
    int newIndex = -1;

    /**
     * Create a new {@link SendElementsBackward} to move edges at a specified index.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToSendBackward
     *            the elements (nodes or edges) to send backward. These elements must be of the same type and have the
     *            same parent.
     * @param index
     *            the new index (ignored if the elements to move are nodes)
     */
    public SendElementsBackward(final TransactionalEditingDomain domain, final List<? extends View> elementsToSendBackward, int index) {
        super(domain, elementsToSendBackward);
        // Reverse the order according to z-order
        this.elementsToMove = reverseSortSelection(this.elementsToMove);
        if (index < -1 && index > (getChildrenSize() - 1)) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.SendElementsBackward_wrongIndex, getChildrenSize() - 1));
        }
        this.newIndex = index;
    }

    private int getChildrenSize() {
        if (elementsToMove.get(0) instanceof Edge && containerView instanceof Diagram) {
            return ((Diagram) containerView).getEdges().size();
        } else {
            return containerView.getChildren().size();
        }
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
                if (newIndex == -1) {
                    // Default behavior (not used by Sirius actions, AbstractEdgesZOrderAction, because there is not
                    // systematically a visual change).
                    EList<? extends Object> children = ((Diagram) containerView).getPersistedEdges();
                    int oldIndex = children.indexOf(view);
                    if (oldIndex > 0) {
                        ((Diagram) containerView).removeEdge((Edge) view);
                        ((Diagram) containerView).insertEdgeAt((Edge) view, oldIndex - 1);
                    }
                } else {
                    ((Diagram) containerView).removeEdge((Edge) view);
                    ((Diagram) containerView).insertEdgeAt((Edge) view, newIndex);
                }
            } else {
                // Same code than
                // org.eclipse.gmf.runtime.diagram.core.internal.commands.SendBackwardCommand.doExecuteWithResult(IProgressMonitor,
                // IAdaptable)
                int oldIndex = containerView.getChildren().indexOf(view);
                if (oldIndex > 0) {
                    ViewUtil.repositionChildAt(containerView, view, oldIndex - 1);
                }
            }
        }
    }

    @Override
    protected String getCommandLabelForOneElement() {
        return Messages.SendElementsBackward_oneElementLabel;
    }

    @Override
    protected String getCommandLabelForSeveralElements() {
        return Messages.SendElementsBackward_severalElementsLabel;
    }
}
