/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.interpreter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Useful operations for interpreters.
 * 
 * @author ymortier
 */
public final class InterpreterUtil {

    /**
     * Avoid instantiation.
     */
    private InterpreterUtil() {
        // empty
    }

    /**
     * Returns the interpreter.
     * 
     * @param eObject
     *            an {@link EObject}.
     * @return the interpreter.
     */
    public static IInterpreter getInterpreter(final EObject eObject) {
        return SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObject);
    }

}
