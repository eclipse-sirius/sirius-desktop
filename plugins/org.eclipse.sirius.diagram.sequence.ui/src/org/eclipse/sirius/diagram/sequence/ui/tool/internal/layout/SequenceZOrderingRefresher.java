/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ObservationPointEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Refreshes the Z-ordering in which interaction uses and combined fragments
 * appear on a diagram.
 * 
 * @author smonnier
 */
public class SequenceZOrderingRefresher implements Runnable {
    private final SequenceDiagramEditPart sequenceDiagramPart;

    /**
     * Creates a command which updates the Z ordering of interaction uses and
     * combined fragments.
     * 
     * @param sequenceDiagramEditPart
     *            the diagram whose interaction uses and combined fragments Z
     *            ordering should be refreshed.
     */
    public SequenceZOrderingRefresher(SequenceDiagramEditPart sequenceDiagramEditPart) {
        this.sequenceDiagramPart = Objects.requireNonNull(sequenceDiagramEditPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        moveInteractionUsesToFront();
        moveCombinedFragmentsToBack();
        moveInteractionContainerToBack();
        moveObservationPointToFront();
    }

    /**
     * Shift the interaction uses before every other children so they appear
     * visually in front of the rest.
     */
    private void moveInteractionUsesToFront() {
        moveParts(InteractionUseEditPart.class, true, new InstanceUseVerticalPositionFunction());
    }

    /**
     * Shift the interaction uses before every other children so they appear
     * visually in front of the rest.
     */
    private void moveObservationPointToFront() {
        moveParts(ObservationPointEditPart.class, true, null);
    }

    /**
     * Shift the combined fragments before every other children so they appear
     * visually behind the rest.
     */
    private void moveCombinedFragmentsToBack() {
        moveParts(CombinedFragmentEditPart.class, false, new CombinedFragmentVerticalPositionFunction());

    }

    /**
     * Shift the container, representing the interaction, if it exists, before every other children so they appear
     * visually behind the rest.
     */
    private void moveInteractionContainerToBack() {
        Optional<DNodeContainerEditPart> optionalInteractionContainer = getInteractionContainer(sequenceDiagramPart.getChildren());
        if (optionalInteractionContainer.isPresent()) {
            sequenceDiagramPart.reorderChild(optionalInteractionContainer.get(), 0);
        }
    }

    /**
     * Return the container representing the interaction if it exists.<BR>
     * 
     * @param children
     *            List of GraphicalEditPart (at the root of a sequence diagram).
     * @return The container representing the interaction if it exists.
     */
    public Optional<DNodeContainerEditPart> getInteractionContainer(List<? extends GraphicalEditPart> children) {
        return (Optional<DNodeContainerEditPart>) children.stream().filter(new Predicate<GraphicalEditPart>() {
            @Override
            public boolean test(GraphicalEditPart editPart) {
                return editPart instanceof InteractionContainerEditPart;
            }
        }).findFirst();
    }

    /**
     * Filter the diagram children parts with type to move. Sort them with the
     * sorter if not null. Reverse the result if move to front.
     */
    private void moveParts(Class<? extends IGraphicalEditPart> typeToMove, boolean moveToFront, Function<IGraphicalEditPart, Integer> sorter) {
        List<? extends IGraphicalEditPart> partsToMove = Lists.newArrayList(Iterables.filter(sequenceDiagramPart.getChildren(), typeToMove));

        if (sorter != null) {
            Ordering<IGraphicalEditPart> onResultOf = Ordering.natural().onResultOf(sorter);
            if (moveToFront) {
                onResultOf = onResultOf.reverse();
            }
            Collections.sort(partsToMove, onResultOf);
        }

        // Bring combined fragments to back/front
        if (!partsToMove.isEmpty()) {
            int index = moveToFront ? Iterables.size(Iterables.filter(sequenceDiagramPart.getChildren(), IGraphicalEditPart.class)) - 1 : 0;
            for (IGraphicalEditPart frame : partsToMove) {
                sequenceDiagramPart.reorderChild(frame, index);
                index = index + (moveToFront ? -1 : 1);
            }
        }
    }

}
