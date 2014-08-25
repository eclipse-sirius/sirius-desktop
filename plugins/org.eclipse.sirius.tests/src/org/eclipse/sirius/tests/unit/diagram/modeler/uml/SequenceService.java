/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.modeler.uml;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

public class SequenceService {

    public int compareEnd(EObject any, MessageOccurrenceSpecification occurrenceSpecification1, MessageOccurrenceSpecification occurrenceSpecification2) {
        Interaction inter = occurrenceSpecification1.getEnclosingInteraction();
        return inter.getFragments().indexOf(occurrenceSpecification1) - inter.getFragments().indexOf(occurrenceSpecification2);
    }

    public int compareMessage(Message m1, Message m2) {
        Interaction inter = m1.getInteraction();
        return inter.getMessages().indexOf(m1) - inter.getMessages().indexOf(m2);
    }

    public EObject moveEndAfter(MessageOccurrenceSpecification toMove, MessageOccurrenceSpecification before) {
        Interaction interaction = toMove.getEnclosingInteraction();
        Message message = toMove.getMessage();
        InteractionFragment receive = (InteractionFragment) message.getReceiveEvent();

        interaction.getFragments().remove(toMove);
        interaction.getFragments().remove(receive);
        interaction.getMessages().remove(message);

        interaction.getFragments().add(interaction.getFragments().indexOf(before) + 1, toMove);
        interaction.getFragments().add(interaction.getFragments().indexOf(before) + 2, receive);

        interaction.getMessages().add(interaction.getMessages().indexOf(before.getMessage()) + 1, message);

        return toMove;
    }

    public EObject moveEndOnBeginning(MessageOccurrenceSpecification toMove) {
        Interaction inter = toMove.getEnclosingInteraction();
        inter.getFragments().move(0, toMove);
        inter.getFragments().move(1, (InteractionFragment) toMove.getMessage().getReceiveEvent());
        inter.getMessages().move(0, toMove.getMessage());
        return toMove;
    }
}
