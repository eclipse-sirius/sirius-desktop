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
 * Task to init interreter variables.
 * 
 * @author mchauvin
 */
public class InitInterpreterFromParsedVariableTask extends AbstractCommandTask {

    private IInterpreter interpreter;

    private String messageMask;

    private String message;

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
    public InitInterpreterFromParsedVariableTask(final IInterpreter inter, final String messageFormat, final String message) {
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
        if (message != null && message.length() == 0) {
            setVariable(Integer.valueOf(0), ""); //$NON-NLS-1$
        } else {
            final MessageFormat parser = new MessageFormat(messageMask);
            try {
                final Object[] values = parser.parse(message);
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
        if (message != null && message.length() == 0) {
            canExecute = true;
        } else {
            final MessageFormat parser = new MessageFormat(messageMask);
            try {
                parser.parse(message);
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
