/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.helper.task.label;

import java.text.MessageFormat;
import java.text.ParseException;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.Messages;

/**
 * Task to init interpreter variables. This task is like InitInterpreterFromParsedVariableTask but if the message is not
 * a String and the message mask is {0}, it just initializes the variable $0 with the message (without passed through
 * MessageFormat).
 * 
 * @author lredor
 */
public class InitInterpreterFromParsedVariableTask2 extends AbstractCommandTask {
    private static final String DEFAULT_MESSAGE_MASK = "{0}"; //$NON-NLS-1$

    private IInterpreter interpreter;

    private String messageMask;

    private Object message;

    private boolean unset;

    /**
     * Constructor to set variables according to the mask.
     * 
     * @param inter
     *            the interpreter
     * @param messageFormat
     *            the message mask
     * @param message
     *            the message
     */
    public InitInterpreterFromParsedVariableTask2(final IInterpreter inter, final String messageFormat, final Object message) {
        this.interpreter = inter;
        this.messageMask = messageFormat;
        this.message = message;
    }

    /**
     * Constructor to unset variables according to the mask.
     * 
     * @param inter
     *            the interpreter
     * @param messageFormat
     *            the message mask
     * @param message
     *            the message
     * @param unset 
     *            true to unset the variables
     */
    public InitInterpreterFromParsedVariableTask2(final IInterpreter inter, final String messageFormat, final Object message, final boolean unset) {
        this.interpreter = inter;
        this.messageMask = messageFormat;
        this.message = message;
        this.unset = unset;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        if (message == null || (message instanceof String && ((String) message).length() == 0)) {
            if (unset) {
                unSetVariable(Integer.valueOf(0));
            } else {
                setVariable(Integer.valueOf(0), ""); //$NON-NLS-1$
            }
        } else if (DEFAULT_MESSAGE_MASK.equals(messageMask) && !(message instanceof String)) {
            if (unset) {
                unSetVariable(Integer.valueOf(0));
            } else {
                setVariable(Integer.valueOf(0), message);
            }
        } else {
            final String strMsg = message.toString();
            final MessageFormat parser = new MessageFormat(messageMask);
            try {
                final Object[] values = parser.parse(strMsg);
                for (int i = 0; i < values.length; i++) {
                    if (unset) {
                        unSetVariable(Integer.valueOf(i));
                    } else {
                        setVariable(Integer.valueOf(i), values[i]);
                    }
                }
            } catch (final ParseException e) {
                /*
                 * it seems impossible to arrive here as the "canExecute" prevent that
                 */
            }
        }
    }

    /*
     * Unset two variables, one with the X as key, the other with "argX" as key.
     */
    private void unSetVariable(Integer i) {
        interpreter.unSetVariable(i.toString());
        interpreter.unSetVariable("arg" + i.toString()); //$NON-NLS-1$
    }

    /*
     * Set two variables, one with the X as key, the other with "argX" as key.
     */
    private void setVariable(Integer i, Object value) {
        interpreter.setVariable(i.toString(), value);
        interpreter.setVariable("arg" + i.toString(), value); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#canExecute()
     */
    @Override
    public boolean canExecute() {
        boolean canExecute = true;
        if (message == null || (message instanceof String && ((String) message).length() == 0)) {
            canExecute = true;
        } else if (DEFAULT_MESSAGE_MASK.equals(messageMask) && !(message instanceof String)) {
            canExecute = true;
        } else {
            final String strMsg = message.toString();
            final MessageFormat parser = new MessageFormat(messageMask);
            try {
                parser.parse(strMsg);
            } catch (final ParseException e) {
                canExecute = false;
            }
        }
        return canExecute;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        if (unset) {
            return Messages.InitInterpreterFromParsedVariableTask_unsetLabel;
        } else {
            return Messages.InitInterpreterFromParsedVariableTask_label;
        }
    }
}
