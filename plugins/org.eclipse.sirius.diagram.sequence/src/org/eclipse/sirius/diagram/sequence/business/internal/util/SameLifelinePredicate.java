/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.Collection;

import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;

/**
 * Predicate used to filter {@link ISequenceEvent} owned by one specified
 * {@link Lifeline}.
 * 
 * @author edugueperoux
 */
public class SameLifelinePredicate implements Predicate<ISequenceEvent> {

    private Lifeline owner;

    /**
     * Default constructor.
     * 
     * @param owner
     *            the {@link Lifeline} owner
     */
    public SameLifelinePredicate(Lifeline owner) {
        assert owner != null;
        this.owner = owner;
    }

    /**
     * Overridden to tells if the specified {@link ISequenceEvent} is owned by
     * the current {@link Lifeline}.
     * 
     * {@inheritDoc}
     */
    public boolean apply(ISequenceEvent input) {
        boolean result = false;
        Option<Lifeline> inputLifeline = input.getLifeline();
        if (inputLifeline.some()) {
            result = inputLifeline.get().equals(owner);
        } else if (input instanceof Message) {
            Message message = (Message) input;
            ISequenceNode sourceElt = message.getSourceElement();
            ISequenceNode targetElt = message.getSourceElement();
            Option<Lifeline> sourceLifeline = sourceElt.getLifeline();
            Option<Lifeline> targetLifeline = targetElt.getLifeline();
            result = sourceLifeline.some() && sourceLifeline.get().equals(owner) || targetLifeline.some() && targetLifeline.get().equals(owner);
        } else if (input instanceof Operand) {
            result = true;
        } else if (input instanceof AbstractFrame) {
            AbstractFrame frame = (AbstractFrame) input;
            Collection<Lifeline> coverage = frame.computeCoveredLifelines();
            result = coverage.contains(owner);
        }
        return result;
    }
}
