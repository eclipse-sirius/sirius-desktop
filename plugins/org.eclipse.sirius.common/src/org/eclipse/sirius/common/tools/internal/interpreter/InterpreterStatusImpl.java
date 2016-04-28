/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;

/**
 * A concrete {@link IInterpreterStatus}.
 * 
 * @author alagarde
 */
public class InterpreterStatusImpl implements IInterpreterStatus {
    /**
     * The message.
     */
    private String message;

    /**
     * The line of the problem in the text.
     */
    private int line;

    /**
     * The beginning index of the problem in the text.
     */
    private int posBegin;

    /**
     * The ending index of the problem in the text.
     */
    private int posEnd;

    private VariableType targets;

    private EStructuralFeature field;

    private String severity;

    /**
     * Default constructor.
     * 
     * @param targets
     *            the names of all possible types for the target of the
     *            evaluated expression
     * @param field
     *            the feature containing the evaluated expression
     * @param severity
     *            the severity of this error (can be
     *            {@link IInterpreterStatus#INFO} or
     *            {@link IInterpreterStatus#WARNING} or
     *            {@link IInterpreterStatus#ERROR})
     * @param message
     *            the message explaining the cause of the error
     * @param line
     *            the line of the error.
     * @param posBegin
     *            the begin position of the error.
     * @param posEnd
     *            the end position of the error
     */
    public InterpreterStatusImpl(VariableType targets, EStructuralFeature field, String severity, String message, int line, int posBegin, int posEnd) {
        this.targets = targets;
        this.field = field;
        this.severity = severity;
        this.message = message;
        this.line = line;
        this.posBegin = posBegin;
        this.posEnd = posEnd;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets the line of the problem in the file.
     * 
     * @return the line of the problem in the file
     */
    @Override
    public int getLine() {
        return line;
    }

    /**
     * Gets the beginning index of the problem in the file.
     * 
     * @return the beginning index of the problem in the file
     */
    @Override
    public int getPosBegin() {
        return posBegin;
    }

    /**
     * Gets the ending index of the problem in the file.
     * 
     * @return the ending index of the problem in the file
     */
    @Override
    public int getPosEnd() {
        return posEnd;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getMessage();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus#getTargetTypes()
     */
    @Override
    public VariableType getTargetTypes() {
        return targets;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus#getField()
     */
    @Override
    public EStructuralFeature getField() {
        return field;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus#getSeverity()
     */
    @Override
    public String getSeverity() {
        return this.severity;
    }
}
