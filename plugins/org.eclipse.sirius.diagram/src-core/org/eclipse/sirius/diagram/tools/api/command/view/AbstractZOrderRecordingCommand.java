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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.Messages;

/**
 * A specific abstract {@link RecordingCommand} for all "z-order commands". It contains common behaviors for all z-order
 * commands.
 * 
 * @author lredor
 */
public abstract class AbstractZOrderRecordingCommand extends RecordingCommand {
    /**
     * A comparator, for a list of edges, that compares the edge index in edges list of the diagram.
     * 
     * @author lredor
     */
    private class EdgeComparator implements Comparator<View> {

        public int compare(View view0, View view1) {

            Diagram parent = (Diagram) view0.eContainer();
            int view0Index = parent.getEdges().indexOf(view0);
            int view1Index = parent.getEdges().indexOf(view1);

            return view0Index - view1Index;
        }
    }

    /**
     * A comparator, for a list of nodes, that compares the node index in children list of their container.
     * 
     * @author lredor
     */
    private class NodeComparator implements Comparator<View> {

        public int compare(View view0, View view1) {

            Diagram parent = (Diagram) view0.eContainer();
            int view0Index = parent.getChildren().indexOf(view0);
            int view1Index = parent.getChildren().indexOf(view1);

            return view0Index - view1Index;
        }
    }

    /** The of GMF elements to move. */
    protected List<? extends View> elementsToMove;


    /** The container view of elements to move. */
    protected View containerView;

    /**
     * Create a new {@link AbstractZOrderRecordingCommand}.
     * 
     * @param domain
     *            the editing domain.
     * @param elementsToMove
     *            the elements (nodes or edges) to move. These elements must be of the same type and have the same
     *            parent.
     */
    public AbstractZOrderRecordingCommand(final TransactionalEditingDomain domain, final List<? extends View> elementsToMove) {
        super(domain);
        if (elementsToMove != null && elementsToMove.size() > 1) {
            setLabel(getCommandLabelForSeveralElements());
        } else {
            setLabel(getCommandLabelForOneElement());
        }
        if (elementsToMove.isEmpty()) {
            throw new IllegalArgumentException(Messages.ZOrderRecordingCommand_emptyList);
        }
        this.containerView = (View) elementsToMove.get(0).eContainer();
        if (!elementsToMove.stream().allMatch(view -> containerView.equals(view.eContainer()))) {
            throw new IllegalArgumentException(Messages.ZOrderRecordingCommand_notSameParent);
        }
        if (!(elementsToMove.stream().allMatch(view -> view instanceof Node) || elementsToMove.stream().allMatch(view -> view instanceof Edge))) {
            throw new IllegalArgumentException(Messages.ZOrderRecordingCommand_notSameType);
        }
        // Sort elements according to z-order
        this.elementsToMove = sortSelection(elementsToMove);
    }

    /**
     * Return the label of the command when the elementsToMove contains only one element.
     * 
     * @return the label of the command when the elementsToMove contains only one element.
     */
    protected abstract String getCommandLabelForOneElement();

    /**
     * Return the label of the command when the elementsToMove contains several elements.
     * 
     * @return the label of the command when the elementsToMove contains several elements.
     */
    protected abstract String getCommandLabelForSeveralElements();

    /**
     * Sort the list according to the z-order index of the views.
     * 
     * @param viewsToSort
     *            List of views to sort
     * @return a sorted list
     */
    protected List<? extends View> sortSelection(List<? extends View> viewsToSort) {

        // IF the list to be sorted is less than 2...
        if (viewsToSort.size() < 2) {
            // Return the original list
            return viewsToSort;
        }

        List<View> toReturn = new ArrayList<View>(viewsToSort.size());
        toReturn.addAll(viewsToSort);

        if (viewsToSort.get(0) instanceof Edge) {
            Collections.sort(toReturn, new EdgeComparator());
        } else if (viewsToSort.get(0) instanceof Edge) {
            Collections.sort(toReturn, new NodeComparator());
        }

        return toReturn;
    }

    /**
     * Sort the list according to the inverse z-order index of the views.
     * 
     * @param viewsToSort
     *            List of views to sort
     * @return a sorted list
     */

    protected List<? extends View> reverseSortSelection(List<? extends View> viewsToSort) {
        List<? extends View> toReturn = sortSelection(viewsToSort);

        Collections.reverse(toReturn);
        return toReturn;
    }
}
