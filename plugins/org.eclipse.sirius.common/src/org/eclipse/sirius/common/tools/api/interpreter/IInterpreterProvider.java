/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.interpreter;

/**
 * Specifies the contract to provides {@link IInterpreter}.
 * 
 * @author ymortier
 */
public interface IInterpreterProvider {

    /**
     * Returns <code>true</code> if this provider can provide an interpreter for
     * the specified expression.
     * 
     * @param expression
     *            the expression.
     * @return <code>true</code> if this provider can provide an interpreter for
     *         the specified expression.
     */
    boolean provides(String expression);

    /**
     * Creates a new {@link IInterpreter}.
     * 
     * @return the created interpreter.
     */
    IInterpreter createInterpreter();

}
