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

import java.util.Map;

/**
 * The variable manager is used to handle the state of the variables.
 *
 * @author sbegaudeau
 */
public interface IVariableManager {
	/**
	 * Puts the new value of the variable.
	 *
	 * @param name
	 *            The name of the variable
	 * @param value
	 *            The value of the variable
	 * @return The previous value of the variable or <code>null</code> otherwise
	 */
	Object put(String name, Object value);

	/**
	 * Returns the variables.
	 *
	 * @return The variables
	 */
	Map<String, Object> getVariables();

	/**
	 * Creates a child {@link IVariableManager}.
	 *
	 * @return A child {@link IVariableManager}
	 */
	IVariableManager createChild();

	/**
	 * Clear the variable manager and its children.
	 */
	void clear();

}
