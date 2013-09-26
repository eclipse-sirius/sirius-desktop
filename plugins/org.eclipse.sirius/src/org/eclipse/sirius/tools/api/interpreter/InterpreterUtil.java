/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.interpreter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

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
