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
 * The factory used to create the {@link IVariableManager}.
 *
 * @author sbegaudeau
 */
public class VariableManagerFactory {
	/**
	 * Returns a new instance of the {@link IVariableManager}.
	 *
	 * @return A new instance of the {@link IVariableManager}
	 */
	public IVariableManager createVariableManager() {
		return new VariableManager();
	}
}
