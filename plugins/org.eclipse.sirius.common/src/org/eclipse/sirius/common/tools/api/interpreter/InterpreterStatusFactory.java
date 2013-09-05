/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.sirius.common.tools.internal.interpreter.InterpreterStatusImpl;

/**
 * Default {@link IInterpreterStatus} factory.
 * 
 * @since 3.2
 * @author alagarde
 */
public final class InterpreterStatusFactory {

    private InterpreterStatusFactory() {

    }

    /**
     * Creates a new {@link IInterpreterStatus} from the given informations.
     * 
     * @param context
     *            the {@link IInterpreterContext} inside which the error
     *            occurred
     * @param severity
     *            the severity of this error (can be
     *            {@link IInterpreterStatus#WARNING} or
     *            {@link IInterpreterStatus#ERROR}).
     * @param message
     *            a message explaining the cause of the error
     * @return a {@link IInterpreterStatus} created from the given informations
     */
    public static IInterpreterStatus createInterpreterStatus(IInterpreterContext context, String severity, String message) {
        return new InterpreterStatusImpl(context.getTargetTypes(), context.getField(), severity, message, 1, 1, 2);
    }

    /**
     * Creates a new {@link IInterpreterStatus} from the given informations.
     * 
     * @param targetTypes
     *            the names of all possible types for the target of the
     *            expression to evaluate
     * @param field
     *            the field containing the incorrect Interpreted Expression
     * @param severity
     *            the severity of this error (can be
     *            {@link IInterpreterStatus#WARNING} or
     *            {@link IInterpreterStatus#ERROR}).
     * @param message
     *            a message explaining the cause of the error
     * @return a {@link IInterpreterStatus} created from the given informations
     */
    public static IInterpreterStatus createInterpreterStatus(Collection<String> targetTypes, EStructuralFeature field, String severity, String message) {
        return new InterpreterStatusImpl(targetTypes, field, severity, message, 0, 0, 0);
    }

    /**
     * Creates a new {@link IInterpreterStatus} from the given informations.
     * 
     * @param targetTypes
     *            the names of all possible types for the target of the
     *            expression to evaluate
     * @param field
     *            the field containing the incorrect Interpreted Expression
     * @param severity
     *            the severity of this error (can be
     *            {@link IInterpreterStatus#WARNING} or
     *            {@link IInterpreterStatus#ERROR}).
     * @param message
     *            a message explaining the cause of the error
     * @param line
     *            the line of the error.
     * @param posBegin
     *            the begin position of the error.
     * @param posEnd
     *            the end position of the error
     * @return a {@link IInterpreterStatus} created from the given informations
     */
    public static IInterpreterStatus createInterpreterStatus(Collection<String> targetTypes, EStructuralFeature field, String severity, String message, int line, int posBegin, int posEnd) {
        return new InterpreterStatusImpl(targetTypes, field, severity, message, line, posBegin, posEnd);
    }
}
