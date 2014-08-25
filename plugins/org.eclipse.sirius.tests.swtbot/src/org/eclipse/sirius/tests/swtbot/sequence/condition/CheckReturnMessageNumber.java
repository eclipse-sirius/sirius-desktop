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
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import java.util.List;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A condition to check the correct numbering of return messages on a sequence
 * diagram.
 */
public class CheckReturnMessageNumber extends DefaultCondition {

    private int expectedReturnMessageNumber;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param expectedReturnMessageNumber
     *            the number of return messages.
     * @param editor
     *            the sequence diagram editor
     */
    public CheckReturnMessageNumber(int expectedReturnMessageNumber, SWTBotSiriusDiagramEditor editor) {
        this.expectedReturnMessageNumber = expectedReturnMessageNumber;
        this.editor = editor;
    }

    /**
     * Create a new condition to check for the next numbered message.
     * 
     * @param editor
     *            the sequence diagram editor.
     * @return the condition.
     */
    public static CheckReturnMessageNumber createIncrementChecker(SWTBotSiriusDiagramEditor editor) {
        int returnMessageNumber = CheckReturnMessageNumber.getReturnMessageNumber(editor);
        return new CheckReturnMessageNumber(returnMessageNumber + 1, editor);
    }

    public boolean test() throws Exception {
        return expectedReturnMessageNumber == CheckReturnMessageNumber.getReturnMessageNumber(editor);
    }

    private static int getReturnMessageNumber(SWTBotSiriusDiagramEditor editor) {
        List<SWTBotGefConnectionEditPart> connections = editor.getConnectionsEditPart();
        Function<SWTBotGefConnectionEditPart, ConnectionEditPart> getEditPart = new Function<SWTBotGefConnectionEditPart, ConnectionEditPart>() {
            public ConnectionEditPart apply(SWTBotGefConnectionEditPart from) {
                return from.part();
            }
        };

        Predicate<ConnectionEditPart> isReturnMessage = new Predicate<ConnectionEditPart>() {
            public boolean apply(ConnectionEditPart input) {
                if (input instanceof SequenceMessageEditPart) {
                    SequenceMessageEditPart smep = (SequenceMessageEditPart) input;

                    Option<Message> message = ISequenceElementAccessor.getMessage(smep.getNotationView());
                    if (Message.viewpointElementPredicate().apply(smep.resolveDiagramElement()) && message.some()) {
                        return Message.Kind.REPLY.equals(message.get().getKind());
                    }
                }
                return false;
            }
        };
        return Iterables.size(Iterables.filter(Iterables.transform(connections, getEditPart), isReturnMessage));
    }

    public String getFailureMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
