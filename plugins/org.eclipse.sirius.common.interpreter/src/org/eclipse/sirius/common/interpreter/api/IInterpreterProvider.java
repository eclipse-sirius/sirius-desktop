/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.interpreter.api;

/**
 * This class is used to create a new instance of an interpreter and determine if it can handle a given expression.
 *
 * @author sbegaudeau
 */
public interface IInterpreterProvider {
	/**
	 * Indicates if the {@link IInterpreter} that can be created by this provider can handle the given expression.
	 *
	 * @param expression
	 *            The expression
	 * @return <code>true</code> if the {@link IInterpreter} that can be created can handle the given expression,
	 *         <code>false</code> otherwise
	 */
	boolean canHandle(String expression);

	/**
	 * Creates a new instance of the interpreter.
	 * 
	 * @return A new instance of the interpreter
	 */
	IInterpreter createInterpreter();
}
