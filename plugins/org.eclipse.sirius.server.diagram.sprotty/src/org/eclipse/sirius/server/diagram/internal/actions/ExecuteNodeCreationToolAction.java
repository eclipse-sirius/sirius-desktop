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
package org.eclipse.sirius.server.diagram.internal.actions;

import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sprotty.Action;

/**
 * Sent from the client to the server in order to execute the sirius {@link NodeCreationDescription} tool.
 *
 * @author gcoutable
 */
public class ExecuteNodeCreationToolAction implements Action {

	/**
	 * The kind of the action
	 */
	public static final String KIND = "executeNodeCreationTool"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = KIND;

	/**
	 * The tool name to execute.
	 */
	private String toolName;

	@Override
	public String getKind() {
		return this.kind;
	}

	/**
	 * Returns the tool name to apply.
	 *
	 * @return The tool name to apply
	 */
	public String getToolName() {
		return toolName;
	}
}
