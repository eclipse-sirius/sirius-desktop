/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.layout;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceElementQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines on a sequence diagram to reflect the
 * semantic order.
 * 
 * @param <S>
 *            the layouted element type.
 * 
 * @param <T>
 *            the layout data type.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceLayout<S, T> {

    /**
     * The diagram to layout.
     */
    protected final SequenceDiagram sequenceDiagram;

    /**
     * A map to register old layout data.
     */
    protected final Map<ISequenceElement, T> oldLayoutData;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram to layout.
     */
    public AbstractSequenceLayout(SequenceDiagram sequenceDiagram) {
        this.sequenceDiagram = sequenceDiagram;

        this.oldLayoutData = new HashMap<>();
    }

    /**
     * Compute and apply a specific layout. Should be use in a {@link org.eclipse.emf.transaction.RecordingCommand}.
     * 
     * @param pack
     *            pack the space between instance roles.
     * 
     * @return true if a layout was applied
     */
    public final boolean layout(boolean pack) {
        // initialisation
        init(pack);

        // vertical range computation
        Map<? extends S, T> finalRanges = computeLayout(pack);

        // Apply computed ranges
        boolean applied = applyComputedLayout(finalRanges, pack);

        dispose();

        return applied;

    }

    /**
     * Init the needed context for layout computation.
     * 
     * @param pack
     *            pack the diagram
     */
    protected abstract void init(boolean pack);

    /**
     * Get old layout data before layout application.
     * 
     * @param ise
     *            the requested sequence element.
     * @return the old layout data.
     */
    protected abstract T getOldLayoutData(S ise);

    /**
     * Computes the absolute vertical (Y) location for all the messages in the sequence diagram.
     * 
     * @param pack
     *            pack the diagram
     * @return a map associating each message edit part to the new absolute vertical location it should have.
     */
    protected abstract Map<? extends S, T> computeLayout(boolean pack);

    /**
     * Apply the computed layout.
     * 
     * @param finalRanges
     *            a map associating each message edit part to the new absolute vertical location it should have.
     * @param pack
     *            pack the diagram
     * 
     * @return true if a layout was applied
     */
    protected abstract boolean applyComputedLayout(Map<? extends S, T> finalRanges, boolean pack);

    /**
     * Dispose the layout context after layout application.
     */
    protected void dispose() {
        oldLayoutData.clear();
    }

    /**
     * Return the non-explicitely created lifelines.
     * 
     * @return the non-explicitely created lifelines.
     */
    protected Iterable<Lifeline> getLifeLinesWithoutCreation() {
        Predicate<Lifeline> isMainLifeline = new Predicate<Lifeline>() {
            public boolean apply(Lifeline input) {
                boolean main = true;
                InstanceRole instanceRole = input.getInstanceRole();
                if (instanceRole != null) {
                    main = !instanceRole.isExplicitlyCreated();
                }
                return main;
            }
        };
        return Iterables.filter(sequenceDiagram.getAllLifelines(), isMainLifeline);
    }

    /**
     * Return the non-explicitely destructed lifelines.
     * 
     * @return the non-explicitely destructed lifelines.
     */
    protected Iterable<Lifeline> getLifeLinesWithoutDestruction() {
        Predicate<Lifeline> isLifelineWithoutDestruction = new Predicate<Lifeline>() {
            public boolean apply(Lifeline input) {
                boolean result = true;
                // filter lifeline with endOfLife
                Option<EndOfLife> endOfLife = input.getEndOfLife();
                if (endOfLife.some()) {
                    result = !endOfLife.get().isExplicitelyDestroyed();
                }
                return result;
            }
        };
        return Iterables.filter(sequenceDiagram.getAllLifelines(), isLifelineWithoutDestruction);
    }

    /**
     * Check if the current sequence node has been created from a tool application. Tool creation flags will be erased
     * after the first layout.
     * 
     * @param node
     *            a sequence node (e.g. LostMessageEnd or Gate).
     * @return true if the end was created by a tool.
     */
    public static boolean createdFromTool(ISequenceNode node) {
        boolean toolCreated = false;
        ISequenceElementQuery query = new ISequenceElementQuery(node);
        if (query.hasAbsoluteBoundsFlag()) {
            Rectangle flag = query.getFlaggedAbsoluteBounds();
            if (flag.x == LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC.x //
                    || node instanceof Gate && flag.width == LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC.x) {
                toolCreated = true;
            }
        }
        return toolCreated;
    }

    /**
     * Check if the current lost end has been created from a tool application. Tool creation flags will be erased after
     * the first layout.
     * 
     * @param lostEnd
     *            the current end.
     * @return true if the end was created by a tool.
     */
    public static boolean createdFromExternalChange(LostMessageEnd lostEnd) {
        boolean externalCreation = false;
        Option<Message> message = lostEnd.getMessage();
        ISequenceElementQuery query = new ISequenceElementQuery(lostEnd);
        if (query.hasAbsoluteBoundsFlag() && query.getFlaggedAbsoluteBounds().x == LayoutConstants.EXTERNAL_CHANGE_FLAG.x) {
            externalCreation = true;
        } else if (message.some()) {
            query = new ISequenceElementQuery(message.get());
            externalCreation = query.hasAbsoluteBoundsFlag() && query.getFlaggedAbsoluteBounds().x == LayoutConstants.EXTERNAL_CHANGE_FLAG.x;
        }
        return externalCreation;
    }
}
