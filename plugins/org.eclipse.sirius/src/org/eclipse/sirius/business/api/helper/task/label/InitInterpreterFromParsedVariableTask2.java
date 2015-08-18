/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task.label;

import java.text.MessageFormat;
import java.text.ParseException;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

/**
 * Task to init interpeter variables. This task is like
 * InitInterpreterFromParsedVariableTask but if the message is not a String and
 * the message mask is {0}, it just initializes the variable $0 with the message
 * (without passed through MessageFormat).
 * 
 * @author lredor
 */
public class InitInterpreterFromParsedVariableTask2 extends AbstractCommandTask {
    private static final String DEFAULT_MESSAGE_MASK = "{0}"; //$NON-NLS-1$

    private IInterpreter interpreter;

    private String messageMask;

    private Object message;

    /**
     * Default constructor.
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
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        if (message == null || (message instanceof String && ((String) message).length() == 0)) {
            setVariable(Integer.valueOf(0), ""); //$NON-NLS-1$
        } else if (DEFAULT_MESSAGE_MASK.equals(messageMask) && !(message instanceof String)) {
            setVariable(Integer.valueOf(0), message);
        } else {
            final String strMsg = message.toString();
            final MessageFormat parser = new MessageFormat(messageMask);
            try {
                final Object[] values = parser.parse(strMsg);
                for (int i = 0; i < values.length; i++) {
                    setVariable(Integer.valueOf(i), values[i]);
                }
            } catch (final ParseException e) {
                /*
                 * it seems impossible to arrive here as the "canExecute"
                 * prevent that
                 */
            }
        }
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
    public String getLabel() {
        return "Init Acceleo interpreter with parsed variables";
    }
}
