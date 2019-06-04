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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sprotty.Action;

/**
 * Sent from the server to the client in order to give the available tools.
 *
 * @author gcoutable
 */
public class SetToolsAction implements Action {

	/**
	 * The kind of the action.
	 */
	public static final String KIND = "setTools"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = KIND;

	/**
	 * The list of tools to send.
	 */
	private List<SiriusTool> tools;

	/**
	 * Constructor.
	 *
	 * @param tools
	 *            The list of tools
	 */
	public SetToolsAction(List<SiriusTool> tools) {
		this.tools = new ArrayList<>(tools);
	}

	@Override
	public String getKind() {
		return this.kind;
	}

	/**
	 * Return the tools.
	 *
	 * @return the tools
	 */
	public List<SiriusTool> getTools() {
		return this.tools;
	}

}
