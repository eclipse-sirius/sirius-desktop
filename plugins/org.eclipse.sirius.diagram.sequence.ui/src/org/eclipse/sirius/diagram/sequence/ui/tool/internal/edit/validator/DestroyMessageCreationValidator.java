/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceDiagramQuery;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Validator to check if a destroy message creation request is valid.
 * 
 * @author edugueperoux
 */
public class DestroyMessageCreationValidator extends DefaultMessageCreationValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(CreateConnectionRequest request) {
        boolean valid = super.isValid(request);

        valid = valid && (sequenceElementTarget instanceof Gate || !sequenceElementTarget.getLifeline().get().isExplicitlyDestroyed());
        valid = valid && checkNotEventAtUpperTimeInSameLifeline();

        return valid;
    }

    /**
     * Check that there is not {@link ISequenceEvent} on the target lifeline
     * with range's upperbound upper than the firstClickLocation.y .
     * 
     * @return true if not {@link ISequenceEvent} upper than
     *         firstClickLocation.y
     */
    private boolean checkNotEventAtUpperTimeInSameLifeline() {
        boolean valid = true;

        SequenceDiagram sequenceDiagram = sequenceElementSource.getDiagram();
        SequenceDiagramQuery sequenceDiagramQuery = new SequenceDiagramQuery(sequenceDiagram);

        for (ISequenceEvent sequenceEvent : Iterables.filter(sequenceDiagramQuery.getAllSequenceEventsUpperThan(firstClickLocation.y), Predicates.not(Predicates.instanceOf(Lifeline.class)))) {
            if (isSequenceEventOfLifeline(sequenceEvent, sequenceElementTarget.getLifeline()) || isMessageTargeting(sequenceEvent, sequenceElementTarget.getLifeline())
                    || isDestroyMessageFor(sequenceEvent, sequenceElementTarget.getLifeline().get().getInstanceRole())) {
                valid = false;
                break;
            }
        }
        return valid;
    }

}
