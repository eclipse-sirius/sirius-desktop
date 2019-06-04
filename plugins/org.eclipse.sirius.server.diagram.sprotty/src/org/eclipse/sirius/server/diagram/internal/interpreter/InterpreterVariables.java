/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
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
package org.eclipse.sirius.server.diagram.internal.interpreter;

/**
 * Interfaces to store interpreter variables.
 *
 * @author gcoutable
 */
public interface InterpreterVariables {

	/**
	 * The self variable.
	 */
	static final String SELF_VARIABLE = "self"; //$NON-NLS-1$

	/**
	 * The container variable.
	 */
	static final String CONTAINER_VARIABLE = "container"; //$NON-NLS-1$

	/**
	 * The containerView variable.
	 */
	static final String CONTAINER_VIEW_VARIABLE = "containerView"; //$NON-NLS-1$

}
