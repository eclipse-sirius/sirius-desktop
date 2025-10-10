/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.optional;

/**
 * This will be used to check whether our optional dependencies are present or not.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class InterpreterDependencyChecks {
    /** Set to <code>true</code> if the <i>org.eclipse.debug.ui</i> dependency is accessible in the classpath. */
    private static final boolean DEBUG_DEPENDENCY;

    static {
        boolean tempDebugDependency;
        try {
            Class.forName("org.eclipse.debug.ui.IDebugView"); //$NON-NLS-1$
            tempDebugDependency = true;
        } catch (ClassNotFoundException e) {
            tempDebugDependency = false;
        }
        DEBUG_DEPENDENCY = tempDebugDependency;
    }

    /** Does not need to be instantiated. */
    private InterpreterDependencyChecks() {
        // Hides default constructor
    }

    /**
     * Checks whether the org.eclipse.debug.ui plugin is accessible in the classpath.
     * 
     * @return <code>true</code> if <i>org.eclipse.debug.ui</i> is accessible from this class, <code>false</code>
     *         otherwise.
     */
    public static boolean isDebugAccessible() {
        return DEBUG_DEPENDENCY;
    }
}
