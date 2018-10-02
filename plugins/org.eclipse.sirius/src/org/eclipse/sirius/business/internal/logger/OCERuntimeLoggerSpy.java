/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.logger;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * Intercept {@link EvaluationException} logged by the
 * {@link org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter} in various context when its cause is an
 * {@link OperationCanceledException} and re-thrown this cause to avoid not doing rollback causing model corruption.
 * Interceptions are done in the following cases:
 * <UL>
 * <LI>Intercept {@link EvaluationException} corresponding to an OperationCanceledException with the specific "-RT-" key
 * word in the message. This is a temporary solution by waiting https://bugs.eclipse.org/bugs/show_bug.cgi?id=495036 to
 * be solved.</LI>
 * </UL>
 * TODO: This class should be useless as soon as https://bugs.eclipse.org/bugs/show_bug.cgi?id=495036 will be solved.
 */
public class OCERuntimeLoggerSpy implements RuntimeLogger {

    /**
     * A specific key in OperationCanceledException message to detect
     * OperationCanceledException to rethrow.
     */
    public static final String RE_THROW_STATUS_MESSAGE_KEY = "-RT-"; //$NON-NLS-1$

    @Override
    public void error(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
        if (exception instanceof EvaluationException) {
            Throwable cause = exception.getCause();
            throwOperationCanceledExceptionIfNeeded(cause);
            if (cause instanceof InvocationTargetException) {
                throwOperationCanceledExceptionIfNeeded(((InvocationTargetException) cause).getTargetException());
            } else if (cause != null && cause.getClass().getSimpleName().equals("AcceleoQueryEvaluationException")) { //$NON-NLS-1$
                throwOperationCanceledExceptionIfNeeded(cause.getCause());
            }
        }
    }


    private void throwOperationCanceledExceptionIfNeeded(Throwable cause) {
        if (cause instanceof OperationCanceledException && cause.getMessage() != null && cause.getMessage().contains(RE_THROW_STATUS_MESSAGE_KEY)) {
            throw (OperationCanceledException) cause;
        }
    }

    @Override
    public void error(EObject odesignObject, EStructuralFeature feature, String message) {
    }

    @Override
    public void warning(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
    }

    @Override
    public void warning(EObject odesignObject, EStructuralFeature feature, String message) {
    }

    @Override
    public void info(EObject odesignObject, EStructuralFeature feature, String message) {
    }

    @Override
    public void info(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
    }

    @Override
    public void clearAll() {
    }

    @Override
    public void clear(EObject eObject) {
    }
}
